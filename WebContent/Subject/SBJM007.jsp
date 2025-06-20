<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
  request.setAttribute("bodyClass", "ここにボディ名を書き込む");
%>
<jsp:include page="../base/header.jsp"></jsp:include>
	<div class = "container">
		<div class = "side-bar">
			<jsp:include page="../base/base.jsp"></jsp:include>
		</div>
		<div class = "main">
		<div class="bg-light border px-3 py-2 mb-3 fw-bold">科目情報削除</div>
			<%-- ここに処理を書き込む --%>
		<div class="card shadow-sm text-center border-0 mb-4" style="width: auto; background-color: #cde3cd;">削除が完了しました。
		</div>

		<a href="${pageContext.request.contextPath}/SubjectListController">科目一覧</a><%--科目一覧画面に移動 --%>

		</div>
	</div>
<jsp:include page="../base/footer.html"></jsp:include>