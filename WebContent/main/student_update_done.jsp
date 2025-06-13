<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    request.setAttribute("bodyClass", "menu-body");
%>

<%-- ヘッダーの読み込み --%>
<jsp:include page="../base/header.jsp"></jsp:include>

<%-- 外部CSSファイルを読み込む --%>
<link rel="stylesheet" href="<c:url value='/css/style.css' />">

<%-- <style> ブロックは style.css に移動したため削除 --%>

<div class="container">
    <div class="side-bar">
        <%-- サイドメニューの読み込み --%>
        <jsp:include page="../base/base.jsp"></jsp:include>
    </div>
    <div class="main">
        <%-- ① 画面タイトル --%>
        <h2>学生情報変更</h2>

        <%-- ② 完了メッセージ --%>
        <div class="success-message">
            変更が完了しました
        </div>

        <%-- ③ 学生一覧へのリンク (c:url を使用した推奨の書き方) --%>
        <a href="<c:url value='/StudentList' />">学生一覧</a>
    </div>
</div>

<%-- フッターの読み込み --%>
<jsp:include page="../base/footer.html"></jsp:include>