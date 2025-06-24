<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- ★★★ 修正点: サイドバー全体をcol-md-2で囲む ★★★ --%>
<div class="col-md-2 bg-light border-end py-3">
    <h6 class="text-primary fw-bold mb-3 px-2">メニュー</h6>
    <ul class="nav flex-column small">
        <li class="nav-item">
            <a class="nav-link text-dark" href="#">学生管理</a>
        </li>
        <li class="nav-item fw-bold mt-2">
            <span class="nav-link text-muted">成績管理</span>
        </li>
        <li class="nav-item ms-3">
            <a class="nav-link text-dark" href="${pageContext.request.contextPath}/servlet/TestRegistController">成績登録</a>
        </li>
        <li class="nav-item ms-3">
            <a class="nav-link text-dark" href="${pageContext.request.contextPath}/servlet/TestListController">成績参照</a>
        </li>
        <li class="nav-item mt-2">
            <a class="nav-link text-dark" href="#">科目管理</a>
        </li>
    </ul>
</div>