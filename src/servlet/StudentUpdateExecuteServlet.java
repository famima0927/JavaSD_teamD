package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.ClassNumDao;
import dao.StudentDao;

@WebServlet("/StudentUpdateExecute.action")
public class StudentUpdateExecuteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 文字化け対策
        request.setCharacterEncoding("UTF-8");

        StudentDao studentDao = new StudentDao();
        ClassNumDao classNumDao = new ClassNumDao();

        List<String> errors = new ArrayList<>();

        // フォームからのパラメータ取得
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String classNum = request.getParameter("class_num");
        boolean isAttend = request.getParameter("is_attend") != null;

        // 氏名が空かどうかのバリデーション
        if (name == null || name.trim().isEmpty()) {
            errors.add("氏名を入力してください。");
        }

        // エラーリストに何か入っていれば、フォームに戻す
        if (!errors.isEmpty()) {
            try {
                // フォームに値を復元するために情報を再取得・設定
                Student student = studentDao.get(no);
                student.setName(name);
                student.setClassNum(classNum);
                student.setIsAttend(isAttend);

                List<String> classNumSet = classNumDao.filter(student.getSchool());

                request.setAttribute("errors", errors);
                request.setAttribute("student", student);
                request.setAttribute("class_num_set", classNumSet);

                // 変更ページにフォワードで戻る
                request.getRequestDispatcher("/student/student_update.jsp").forward(request, response);

            } catch (Exception e) {
                // 予期せぬエラーはエラーページへ
                e.printStackTrace();
                request.setAttribute("error", "エラーが発生しました。");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        } else {
            // エラーがなければ、データベースを更新
            try {
                Student studentToUpdate = new Student();
                studentToUpdate.setNo(no);
                studentToUpdate.setName(name);
                studentToUpdate.setClassNum(classNum);
                studentToUpdate.setIsAttend(isAttend);

                studentDao.save(studentToUpdate);

                // 処理完了後、完了ページにリダイレクト
                response.sendRedirect("StudentUpdateDone.action");

            } catch (Exception e) {
                // データベース更新中のエラーはエラーページへ
                e.printStackTrace();
                request.setAttribute("error", "データベースの更新中にエラーが発生しました。");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        }
    }
}