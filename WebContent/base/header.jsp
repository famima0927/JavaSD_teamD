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
      ようこそ、<%= session.getAttribute("userName") != null ? session.getAttribute("userName") : "ゲスト" %> さん
      <a href="<%= request.getContextPath() %>/logout" class="logout-link">ログアウト</a>
    </div>
  </header>