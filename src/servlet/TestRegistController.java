package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import dao.TestListSubjectDao;

@WebServlet(urlPatterns = {"/servlet/TestRegistController"})
public class TestRegistController extends HttpServlet {

    /**
     * GETリクエストを処理するメソッド。
     * 主に成績一覧の初期表示と検索機能を担当します。
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        if (teacher == null || teacher.getSchool() == null) {
            response.sendRedirect(request.getContextPath() + "/Login/LOGI001.jsp");
            return;
        }

        School school = teacher.getSchool();
        ClassNumDao classNumDao = new ClassNumDao();
        SubjectDao subjectDao = new SubjectDao();
        TestListSubjectDao testListSubjectDao = new TestListSubjectDao();

        // ドロップダウンリスト用のデータを準備
        try {
            request.setAttribute("class_num_set", classNumDao.filter(school));
            request.setAttribute("subjects", subjectDao.filter(school));
            List<Integer> yearList = new ArrayList<>();
            int currentYear = LocalDate.now().getYear();
            for (int i = currentYear; i > currentYear - 10; i--) { yearList.add(i); }
            request.setAttribute("ent_year_set", yearList);
            request.setAttribute("test_no_set", Arrays.asList(1, 2));
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "データの初期化中にエラーが発生しました。");
        }

        // 検索条件を取得
        String entYearStr = request.getParameter("f1_ent_year");
        String classNum = request.getParameter("f2_class_num");
        String subjectCd = request.getParameter("f3_subject");
        String testNoStr = request.getParameter("f4_test_no");
        int entYear = 0;
        int testNo = 0;

        if (entYearStr != null && !entYearStr.isEmpty()) entYear = Integer.parseInt(entYearStr);
        if (testNoStr != null && !testNoStr.isEmpty()) testNo = Integer.parseInt(testNoStr);

        // 検索条件が有効ならDAOを呼び出す
        if (entYear != 0 && classNum != null && !classNum.equals("0") && subjectCd != null && !subjectCd.equals("0")) {
            Subject subject = new Subject();
            subject.setCd(subjectCd);
            try {
                List<TestListSubject> testList = testListSubjectDao.filter(entYear, classNum, subject, school);
                request.setAttribute("test_list", testList);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "検索中にエラーが発生しました。");
            }
        }

        // 選択された検索条件をJSPに戻す
        request.setAttribute("ent_year", entYear);
        request.setAttribute("class_num", classNum);
        request.setAttribute("subject_cd", subjectCd);
        request.setAttribute("test_no", testNo);

        // JSPにフォワード
        request.getRequestDispatcher("/Score/GRMU001.jsp").forward(request, response);
    }

    /**
     * POSTリクエストを処理するメソッド。
     * 主に成績の登録機能を担当します。
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        if (teacher == null || teacher.getSchool() == null) {
            response.sendRedirect(request.getContextPath() + "/Login/LOGI001.jsp");
            return;
        }

        School school = teacher.getSchool();
        Map<String, String> errors = new HashMap<>();
        Map<String, String> originalPoints = new HashMap<>();
        String subjectCd = request.getParameter("subject_cd");
        String testNoStr = request.getParameter("test_no");
        int testNo = Integer.parseInt(testNoStr);
        List<Test> testsToSave = new ArrayList<>();
        Enumeration<String> parameterNames = request.getParameterNames();

        // 点数のバリデーションチェック
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (paramName.startsWith("point_")) {
                String studentNo = paramName.substring("point_".length());
                String pointStr = request.getParameter(paramName);
                originalPoints.put(studentNo, pointStr);

                if (pointStr != null && !pointStr.isEmpty()) {
                    try {
                        int point = Integer.parseInt(pointStr);
                        if (point < 0 || point > 100) {
                            errors.put(studentNo, "0～100の範囲で入力してください");
                        } else {
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
                    } catch (NumberFormatException e) {
                        errors.put(studentNo, "0～100の整数を入力してください");
                    }
                }
            }
        }

        if (!errors.isEmpty()) {
            // エラーがあった場合：元の画面に必要な情報をすべてセットして戻す
            request.setAttribute("errors", errors);
            request.setAttribute("originalPoints", originalPoints);
            // doGetを呼び出して、画面表示に必要な処理を再実行させる
            doGet(request, response);
        } else {
            // エラーがなかった場合：DBに保存して完了ページへ
            try {
                TestDao testDao = new TestDao();
                testDao.save(testsToSave);
            } catch (Exception e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("/Score/GRMU002.jsp").forward(request, response);
        }
    }
}