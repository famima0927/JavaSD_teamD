<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    request.setAttribute("bodyClass", "menu-body");
%>

<%-- ★★★ 修正点：includeのパスを絶対パスに変更 ★★★ --%>
<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
    <jsp:include page="../base/header.jsp" />
</div>

<link rel="stylesheet" href="<c:url value='/css/style.css' />">
<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
<div class="container-fluid">
    <div class="row">
        <%-- サイドバをインクルード --%>
        <%-- この testtest.jsp の中身が <div class="col-md-2 ..."> で始まっている想定です --%>
        <jsp:include page="../base/base.jsp" />

        <%-- ★★★ 修正点: mainクラスに col-md-10 を追加 ★★★ --%>
        <div class="main col-md-10 py-4">
      <div class="bg-light border px-3 py-2 mb-3 fw-bold">
        学生管理
      </div>
        <%-- ★★★ .修正点：他のページとクラス名を統一 ★★★ --%>
        <div class="success-message">
            変更が完了しました。
        </div>

        <div class="actions">
            <%-- ★★★ 修正点：リンク先を修正し、ボタン風の表示に変更 ★★★ --%>
            <a href="<c:url value='/StudentList' />" class="btn btn-secondary">学生一覧へ</a>
        </div>
    </div>
</div>
</div>
<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
    <jsp:include page="../base/header.jsp" />
</div>