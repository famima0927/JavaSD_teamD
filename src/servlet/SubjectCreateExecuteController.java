package servlet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import bean.Subject;
import bean.Teacher;
import tool.CommonServlet;

@WebServlet("/SubjectCreateExecute")
public class SubjectCreateExecuteController extends CommonServlet {

    /**
     * GETメソッドでアクセスされたときの処理。
     * 主にページ初期表示（例：リンククリックやURL直接入力）に使われる。
     */
    @Override
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    	}


    @Override
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    	 HttpSession session = req.getSession();
         Teacher teacher = (Teacher) session.getAttribute("user");
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
			PreparedStatement st1=con.prepareStatement(
					"SELECT SCHOOL_CD FROM TEACHER WHERE ID=?");
					 st1.setString(1, teacher.getId());
					 ResultSet rs1=st1.executeQuery();
					 if (rs1.next()) {
						 System.out.println(rs1);
					 }

			//SQL文の準備
			PreparedStatement st2 = con.prepareStatement("INSERT INTO SUBJECT VALUES (?,?,?)");
//school_idは必要に応じて追加

			String ID=rs1.getString("SCHOOL_CD");
			st2.setString(1, ID);
			st2.setString(2, Subject.getCd());
			st2.setString(3, Subject.getName());


			//SQL文の実行(DBの更新)
			int num = st2.executeUpdate();

			//DBとの接続を切る
			st2.close();
			con.close();

			if (num >0) {
				// 正常登録メッセージをセット
				req.getRequestDispatcher("/Subject/SBJM003.jsp").forward(req, resp);


			} else {
				// 学生登録に失敗した場合のメッセージ
				req.setAttribute("error", "登録に失敗しました。");
			}

		     } catch (Exception e) {
		   //エラー時
			e.printStackTrace();
			req.setAttribute("error","科目コードが重複しています");
			System.out.println("cd = " + req.getParameter("cd"));
			System.out.println("name = " + req.getParameter("name"));
			req.getRequestDispatcher("/Subject/SBJM002.jsp").forward(req, resp);
		}


    // フォワード：ブラウザのURLは変わらず、サーバー内部でページを切り替える
    req.getRequestDispatcher("/Subject/SBJM002.jsp").forward(req, resp);}}



