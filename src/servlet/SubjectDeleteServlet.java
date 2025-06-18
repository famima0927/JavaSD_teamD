package servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import bean.School;
import dao.SchoolDao;
import dao.SubjectDao;
import tool.CommonServlet;
@WebServlet("/SubjectDeleteServlet")
public class SubjectDeleteServlet extends CommonServlet  {
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {

// --- 情報の取得 ---
	try {
		 String cd = req.getParameter("id");
		 System.out.println("cd = " + req.getParameter(cd));
		    SchoolDao schD = new SchoolDao();
		    SubjectDao subD = new SubjectDao();
		    School sch = schD.get("oom"); // 学校IDを固定で取得

		    req.setAttribute("subject", subD.get(cd, sch));
		    System.out.println("cd = " + req.getParameter("cd"));
			System.out.println("name = " + req.getParameter("name"));
		    req.getRequestDispatcher("/Subject/SBJM006.jsp").forward(req, resp);

	 } catch (Exception e) {
		   //エラー時
			e.printStackTrace();}
}


@Override
protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {

	try {
		// --- ① フォーム情報を取得 ---

		String CD=req.getParameter("cd");
		 SchoolDao schD = new SchoolDao();
		    SubjectDao subD = new SubjectDao();
		    School sch = schD.get("oom"); // 学校IDを固定で取得

		    req.setAttribute("subject", subD.get(CD, sch));



		System.out.println("cd = " + CD);

		// --- ② DBに登録 ---

		InitialContext ic=new InitialContext();
		DataSource ds=(DataSource)ic.lookup(
			"java:/comp/env/jdbc/JavaSDDB");
		Connection con=ds.getConnection();
		//SQL文の準備
		 PreparedStatement st = con.prepareStatement("DELETE FROM SUBJECT WHERE CD = ?");
		    st.setString(1,CD);

		    // SQL文の実行（DBから削除）
		    int num = st.executeUpdate();

		//DBとの接続を切る
		st.close();
		con.close();

		if (num >0) {
			// 正常削除メッセージをセット
			req.getRequestDispatcher("/Subject/SBJM007.jsp").forward(req, resp);


		} else {
			// 削除に失敗した場合のメッセージ
			req.setAttribute("error", "登録に失敗しました。");
		}

	     } catch (Exception e) {
	   //エラー時
		e.printStackTrace();
		req.setAttribute("error", "入力内容が不正です：" + e.getMessage());
		System.out.println("cd = " + req.getParameter("cd"));
		System.out.println("name = " + req.getParameter("name"));
		req.getRequestDispatcher("/Subject/SBJM006.jsp").forward(req, resp);
	}


// フォワード：ブラウザのURLは変わらず、サーバー内部でページを切り替える
req.getRequestDispatcher("/Subject/SBJM006.jsp").forward(req, resp);


}
}
