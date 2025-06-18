<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  request.setAttribute("bodyClass", "menu-body");
%>

<jsp:include page="../base/header.jsp"></jsp:include>

<div class="container-fluid">
  <div class="row">
    <!-- サイドバー -->
    <div class="col-md-2 bg-white border-end py-3">
      <h6 class="text-primary fw-bold mb-3">メニュー</h6>
      <ul class="nav flex-column small">
        <li class="nav-item"><a class="nav-link text-primary" href="#">学生管理</a></li>
        <li class="nav-item fw-bold">成績管理</li>
        <li class="nav-item ms-3"><a class="nav-link text-primary" href="#">成績登録</a></li>
        <li class="nav-item ms-3"><a class="nav-link text-primary" href="#">成績参照</a></li>
        <li class="nav-item"><a class="nav-link text-primary" href="#">科目管理</a></li>
      </ul>
    </div>

    <!-- メイン -->
    <div class="col-md-10 py-4">
      <div class="bg-light border px-3 py-2 mb-4 fw-bold">メニュー</div>

      <div class="d-flex gap-4 justify-content-start">
        <!-- 学生管理 -->
        <div class="card shadow-sm border-0" style="width: 180px; background-color: #eacaca;">
          <div class="card-body text-center">
            <a href="${pageContext.request.contextPath}/StudentList" class="stretched-link fw-bold text-decoration-none text-primary">学生管理</a>
          </div>
        </div>

        <!-- 成績管理 -->
        <div class="card shadow-sm border-0" style="width: 180px; background-color: #cde3cd;">
          <div class="card-body text-center p-2">
            <div class="fw-bold mb-2">成績管理</div>
            <a href="${pageContext.request.contextPath}/servlet/TestRegistController" class="d-block text-decoration-none text-primary small mb-1">成績登録</a>
            <a href="${pageContext.request.contextPath}/servlet/TestListController" class="d-block text-decoration-none text-primary small">成績参照</a>
          </div>
        </div>

        <!-- 科目管理 -->
        <div class="card shadow-sm border-0" style="width: 180px; background-color: #cfcfe7;">
          <div class="card-body text-center">

            <a href="${pageContext.request.contextPath}/SubjectListController" class="stretched-link fw-bold text-decoration-none text-primary">科目管理</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<jsp:include page="../base/footer.html"></jsp:include>
