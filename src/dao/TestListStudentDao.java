package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {
	public String baseSql = "";
	private List<TestListStudent> postFilter(ResultSet rSet)throws Exception{
		List<TestListStudent> list = new ArrayList<TestListStudent>();

		return list;
	}

	public List<TestListStudent> filter(Student student)throws Exception{
		List<TestListStudent> list = new ArrayList<TestListStudent>();

		return list;
	}
}
