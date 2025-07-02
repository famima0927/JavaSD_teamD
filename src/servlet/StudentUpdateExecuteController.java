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

@WebServlet("/StudentUpdateExecute")
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
        String entYearStr = request.getParameter("ent_year"); // 入学年度
        boolean isAttend = request.getParameter("is_attend") != null;

        // 氏名の入力チェック
        if (name == null || name.trim().isEmpty()) {
            errors.add("氏名を入力してください。");
        }

        // 入学年度が未入力の場合にクラス番号が入力されていたらエラーメッセージ
        if (classNum != null && !classNum.trim().isEmpty() && (entYearStr == null || entYearStr.trim().isEmpty())) {
            errors.add("クラスを指定する場合は入学年度も指定してください。");
        }

        // 入学年度の入力チェック（もし入学年度が必須の場合）
        if (entYearStr == null || entYearStr.trim().isEmpty()) {
            errors.add("入学年度を入力してください。");
        }

        // エラーがあった場合
        if (!errors.isEmpty()) {
            // フォームに値を復元するために情報を再取得・設定
            Student student = studentDao.get(no);
            student.setName(name);
            student.setClassNum(classNum);
            student.setIsAttend(isAttend);

            // 入学年度が入力されていた場合、設定
            student.setEntYear(entYearStr != null ? Integer.parseInt(entYearStr) : 0);

            // クラス一覧も再度取得
            List<String> classNumSet = classNumDao.filter(student.getSchool());

            // エラーリスト、学生情報、クラス番号セットをリクエストにセット
            request.setAttribute("errors", errors);
            request.setAttribute("student", student);
            request.setAttribute("class_num_set", classNumSet);

            // ★★★ 修正点：戻り先を新しい統合JSPに変更 ★★★
            request.getRequestDispatcher("/student/STDM001.jsp").forward(request, response);

        } else {
            // エラーがなければ更新
            Student studentToUpdate = new Student();
            studentToUpdate.setNo(no);
            studentToUpdate.setName(name);
            studentToUpdate.setClassNum(classNum);
            studentToUpdate.setIsAttend(isAttend);

            // 入学年度があれば設定
            if (entYearStr != null && !entYearStr.trim().isEmpty()) {
                studentToUpdate.setEntYear(Integer.parseInt(entYearStr));
            }

            // saveメソッドで学校情報が必要な場合に備えてセットしておく
            School school = new School();
            school.setCd("oom"); // 仮の学校コード
            studentToUpdate.setSchool(school);

            studentDao.save(studentToUpdate);

            // 処理完了後、完了ページにリダイレクト
            response.sendRedirect(request.getContextPath() + "/student/STDM005.jsp");
        }
    }

    @Override
    public void get(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // GETリクエストは受け付けない
    }
}
