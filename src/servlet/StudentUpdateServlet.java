package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.ClassNumDao;
import dao.StudentDao;

/**
 * 学生情報変更ページを表示するためのサーブレット
 */
// ↓↓↓ これがURLとこのプログラムを結びつける最も重要な部分です！ ↓↓↓
@WebServlet("/StudentUpdate.action")
public class StudentUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // DAOをインスタンス化
        StudentDao studentDao = new StudentDao();
        ClassNumDao classNumDao = new ClassNumDao();

        // JSPに渡すための変数
        Student student = null;
        List<String> classNumSet = null;

        try {
            // 1. リクエストパラメータから学生番号を取得
            String no = request.getParameter("no");

            // 2. 学生番号を基にデータベースから学生情報を取得
            student = studentDao.get(no);

            // 3. 学生が所属する学校のクラス一覧を取得
            //    (student.getSchool()で学校情報を取得できることを想定)
            if (student != null) {
                classNumSet = classNumDao.filter(student.getSchool());
            }

            // 4. JSPに渡すために、取得した情報をリクエストスコープにセット
            request.setAttribute("student", student);
            request.setAttribute("class_num_set", classNumSet);

        } catch (Exception e) {
            e.printStackTrace();
            // エラーが発生した場合、エラーページにフォワードするなどの処理
            request.setAttribute("error", "学生情報の取得中にエラーが発生しました。");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return; // 以降の処理を中断
        }

        // 5. 学生情報変更ページ (student_update.jsp) にフォワード
        request.getRequestDispatcher("/student/student_update.jsp").forward(request, response);
    }
}