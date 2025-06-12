package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet_old")
public class LoginServlet extends HttpServlet {

    private static final String JDBC_URL = "jdbc:h2:tcp://localhost/~/JavaSDDB"; // H2 DBのパス（例）
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String password = request.getParameter("password");

        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            String sql = "SELECT * FROM teacher WHERE ID = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("teacherID", id);
                response.sendRedirect(request.getContextPath() + "/main/MMNU001.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/Login/LOGI001.jsp?error=true");
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        	HttpSession UserLoginSession = request.getSession();
        	UserLoginSession.setAttribute("loginError", "エラーが発生しました。");
        	response.sendRedirect(request.getContextPath() + "/Login/LoginError.jsp");
        }
    }
}
