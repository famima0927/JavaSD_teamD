package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao {

    // フィルター検索の基本SQL（WHERE SCHOOL_CD = ?）
    public String baseSql = "SELECT * FROM STUDENT WHERE SCHOOL_CD = ?";

    /**
     * [参照] 学生番号を指定して学生情報を1件取得する
     * (学生変更時のデータ表示や、各種存在チェックで使用)
     * @param no: String 学生番号
     * @return Student 学生オブジェクト。存在しない場合はnull。
     * @throws Exception
     */
    public Student get(String no) throws Exception {
        Student student = null;
        SchoolDao schoolDao = new SchoolDao();

        String sql = "SELECT * FROM STUDENT WHERE NO = ?";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, no);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    student = new Student();
                    student.setNo(rs.getString("NO"));
                    student.setName(rs.getString("NAME"));
                    student.setEntYear(rs.getInt("ENT_YEAR"));
                    student.setClassNum(rs.getString("CLASS_NUM"));
                    student.setIsAttend(rs.getInt("IS_ATTEND") == 1);

                    School school = schoolDao.get(rs.getString("SCHOOL_CD"));
                    student.setSchool(school);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return student;
    }

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
