<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../base/header.jsp"></jsp:include>
				<%
		    HttpSession UserLoginSession = request.getSession(false);
		    String errorMessage = null;
		    if (session != null) {
		        errorMessage = (String) UserLoginSession.getAttribute("loginError");
		        session.removeAttribute("loginError"); // 一度表示したら消す
		    }
			%>

			<% if (errorMessage != null) { %>
			    <div class="error-message"><%= errorMessage %></div>
			<% } %>

<jsp:include page="../base/footer.html"></jsp:include>