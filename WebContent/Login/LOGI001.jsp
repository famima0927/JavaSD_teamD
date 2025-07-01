<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("bodyClass", "");
%>
<div class = "sukima">
<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
    <jsp:include page="../base/header.jsp" />
</div>

<div class="d-flex align-items-center justify-content-center" style="min-height:60vh;">

    <%--
      修正点2:
      - 元のコードのカード構造をそのまま使います。
      - `max-width`でフォームの最大幅を設計書に合わせて指定します。
      - `w-100`クラスで、小さな画面でも親要素に合わせて幅が広がるようにします。
    --%>
<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
    <div class="card shadow-sm" style="width: 500px">
        <div class="card-header text-center fw-bold bg-light">ログイン</div>

        <div class="card-body p-3">
            <!-- エラー表示 (変更なし) -->
            <% if ("true".equals(request.getParameter("error"))) { %>
                <div class="text-danger text-center mb-3">
                    IDまたはパスワードが間違っています。
                </div>
            <% } %>

            <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
                <!-- ID欄 (変更なしe) -->
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="idField" name="id" value="admin" placeholder="ID">
                    <label for="idField">ID</label>
                </div>

                <!-- パスワード欄 (変更なし) -->
                <div class="form-floating mb-3">
                    <input type="password" class="form-control" id="passwordField" name="password" placeholder="パスワード">
                    <label for="passwordField">パスワード</label>
                </div>

                <%-- チェックボックス (元のコードの中央揃えを維持) --%>
                <div class="text-center mb-3">
                    <div class="form-check d-inline-flex align-items-center">
                        <input class="form-check-input me-2" type="checkbox" id="showCredentials">
                        <label class="form-check-label" for="showCredentials">ID・パスワードを表示</label>
                    </div>
                </div>

                <%--
                  修正点3:
                  - ログインボタンを`d-grid`で囲むことで、横幅いっぱいに広がるモダンなボタンにします。
                    設計書に近づける場合は元の<div class="text-center">に戻してください。
                --%>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary px-5">ログイン</button>
                </div>
            </form>
        </div>
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

        passwordField.type = show ? "text" : "password";
    });
</script>

<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
    <jsp:include page="../base/footer.jsp" />
</div>
