package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import dao.SchoolDao;
import dao.StudentDao;

@WebServlet("/StudentList")
public class StudentListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        try {
            // 学校を取得（ここでは仮に "oom" を使っている）
            SchoolDao schoolDao = new SchoolDao();
            School school = schoolDao.get("oom");

            // 学生DAO
            StudentDao studentDao = new StudentDao();

            // 在学中と退学両方を取得
            List<Student> allStudents = new ArrayList<>();
            allStudents.addAll(studentDao.filter(school, true));  // 在学中
            allStudents.addAll(studentDao.filter(school, false)); // 退学

            // リクエストに詰めてJSPへ
            request.setAttribute("studentList", allStudents);

            // 転送
            request.getRequestDispatcher("/student/studentlist.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "学生一覧の取得に失敗しました");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
