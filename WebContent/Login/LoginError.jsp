<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
    <jsp:include page="../base/header.jsp" />
</div>
<div class = "allpad"><%
		    HttpSession UserLoginSession = request.getSession(false);
		    String errorMessage = null;
		    if (session != null) {
		        errorMessage = (String) UserLoginSession.getAttribute("loginError");
		        session.removeAttribute("loginError"); // 一度表示したら消すsi
		    }
			%>

			<% if (errorMessage != null) { %>
			    <div class="error-message"><%= errorMessage %></div>
			<% } %>
</div>
<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
    <jsp:include page="../base/footer.jsp" />
</div>