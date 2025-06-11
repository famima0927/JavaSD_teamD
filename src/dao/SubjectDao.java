package dao;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

//bean名前違ったら変えて～
import bean.School;

//ここのエラー分からん      ・小森谷
import bean.Subject;



public class SubjectDao extends Dao {


	//学生の一人の成績を検索して
	public Subject get(String cd,School school) throws Exception{
		Subject subject = new Subject();

		//引数をもとにデータベースから科目(subject)を返す関数

		return subject;
	}

	//schoolをもとに科目の一覧を返す
	public List<Subject> filter(School school) throws Exception{
	    List<Subject> list = new ArrayList<>();

		return list;
	}


	//科目(subject)の保存を行う
	public boolean save(Subject subject) throws Exception{
		boolean isSave = false;

		return isSave;
	}


	public boolean delete(Subject suvject) throws Exception{
		boolean isSave = false;

		return isSave;
	}
}

