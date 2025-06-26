<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
  request.setAttribute("bodyClass", "ここにボディ名を書き込む");
%>
<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
    <jsp:include page="../base/header.jsp" />
</div>

	<div class="container-fluid">
    <div class="row">
        <%-- サイドバをインクルード --%>
        <%-- この testtest.jsp の中身が <div class="col-md-2 ..."> で始まっている想定です --%>
        <jsp:include page="../base/base.jsp" />

        <%-- ★★★ 修正点: mainクラスに col-md-10 を追加 ★★★ --%>
        <div class="main col-md-10 py-4">
		<div class="bg-light border px-3 py-2 mb-3 fw-bold">科目情報削除</div>
			<%-- ここに処理を書き込む --%>
		<div class="card shadow-sm text-center border-0 mb-4" style="width: auto; background-color: #cde3cd;">削除が完了しました。
		</div>

		<a href="${pageContext.request.contextPath}/SubjectListController">科目一覧</a><%--科目一覧画面に移動 --%>

		</div>
	</div>
	</div>
<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
    <jsp:include page="../base/footer.jsp" />
</div>
