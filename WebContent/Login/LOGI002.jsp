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
<div class = "d-flex justify-content-center" style="margin-right: 200px; margin-left: 200px; overflow: auto;">

        <%-- ★★★ 修正点: mainクラスに col-md-10 を追加 ★★★ --%>
        <div class="main col-md-10 py-4"style="margin-bottom: 10rem;">
      <div class="bg-light border px-3 py-2 mb-3 fw-bold fs-5" >
        ログアウト
      </div>
        <%-- ★★★ .修正点：他のページとクラス名を統一 ★★★ --%>
        <div class="card shadow-sm text-center border-0 mb-4" style="width: auto; background-color: #cde3cd;">
            ログアウトしました
        </div>

        <div class="actions">
            <%-- ★★★ 修正点：リンク先を修正し、ボタン風の表示に変更 ★★★ --%>
            <a href="<c:url value='/LoginController' />" class="mt-5">ログイン</a>
        </div>
    </div>

</div>

    <div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
    <jsp:include page="../base/footer.jsp" />
</div>