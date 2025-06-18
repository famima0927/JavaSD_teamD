package servlet;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.CommonServlet;
import tool.Page;

@WebServlet("/SubjectRegisterServlet")
public class SubjectCreateController extends CommonServlet {

    /**
     * GETメソッドでアクセスされたときの処理。
     * 主にページ初期表示（例：リンククリックやURL直接入力）に使われる。
     */
    @Override
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    	resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out=resp.getWriter();
		Page.header(out);

        // フォワード：ブラウザのURLは変わらず、サーバー内部でページを切り替える
        req.getRequestDispatcher("/Subject/SBJM002.jsp").forward(req, resp);}


    @Override
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    	}}



