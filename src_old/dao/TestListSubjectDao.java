package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {
	public String baseSql = "";
	private List<TestListSubject> postFilter(ResultSet rSet)throws Exception{
		List<TestListSubject> list = new ArrayList<TestListSubject>();

		return list;
	}

	public List<TestListSubject> filter(int entYear,String classNum ,Subject subject,School school)throws Exception{
		List<TestListSubject> list = new ArrayList<TestListSubject>();

		return list;
	}
}
