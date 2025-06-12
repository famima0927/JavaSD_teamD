package tool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * すべてのActionクラスの親となる抽象クラス。
 * このクラスを継承するクラスは、executeメソッドを必ず実装する必要がある。
 */
public abstract class Action {

    /**
     * このメソッドは、各具象Actionクラス（例: LoginAction, TestListActionなど）で
     * 実際の処理を実装（オーバーライド）するためのもの。
     *
     * @param request : HttpServletRequest httpリクエスト
     * @param response : HttpServletResponse httpレスポンス
     * @throws Exception : 発生した例外
     */
    public abstract void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception;

}