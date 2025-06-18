<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Servlet/JSP Samples</title>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
	</head>
	<body>




		<a href = "${pageContext.request.contextPath}/main/MMNU001.jsp">メニュー</a><br><br>
		<a href = "${pageContext.request.contextPath}/StudentList">学生管理</a>
		<br>

		<p>成績管理</p>
		<ul>

			<li><a href = "${pageContext.request.contextPath}/servlet/TestRegistController">成績登録</a></li>
			<li><a href = "${pageContext.request.contextPath}/servlet/TestListController">成績参照</a></li>
		</ul>

		<a href="${pageContext.request.contextPath}/SubjectListController">科目管理</a>

	</body>
</html>


