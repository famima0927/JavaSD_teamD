package servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.CommonServlet;

// 正しいURLでマッピング
@WebServlet("/StudentUpdateExecute.action")
// クラス名を新しいファイル名に合わせる
public class StudentUpdateExecuteController extends CommonServlet {

    @Override
    public void post(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        request.setCharacterEncoding("UTF-8");

        StudentDao studentDao = new StudentDao();
        ClassNumDao classNumDao = new ClassNumDao();

        List<String> errors = new ArrayList<>();

        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String classNum = request.getParameter("class_num");
        boolean isAttend = request.getParameter("is_attend") != null;

        if (name == null || name.trim().isEmpty()) {
            errors.add("氏名を入力してください。");
        }

        if (!errors.isEmpty()) {
            // エラーがあった場合
            Student student = studentDao.get(no);
            student.setName(name);
            student.setClassNum(classNum);
            student.setIsAttend(isAttend);

            List<String> classNumSet = classNumDao.filter(student.getSchool());

            request.setAttribute("errors", errors);
            request.setAttribute("student", student);
            request.setAttribute("class_num_set", classNumSet);

            request.getRequestDispatcher("/student/student_update.jsp").forward(request, response);

        } else {
            // エラーがなければ更新
            Student studentToUpdate = new Student();
            studentToUpdate.setNo(no);
            studentToUpdate.setName(name);
            studentToUpdate.setClassNum(classNum);
            studentToUpdate.setIsAttend(isAttend);

            studentDao.save(studentToUpdate);

            response.sendRedirect("StudentUpdateDone.action");
        }
    }

    @Override
    public void get(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // GETリクエストは受け付けない（またはPOSTに処理を渡す）
    }
}