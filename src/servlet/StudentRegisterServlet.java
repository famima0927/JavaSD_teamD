package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;

public class StudentRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // パラメータ取得
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String entYearStr = request.getParameter("entYear");
        String classNum = request.getParameter("classNum");

        // エラーフラグ & メッセージ
        boolean hasError = false;
        StringBuilder errorMessage = new StringBuilder();

        // 入学年度チェック（未選択＝空欄 or null）
        int entYear = 0;
        if (entYearStr == null || entYearStr.isEmpty()) {
            hasError = true;
            request.setAttribute("entYearError", "入学年度を選択してください。");
        } else {
            entYear = Integer.parseInt(entYearStr);
        }

        // 学生番号未入力チェック
        if (no == null || no.isEmpty()) {
            hasError = true;
            request.setAttribute("noError", "学生番号を入力してください。");
        } else {
            // 学生番号重複チェック
            StudentDao dao = new StudentDao();
//            if (dao.findByNo(no) != null) {
//                hasError = true;
//                request.setAttribute("noDuplicateError", "その学生番号は既に使われています。");
//            }
        }

        // 氏名未入力チェック
        if (name == null || name.isEmpty()) {
            hasError = true;
            request.setAttribute("nameError", "氏名を入力してください。");
        }

        // エラーがある場合、フォームへ戻す
        if (hasError) {
            request.setAttribute("no", no);
            request.setAttribute("name", name);
            request.setAttribute("entYear", entYearStr);
            request.setAttribute("classNum", classNum);

            RequestDispatcher rd = request.getRequestDispatcher("/registerForm.jsp");
            rd.forward(request, response);
            return;
        }

        // 学生オブジェクト作成
        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setEntYear(entYear);
        student.setClassNum(classNum);
//        student.setAttend(false);
        student.setSchool(null); // 学校情報がフォームに無いのでnull

        // 登録処理
        StudentDao dao = new StudentDao();
//        boolean success = dao.insert(student);

//        if (success) {
//            request.setAttribute("message", "学生情報を登録しました。");
//        } else {
//            request.setAttribute("message", "登録に失敗しました。");
//        }

        RequestDispatcher rd = request.getRequestDispatcher("/registerResult.jsp");
        rd.forward(request, response);
    }
}