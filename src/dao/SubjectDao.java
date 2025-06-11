package dao;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD


=======
>>>>>>> branch 'master' of https://github.com/famima0927/JavaSD_teamD.git
//bean名前違ったら変えて～
import bean.School;
import bean.Subject;



public class SubjectDao extends Dao {


	//学生の一人の成績を検索して
//<<<<<<< HEAD
	public Subject get(String cd,School school) throws Exception{
		Subject subject = new subject;
//=======
	public Subject get(String cd,School school) throws Exception{
		Subject subject = new Subject();
//>>>>>>> branch 'master' of https://github.com/famima0927/JavaSD_teamD.git

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

