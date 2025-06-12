package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;           // ← クラス名修正済み
import dao.StudentDao;         // ← クラス名修正済み

public class StudentListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        StudentDao dao = new StudentDao();     // ← クラス名修正済み
        List<Student> list = dao.findAll();    // ← 型名修正済み

        request.setAttribute("studentList", list);
        RequestDispatcher rd = request.getRequestDispatcher("/studentList.jsp");
        rd.forward(request, response);

    }
}
