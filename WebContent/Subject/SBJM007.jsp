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
		<div class="menu-header">科目情報削除</div>
			<%-- ここに処理を書き込む --%>
		<div class="success"><p>削除が完了しました。</p></div>

		<a href="#">成績参照</a><%--科目一覧画面に移動 --%>

		</div>
	</div>
<jsp:include page="../base/footer.html"></jsp:include>