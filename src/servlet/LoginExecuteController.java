package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
@WebServlet("/LoginServlet")
public class LoginExecuteController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String password = request.getParameter("password");

        try {
            TeacherDao dao = new TeacherDao();
            Teacher teacher = dao.login(id, password); // loginメソッドを呼び出す

            if (teacher != null) {
                // ログイン成功
                HttpSession session = request.getSession();
                session.setAttribute("teacherID", teacher.getId());
                response.sendRedirect(request.getContextPath() + "/main/MMNU001.jsp");
            } else {
                // ログイン失敗
                response.sendRedirect(request.getContextPath() + "/Login/LOGI001.jsp?error=true");
            }

        } catch (Exception e) {
            e.printStackTrace();
            // エラーハンドリング
            HttpSession session = request.getSession();
            session.setAttribute("loginError", "エラーが発生しました。");
            response.sendRedirect(request.getContextPath() + "/Login/LoginError.jsp");
        }
    }
}
