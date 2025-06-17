package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

    /**
     * [private] ResultSetをTestListSubjectのリストに変換するヘルパーメソッド。
     * 複数の成績レコードを学生一人に集約する処理も行う。
     * @param rSet: ResultSet SQLの実行結果
     * @return List<TestListSubject> 科目別の成績リスト
     * @throws Exception
     */
	private List<TestListSubject> postFilter(ResultSet rSet) throws Exception {
	    Map<String, TestListSubject> map = new HashMap<>();
	    List<TestListSubject> list = new ArrayList<>();

	    System.out.println("--- DAO: postFilter 開始 ---"); // デバッグ開始

	    try {
	        int rowNum = 1;
	        while (rSet.next()) {
	            System.out.println("--- " + (rowNum++) + "行目のデータ ---"); // 何行目のデータか表示

	            String studentNo = rSet.getString("student_no");
	            System.out.println("student_no: " + studentNo);

	            // pointカラムの実際の値（生データ）を表示
	            Object pointObj = rSet.getObject("point");
	            System.out.println("point (Object): " + pointObj);

	            // test_noカラムの実際の値（生データ）を表示
	            Object testNoObj = rSet.getObject("test_no");
	            System.out.println("test_no (Object): " + testNoObj);


	            TestListSubject tls = map.get(studentNo);
	            if (tls == null) {
	                tls = new TestListSubject();
	                tls.setEntYear(rSet.getInt("ent_year"));
	                tls.setStudentNo(studentNo);
	                tls.setStudentName(rSet.getString("student_name"));
	                tls.setClassNum(rSet.getString("class_num"));
	                map.put(studentNo, tls);
	                list.add(tls);
	                System.out.println("   -> 新しい学生オブジェクトを作成しました。");
	            }

	            if (pointObj != null) {
	                System.out.println("   -> 点数が見つかりました。putPointを呼び出します。");
	                tls.putPoint(rSet.getInt("test_no"), rSet.getInt("point"));
	            } else {
	                System.out.println("   -> 点数が見つかりませんでした。putPointは呼び出しません。");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    }

	    System.out.println("--- DAO: postFilter 終了 ---"); // デバッグ終了
	    return list;
	}

    /**
     * [検索] 学生の所属情報と科目を指定して、クラスの成績一覧を取得する
     * @param entYear: int 入学年度
     * @param classNum: String クラス番号
     * @param subject: Subject 科目オブジェクト
     * @param school: School 学校オブジェクト
     * @return List<TestListSubject>
     * @throws Exception
     */
    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
        List<TestListSubject> list = new ArrayList<>();

        // STUDENTテーブルを主軸にTESTテーブルをLEFT JOINする
        // これにより、まだ点数が登録されていない学生も一覧に表示される
        String sql = "SELECT s.ENT_YEAR, s.NO AS student_no, s.NAME AS student_name, s.CLASS_NUM, t.NO AS test_no, t.POINT " +
                     "FROM STUDENT s " +
                     "LEFT JOIN TEST t ON s.NO = t.STUDENT_NO AND t.SUBJECT_CD = ? AND t.SCHOOL_CD = ? " +
                     "WHERE s.ENT_YEAR = ? AND s.CLASS_NUM = ? AND s.SCHOOL_CD = ? " +
                     "ORDER BY s.NO";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            // パラメータをセット
            st.setString(1, subject.getCd());
            st.setString(2, school.getCd());
            st.setInt(3, entYear);
            st.setString(4, classNum);
            st.setString(5, school.getCd());

            try (ResultSet rs = st.executeQuery()) {
                list = postFilter(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return list;
    }
}