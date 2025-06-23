<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- サイドバー本体 --%>
<div class="col-md-2 bg-white border-end py-3">
    <h6 class="text-primary fw-bold mb-3">メニュー</h6>
    <ul class="nav flex-column small">
        <li class="nav-item">
            <a class="nav-link text-primary" href="${pageContext.request.contextPath}/StudentListServlet">学生管理</a>
        </li>
        <li class="nav-item fw-bold mt-2">
            成績管理
        </li>
        <li class="nav-item ms-3">
            <a class="nav-link text-primary" href="${pageContext.request.contextPath}/TestRegistController">成績登録</a>
        </li>
        <li class="nav-item ms-3">
            <a class="nav-link text-primary" href="${pageContext.request.contextPath}/TestListController">成績参照</a>
        </li>
        <li class="nav-item mt-2">
            <a class="nav-link text-primary" href="${pageContext.request.contextPath}/SubjectListServlet">科目管理</a>
        </li>
    </ul>
</div>