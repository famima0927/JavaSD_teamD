package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutController extends HttpServlet {

    private static final long serialVersionUID = 1L; // シリアルID（警告防止）

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



        // 既存セッションを取得（なければ null）
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate(); // セッションを破棄
        }

        // ログインページへリダイレクト（パスは必要に応じて調整）
        response.sendRedirect(request.getContextPath() + "/Login/LOGI002.jsp");
    }
}
