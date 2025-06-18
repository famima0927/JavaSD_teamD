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
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import dao.TestListSubjectDao;
import tool.CommonServlet;

@WebServlet(urlPatterns = {"/servlet/TestListController"})
public class TestListController extends CommonServlet {

    @Override
    public void get(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        if (teacher == null || teacher.getSchool() == null) {
            response.sendRedirect(request.getContextPath() + "/Login/LOGI001.jsp");
            return;
        }

        prepareScreenData(request, teacher.getSchool());

        String action = request.getParameter("search_action");

        if (action != null) {
            if (action.equals("subject_search")) {
                 setTestListSubject(request, teacher.getSchool());
                 request.getRequestDispatcher("/Score/GRMR002.jsp").forward(request, response);
            } else if (action.equals("student_search")) {
            	 setTestListStudent(request, teacher.getSchool());
            	 request.getRequestDispatcher("/Score/GRMR003.jsp").forward(request, response);
            }
        }

        request.getRequestDispatcher("/Score/GRMR002.jsp").forward(request, response);
    }

    @Override
    public void post(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        get(request, response);
    }

    /**
     * ① 科目検索のロジック
     */
    private void setTestListSubject(HttpServletRequest request, School school) throws Exception {
        String entYearStr = request.getParameter("f1_ent_year");
        String classNum = request.getParameter("f2_class_num");
        String subjectCd = request.getParameter("f3_subject");

        int entYear = Integer.parseInt(entYearStr);

        // ★★★ 入力チェックを追加 ★★★
        if (entYear == 0 || classNum.equals("0") || subjectCd.equals("0")) {
            request.setAttribute("criteria_error", "入学年度とクラスと科目を選択してください");
            // 検索条件をリクエストに保持して早期リターン
            request.setAttribute("ent_year", entYear);
            request.setAttribute("class_num", classNum);
            request.setAttribute("subject_cd", subjectCd);
            return;
        }

        TestListSubjectDao dao = new TestListSubjectDao();
        Subject subject = new Subject();
        subject.setCd(subjectCd);

        List<TestListSubject> list = dao.filter(entYear, classNum, subject, school);

        // ★★★ 結果0件チェックを追加 ★★★
        if (list.isEmpty()) {
            request.setAttribute("no_results_error", "学生情報が存在しません");
        }

        request.setAttribute("subject_search_results", list);
        // 検索条件をリクエストに保持
        request.setAttribute("ent_year", entYear);
        request.setAttribute("class_num", classNum);
        request.setAttribute("subject_cd", subjectCd);
    }

    /**
     * ② 学生番号検索のロジック
     */
    private void setTestListStudent(HttpServletRequest request, School school) throws Exception {
        String studentNo = request.getParameter("f5_student_no");

        // ★★★ 入力チェックを追加 ★★★
        if (studentNo == null || studentNo.isEmpty()) {
            request.setAttribute("criteria_error", "学生番号を入力してください");
            return;
        }

        StudentDao studentDao = new StudentDao();
        TestListStudentDao testListStudentDao = new TestListStudentDao();

        Student student = studentDao.get(studentNo);

        if (student != null) {
            List<TestListStudent> list = testListStudentDao.filter(student);

            // ★★★ 結果0件チェックを追加 ★★★
            if (list.isEmpty()) {
                request.setAttribute("no_results_error", "登録されている成績情報はありません");
            }

            request.setAttribute("student_search_results", list);
            request.setAttribute("student", student);
        } else {
            // ★★★ 存在しない学生番号だった場合のエラー ★★★
            request.setAttribute("no_results_error", "指定された学生は存在しません");
        }
        request.setAttribute("f5_student_no", studentNo);
    }

    /**
     * ドロップダウンリスト用のデータを準備する
     */
    private void prepareScreenData(HttpServletRequest request, School school) {
        try {
            ClassNumDao classNumDao = new ClassNumDao();
            SubjectDao subjectDao = new SubjectDao();
            request.setAttribute("class_num_set", classNumDao.filter(school));
            request.setAttribute("subjects", subjectDao.filter(school));

            List<Integer> yearList = new ArrayList<>();
            int currentYear = LocalDate.now().getYear();
            for (int i = currentYear; i > currentYear - 10; i--) {
                yearList.add(i);
            }
            request.setAttribute("ent_year_set", yearList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}