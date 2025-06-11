<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>得点管理システム</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/base/css/styles.css">
</head>
<body class="<%= request.getAttribute("bodyClass") != null ? request.getAttribute("bodyClass") : "" %>">

  <header class="header">
    <div class="header-title">得点管理システム</div>
    <div class="header-user">
      <%-- ログインユーザー名の表示 --%>
      	<%
		    HttpSession UserSession = request.getSession(false); // セッションがあれば取得、なければnull
		    String teacherID = null;
		    if (session != null) {
		        teacherID = (String) session.getAttribute("teacherID");
		    }
		%>

		<% if (teacherID != null) { %>
		    <!-- ログインしている場合の表示 -->
		    <p>ようこそ、<%= teacherID %> さん</p>
		    <a href="<%= request.getContextPath() %>/LogoutServlet" class="logout-link">ログアウト</a>
		<% } else { %>
		    <!-- ログインしていない場合の表示 -->
		<% } %>

    </div>
  </header>