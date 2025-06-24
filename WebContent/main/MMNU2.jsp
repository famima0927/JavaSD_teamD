<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    request.setAttribute("bodyClass", "menu-body");
%>
<jsp:include page="../base/header2.jsp"/>

<%-- ★★ 修正点: メインコンテンツを.containerで囲み、上下に余白(py-4)を追加 ★★★ --%>
<div class="container py-4">
    <div class="row">
        <%-- サイドバーをインクルード --%>
        <jsp:include page="../base/base.jsp" />

        <%-- メインコンテンツの列 --%>
        <div class="col-md-10">
            <div class="bg-light border px-3 py-2 mb-4 fw-bold">メニュー</div>
            <div class="d-flex gap-4 justify-content-start">
                <%-- (カードなどのコンテンツは変更なし) --%>
                <div class="card shadow-sm border-0" style="width: 180px; height: 100px; background-color: #eacaca;">
                    ...
                </div>
                <div class="card shadow-sm border-0" style="width: 180px; height: 100px; background-color: #cde3cd;">
                    ...
                </div>
                <div class="card shadow-sm border-0" style="width: 180px; height: 100px; background-color: #cfcfe7;">
                    ...
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../base/footer2.jsp"/>