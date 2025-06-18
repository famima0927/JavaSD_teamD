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
        String forwardPath = "/Score/GRMR001.jsp"; // デフォルトの遷移先は検索ページ

        if (action != null) {
            // 検索ボタンが押された場合
            boolean isValid = true; // 入力チェックの結果を保持

            if (action.equals("subject_search")) {
                isValid = handleSubjectSearch(request, teacher.getSchool());
                if (isValid) forwardPath = "/Score/GRMR002.jsp"; // 入力OKなら結果ページへ

            } else if (action.equals("student_search")) {
                isValid = handleStudentSearch(request, teacher.getSchool());
                if (isValid) forwardPath = "/Score/GRMR003.jsp"; // 入力OKなら結果ページへ
            }
        }

        // 最終的に決定されたパスにフォワード
        request.getRequestDispatcher(forwardPath).forward(request, response);
    }

    @Override
    public void post(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        get(request, response);
    }

    /**
     * ① 科目検索のロジック。入力が不正な場合はfalseを返す。
     */
    private boolean handleSubjectSearch(HttpServletRequest request, School school) throws Exception {
        String entYearStr = request.getParameter("f1_ent_year");
        String classNum = request.getParameter("f2_class_num");
        String subjectCd = request.getParameter("f3_subject");

        // 検索条件をリクエストに保持（入力エラーで戻った時に選択値を復元するため）
        request.setAttribute("ent_year", Integer.parseInt(entYearStr));
        request.setAttribute("class_num", classNum);
        request.setAttribute("subject_cd", subjectCd);

        if (entYearStr.equals("0") || classNum.equals("0") || subjectCd.equals("0")) {
            request.setAttribute("criteria_error", "入学年度、クラス、科目を選択してください");
            return false; // 入力チェックエラー
        }

        TestListSubjectDao dao = new TestListSubjectDao();
        Subject subject = new Subject();
        subject.setCd(subjectCd);
        List<TestListSubject> list = dao.filter(Integer.parseInt(entYearStr), classNum, subject, school);

        if (list.isEmpty()) {
            request.setAttribute("no_results_error", "学生情報が存在しません");
        }
        request.setAttribute("subject_search_results", list);
        return true; // 正常終了
    }

    /**
     * ② 学生番号検索のロジック。入力が不正な場合はfalseを返す。
     */
    private boolean handleStudentSearch(HttpServletRequest request, School school) throws Exception {
        String studentNo = request.getParameter("f5_student_no");
        request.setAttribute("f5_student_no", studentNo); // 入力値を復元するため

        if (studentNo == null || studentNo.isEmpty()) {
            request.setAttribute("criteria_error", "学生番号を入力してください");
            return false; // 入力チェックエラー
        }

        StudentDao studentDao = new StudentDao();
        Student student = studentDao.get(studentNo);

        if (student != null) {
            TestListStudentDao testListStudentDao = new TestListStudentDao();
            List<TestListStudent> list = testListStudentDao.filter(student);
            if (list.isEmpty()) {
                request.setAttribute("no_results_error", "登録されている成績情報はありません");
            }
            request.setAttribute("student_search_results", list);
            request.setAttribute("student", student);
        } else {
            request.setAttribute("no_results_error", "指定された学生は存在しません");
        }
        return true; // 正常終了
    }

    private void prepareScreenData(HttpServletRequest request, School school) {
        // (このメソッドの中身は変更なし)
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