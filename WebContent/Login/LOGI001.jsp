<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("bodyClass", "login-body");
%>
<jsp:include page="../base/header.jsp"></jsp:include>

<div class="container-fluid bg-white min-vh-100 d-flex justify-content-center align-items-center">
    <div class="card shadow-sm" style="width: 360px;">
        <!-- ① ログイン見出し（グレー背景） -->
        <div class="card-header text-center fw-bold bg-light">ログイン</div>

        <div class="card-body p-4">
            <!-- エラー表示 -->
            <% if ("true".equals(request.getParameter("error"))) { %>
                <div class="alert alert-danger text-center py-2" role="alert">
                    IDまたはパスワードが間違っています。
                </div>
            <% } %>

            <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
                <!-- ID欄（form-floatingで浮かぶラベル） -->
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="idField" name="id" value="admin" placeholder="ID">
                    <label for="idField">ID</label>
                </div>

                <!-- パスワード欄 -->
                <div class="form-floating mb-3">
                    <input type="password" class="form-control" id="passwordField" name="password" value="*****" placeholder="パスワード">
                    <label for="passwordField">パスワード</label>
                </div>

                <!-- ID・パスワード表示チェック -->
                <div class="form-check text-end mb-3">
                    <input class="form-check-input" type="checkbox" id="showCredentials">
                    <label class="form-check-label" for="showCredentials">ID・パスワードを表示</label>
                </div>

                <!-- ログインボタン -->
                <button type="submit" class="btn btn-primary w-100 rounded">ログイン</button>
            </form>
        </div>
    </div>
</div>

<!-- パスワード・ID表示切替 -->
<script>
    document.getElementById("showCredentials").addEventListener("change", function () {
        const idField = document.getElementById("idField");
        const passwordField = document.getElementById("passwordField");
        const show = this.checked;

        idField.type = show ? "text" : "text"; // IDは常にtextだが切替対応も可能
        passwordField.type = show ? "text" : "password";
    });
</script>

<jsp:include page="../base/footer.html"></jsp:include>
