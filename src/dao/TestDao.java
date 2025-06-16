package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

	// privateなgetメソッドは、schoolも引数で受け取るように変更します。
    private Test get(Student student, Subject subject, School school, int no, Connection connection) throws Exception {
        Test test = null;
        String sql = "SELECT * FROM TEST WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND SCHOOL_CD = ? AND NO = ?";

        // ★★★ この try ブロックの中がエラー発生箇所です ★★★
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            // studentやsubjectがnullでないか念のため確認
            if (student == null || subject == null || school == null) {
                // もし、いずれかがnullなら、何もせずnullを返す（防御的プログラミング）
                return null;
            }
            st.setString(1, student.getNo());
            st.setString(2, subject.getCd());
            st.setString(3, school.getCd()); // 引数で受け取ったschoolオブジェクトを直接使います。
            st.setInt(4, no);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    test = new Test();
                    test.setStudent(student);
                    test.setSubject(subject);
                    test.setSchool(school);
                    test.setNo(no);
                    test.setPoint(rs.getInt("POINT"));
                    test.setClassNum(rs.getString("CLASS_NUM"));
                }
            }
        }
        return test;
    }

    public Test get(Student student, Subject subject, School school, int no) throws Exception {
        try (Connection con = getConnection()) {
            return get(student, subject, school, no, con);
        }
    }

    /**
     * [private] ResultSetをTestリストに変換するヘルパーメソッド
     */
    private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        StudentDao studentDao = new StudentDao();
        SubjectDao subjectDao = new SubjectDao();

        try {
            while (rSet.next()) {
                Test test = new Test();
                test.setSchool(school);
                // 学生、科目は各DAOから取得してセット
                test.setStudent(studentDao.get(rSet.getString("student_no")));
                test.setSubject(subjectDao.get(rSet.getString("subject_cd"), school));
                test.setNo(rSet.getInt("no"));
                test.setPoint(rSet.getInt("point"));
                test.setClassNum(rSet.getString("class_num"));
                list.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return list;
    }

    /**
     * [検索] 学生の所属情報と科目、回数を指定して成績データを検索する
     */
    public List<Test> filter(int entYear, String classNum, Subject subject, int no, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        String sql = "SELECT t.* FROM TEST t JOIN STUDENT s ON t.STUDENT_NO = s.NO " +
                     "WHERE s.ENT_YEAR = ? AND s.CLASS_NUM = ? AND t.SUBJECT_CD = ? AND t.NO = ? AND t.SCHOOL_CD = ? " +
                     "ORDER BY t.STUDENT_NO";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, entYear);
            st.setString(2, classNum);
            st.setString(3, subject.getCd());
            st.setInt(4, no);
            st.setString(5, school.getCd());

            try (ResultSet rs = st.executeQuery()) {
                list = postFilter(rs, school);
            }
        }
        return list;
    }

    public boolean save(List<Test> list) throws Exception {
        try (Connection con = getConnection()) {
            con.setAutoCommit(false);
            try {
                for (Test test : list) {
                    save(test, con);
                }
                con.commit();
                return true;
            } catch (Exception e) {
                con.rollback();
                throw e;
            }
        }
    }


    private boolean save(Test test, Connection connection) throws Exception {
        // testオブジェクトが持つschool情報をgetメソッドに渡します。
        Test old = get(test.getStudent(), test.getSubject(), test.getSchool(), test.getNo(), connection);
        int count = 0;

        if (old == null) {
            // INSERT
            String sql = "INSERT INTO TEST (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement st = connection.prepareStatement(sql)) {
                st.setString(1, test.getStudent().getNo());
                st.setString(2, test.getSubject().getCd());
                st.setString(3, test.getSchool().getCd());
                st.setInt(4, test.getNo());
                st.setInt(5, test.getPoint());
                st.setString(6, test.getStudent().getClassNum()); // ここもnullの可能性があるが、DBが許容すればエラーにならない
                count = st.executeUpdate();
            }
        } else {
            // UPDATE
            String sql = "UPDATE TEST SET POINT = ? WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND SCHOOL_CD = ? AND NO = ?";
            try (PreparedStatement st = connection.prepareStatement(sql)) {
                st.setInt(1, test.getPoint());
                st.setString(2, test.getStudent().getNo());
                st.setString(3, test.getSubject().getCd());
                st.setString(4, test.getSchool().getCd());
                st.setInt(5, test.getNo());
                count = st.executeUpdate();
            }
        }
        return count > 0;
    }


    /**
     * [削除] 複数の成績データをまとめて削除する（トランザクション処理）
     */
    public boolean delete(List<Test> list) throws Exception {
        try (Connection con = getConnection()) {
            con.setAutoCommit(false);
            try {
                for (Test test : list) {
                    delete(test, con);
                }
                con.commit();
                return true;
            } catch (Exception e) {
                con.rollback();
                e.printStackTrace();
                return false;
            }
        }
    }

    /**
     * [private] 1件の成績データを削除するヘルパーメソッド
     */
    private boolean delete(Test test, Connection connection) throws Exception {
        String sql = "DELETE FROM TEST WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND SCHOOL_CD = ? AND NO = ?";
        int count = 0;
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, test.getStudent().getNo());
            st.setString(2, test.getSubject().getCd());
            st.setString(3, test.getSchool().getCd());
            st.setInt(4, test.getNo());
            count = st.executeUpdate();
        }
        return count > 0;
    }
}