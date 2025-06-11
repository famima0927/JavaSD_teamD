package dao;

//bean名前違うなら変えて～
 import bean.Teacher;

public class TeacherDao extends Dao{
	public Teacher get(String id)  throws Exception{
		Teacher teacher = new Teacher();

		return teacher;
	}

	public Teacher login(String id,String password)  throws Exception{
		Teacher teacher = new Teacher();

		return teacher;
	}
}
