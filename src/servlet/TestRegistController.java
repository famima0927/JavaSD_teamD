package servlet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
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
import tool.CommonServlet; // ★ 継承するクラスをインポート

// ★ 継承元を HttpServlet から CommonServlet に変更
@WebServlet(urlPatterns = {"/servlet/TestRegistController"})
public class TestRegistController extends CommonServlet {

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
        String entYearStr = request.getParameter("ent_year");
        String classNum = request.getParameter("class_num");
        String subjectCd = request.getParameter("subject_cd");
        String testNoStr = request.getParameter("test_no");
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

    /***
     * POSTリクエストを処理するメソッド。
     * CommonServletのルールに従い、doPostではなくpostメソッドに処理を記述します。
     */
    @Override
    public void post(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        if (teacher == null || teacher.getSchool() == null) {
            response.sendRedirect(request.getContextPath() + "/Login/LOGI001.jsp");
            return;
        }

        School school = teacher.getSchool();
        String subjectCd = request.getParameter("subject_cd");
        String testNoStr = request.getParameter("test_no");
        int testNo = Integer.parseInt(testNoStr);

        TestDao testDao = new TestDao();

        // ★★★ 1. 削除処理 ★★★
        // 削除用チェックボックスでチェックされた学生番号の配列を取得
        String[] deleteStudentNos = request.getParameterValues("delete");
        List<Test> testsToDelete = new ArrayList<>();

        if (deleteStudentNos != null) {
            for (String studentNo : deleteStudentNos) {
                Test test = new Test();
                Student student = new Student();
                Subject subject = new Subject();

                student.setNo(studentNo);
                subject.setCd(subjectCd);

                test.setStudent(student);
                test.setSubject(subject);
                test.setSchool(school);
                test.setNo(testNo);

                testsToDelete.add(test);
            }
            // TestDaoのdeleteメソッドを呼び出し
            testDao.delete(testsToDelete);
        }

        // ★★★ 2. 登録・更新処理 ★★★
        Map<String, String> errors = new HashMap<>();
        Map<String, String> originalPoints = new HashMap<>();
        List<Test> testsToSave = new ArrayList<>();
        Enumeration<String> parameterNames = request.getParameterNames();

        // 削除対象の学生リスト（効率的な検索のため）
        List<String> deleteList = (deleteStudentNos != null) ? Arrays.asList(deleteStudentNos) : new ArrayList<>();

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (paramName.startsWith("point_")) {
                String studentNo = paramName.substring("point_".length());

                // ★ もし削除対象に含まれていたら、登録・更新処理はスキップ
                if (deleteList.contains(studentNo)) {
                    continue;
                }

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
                            Subject subject = new Subject();
                            student.setNo(studentNo);
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
            request.setAttribute("errors", errors);
            request.setAttribute("originalPoints", originalPoints);
            // エラーがあった場合は、GETの処理を再実行して画面を再表示
            get(request, response);
        } else {
            // エラーがなければ、登録・更新処理を実行
            if (!testsToSave.isEmpty()) {
                testDao.save(testsToSave);
            }
            request.getRequestDispatcher("/Score/GRMU002.jsp").forward(request, response);
        }
    }
}