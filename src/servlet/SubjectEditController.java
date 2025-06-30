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

import bean.School;
import bean.Teacher;
import dao.SchoolDao;
import dao.SubjectDao;
import tool.CommonServlet;
@WebServlet("/SubjectEdit")
public class SubjectEditController extends CommonServlet  {
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		 HttpSession session = req.getSession();
         Teacher teacher = (Teacher) session.getAttribute("user");
//DB接続、SCHOOL_CD取得
	try {
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
		 String ID=rs1.getString("SCHOOL_CD");//school_cd
		 String cd = req.getParameter("id");
		 System.out.println("cd = " + req.getParameter("cd"));
		    SchoolDao schD = new SchoolDao();
		    SubjectDao subD = new SubjectDao();
		    School sch = schD.get(ID); // 学校ID取得

		    req.setAttribute("subject", subD.get(cd, sch));
		    System.out.println("cd = " + req.getParameter("cd"));
		    req.getRequestDispatcher("/Subject/SBJM004.jsp").forward(req, resp);

	 } catch (Exception e) {
		   //エラー時
			e.printStackTrace();}
}


@Override
protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {



}
}
