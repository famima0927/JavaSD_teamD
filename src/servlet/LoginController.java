package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.CommonServlet;

@WebServlet("/LoginController")
public class LoginController extends CommonServlet {
    @Override
    protected void get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/Login/LOGI001.jsp");
    }

    @Override
    protected void post(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
