package dao;

import bean.School;

public class SchoolDao {
	//StudentDaoに学校の情報などを継承するためのdao
	public School get(String no)throws Exception{
		School school = new School();

		return school;
	}
}
