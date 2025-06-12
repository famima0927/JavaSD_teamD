package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;

public class SchoolDao extends Dao{

	/***
     * 学校コード（cd）をもとに SCHOOL テーブルから1件取得し、Schoolオブジェクトとして返す
     */
	public School get(String no)throws Exception{
		 School school = new School();

	        String sql = "SELECT * FROM SCHOOL WHERE CD = ?";
	        Connection con = getConnection();
	        try (PreparedStatement st = con.prepareStatement(sql)) {
	            st.setString(1, no);
	            try (ResultSet rs = st.executeQuery()) {
	                if (rs.next()) {
	                    school.setCd(rs.getString("CD"));
	                    school.setName(rs.getString("NAME"));
	                }
	            }
	        }

			return school;
	}
}
