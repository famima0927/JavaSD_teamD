package servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.CommonServlet;

@WebServlet("/StudentUpdateExecute")
// ★★★ 修正点1：abstract を削除 ★★★
public class StudentUpdateExecuteController extends CommonServlet {

    // ★★★ 修正点2：post メソッドの throws を Exception に修正 & 不要なtry-catchを削除 ★★★
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
            // エラーがあった場合、フォームに値を復元して戻す
            // DBアクセスでエラーが起きてもCommonServletがcatchしてくれる
            Student student = studentDao.get(no);
            student.setName(name);
            student.setClassNum(classNum);
            student.setIsAttend(isAttend);

            List<String> classNumSet = classNumDao.filter(student.getSchool());

            request.setAttribute("errors", errors);
            request.setAttribute("student", student);
            request.setAttribute("class_num_set", classNumSet);

            request.getRequestDispatcher("/student/student_update.jsp").forward(request, response);

        } else {
            // エラーがなければ、データベースを更新
            Student studentToUpdate = new Student();
            studentToUpdate.setNo(no);
            studentToUpdate.setName(name);
            studentToUpdate.setClassNum(classNum);
            studentToUpdate.setIsAttend(isAttend);

            studentDao.save(studentToUpdate);

            // 処理完了後、完了ページにリダイレクト
            response.sendRedirect("${pageContext.request.contextPath}/student/STDM005.jsp");
        }
    }

    // ★★★ 修正点3：get メソッドを空で実装 ★★★
    @Override
    public void get(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // このサーブレットは更新処理専用なので、GETの処理は不要
    }
}