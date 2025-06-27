<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
  request.setAttribute("bodyClass", "login-body");
%>
<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
    <jsp:include page="../base/header.jsp" />
</div>

		    <div class="login-container">
		        <h2>ログイン</h2>

					<!-- エラー処理 -->
			        <% if ("true".equals(request.getParameter("error"))) { %>
   					 <div class="error-message">IDまたはパスワードが間違っています。</div>
					<% } %>


					<!-- LoginServlet名前変更予定e -->
		        <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
		            <div class="form-group">
		                <input type="text" name="id" value="admin" placeholder="ID">
		            </div>
		            <div class="form-group">
		                <input type="password" name="password" value="*****" placeholder="パスワード">
		            </div>
		            <div class="checkbox-group">
		                <input type="checkbox" id="showPassword">
		                <label for="showPassword">パスワードを表示</label>
		            </div>
		            <button type="submit" class="login-button">ログイン</button>
		        </form>
		    </div>

		    <script>
		        // パスワード表示切り替え
		        document.getElementById("showPassword").addEventListener("change", function() {
		            const passwordField = document.querySelector('input[name="password"]');
		            passwordField.type = this.checked ? "text" : "password";
		        });
		    </script>

<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
    <jsp:include page="../base/footer.jsp" />
</div>