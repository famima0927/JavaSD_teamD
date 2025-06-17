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
			<div class="success"><p>登録が完了しました。</p></div>

		<a href="${pageContext.request.contextPath}/servlet/TestRegistController">戻る</a><%--成績登録画面に戻る --%>
		<a href="${pageContext.request.contextPath}/servlet/TestListController">成績参照</a><%--成績参照画面に移動 --%>

		</div>
	</div>
<jsp:include page="../base/footer.html"></jsp:include>