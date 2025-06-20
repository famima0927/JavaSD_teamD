<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("bodyClass", "login-body");
%>
<jsp:include page="../base/header.jsp"></jsp:include>
<div class = "allpad">
<%-- 修正点1: 画面上部に余白(py-5)を持たせ、フォームが下に寄りすぎないように調整 --%>
<div class="container-fluid bg-white d-flex justify-content-center py-5">

    <%-- 修正点2: カードの最大幅を広げ(max-width: 420px)、入力欄を横長にする --%>
    <div class="card shadow-sm" style="max-width: 420px; width: 100%;">
        <div class="card-header text-center fw-bold bg-light">ログイン</div>

        <div class="card-body p-4">
            <!-- エラー表示 (変更なし) -->
            <% if ("true".equals(request.getParameter("error"))) { %>
                <div class="alert alert-danger text-center py-2" role="alert">
                    IDまたはパスワードが間違っています。
                </div>
            <% } %>

            <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
                <!-- ID欄 (変更なし) -->
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="idField" name="id" value="admin" placeholder="ID">
                    <label for="idField">ID</label>
                </div>

                <!-- パスワード欄 (変更なし) -->
                <div class="form-floating mb-3">
                    <input type="password" class="form-control" id="passwordField" name="password" value="*****" placeholder="パスワード">
                    <label for="passwordField">パスワード</label>
                </div>

                <%-- 修正点3: チェックボックスを中央揃え --%>
                <div class="text-center mb-3">
                    <div class="form-check d-inline-flex align-items-center">
                        <input class="form-check-input me-2" type="checkbox" id="showCredentials">
                        <label class="form-check-label" for="showCredentials">ID・パスワードを表示</label>
                    </div>
                </div>

                <%-- 修正点3: ログインボタンを中央揃え --%>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary rounded px-5">ログイン</button>
                </div>
            </form>
        </div>
    </div>
</div>
</div>

<!-- パスワード・ID表示切替 (変更なし) -->
<script>
    document.getElementById("showCredentials").addEventListener("change", function () {
        const idField = document.getElementById("idField");
        const passwordField = document.getElementById("passwordField");
        const show = this.checked;

        // IDは常にtextですが、念のため切替に対応
        // idField.type = show ? "text" : "text";
        passwordField.type = show ? "text" : "password";
    });
</script>

<jsp:include page="../base/footer.html"></jsp:include>