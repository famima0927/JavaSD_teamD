package dao;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

import bean.shool
import bean.subject



public class SubjectDao extends Dao {


	//学生の一人の成績を検索して
	public subject get(String cd,school school) throws Exception{
		subject subject = new subject;
		
		//引数をもとにデータベースから科目(subject)を返す関数

		return subject;
	}


	public List<Subject> filter(school school) throws Exception{
	    List<Subject> list = new ArrayList<>();

		return list;
	}


	public boolean save(subject subject){

	}


	public boolean delete(subject suvject) throws Exception{

	}
}

