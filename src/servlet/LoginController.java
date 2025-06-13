package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // JSPページへリダイレクト（URLが変わる）
        response.sendRedirect(request.getContextPath() + "/Login/LOGI001.jsp");
    }
	protected void post(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

	}
}