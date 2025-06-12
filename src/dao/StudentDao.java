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
                    // DBのBOOLEAN型は getInt() == 1 で判定するのが安全
                    student.setIsAttend(rs.getInt("IS_ATTEND") == 1);

                    // 学校コードからSchoolオブジェクトを取得してセット
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

    /**
     * [private] ResultSetをStudentリストに変換するヘルパーメソッド
     * @param rSet: ResultSet SQLの実行結果
     * @param school: School 検索条件となった学校オブジェクト
     * @return List<Student> 学生リスト
     * @throws SQLException
     */
    private List<Student> postFilter(ResultSet rSet, School school) throws SQLException {
        List<Student> list = new ArrayList<>();
        try {
            // ResultSetをループして学生リストを作成
            while (rSet.next()) {
                Student student = new Student();
                student.setNo(rSet.getString("NO"));
                student.setName(rSet.getString("NAME"));
                student.setEntYear(rSet.getInt("ENT_YEAR"));
                student.setClassNum(rSet.getString("CLASS_NUM"));
                student.setIsAttend(rSet.getInt("IS_ATTEND") == 1);
                // 引数で受け取った学校オブジェクトをセット（DB再検索は不要）
                student.setSchool(school);
                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return list;
    }

    /**
     * [検索] 学校と在学状況で絞り込む
     * @param school: School
     * @param isAttend: boolean
     * @return List<Student>
     * @throws Exception
     */
    public List<Student> filter(School school, boolean isAttend) throws Exception {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM STUDENT WHERE SCHOOL_CD = ? AND IS_ATTEND = ?";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, school.getCd());
            st.setBoolean(2, isAttend);

            try (ResultSet rs = st.executeQuery()) {
                // postFilterヘルパーを呼び出してリスト化
                list = postFilter(rs, school);
            }
        }
        return list;
    }

    /**
     * [検索] 学校、入学年度、在学状況で絞り込む
     * @param school: School
     * @param entYear: int
     * @param isAttend: boolean
     * @return List<Student>
     * @throws Exception
     */
    public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM STUDENT WHERE SCHOOL_CD = ? AND ENT_YEAR = ? AND IS_ATTEND = ?";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, school.getCd());
            st.setInt(2, entYear);
            st.setBoolean(3, isAttend);

            try (ResultSet rs = st.executeQuery()) {
                list = postFilter(rs, school);
            }
        }
        return list;
    }

    /**
     * [検索] 学校、入学年度、クラス、在学状況で絞り込む
     * @param school: School
     * @param entYear: int
     * @param classNum: String
     * @param isAttend: boolean
     * @return List<Student>
     * @throws Exception
     */
    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM STUDENT WHERE SCHOOL_CD = ? AND ENT_YEAR = ? AND CLASS_NUM = ? AND IS_ATTEND = ?";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, school.getCd());
            st.setInt(2, entYear);
            st.setString(3, classNum);
            st.setBoolean(4, isAttend);

            try (ResultSet rs = st.executeQuery()) {
                list = postFilter(rs, school);
            }
        }
        return list;
    }

    /**
     * [登録/更新] 学生情報を保存する
     * 該当学生が存在しない場合はINSERT、存在する場合はUPDATEを実行
     * @param student: Student 保存する学生オブジェクト
     * @return boolean 成功した場合true
     * @throws Exception
     */
    public boolean save(Student student) throws Exception {
        int count = 0;

        // DBから既存の学生情報を取得
        Student old = get(student.getNo());

        if (old == null) {
            // ----- 学生が存在しない場合：INSERT -----
            String sql = "INSERT INTO STUDENT (NO, NAME, ENT_YEAR, CLASS_NUM, IS_ATTEND, SCHOOL_CD) VALUES (?, ?, ?, ?, ?, ?)";
            try (Connection con = getConnection();
                 PreparedStatement st = con.prepareStatement(sql)) {
                st.setString(1, student.getNo());
                st.setString(2, student.getName());
                st.setInt(3, student.getEntYear());
                st.setString(4, student.getClassNum());
                st.setBoolean(5, student.getIsAttend());
                st.setString(6, student.getSchool().getCd());
                count = st.executeUpdate();
            }
        } else {
            // ----- 学生が存在する場合：UPDATE -----
            String sql = "UPDATE STUDENT SET NAME = ?, ENT_YEAR = ?, CLASS_NUM = ?, IS_ATTEND = ? WHERE NO = ?";
            try (Connection con = getConnection();
                 PreparedStatement st = con.prepareStatement(sql)) {
                st.setString(1, student.getName());
                st.setInt(2, student.getEntYear());
                st.setString(3, student.getClassNum());
                st.setBoolean(4, student.getIsAttend());
                st.setString(5, student.getNo());
                count = st.executeUpdate();
            }
        }
        // 1件の登録/更新が成功したかを返す
        return count > 0;
    }
}