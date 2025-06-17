package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import dao.ClassNumDao;
import dao.SchoolDao;
import dao.StudentDao;
import tool.CommonServlet;

@WebServlet("/StudentList")
public abstract class StudentListServlet extends CommonServlet {

    @Override
    protected void get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        StudentDao studentDao = new StudentDao();
        ClassNumDao classNumDao = new ClassNumDao();
        SchoolDao schoolDao = new SchoolDao();

        List<Student> studentList = null;
        List<String> classNumSet = null;
        List<Integer> entYearSet = new ArrayList<>();
        School school = null;

        request.setCharacterEncoding("UTF-8");

        // リクエストからパラメータを取得
        String entYearStr = request.getParameter("f1");
        String classNum = request.getParameter("f2");
        String isAttendStr = request.getParameter("f3");

        // パラメータを適切な型に変換
        int entYear = 0;
        boolean isAttend = false;

        if (entYearStr != null && !entYearStr.isEmpty()) {
            try {
                entYear = Integer.parseInt(entYearStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        if (isAttendStr != null && isAttendStr.equals("true")) {
            isAttend = true;
        }

        try {
            school = schoolDao.get("oom");

            // ★★★ DAOの新しいfilterメソッドを呼び出すだけ！ ★★★
            // 複雑な条件分岐は不要になり、コードがシンプルになりました。
            studentList = studentDao.filter(school, entYear, classNum, isAttend);

            // --- フォーム表示用のデータを準備 ---
            classNumSet = classNumDao.filter(school);
            int currentYear = LocalDate.now().getYear();
            for (int i = currentYear; i >= currentYear - 10; i--) {
                entYearSet.add(i);
            }

            // --- JSPに渡すデータをリクエストにセット ---
            request.setAttribute("studentList", studentList);
            request.setAttribute("class_num_set", classNumSet);
            request.setAttribute("ent_year_set", entYearSet);

            // 絞り込み条件をフォームに再表示するためにセット
            request.setAttribute("f1", entYear);
            request.setAttribute("f2", classNum);
            request.setAttribute("f3", isAttend);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "学生一覧の取得に失敗しました");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        // JSPにフォワード
        request.getRequestDispatcher("/student/studentlist.jsp").forward(request, response);
    }
}