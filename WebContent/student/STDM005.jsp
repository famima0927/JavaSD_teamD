<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    request.setAttribute("bodyClass", "menu-body");
%>

<%-- ★★★ 修正点：includeのパスを絶対パスに変更 ★★★ --%>
<jsp:include page="/base/header.jsp"></jsp:include>
<link rel="stylesheet" href="<c:url value='/css/style.css' />">

<div class="container">
    <div class="side-bar">
        <jsp:include page="/base/base.jsp"></jsp:include>
    </div>
    <div class="main">
        <%-- ★★★ 修正点：タイトルをh2タグに変更 ★★★ --%>
        <h2>学生情報変更</h2>

        <%-- ★★★ 修正点：他のページとクラス名を統一 ★★★ --%>
        <div class="success-message">
            変更が完了しました。
        </div>

        <div class="actions">
            <%-- ★★★ 修正点：リンク先を修正し、ボタン風の表示に変更 ★★★ --%>
            <a href="<c:url value='/StudentList' />" class="btn btn-secondary">学生一覧へ</a>
        </div>
    </div>
</div>

<jsp:include page="/base/footer.html"></jsp:include>