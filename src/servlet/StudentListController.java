package servlet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SchoolDao;
import dao.StudentDao;
import tool.CommonServlet;


@WebServlet("/StudentList")
public class StudentListController extends CommonServlet {

    @Override
    public void get(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        StudentDao studentDao = new StudentDao();
        ClassNumDao classNumDao = new ClassNumDao();
        SchoolDao schoolDao = new SchoolDao();

        // セッションから教師情報を取得
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        String schoolCode = teacher != null ? teacher.getSchool().getCd() : "oom"; // デフォルトとして"oom"を設定

        String entYearStr = request.getParameter("f1");
        String classNum = request.getParameter("f2");
        String isAttendStr = request.getParameter("f3");

        int entYear = 0;
        boolean isAttend = false;

        if (entYearStr != null && !entYearStr.isEmpty()) {
            try {
                entYear = Integer.parseInt(entYearStr);
            } catch (NumberFormatException e) {
                entYear = 0;
            }
        }

        if ("true".equals(isAttendStr)) {
            isAttend = true;
        }

        School school = schoolDao.get(schoolCode);  // 学校コードに基づいて学校を取得

        List<Student> studentList = studentDao.filter(school, entYear, classNum, isAttend);
        List<String> classNumSet = classNumDao.filter(school);
        List<Integer> entYearSet = new ArrayList<>();

        int currentYear = LocalDate.now().getYear();
        for (int i = currentYear; i >= currentYear - 10; i--) {
            entYearSet.add(i);
        }

        // 入学年度が未選択かつクラスが指定されている場合、エラーメッセージを設定
        if (classNum != null && !classNum.isEmpty() && entYear == 0) {
            request.setAttribute("no_results_error", "クラスを指定する場合は入学年度も指定してください。");
        }

        request.setAttribute("studentList", studentList);
        request.setAttribute("class_num_set", classNumSet);
        request.setAttribute("ent_year_set", entYearSet);
        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", isAttend);

        request.getRequestDispatcher("/student/STDM001.jsp").forward(request, response);
    }

    @Override
    public void post(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 不要なら空でもOK
    }
}

