package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import dao.ClassNumDao;
import tool.CommonServlet;

@WebServlet("/StudentCreate.action")
public abstract class StudentCreateServlet extends CommonServlet {
//新規登録のやつ
    @Override
    protected void get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // DAOをインスタンス化
        ClassNumDao classNumDao = new ClassNumDao();

        // 学校情報を取得（セッションやDBから取得するのが望ましいが、ここでは仮に固定）
        School school = new School();
        school.setCd("oom");

        try {
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

        } catch (Exception e) {
            e.printStackTrace();
            // エラーが発生した場合の処理
            request.setAttribute("error", "画面の初期表示中にエラーが発生しました。");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        // 登録ページ (student_create.jsp) にフォワード
        request.getRequestDispatcher("/student/student_create.jsp").forward(request, response);
    }
}