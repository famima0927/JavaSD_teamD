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
import tool.CommonServlet; // ★ 継承するクラスをインポート

// ★ 継承元を HttpServlet から CommonServlet に変更
@WebServlet(urlPatterns = {"/servlet/TestListController"})
public class TestListController extends CommonServlet {

    /**
     * GETリクエストを処理するメソッド。
     * CommonServletのルールに従い、doGetではなくgetメソッドに処理を記述します。
     */
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

        // --- 常に画面に必要なデータを準備 ---
        prepareScreenData(request, teacher.getSchool());

        // 押されたボタンの種類を取得
        String action = request.getParameter("search_action");

        if (action != null) {
            if (action.equals("subject_search")) {
                // --- ① 科目検索が実行された場合 ---
                handleSubjectSearch(request, teacher.getSchool());
            } else if (action.equals("student_search")) {
                // --- ② 学生番号検索が実行された場合 ---
                handleStudentSearch(request, teacher.getSchool());
            }
        }

        // 最終的にJSPにフォワード
        request.getRequestDispatcher("/Score/GRMR001.jsp").forward(request, response);
    }

    /**
     * POSTリクエストを処理するメソッド。
     * 今回この機能ではPOSTを使わないが、CommonServletを継承する上で実装が必要。
     * GETと同じ処理を行うようにgetメソッドを呼び出す。
     */
    @Override
    public void post(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        get(request, response);
    }

    /**
     * ① 科目検索のロジック
     */
    private void handleSubjectSearch(HttpServletRequest request, School school) throws Exception {
        int entYear = Integer.parseInt(request.getParameter("f1_ent_year"));
        String classNum = request.getParameter("f2_class_num");
        String subjectCd = request.getParameter("f3_subject");

        if (entYear != 0 && !classNum.equals("0") && !subjectCd.equals("0")) {
            TestListSubjectDao dao = new TestListSubjectDao();
            Subject subject = new Subject();
            subject.setCd(subjectCd);

            List<TestListSubject> list = dao.filter(entYear, classNum, subject, school);
            request.setAttribute("subject_search_results", list);
        }
        // 検索条件をリクエストに保持
        request.setAttribute("ent_year", entYear);
        request.setAttribute("class_num", classNum);
        request.setAttribute("subject_cd", subjectCd);
    }

    /**
     * ② 学生番号検索のロジック
     */
    private void handleStudentSearch(HttpServletRequest request, School school) throws Exception {
        String studentNo = request.getParameter("f5_student_no");

        if (studentNo != null && !studentNo.isEmpty()) {
            StudentDao studentDao = new StudentDao();
            TestListStudentDao testListStudentDao = new TestListStudentDao();

            Student student = studentDao.get(studentNo);

            if (student != null) {
                // TestListStudentDaoのfilterメソッドにschoolも渡すように修正
                List<TestListStudent> list = testListStudentDao.filter(student);
                request.setAttribute("student_search_results", list);
                request.setAttribute("student", student);
            } else {
                request.setAttribute("error", "指定された学生は存在しません。");
            }
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