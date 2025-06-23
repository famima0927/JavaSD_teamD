package servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import bean.Subject;
import bean.Teacher;
import tool.CommonServlet;
@WebServlet("/SubjectListController")
public class SubjectListController extends CommonServlet  {
@Override
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {

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

		PreparedStatement st=con.prepareStatement(
		"SELECT * FROM SUBJECT");
		ResultSet rs=st.executeQuery();
		List<Subject> subjects = new ArrayList<>();



		while (rs.next()) {
		    Subject subject = new Subject();
		    subject.setCd(rs.getString("cd"));
		    subject.setName(rs.getString("name"));

		    subjects.add(subject);}

		rs.close();
		st.close();
		con.close();

		// JSPへ渡す
		req.setAttribute("subjects", subjects);
		req.getRequestDispatcher("Subject/SBJM001.jsp").forward(req, resp);}


		   //エラー時


@Override
protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	// TODO 自動生成されたメソッド・スタブ

}
}
