package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao {

	//共通で使うSQLを入れておく変数(フィルター周りで使いそう？)
	public String baseSql="";


	private List<Student> postFilter(ResultSet rSet,School school){
		List<Student> list = new ArrayList<>();

		return list;
	}

	public List<Student> filter(School school,int entYear, String classNum, boolean isAttend) throws Exception{
		List<Student> list = new ArrayList<>();

		return list;
	}

	public List<Student> filter(School school,int entYear, boolean isAttend) throws Exception{
		List<Student> list = new ArrayList<>();

		return list;
	}

	public List<Student> filter(School school,boolean isAttend) throws Exception{
		List<Student> list = new ArrayList<>();

		return list;
	}

	public boolean save(Student student) throws Exception{
		boolean isSave = false;

		return isSave;
	}
}
