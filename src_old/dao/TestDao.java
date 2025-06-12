package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

	public String baseSql="";

	public Test get(Student student,Subject subject ,School school ,int no){
		Test test = new Test();

		return test;
	}

	public List<Test> postFilter(ResultSet rSet,School school){
		List<Test> list = new ArrayList<Test>();

		return list;
	}

	public List<Test> Filter(int entYear ,String classNum ,Subject subject ,int num ,School school){
		List<Test> list = new ArrayList<Test>();

		return list;
	}

	public boolean save(List<Test> list){
		boolean isSave = false;

		return isSave;

	}

	private boolean save(Test test ,Connection connection){
		boolean isSave = false;

		return isSave;

	}

	public boolean Delete(List<Test> list){
		boolean isDelete = false;

		return isDelete;

	}

	private boolean Delete(Test test ,Connection connection){
		boolean isDelete = false;

		return isDelete;

	}
}
