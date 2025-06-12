package servlet; // パッケージ名はプロジェクトに合わせてください

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import tool.Action;

public class TestRegistController extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッションを取得
        HttpSession session = request.getSession();
        // ログイン中の教員情報を取得
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // DAOをインスタンス化
        TestDao testDao = new TestDao();

        // -----------------------------------------------------
        // 1. リクエストパラメータを取得
        // -----------------------------------------------------

        // 隠しフィールドから科目コードと回数を取得
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

        boolean success = testDao.save(testsToSave);

        // -----------------------------------------------------
        // 4. 完了ページにフォワード
        // -----------------------------------------------------
        // TODO: エラーハンドリングをここに追加可能

        request.getRequestDispatcher("/score_management/test_regist_done.jsp").forward(request, response);
    }
}