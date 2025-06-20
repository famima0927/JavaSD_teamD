<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    request.setAttribute("bodyClass", "menu-body");
%>

<jsp:include page="../base/header.jsp"></jsp:include>
<link rel="stylesheet" href="<c:url value='/css/style.css' />">

<div class="container">
    <div class="side-bar">
        <jsp:include page="../base/base.jsp"></jsp:include>
    </div>
    <div class="main">
        <h2>学生情報登録</h2>

        <%-- 完了メッセージ --%>
        <div class="success-message">
            登録が完了しました。
        </div>

        <div class="actions">
            <%-- リンクをボタン風に表示 --%>
            <a href="<c:url value='/StudentCreate.action' />" class="btn btn-primary">続けて登録する</a>
            <a href="<c:url value='/StudentList' />" class="btn btn-secondary">学生一覧へ</a>
        </div>
    </div>
</div>

<jsp:include page="../base/footer.html"></jsp:include>