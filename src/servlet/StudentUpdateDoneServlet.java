package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.CommonServlet;

@WebServlet("/StudentUpdateDone.action")
public abstract class StudentUpdateDoneServlet extends CommonServlet {

    @Override
    protected void get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ★★★ JSPファイルの場所を実際のファイル構成に合わせて修正 ★★★
        request.getRequestDispatcher("/main/student_update_done.jsp").forward(request, response);
    }
}