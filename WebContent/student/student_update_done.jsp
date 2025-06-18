<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    request.setAttribute("bodyClass", "menu-body");
%>

<%-- ★★★ ここを修正 (絶対パスに変更) ★★★ --%>
<jsp:include page="/base/header.jsp"></jsp:include>

<link rel="stylesheet" href="<c:url value='/css/style.css' />">

<div class="container">
    <div class="side-bar">
        <%-- ★★★ ここを修正 (絶対パスに変更) ★★★ --%>
        <jsp:include page="/base/base.jsp"></jsp:include>
    </div>
    <div class="main">
        <h2>学生情報変更</h2>

        <div class="success-message">
            変更が完了しました
        </div>

        <a href="<c:url value='/StudentList' />">学生一覧</a>
    </div>
</div>

<%-- ★★★ ここを修正 (絶対パスに変更) ★★★ --%>
<jsp:include page="/base/footer.html"></jsp:include>