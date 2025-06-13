package servlet; // パッケージ名はプロジェクトに合わせてください

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;

@WebServlet(urlPatterns = {"/servlet/TestListController"})
public class TestListController extends HttpServlet {
   // ... 他のフィールド ...

    // ↓↓↓ この行を修正します ↓↓↓

    // 修正前（おそらくこうなっている）:
    // private Map<Integer, Integer> points;

    // 修正後:
    private Map<Integer, Integer> points = new HashMap<>(); // ★ `= new HashMap<>();` を追加

    // ... (ゲッター/セッターは変更なし) ...

    public Map<Integer, Integer> getPoints() {
        return points;
    }
    public void setPoints(Map<Integer, Integer> points) {
        this.points = points;
    }
    public void putPoint(int key, int value) {
        // pointsが初期化されているので、ここでエラーは起きなくなる
        this.points.put(key, value);
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // セッションを取得
        HttpSession session = request.getSession();
        // ログイン中の教員情報を取得
        Teacher teacher = (Teacher) session.getAttribute("user");

        // ログインしていない場合はログインページにリダイレクト
        if (teacher == null) {
        	response.sendRedirect(request.getContextPath() + "/Login/LOGI001.jsp"); // TODO: アプリケーション名とパスを要確認
            return;
        }

        // DAOをインスタンス化
        ClassNumDao classNumDao = new ClassNumDao();
        SubjectDao subjectDao = new SubjectDao();
        TestListSubjectDao testListSubjectDao = new TestListSubjectDao();

        School school = teacher.getSchool();

        // --- ドロップダウンリスト用のデータを準備 ---
        List<String> classList = new ArrayList<>();
        List<Subject> subjectList = new ArrayList<>();
        try {
            classList = classNumDao.filter(school);
            subjectList = subjectDao.filter(school);
        } catch (Exception e) {
            e.printStackTrace(); // エラーハンドリング
        }

        List<Integer> yearList = new ArrayList<>();
        int currentYear = LocalDate.now().getYear();
        for (int i = currentYear; i > currentYear - 10; i--) {
            yearList.add(i);
        }
        List<Integer> numList = Arrays.asList(1, 2);

        // --- リクエストパラメータ（検索条件）を取得 ---
        String entYearStr = request.getParameter("f1_ent_year");
        String classNum = request.getParameter("f2_class_num");
        String subjectCd = request.getParameter("f3_subject");
        String testNoStr = request.getParameter("f4_test_no"); // 「回数」のパラメータを取得
        int testNo = 0; // testNo変数を宣言し、初期値を0に設定

        int entYear = 0;
        if (entYearStr != null && !entYearStr.isEmpty()) {
            entYear = Integer.parseInt(entYearStr);
        }

        List<TestListSubject> testList = null;

        // --- 検索条件が指定されていればDAOを呼び出す ---
        if (entYear != 0 && classNum != null && !classNum.equals("0") && subjectCd != null && !subjectCd.equals("0")) {
            Subject subject = new Subject();
            subject.setCd(subjectCd);
            try {
                testList = testListSubjectDao.filter(entYear, classNum, subject, school);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // --- JSPに渡すデータをリクエストスコープにセット ---
        request.setAttribute("ent_year_set", yearList);
        request.setAttribute("class_num_set", classList);
        request.setAttribute("subjects", subjectList);
        request.setAttribute("test_no_set", numList);

        request.setAttribute("ent_year", entYear);
        request.setAttribute("class_num", classNum);
        request.setAttribute("subject_cd", subjectCd);
        request.setAttribute("test_no", testNo); // 検索された「回数」もJSPに渡す

        if (testList != null) {
            request.setAttribute("test_list", testList);
        }

        // --- JSPにフォワード ---
        request.getRequestDispatcher("/Score/GRMU001.jsp").forward(request, response);
    }
}