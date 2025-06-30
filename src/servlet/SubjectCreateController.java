package servlet;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import bean.Teacher;
import tool.CommonServlet;
import tool.Page;

@WebServlet("/SubjectCreate")
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
		req.setCharacterEncoding("UTF-8");
	     HttpSession session = req.getSession();
	     Teacher teacher = (Teacher) session.getAttribute("user");

	     if (teacher == null || teacher.getSchool() == null) {
	         resp.sendRedirect(req.getContextPath() + "/Login/LOGI001.jsp");
	         return;
	     }
			// --- 情報の取得 ---

			InitialContext ic=new InitialContext();
			DataSource ds=(DataSource)ic.lookup(
				"java:/comp/env/jdbc/JavaSDDB");
			Connection con=ds.getConnection();


			PreparedStatement st1=con.prepareStatement(
					"SELECT SCHOOL_CD FROM TEACHER WHERE ID=?");
					 st1.setString(1, teacher.getId());
					 ResultSet rs1=st1.executeQuery();
					 if (rs1.next()) {
					 System.out.println(rs1);
					 }

        // フォワード：ブラウザのURLは変わらず、サーバー内部でページを切り替える
        req.getRequestDispatcher("/Subject/SBJM002.jsp").forward(req, resp);}


    @Override
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    	}}



