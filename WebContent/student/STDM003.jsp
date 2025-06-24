<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    request.setAttribute("bodyClass", "menu-body");
%>

<jsp:include page="../base/header.jsp"></jsp:include>
<link rel="stylesheet" href="<c:url value='/css/style.css' />">
<div class="container-fluid">
    <div class="row">
        <%-- サイドバをインクルード --%>
        <%-- この testtest.jsp の中身が <div class="col-md-2 ..."> で始まっている想定です --%>
        <jsp:include page="../base/base.jsp" />

        <%-- ★★★ 修正点: mainクラスに col-md-10 を追加 ★★★ --%>
        <div class="main col-md-10 py-4">
            <div class="bg-light border px-3 py-2 mb-3 fw-bold">学生情報登録</div>


        <%-- 完了メッセージ --%>
        <div class="success-message">
            登録が完了しました。
        </div>

        <div class="actions">
            <%-- リンクをボタン風に表示 --%>
            <a href="<c:url value='/StudentCreate' />" class="btn btn-primary">続けて登録する</a>
            <a href="<c:url value='/StudentList' />" class="btn btn-secondary">学生一覧へ</a>
        </div>
    </div>
</div>

<jsp:include page="../base/footer.html"></jsp:include>