package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StudentUpdateDone.action")
public class StudentUpdateDoneServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ★★★ JSPファイルの場所を実際のファイル構成に合わせて修正 ★★★
        request.getRequestDispatcher("/main/student_update_done.jsp").forward(request, response);
    }
}