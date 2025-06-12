import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;

@WebServlet("/StudentUpdate")
public class StudentUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        StudentDAO studentDao = new StudentDAO();
        ClassNumDAO classNumDao = new ClassNumDAO();

        String studentNo = request.getParameter("no");

        Student student = studentDao.get(studentNo);

        List<String> classList = classNumDao.getFilterBySchoolCd("oom");

        request.setAttribute("student", student);
        request.setAttribute("classList", classList);

        request.getRequestDispatcher("student_update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        StudentDAO studentDao = new StudentDAO();
        Student student = new Student();

        String studentNo = request.getParameter("no");
        String studentName = request.getParameter("name");
        String classNum = request.getParameter("class_num");
        String isAttendStr = request.getParameter("is_attend");
        boolean isAttend = (isAttendStr != null && isAttendStr.equals("on"));

        student.setNo(studentNo);
        student.setName(studentName);
        student.setClassNum(classNum);
        student.setAttend(isAttend);

        studentDao.update(student);

        request.getRequestDispatcher("student_update_done.jsp").forward(request, response);
    }
}