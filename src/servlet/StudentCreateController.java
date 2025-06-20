package servlet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import dao.ClassNumDao;
import tool.CommonServlet;

@WebServlet("/StudentCreate")
public class StudentCreateController extends CommonServlet {

    @Override
    public void get(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ClassNumDao classNumDao = new ClassNumDao();
        School school = new School();
        school.setCd("oom");

        // クラス番号の一覧を取得
        List<String> classNumSet = classNumDao.filter(school);

        // 入学年度の選択肢リストを作成
        List<Integer> entYearSet = new ArrayList<>();
        int currentYear = LocalDate.now().getYear();
        for (int i =
        		currentYear; i >= currentYear - 10; i--) {
            entYearSet.add(i);
        }

        // JSPに渡すためにリクエストスコープにセット
        request.setAttribute("class_num_set", classNumSet);
        request.setAttribute("ent_year_set", entYearSet);

        // ★★★ 修正点：フォワード先を登録専用の.jspに変更 ★★★
        request.getRequestDispatcher("/student/student_create.jsp").forward(request, response);
    }

    @Override
    public void post(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // GETリクエストに処理を渡す
        get(request, response);
    }
}