package servlet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import dao.ClassNumDao;
import tool.CommonServlet; // 作成したCommonServletをインポート

@WebServlet("/StudentCreate.action")
// ★★★ 修正点1：HttpServletからCommonServletを継承 ★★★
public class StudentCreateServlet extends CommonServlet {

    // ★★★ 修正点2：doGetではなく、getメソッドに処理を記述 ★★★
    @Override
    public void get(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // DAOをインスタンス化
        ClassNumDao classNumDao = new ClassNumDao();

        // 学校情報を取得（セッションやDBから取得するのが望ましいが、ここでは仮に固定）
        School school = new School();
        school.setCd("oom");

        // クラス番号の一覧を取得
        List<String> classNumSet = classNumDao.filter(school);

        // 入学年度の選択肢リストを作成（現在の西暦から10年前まで）
        List<Integer> entYearSet = new ArrayList<>();
        int currentYear = LocalDate.now().getYear();
        for (int i = currentYear; i >= currentYear - 10; i--) {
            entYearSet.add(i);
        }

        // JSPに渡すためにリクエストスコープにセット
        request.setAttribute("class_num_set", classNumSet);
        request.setAttribute("ent_year_set", entYearSet);

        // 登録ページ (student_create.jsp) にフォワード
        request.getRequestDispatcher("/student/student_create.jsp").forward(request, response);
    }

    // ★★★ 修正点3：postメソッドを空で実装 ★★★
    @Override
    public void post(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // このサーブレットはフォームを表示するだけなので、POSTの処理は不要
    }
}