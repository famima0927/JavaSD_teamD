package dao;

import java.util.ArrayList;
import java.util.List;

//bean名前違ったら変えて～
import bean.School;
import bean.Subject;



public class SubjectDao extends Dao {



	public Subject get(String cd,School school) throws Exception{
		Subject subject = new Subject();
		
		//引数をもとにデータベースから科目(subject)を返す関数

		return subject;
	}

	public List<Subject> filter(School school) throws Exception{
	    List<Subject> list = new ArrayList<>();

		return list;
	}


	public boolean save(Subject subject) throws Exception{
		boolean isSave = false;

		return isSave;
	}


	public boolean delete(Subject suvject) throws Exception{
		boolean isDelete = false;

		return isDelete;
	}
}

