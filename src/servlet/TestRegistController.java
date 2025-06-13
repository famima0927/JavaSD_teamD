package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import dao.TestListSubjectDao;

@WebServlet(urlPatterns = {"/servlet/TestRegistController"})
public class TestRegistController extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    // セッションを取得
	    HttpSession session = request.getSession();
	    // ログイン中の教員情報を取得
	    Teacher teacher = (Teacher) session.getAttribute("user");

	    // ログインしていない場合はログインページにリダイレクト
	    if (teacher == null) {
	        response.sendRedirect(request.getContextPath() + "/Login/LOGI001.jsp");
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
	        e.printStackTrace();
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

	    // ★★★★★★★★★★★★★★★★★★
	    // ★★★ 以前のコードで欠けていた部分 ★★★
	    String testNoStr = request.getParameter("f4_test_no"); // ①「回数」のパラメータを取得
	    int testNo = 0; // ② testNo変数を宣言
	    int entYear = 0;

	    if (entYearStr != null && !entYearStr.isEmpty()) {
	        entYear = Integer.parseInt(entYearStr);
	    }
	    if (testNoStr != null && !testNoStr.isEmpty()) {
	        testNo = Integer.parseInt(testNoStr); // ③ 回数が選択されていれば、数値に変換
	    }
	    // ★★★★★★★★★★★★★★★★★★

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

	    // ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
	    // ★★★ ④ 宣言したtestNo変数をJSPに渡す ★★★
	    request.setAttribute("test_no", testNo);
	    // ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★

	    if (testList != null) {
	        request.setAttribute("test_list", testList);
	    }

	    // --- JSPにフォワード ---
	    request.getRequestDispatcher("/Score/GRMU001.jsp").forward(request, response);
	}






	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // POSTデータの文字コードを設定
        request.setCharacterEncoding("UTF-8");

        // セッションを取得し、ログイン状態を確認
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // セッションチェック（ログインしていない、または学校情報が不正な場合）
        if (teacher == null || teacher.getSchool() == null) {
            // ログインページにリダイレクト
            response.sendRedirect(request.getContextPath() + "/Login/LOGI001.jsp");
            return;
        }

        School school = teacher.getSchool();
        TestDao testDao = new TestDao();

        // -----------------------------------------------------
        // 1. リクエストパラメータを取得
        // -----------------------------------------------------

        // JSPの隠しフィールドから科目コードと回数を取得
        String subjectCd = request.getParameter("subject_cd");
        String testNoStr = request.getParameter("test_no");
        int testNo = Integer.parseInt(testNoStr);

        // これからDBに保存するTestオブジェクトのリストを準備
        List<Test> testsToSave = new ArrayList<>();

        // -----------------------------------------------------
        // 2. 全てのパラメータを走査し、点数データを抽出
        // -----------------------------------------------------

        // JSPから送られてきた全パラメータの名前を取得
        Enumeration<String> parameterNames = request.getParameterNames();

        // 全てのパラメータ名をループでチェック
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();

            // パラメータ名が "point_" で始まるものだけを処理 (例: point_11111)
            if (paramName.startsWith("point_")) {
                // "point_" の後ろの部分（学生番号）を切り出す
                String studentNo = paramName.substring("point_".length());

                // そのパラメータの値（入力された点数）を取得
                String pointStr = request.getParameter(paramName);

                // 点数が入力されている場合のみ処理
                if (pointStr != null && !pointStr.isEmpty()) {
                    int point = Integer.parseInt(pointStr);

                    // 保存用のTestオブジェクトを作成
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

                    // 保存リストに追加
                    testsToSave.add(test);
                }
            }
        }

        // -----------------------------------------------------
        // 3. DAOを呼び出してDBに保存
        // -----------------------------------------------------

        try {
            // TestDaoのsaveメソッドは複数のデータをまとめて保存するトランザクション処理に対応
            testDao.save(testsToSave);
        } catch (Exception e) {
            // TODO: 本来はエラーページに飛ばすなどのエラーハンドリングを行う
            e.printStackTrace();
        }

        // -----------------------------------------------------
        // 4. 完了ページにフォワード
        // -----------------------------------------------------
        request.getRequestDispatcher("/Score/GRMU002.jsp").forward(request, response);
    }
}
