package servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import bean.Subject;
import tool.CommonServlet;
@WebServlet("/SubjectEditExecute")
public class SubjectEditExecuteController extends CommonServlet  {
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {


}


@Override
protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {

	try {
		// --- ① フォーム入力から学生情報を取得 ---
		Subject Subject = new Subject();
		Subject.setCd(req.getParameter("cd"));
		Subject.setName(req.getParameter("name"));
		System.out.println("cd = " + req.getParameter("cd"));
		System.out.println("name = " + req.getParameter("name"));


		// --- ② 学生情報をDBに登録 ---

		InitialContext ic=new InitialContext();
		DataSource ds=(DataSource)ic.lookup(
			"java:/comp/env/jdbc/JavaSDDB");
		Connection con=ds.getConnection();
		//SQL文の準備
		PreparedStatement st = con.prepareStatement("UPDATE SUBJECT SET NAME=? WHERE CD=?");
//school_idは必要に応じて追加
	    st.setString(1, Subject.getName());
		st.setString(2, Subject.getCd());

		//SQL文の実行(DBの更新)
		int num = st.executeUpdate();

		//DBとの接続を切る
		st.close();
		con.close();

		if (num >0) {
			// 正常登録メッセージをセット
			req.getRequestDispatcher("/Subject/SBJM005.jsp").forward(req, resp);


		} else {
			// 学生登録に失敗した場合のメッセージ
			req.setAttribute("error", "登録に失敗しました。");
		}

	     } catch (Exception e) {
	   //エラー時
		e.printStackTrace();
		req.setAttribute("error", "入力内容が不正です：" + e.getMessage());
		System.out.println("cd = " + req.getParameter("cd"));
		System.out.println("cd = " + req.getParameter("cd"));
		System.out.println("name = " + req.getParameter("name"));
		req.getRequestDispatcher("/Subject/SBJM004.jsp").forward(req, resp);
	}


// フォワード：ブラウザのURLは変わらず、サーバー内部でページを切り替える
req.getRequestDispatcher("/Subject/SBJM004.jsp").forward(req, resp);


}
}
