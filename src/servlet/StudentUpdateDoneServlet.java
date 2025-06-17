package servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.CommonServlet;

@WebServlet("/StudentUpdateDone.action")
// ★★★ 修正点1：abstract を削除 ★★★
public class StudentUpdateDoneServlet extends CommonServlet {

    // ★★★ 修正点2：get メソッドの throws を Exception に修正 ★★★
    @Override
    public void get(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // 完了ページ (student_update_done.jsp) にフォワード
        request.getRequestDispatcher("/student/student_update_done.jsp").forward(request, response);
    }

    // ★★★ 修正点3：post メソッドを空で実装 ★★★
    @Override
    public void post(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // このサーブレットは完了ページを表示するだけなので、POSTの処理は不要
    }
}