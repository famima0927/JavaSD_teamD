package servlet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.HttpServlet; // こちらは不要に
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import dao.ClassNumDao;
import dao.SchoolDao;
import dao.StudentDao;
import tool.CommonServlet; // 作成したCommonServletをインポート

@WebServlet("/StudentList")
// ★★★ 修正点1：HttpServletからCommonServletを継承し、abstractを削除 ★★★
public class StudentListServlet extends CommonServlet {

    // ★★★ 修正点2：doGetではなく、CommonServletで定義された get メソッドを実装 ★★★
    @Override
    public void get(HttpServletRequest request, HttpServletResponse response)
            throws Exception { // Exceptionをスローする形に合わせる

        StudentDao studentDao = new StudentDao();
        ClassNumDao classNumDao = new ClassNumDao();
        SchoolDao schoolDao = new SchoolDao();

        List<Student> studentList = new ArrayList<>();
        List<String> classNumSet = new ArrayList<>();
        List<Integer> entYearSet = new ArrayList<>();
        School school = null;

        request.setCharacterEncoding("UTF-8");

        String entYearStr = request.getParameter("f1");
        String classNum = request.getParameter("f2");
        String isAttendStr = request.getParameter("f3");

        int entYear = 0;
        boolean isAttend = false;

        if (entYearStr != null && !entYearStr.isEmpty()) {
            try {
                entYear = Integer.parseInt(entYearStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        if (isAttendStr != null && isAttendStr.equals("true")) {
            isAttend = true;
        }

        // try-catchはCommonServletが担当するので、このクラス内では不要
        school = schoolDao.get("oom");

        studentList = studentDao.filter(school, entYear, classNum, isAttend);

        classNumSet = classNumDao.filter(school);
        int currentYear = LocalDate.now().getYear();
        for (int i = currentYear; i >= currentYear - 10; i--) {
            entYearSet.add(i);
        }

        request.setAttribute("studentList", studentList);
        request.setAttribute("class_num_set", classNumSet);
        request.setAttribute("ent_year_set", entYearSet);

        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", isAttend);

        request.getRequestDispatcher("/student/studentlist.jsp").forward(request, response);
    }

    // ★★★ 修正点3：postメソッドも実装する（中身は空でOK） ★★★
    @Override
    public void post(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 今回はPOSTでの処理はないため、中身は空
    }
}