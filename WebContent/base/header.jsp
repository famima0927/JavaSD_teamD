<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>得点管理システム</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/base/css/styles.css">
	<!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body class="<%= request.getAttribute("bodyClass") != null ? request.getAttribute("bodyClass") : "" %> ">

  <header class="header">
    <h2 class="px-3">得点管理システム</h2>
	    <div class="header-user">
	  <%-- ログインユーザー名の表示 --%>
	  <%
	    HttpSession UserSession = request.getSession(false);
	    String teacherID = null;
	    if (session != null) {
	        teacherID = (String) session.getAttribute("teacherName");
	    }
	  %>

	  <% if (teacherID != null) { %>
	    <!-- ログインしている場合の表示 -->
	    <span>ようこそ、<%= teacherID %> さん</span>
	    <a href="<%= request.getContextPath() %>/LogoutServlet" class="logout-link">ログアウト</a>
	  <% } else { %>
	    <!-- ログインしていない場合の表示 -->
	  <% } %>
	</div>
  </header>