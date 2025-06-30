package servlet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import tool.CommonServlet;

@WebServlet("/StudentCreate")
public class StudentCreateController extends CommonServlet {

    @Override
    public void get(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
        // ★★★ ここからが修正部分です ★★★
        // ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★

        // セッションを取得
        HttpSession session = request.getSession();
        // ログイン中の教員情報を取得
        Teacher teacher = (Teacher) session.getAttribute("user");

        // セッションが切れている、または不正な場合はログインページにリダイレクト
        if (teacher == null || teacher.getSchool() == null) {
            response.sendRedirect(request.getContextPath() + "/Login/LOGI001.jsp");
            return;
        }

        // ログインしている教員が所属する学校の情報を取得
        School school = teacher.getSchool();

        // ハードコードされていた部分を削除
        // ClassNumDao classNumDao = new ClassNumDao();
        // School school = new School();
        // school.setCd("oom"); // ← この行を削除

        // ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
        // ★★★ 修正部分はここまでです ★★
        // ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★

        ClassNumDao classNumDao = new ClassNumDao();

        // クラス番号の一覧を取得（変数schoolにはログイン中の先生の学校情報が入っている）
        List<String> classNumSet = classNumDao.filter(school);

        // 入学年度の選択肢リストを作成
        List<Integer> entYearSet = new ArrayList<>();
        int currentYear = LocalDate.now().getYear();
        for (int i = currentYear; i >= currentYear - 10; i--) {
            entYearSet.add(i);
        }

        // JSPに渡すためにリクエストスコープにセット
        request.setAttribute("class_num_set", classNumSet);
        request.setAttribute("ent_year_set", entYearSet);

        // フォワード先を登録専用の.jspに変更
        request.getRequestDispatcher("/student/STDM002.jsp").forward(request, response);
    }

    @Override
    public void post(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 学生登録のフォームがPOSTで送信された場合の処理をここに記述
        // (現在は未実装のため、GETに処理を渡す、またはエラー処理)
        get(request, response);
    }
}