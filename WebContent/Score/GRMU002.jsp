<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%
  request.setAttribute("bodyClass", "ここにボディ名を書き込む");
%>
<jsp:include page="../base/header.jsp"></jsp:include>
	<div class = "container">
		<div class = "side-bar">
			<jsp:include page="../base/base.jsp"></jsp:include>
		</div>
		<div class = "main">
		<div class="menu-header">成績管理</div>
			<%-- ここに処理を書き込む --%>
			<c:if test="${not empty success}">
				<p class="success">${success}</p>
			</c:if>
		<a href="${pageContext.request.contextPath}/Score/GRMU001.jsp">戻る</a><%--成績登録画面に戻る --%>
		<a href="${pageContext.request.contextPath}/Score/GRMR001.jsp">成績参照</a><%--成績参照画面に移動 --%>

		</div>
	</div>
<jsp:include page="../base/footer.html"></jsp:include>