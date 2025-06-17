package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import dao.StudentDao;
import tool.CommonServlet;

@WebServlet("/StudentRegister.action")
public abstract class StudentRegisterServlet extends CommonServlet {

    @Override
    protected void post(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        boolean hasError = false;
        String entYearStr = request.getParameter("ent_year");
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String classNum = request.getParameter("class_num");
        int entYear = 0;

        if (entYearStr == null || entYearStr.isEmpty()) {
            hasError = true;
            request.setAttribute("entYearError", "入学年度を選択してください。");
        } else {
            try {
                entYear = Integer.parseInt(entYearStr);
            } catch (NumberFormatException e) {
                hasError = true;
                request.setAttribute("entYearError", "入学年度が不正です。");
            }
        }

        if (no == null || no.isEmpty()) {
            hasError = true;
            request.setAttribute("noError", "学生番号を入力してください。");
        } else {
            StudentDao dao = new StudentDao();
            try {
                if (dao.get(no) != null) {
                    hasError = true;
                    request.setAttribute("noDuplicateError", "その学生番号は既に使われています。");
                }
            } catch (Exception e) {
                hasError = true;
                request.setAttribute("noDuplicateError", "データベースエラーが発生しました。");
                e.printStackTrace();
            }
        }

        if (name == null || name.isEmpty()) {
            hasError = true;
            request.setAttribute("nameError", "氏名を入力してください。");
        }

        if (hasError) {
            request.setAttribute("no", no);
            request.setAttribute("name", name);
            request.setAttribute("entYearStr", entYearStr);
            request.setAttribute("classNum", classNum);

            RequestDispatcher rd = request.getRequestDispatcher("/student/student_register.jsp");
            rd.forward(request, response);
            return;
        }

        try {
            Student student = new Student();
            student.setNo(no);
            student.setName(name);
            student.setEntYear(entYear);
            student.setClassNum(classNum);
            student.setIsAttend(true);

            School school = new School();
            school.setCd("oom");
            student.setSchool(school);

            StudentDao dao = new StudentDao();
            dao.save(student);

            // ★★★ ここを修正 ★★★
            // リダイレクト先を "/StudentList" に統一
            response.sendRedirect("StudentList");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "データの保存中にエラーが発生しました。");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}