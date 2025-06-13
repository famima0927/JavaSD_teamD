package bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 科目別の成績一覧の1行分（学生一人分）のデータを格納するJavaBean
 */
public class TestListSubject implements Serializable {

    private int entYear;
    private String studentNo;
    private String studentName;
    private String classNum;

    // ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
    // ★★★ この行で必ず new HashMap<>() による初期化が必要です ★★★
    private Map<Integer, Integer> points = new HashMap<>();
    // ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★

    // --- 以下、各フィールドのゲッターとセッター ---

    public int getEntYear() {
        return entYear;
    }
    public void setEntYear(int entYear) {
        this.entYear = entYear;
    }
    public String getStudentNo() {
        return studentNo;
    }
    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public String getClassNum() {
        return classNum;
    }
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }
    public Map<Integer, Integer> getPoints() {
        return points;
    }
    public void setPoints(Map<Integer, Integer> points) {
        this.points = points;
    }

    // pointsに値を追加するための便利なメソッド
    public void putPoint(int key, int value) {
        this.points.put(key, value);
    }
}