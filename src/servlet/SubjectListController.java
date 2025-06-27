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


		PreparedStatement st1=con.prepareStatement(
				"SELECT SCHOOL_CD FROM TEACHER WHERE ID=?");
				 st1.setString(1, teacher.getId());
				 ResultSet rs1=st1.executeQuery();
				 if (rs1.next()) {
				 System.out.println(rs1);
				 }
		PreparedStatement st2=con.prepareStatement(
		"SELECT * FROM SUBJECT WHERE SCHOOL_CD=?");
		st2.setString(1,rs1.getString("SCHOOL_CD"));

		ResultSet rs2=st2.executeQuery();
		List<Subject> subjects = new ArrayList<>();



		while (rs2.next()) {
		    Subject subject = new Subject();
		    subject.setCd(rs2.getString("cd"));
		    subject.setName(rs2.getString("name"));

		    subjects.add(subject);}

		rs1.close();
		st1.close();
		rs2.close();
		st2.close();
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
