package servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import dao.SchoolDao;
import dao.SubjectDao;
import tool.CommonServlet;
@WebServlet("/SubjectEditServlet")
public class SubjectEditServlet extends CommonServlet  {
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// --- 情報の取得 ---
	try {
		 String cd = req.getParameter("cd");

		    SchoolDao schD = new SchoolDao();
		    SubjectDao subD = new SubjectDao();
		    School sch = schD.get("oom"); // 学校IDを固定で取得

		    req.setAttribute("subject", subD.get(cd, sch));

		    req.getRequestDispatcher("/Subject/SBJM004.jsp").forward(req, resp);

	 } catch (Exception e) {
		   //エラー時
			e.printStackTrace();}
}


@Override
protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	// TODO 自動生成されたメソッド・スタブ

}
}
