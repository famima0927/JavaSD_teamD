package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {

    /**
     * [private] ResultSetをTestListStudentのリストに変換するヘルパーメソッド
     * @param rSet: ResultSet SQLの実行結果
     * @return List<TestListStudent> 学生の成績リスト
     * @throws Exception
     */
    private List<TestListStudent> postFilter(ResultSet rSet) throws Exception {
        List<TestListStudent> list = new ArrayList<>();
        try {
            // ResultSetをループしてリストを作成
            while (rSet.next()) {
                TestListStudent tls = new TestListStudent();
                // 取得した科目名、科目コード、回数、得点をセット
                tls.setSubjectName(rSet.getString("subject_name"));
                tls.setSubjectCd(rSet.getString("subject_cd"));
                tls.setNum(rSet.getInt("no"));
                tls.setPoint(rSet.getInt("point"));
                list.add(tls);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return list;
    }

    /**
     * [検索] 学生を指定して、その学生の成績一覧を取得する
     * @param student: Student 対象の学生オブジェクト
     * @return List<TestListStudent> 学生の成績リスト
     * @throws Exception
     */
    public List<TestListStudent> filter(Student student) throws Exception {
        List<TestListStudent> list = new ArrayList<>();

        // TESTテーブルとSUBJECTテーブルを結合し、必要な情報を取得するSQL
        String sql = "SELECT s.NAME AS subject_name, t.SUBJECT_CD, t.NO, t.POINT " +
                     "FROM TEST t " +
                     "JOIN SUBJECT s ON t.SUBJECT_CD = s.CD AND t.SCHOOL_CD = s.SCHOOL_CD " +
                     "WHERE t.STUDENT_NO = ? " +
                     "ORDER BY t.SUBJECT_CD ASC, t.NO ASC";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            // パラメータに学生番号をセット
            st.setString(1, student.getNo());

            try (ResultSet rs = st.executeQuery()) {
                // postFilterヘルパーを呼び出してリスト化
                list = postFilter(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return list;
    }
}