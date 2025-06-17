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
			<div class="menu-header">学生情報登録</div>
			<%-- ここに処理を書き込む --%>
			<div class="success">登録が完了しました。</div>

					<a href="${pageContext.request.contextPath}/StudentCreate.action">戻る</a><%--学生登録画面に戻る --%>
					<a href="${pageContext.request.contextPath}/StudentList">学生一覧</a><%--学生一覧画面に移動 --%>

		</div>
	</div>
<jsp:include page="../base/footer.html"></jsp:include>