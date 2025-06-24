<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>得点管理システム</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/base/css/styles.css' />">
</head>
<%-- ★★ 修正点1: bodyタグにFlexboxクラスを設定 ★★★ --%>
<body class="d-flex flex-column vh-100 ${bodyClass}">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <%-- ★★★ 修正点2: ヘッダーの中身を.containerで囲む ★★★ --%>
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/main/MMNU001.jsp">得点管理システム</a>
        <div class="ms-auto">
            <ul class="navbar-nav">
                <c:if test="${not empty sessionScope.user}">
                    <li class="nav-item"><span class="navbar-text">ようこそ、${sessionScope.user.name} さん</span></li>
                    <li class="nav-item ms-3"><a href="<c:url value='/LogoutServlet' />" class="btn btn-outline-light btn-sm">ログアウト</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>

<%-- ★★★ 修正点3: メインコンテンツを囲む<main>タグを開始 ★★★ --%>
<main class="flex-grow-1">