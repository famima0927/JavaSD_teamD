<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
  request.setAttribute("bodyClass", "login-body");
%>
<jsp:include page="../base/header.jsp"></jsp:include>
	<div class = "container">
		<div class = "side-bar">
			<jsp:include page="../base/base.jsp"></jsp:include>
		</div>
		<div class = "main">
		    <div class="login-container">
		        <h2>ログイン</h2>

			        <% String error = (String) request.getAttribute("errorMessage"); %>
					<% if (error != null) { %>
					    <div class="error-message"><%= error %></div>
					<% } %>


					<!-- LoginServlet名前変更予定 -->
		        <form action="LoginServlet" method="post">
		            <div class="form-group">
		                <input type="text" name="username" value="admin" placeholder="ID">
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
		</div>
	</div>
<jsp:include page="../base/footer.html"></jsp:include>