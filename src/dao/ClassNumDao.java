package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;

public class ClassNumDao extends Dao {

    /**
     * 学校に所属するクラス番号の一覧を取得します。
     *
     * @param school: School 学校オブジェクト
     * @return List<String> クラス番号の文字列リスト。存在しない場合は空のリスト。
     * @throws Exception
     */
    public List<String> filter(School school) throws Exception {
        List<String> list = new ArrayList<>();

        // データベースへの接続を取得
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(
                 "SELECT CLASS_NUM FROM CLASS_NUM WHERE SCHOOL_CD = ? ORDER BY CLASS_NUM")) {

            // パラメータに学校コードをセット
            st.setString(1, school.getCd());

            // SQLを実行
            try (ResultSet rs = st.executeQuery()) {
                // 結果をループしてリストに追加
                while (rs.next()) {
                    list.add(rs.getString("CLASS_NUM"));
                }
            }
        } catch (Exception e) {
            // エラーが発生した場合は上位にスロー
            throw e;
        }

        return list;
    }
}