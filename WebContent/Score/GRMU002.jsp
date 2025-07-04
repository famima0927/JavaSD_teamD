<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%
  request.setAttribute("bodyClass", "ここにボディ名を書き込む");
%>

<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
    <jsp:include page="../base/header.jsp" />
</div>

<div class = "allpad">
<div class="container-fluid">
    <div class="row">
        <%-- サイドバをインクルード --%>
        <%-- この testtest.jsp の中身が <div class="col-md-2 ..."> で始まっている想定です --%>
        <jsp:include page="../base/base.jsp" />

        <%-- ★★★ 修正点: mainクラスに col-md-10 を追加 ★★★ --%>
        <div class="main col-md-10 py-4">
            <div class="bg-light border px-3 py-2 mb-3 fw-bold fs-5">成績参照</div>
			<%-- ここに処理を書き込む --%>
			<%-- 登録完了メッセージをBootstrapのAlertで表示 --%>
			<div class="card shadow-sm text-center border-0 mb-4" style="width: auto; background-color: #cde3cd;">
				登録が完了しました。
			</div>

		<a href="${pageContext.request.contextPath}/servlet/TestRegistController" class="me-3">戻る</a><%--成績登録画面に戻る --%>
		<a href="${pageContext.request.contextPath}/servlet/TestListController">成績参照</a><%--成績参照画面に移動 --%>

		</div>
	</div>
</div>
</div>
<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
    <jsp:include page="../base/footer.jsp" />
</div>