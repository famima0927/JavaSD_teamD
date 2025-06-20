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
import tool.CommonServlet;

@WebServlet(urlPatterns = {"/servlet/TestRegistController"})
public class TestRegistController extends CommonServlet {

    @Override
    public void get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        if (teacher == null || teacher.getSchool() == null) {
            response.sendRedirect(request.getContextPath() + "/Login/LOGI001.jsp");
            return;
        }

        School school = teacher.getSchool();

        // --- ▼▼▼ ドロップダウンリスト用のデータ準備（ここから展開）▼▼▼ ---
        try {
            ClassNumDao classNumDao = new ClassNumDao();
            SubjectDao subjectDao = new SubjectDao();
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
        // --- ▲▲▲ ドロップダウンリスト用のデータ準備（ここまで展開）▲▲▲ ---

        String entYearStr = request.getParameter("ent_year");
        String classNum = request.getParameter("class_num");
        String subjectCd = request.getParameter("subject_cd");
        String testNoStr = request.getParameter("test_no");

        if (entYearStr != null) {
            if ("0".equals(entYearStr) || "0".equals(classNum) || "0".equals(subjectCd) || "0".equals(testNoStr)) {
                request.setAttribute("error", "入学年度とクラスと科目と回数を選択してください");
            } else {
                int entYear = Integer.parseInt(entYearStr);
                TestListSubjectDao testListSubjectDao = new TestListSubjectDao();
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

            request.setAttribute("ent_year", Integer.parseInt(entYearStr));
            request.setAttribute("class_num", classNum);
            request.setAttribute("subject_cd", subjectCd);
            request.setAttribute("test_no", Integer.parseInt(testNoStr));
        }

        request.getRequestDispatcher("/Score/GRMU001.jsp").forward(request, response);
    }

    @Override
    protected void post(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        String action = request.getParameter("action");
        TestDao testDao = new TestDao();

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
        }

        Map<String, String> errors = new HashMap<>();
        Map<String, String> originalPoints = new HashMap<>();
        List<Test> testsToSave = new ArrayList<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        List<String> deleteList = (deleteStudentNos != null) ? Arrays.asList(deleteStudentNos) : new ArrayList<>();

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (paramName.startsWith("point_")) {
                String studentNo = paramName.substring("point_".length());
                if (deleteList.contains(studentNo)) continue;
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

            // --- ▼▼▼ ドロップダウンリスト用のデータ準備（ここから展開）▼▼▼ ---
            try {
                ClassNumDao classNumDao = new ClassNumDao();
                SubjectDao subjectDao = new SubjectDao();
                request.setAttribute("class_num_set", classNumDao.filter(school));
                request.setAttribute("subjects", subjectDao.filter(school));
                List<Integer> yearList = new ArrayList<>();
                int currentYear = LocalDate.now().getYear();
                for (int i = currentYear; i > currentYear - 10; i--) { yearList.add(i); }
                request.setAttribute("ent_year_set", yearList);
                request.setAttribute("test_no_set", Arrays.asList(1, 2));
            } catch (Exception e) {
                e.printStackTrace();
            }
            // --- ▲▲▲ ドロップダウンリスト用のデータ準備（ここまで展開）▲▲▲ ---

            // エラー時の画面再表示に必要なデータをセット
            try {
                int entYear = Integer.parseInt(request.getParameter("ent_year"));
                String classNum = request.getParameter("class_num");
                TestListSubjectDao testListSubjectDao = new TestListSubjectDao();
                Subject subject = new Subject();
                subject.setCd(subjectCd);
                List<TestListSubject> testList = testListSubjectDao.filter(entYear, classNum, subject, school);
                request.setAttribute("test_list", testList);
                request.setAttribute("ent_year", entYear);
                request.setAttribute("class_num", classNum);
                request.setAttribute("subject_cd", subjectCd);
                request.setAttribute("test_no", testNo);
            } catch (Exception e) {
                e.printStackTrace();
            }

            request.getRequestDispatcher("/Score/GRMU001.jsp").forward(request, response);

        } else {
            try {
                if (!testsToDelete.isEmpty()) testDao.delete(testsToDelete);
                if (!testsToSave.isEmpty()) testDao.save(testsToSave);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (action != null && action.equals("update")) {
                doGet(request, response);
            } else {
                request.getRequestDispatcher("/Score/GRMU002.jsp").forward(request, response);
            }
        }
    }
}