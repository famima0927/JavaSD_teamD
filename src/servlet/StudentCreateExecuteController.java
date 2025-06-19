package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import dao.StudentDao;
import tool.CommonServlet;

// ★★★ 修正点：URLをフォームの送信先と一致させる ★★★
@WebServlet("/StudentRegister.action")
public class StudentCreateExecuteController extends CommonServlet {

    @Override
    public void post(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        request.setCharacterEncoding("UTF-8");

        boolean hasError = false;
        String entYearStr = request.getParameter("ent_year");
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String classNum = request.getParameter("class_num");
        int entYear = 0;

        // ----- 入力値のチェック -----
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
            if (dao.get(no) != null) {
                hasError = true;
                request.setAttribute("noDuplicateError", "その学生番号は既に使われています。");
            }
        }

        if (name == null || name.isEmpty()) {
            hasError = true;
            request.setAttribute("nameError", "氏名を入力してください。");
        } else {
            StudentDao dao = new StudentDao();
            if (dao.getByName(name) != null) {
                hasError = true;
                request.setAttribute("nameDuplicateError", "その氏名は既に登録されています。");
            }
        }

        // ----- エラーがある場合はフォームに戻す -----
        if (hasError) {
            request.setAttribute("no", no);
            request.setAttribute("name", name);
            request.setAttribute("entYearStr", entYearStr);
            request.setAttribute("classNum", classNum);

            RequestDispatcher rd = request.getRequestDispatcher("/student/STDM002.jsp");
            rd.forward(request, response);
            return;
        }

        // ----- エラーがなければDBに保存 -----
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

        // 登録完了ページにリダイレクト
        response.sendRedirect("StudentRegisterDone.action");
    }

    @Override
    public void get(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // GETでアクセスされた場合は、登録処理(POST)にそのまま処理を渡す
        post(request, response);
    }
}