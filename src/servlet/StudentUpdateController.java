package servlet;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.CommonServlet;

/**
 * 学生情報変更ページを表示するためのサーブレット
 */
@WebServlet("/StudentUpdate.action")
// クラス名はご自身のファイル名に合わせてください (StudentUpdateServlet または StudentUpdateController)
public class StudentUpdateController extends CommonServlet {

    @Override
    public void get(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // DAOをインスタンス化
        StudentDao studentDao = new StudentDao();
        ClassNumDao classNumDao = new ClassNumDao();

        // 1. リクエストパラメータから学生番号を取得
        String no = request.getParameter("no");

        // 2. 学生番号を基にデータベースから学生情報を取得
        Student student = studentDao.get(no);

        // クラス番号の一覧を格納するリスト
        List<String> classNumSet = null;

        // 学生が見つかった場合のみ、クラス一覧を取得
        if (student != null) {
            classNumSet = classNumDao.filter(student.getSchool());
        } else {
            // 学生が見つからなかった場合（手動でURLを書き換えた場合など）
            request.setAttribute("error", "指定された学生は存在しません。");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        // 4. JSPに渡すために、取得した情報をリクエストスコープにセット
        request.setAttribute("student", student);
        request.setAttribute("class_num_set", classNumSet);

        // ★★★ 修正点：フォワード先を新しい統合JSPに変更 ★★★
        request.getRequestDispatcher("/student/STDM004.jsp").forward(request, response);


    }

    @Override
    public void post(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // このサーブレットはフォーム表示専用なので、POSTの処理は不要
    }
}