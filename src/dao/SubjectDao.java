package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {

    /**
     * [参照] 学校と科目コードを指定して科目を1件取得する
     * @param cd: String 科目コード
     * @param school: School 学校オブジェクト
     * @return Subject 科目オブジェクト。存在しない場合はnull。
     * @throws Exception
     */
    public Subject get(String cd, School school) throws Exception {
        Subject subject = null;
        String sql = "SELECT * FROM SUBJECT WHERE SCHOOL_CD = ? AND CD = ?";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, school.getCd());
            st.setString(2, cd);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    subject = new Subject();
                    subject.setCd(rs.getString("CD"));
                    subject.setName(rs.getString("NAME"));
                    subject.setSchool(school); // 引数の学校オブジェクトをセット
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return subject;
    }

    /**
     * [検索] 学校を指定して、その学校の科目一覧を取得する
     * @param school: School 学校オブジェクト
     * @return List<Subject> 科目リスト
     * @throws Exception
     */
    public List<Subject> filter(School school) throws Exception {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM SUBJECT WHERE SCHOOL_CD = ? ORDER BY CD ASC";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, school.getCd());

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Subject subject = new Subject();
                    subject.setCd(rs.getString("CD"));
                    subject.setName(rs.getString("NAME"));
                    subject.setSchool(school); // 引数の学校オブジェクトをセット
                    list.add(subject);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    /**
     * [登録/更新] 科目情報を保存する
     * @param subject: Subject 保存する科目オブジェクト
     * @return boolean 成功した場合true
     * @throws Exception
     */
    public boolean save(Subject subject) throws Exception {
        int count = 0;
        // 既存の科目をチェック
        Subject old = get(subject.getCd(), subject.getSchool());

        if (old == null) {
            // ----- 科目が存在しない場合：INSERT -----
            String sql = "INSERT INTO SUBJECT (SCHOOL_CD, CD, NAME) VALUES (?, ?, ?)";
            try (Connection con = getConnection();
                 PreparedStatement st = con.prepareStatement(sql)) {

                st.setString(1, subject.getSchool().getCd());
                st.setString(2, subject.getCd());
                st.setString(3, subject.getName());
                count = st.executeUpdate();
            }
        } else {
            // ----- 科目が存在する場合：UPDATE -----
            String sql = "UPDATE SUBJECT SET NAME = ? WHERE SCHOOL_CD = ? AND CD = ?";
            try (Connection con = getConnection();
                 PreparedStatement st = con.prepareStatement(sql)) {

                st.setString(1, subject.getName());
                st.setString(2, subject.getSchool().getCd());
                st.setString(3, subject.getCd());
                count = st.executeUpdate();
            }
        }
        return count > 0;
    }

    /**
     * [削除] 科目情報を削除する
     * @param subject: Subject 削除する科目オブジェクト
     * @return boolean 成功した場合true
     * @throws Exception
     */
    public boolean delete(Subject subject) throws Exception {
        int count = 0;
        String sql = "DELETE FROM SUBJECT WHERE SCHOOL_CD = ? AND CD = ?";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, subject.getSchool().getCd());
            st.setString(2, subject.getCd());
            count = st.executeUpdate();
        }
        return count > 0;
    }
}