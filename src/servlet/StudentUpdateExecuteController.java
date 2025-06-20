package servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.CommonServlet;

@WebServlet("/StudentUpdateExecute.action")
public class StudentUpdateExecuteController extends CommonServlet {

    @Override
    public void post(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        request.setCharacterEncoding("UTF-8");

        StudentDao studentDao = new StudentDao();
        ClassNumDao classNumDao = new ClassNumDao();

        List<String> errors = new ArrayList<>();

        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String classNum = request.getParameter("class_num");
        boolean isAttend = request.getParameter("is_attend") != null;

        if (name == null || name.trim().isEmpty()) {
            errors.add("氏名を入力してください。");
        }

        if (!errors.isEmpty()) {
            // エラーがあった場合
            // フォームに値を復元するために情報を再取得・設定
            Student student = studentDao.get(no);
            // ユーザーが入力した値で上書きしてフォームに表示
            student.setName(name);
            student.setClassNum(classNum);
            student.setIsAttend(isAttend);

            // クラス一覧も再度取得
            List<String> classNumSet = classNumDao.filter(student.getSchool());

            request.setAttribute("errors", errors);
            request.setAttribute("student", student);
            request.setAttribute("class_num_set", classNumSet);

            // ★★★ 修正点：戻り先を新しい統合JSPに変更 ★★★
            request.getRequestDispatcher("/student/STDM004.jsp").forward(request, response);

        } else {
            // エラーがなければ更新
            Student studentToUpdate = new Student();
            studentToUpdate.setNo(no);
            studentToUpdate.setName(name);
            studentToUpdate.setClassNum(classNum);
            studentToUpdate.setIsAttend(isAttend);

            // saveメソッドで学校情報が必要な場合に備えてセットしておく
            School school = new School();
            school.setCd("oom"); // 仮の学校コード
            studentToUpdate.setSchool(school);

            studentDao.save(studentToUpdate);

            // 処理完了後、完了ページにリダイレクト
            response.sendRedirect("/student/student_update_done.jsp");
        }
    }

    @Override
    public void get(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // GETリクエストは受け付けない
    }
}