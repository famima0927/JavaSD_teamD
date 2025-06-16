<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  request.setAttribute("bodyClass", "menu-body");
%>

<jsp:include page="../base/header.jsp"></jsp:include>
	<div class = "container">
		<div class = "side-bar">
			<jsp:include page="../base/base.jsp"></jsp:include>
		</div>
		<div class = "main">
			<div class="menu-header">メニュー</div>
			  <div class="menu-container">
			    <div class="menu-box red">
			      <a href="${pageContext.request.contextPath}/StudentList">学生管理</a>

			    </div>
			    <div class="menu-box green">
			      <div>成績管理</div>


			      <a href="${pageContext.request.contextPath}/servlet/StudentRegister">成績登録</a><br>
			      <a href="${pageContext.request.contextPath}/servlet/TestListController">成績参照</a>

			    </div>
			    <div class="menu-box blue">
			      <a href="${pageContext.request.contextPath}/SubjectListServlet">科目管理</a>
			    </div>
			  </div>
		</div>
	</div>
<jsp:include page="../base/footer.html"></jsp:include>