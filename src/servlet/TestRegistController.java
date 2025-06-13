package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.TestDao;

@WebServlet(urlPatterns = {"/servlet/TestRegistController"})
public class TestRegistController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        TestDao testDao = new TestDao();

        // --- リクエストパラメータを取得 ---
        String subjectCd = request.getParameter("subject_cd");
        String testNoStr = request.getParameter("test_no");
        int testNo = Integer.parseInt(testNoStr);

        List<Test> testsToSave = new ArrayList<>();
        Enumeration<String> parameterNames = request.getParameterNames();

        // --- 全てのパラメータを走査し、点数データを抽出 ---
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (paramName.startsWith("point_")) {
                String studentNo = paramName.substring("point_".length());
                String pointStr = request.getParameter(paramName);
                if (pointStr != null && !pointStr.isEmpty()) {
                    int point = Integer.parseInt(pointStr);

                    Test test = new Test();
                    Student student = new Student();
                    student.setNo(studentNo);

                    Subject subject = new Subject();
                    subject.setCd(subjectCd);

                    test.setStudent(student);
                    test.setSubject(subject);
                    test.setSchool(school);
                    test.setNo(testNo);
                    test.setPoint(point);

                    testsToSave.add(test);
                }
            }
        }

        // --- DAOを呼び出してDBに保存 ---
        try {
            testDao.save(testsToSave);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // --- 完了ページにフォワード ---
        request.getRequestDispatcher("/Score/GRMU002.jsp").forward(request, response);
    }
}