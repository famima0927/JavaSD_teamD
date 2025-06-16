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

    private List<Student> postFilter(ResultSet rSet, School school) throws Exception {
        List<Student> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                Student student = new Student();
                student.setNo(rSet.getString("NO"));
                student.setName(rSet.getString("NAME"));
                student.setEntYear(rSet.getInt("ENT_YEAR"));
                student.setClassNum(rSet.getString("CLASS_NUM"));
                student.setIsAttend(rSet.getInt("IS_ATTEND") == 1);
                student.setSchool(school);
                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return list;
    }

    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
        List<Student> list = new ArrayList<>();
        List<Object> params = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT * FROM STUDENT WHERE SCHOOL_CD = ?");
        params.add(school.getCd());

        if (entYear > 0) {
            sql.append(" AND ENT_YEAR = ?");
            params.add(entYear);
        }

        if (classNum != null && !classNum.isEmpty()) {
            sql.append(" AND CLASS_NUM = ?");
            params.add(classNum);
        }

        if (isAttend) {
            sql.append(" AND IS_ATTEND = true");
        }

        sql.append(" ORDER BY NO ASC");

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                st.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = st.executeQuery()) {
                list = postFilter(rs, school);
            }
        }
        return list;
    }

    public boolean save(Student student) throws Exception {
        int count = 0;
        Connection con = getConnection();

        Student old = this.get(student.getNo());

        if (old == null) {
            // 学生が存在しない場合：INSERT
            String sql = "INSERT INTO STUDENT (NO, NAME, ENT_YEAR, CLASS_NUM, IS_ATTEND, SCHOOL_CD) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement st = con.prepareStatement(sql)) {
                st.setString(1, student.getNo());
                st.setString(2, student.getName());
                st.setInt(3, student.getEntYear());
                st.setString(4, student.getClassNum());
                st.setBoolean(5, student.getIsAttend()); // 正しいメソッド呼び出し
                st.setString(6, student.getSchool().getCd());
                count = st.executeUpdate();
            }
        } else {
            // 学生が存在する場合：UPDATE
            String sql = "UPDATE STUDENT SET NAME = ?, CLASS_NUM = ?, IS_ATTEND = ? WHERE NO = ?";
            try (PreparedStatement st = con.prepareStatement(sql)) {
                st.setString(1, student.getName());
                st.setString(2, student.getClassNum());
                st.setBoolean(3, student.getIsAttend()); // 正しいメソッド呼び出し
                st.setString(4, student.getNo());
                count = st.executeUpdate();
            }
        }

        if (con != null) {
            try {
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }

        return count > 0;
    }
}