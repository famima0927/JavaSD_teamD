package tool; // パッケージ名はプロジェクトに合わせてください

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// "*.controller"というURLパターンのリクエストをすべてこのサーブレットで受け取る
@WebServlet(urlPatterns={"*.controller"})
public class FrontController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            // URLから対応するControllerクラスを特定する
            String path = request.getServletPath().substring(1); // 例: "/Login.Controller" -> "Login.Controller"
            String name = path.replace(".c", "C").replace("ontroller", ""); // 例: "Login.Controller" -> "LoginController"

            // Controllerクラスのフルネームからインスタンスを生成
            // 例: "Controller.LoginController"
            Controller Controller = (Controller)Class.forName("Controller." + name).newInstance();

            // 特定したControllerクラスのexecuteメソッドを実行
            Controller.execute(request, response);

        } catch (Exception e) {
            // エラーハンドリング
            e.printStackTrace();
            // エラーページにフォワード
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doPost(request, response); // GETリクエストもPOSTと同じ処理を行う
    }
}