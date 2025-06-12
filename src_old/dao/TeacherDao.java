package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Teacher;

public class TeacherDao extends Dao {

    /**
     * IDを指定して教員情報を1件取得します。
     * パスワードは検証しません。
     *
     * @param id 教員ID
     * @return Teacher 教員オブジェクト。存在しない場合はnull。
     * @throws Exception
     */
    public Teacher get(String id) throws Exception {
        Teacher teacher = null;
        SchoolDao schoolDao = new SchoolDao();

        // データベースへの接続を取得
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("SELECT * FROM TEACHER WHERE ID = ?")) {

            st.setString(1, id);

            // SQLを実行
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    teacher = new Teacher();
                    teacher.setId(rs.getString("ID"));
                    // パスワードはセキュリティのため通常セットしない
                    teacher.setName(rs.getString("NAME"));

                    // 学校コードからSchoolオブジェクトを取得してセット
                    School school = schoolDao.get(rs.getString("SCHOOL_CD"));
                    teacher.setSchool(school);
                }
            }
        } catch (Exception e) {
            // エラーが発生した場合は上位にスロー
            throw e;
        }
        return teacher;
    }

    /**
     * IDとパスワードを使用してログイン認証を行います。
     *
     * @param id 教員ID
     * @param password パスワード
     * @return Teacher 認証成功時に教員オブジェクトを返す。失敗時はnull。
     * @throws Exception
     */
    public Teacher login(String id, String password) throws Exception {
        Teacher teacher = null;
        SchoolDao schoolDao = new SchoolDao();

        // データベースへの接続を取得
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(
                 "SELECT * FROM TEACHER WHERE ID = ? AND PASSWORD = ?")) {

            st.setString(1, id);
            st.setString(2, password);

            // SQLを実行
            try (ResultSet rs = st.executeQuery()) {
                // レコードが存在すれば認証成功
                if (rs.next()) {
                    // 戻り値用のTeacherオブジェクトを生成
                    teacher = new Teacher();
                    teacher.setId(rs.getString("ID"));
                    teacher.setName(rs.getString("NAME"));

                    // 学校コードからSchoolオブジェクトを取得してセット
                    School school = schoolDao.get(rs.getString("SCHOOL_CD"));
                    teacher.setSchool(school);
                }
            }
        } catch (Exception e) {
            // エラーが発生した場合は上位にスロー
            throw e;
        }
        return teacher;
    }
}