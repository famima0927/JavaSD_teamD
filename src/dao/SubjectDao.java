package dao;

import java.util.ArrayList;
import java.util.List;

//<<<<<<< HEAD
<<<<<<< HEAD
//
//
//=======
//>>>>>>> branch 'master' of https://github.com/famima0927/JavaSD_teamD.git
////bean名前違ったら変えて～
=======


//=======
//>>>>>>> branch 'master' of https://github.com/famima0927/JavaSD_teamD.git
//bean名前違ったら変えて～
>>>>>>> branch 'master' of https://github.com/famima0927/JavaSD_teamD.git
import bean.School;
import bean.Subject;



public class SubjectDao extends Dao {


<<<<<<< HEAD
=======
//<<<<<<< HEAD

//=======
	//学生の一人の成績を検索して
>>>>>>> branch 'master' of https://github.com/famima0927/JavaSD_teamD.git
//<<<<<<< HEAD
<<<<<<< HEAD
//
=======
//	public Subject get(String cd,School school) throws Exception{
//		Subject subject = new subject;
>>>>>>> branch 'master' of https://github.com/famima0927/JavaSD_teamD.git
//=======
<<<<<<< HEAD
//	//学生の一人の成績を検索して
////<<<<<<< HEAD
//	public Subject get(String cd,School school) throws Exception{
//		Subject subject = new subject;
////=======
=======
//>>>>>>> branch 'master' of https://github.com/famima0927/JavaSD_teamD.git
	public Subject get(String cd,School school) throws Exception{
		Subject subject = new Subject();
//<<<<<<< HEAD

//=======
>>>>>>> branch 'master' of https://github.com/famima0927/JavaSD_teamD.git
//>>>>>>> branch 'master' of https://github.com/famima0927/JavaSD_teamD.git
<<<<<<< HEAD
//	public Subject get(String cd,School school) throws Exception{
//		Subject subject = new Subject();
//<<<<<<< HEAD
//
//=======
////>>>>>>> branch 'master' of https://github.com/famima0927/JavaSD_teamD.git
//
//>>>>>>> branch 'master' of https://github.com/famima0927/JavaSD_teamD.git
//		//引数をもとにデータベースから科目(subject)を返す関数
//
//		return subject;
//	}
=======

//>>>>>>> branch 'master' of https://github.com/famima0927/JavaSD_teamD.git
		//引数をもとにデータベースから科目(subject)を返す関数

		return subject;
	}
>>>>>>> branch 'master' of https://github.com/famima0927/JavaSD_teamD.git

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

