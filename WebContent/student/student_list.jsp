<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setAttribute("bodyClass", "menu-body"); %>

<jsp:include page="../base/header.jsp" />

<div class="container-fluid mb-5">
  <div class="row">
    <!-- サイドバー -->
    <div class="col-md-2 bg-white border-end py-3">
      <h6 class="text-primary fw-bold mb-3">メニュー</h6>
      <ul class="nav flex-column small">
        <li class="nav-item"><a class="nav-link text-primary" href="${pageContext.request.contextPath}/StudentList">学生管理</a></li>
        <li class="nav-item fw-bold">成績管理</li>
        <li class="nav-item ms-3"><a class="nav-link text-primary" href="${pageContext.request.contextPath}/servlet/TestRegistController">成績登録</a></li>
        <li class="nav-item ms-3"><a class="nav-link text-primary" href="${pageContext.request.contextPath}/servlet/TestListController">成績参照</a></li>
        <li class="nav-item"><a class="nav-link text-primary" href="${pageContext.request.contextPath}/SubjectListController">科目管理</a></li>
      </ul>
    </div>

    <!-- メインコンテンツ -->
    <div class="col-md-10 py-3">
      <!-- タイトル -->
      <div class="bg-light border px-3 py-2 mb-3 fw-bold">
        学生管理
      </div>

      <!-- 新規登録ボタン -->
      <div class="text-end mb-4">
        <a href="<c:url value='/StudentCreate' />" class="btn btn-success">新規登録</a>
      </div>

      <!-- 絞り込みフォーム -->
      <form action="<c:url value='/StudentList' />" method="get" class="mb-4">
        <div class="row g-3 align-items-end">
          <div class="col-md">
            <label for="entYear" class="form-label">入学年度</label>
            <select name="f1" id="entYear" class="form-select">
              <option value="0">--------</option>
              <c:forEach var="year" items="${ent_year_set}">
                <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
              </c:forEach>
            </select>
          </div>
          <div class="col-md">
            <label for="classNum" class="form-label">クラス</label>
            <select name="f2" id="classNum" class="form-select">
              <option value="">--------</option>
              <c:forEach var="classNum" items="${class_num_set}">
                <option value="${classNum}" <c:if test="${classNum == f2}">selected</c:if>>${classNum}</option>
              </c:forEach>
            </select>
          </div>
          <div class="col-md-auto d-flex align-items-end">
            <div class="form-check">
              <input type="checkbox" name="f3" id="isAttend" value="true" class="form-check-input" <c:if test="${f3}">checked</c:if>>
              <label for="isAttend" class="form-check-label">在学中</label>
            </div>
          </div>
          <div class="col-md-auto">
            <button type="submit" class="btn btn-primary">絞り込み</button>
          </div>
        </div>
      </form>

      <!-- 検索結果件数 -->
      <div class="mb-3">
        <span class="text-muted">検索結果：${not empty studentList ? studentList.size() : 0}件</span>
      </div>

      <!-- 学生一覧テーブル -->
      <table class="table table-hover student-table">
        <thead>
          <tr>
            <th>
              <a href="<c:url value='/StudentList?f1=${f1}&f2=${f2}&f3=${f3}&sort=ent_year_${sort_order == 'asc' ? 'desc' : 'asc'}' />">
                入学年度 ${sort_key == 'ent_year' ? (sort_order == 'asc' ? '▲' : '▼') : ''}
              </a>
            </th>
            <th>
              <a href="<c:url value='/StudentList?f1=${f1}&f2=${f2}&f3=${f3}&sort=no_${sort_order == 'asc' ? 'desc' : 'asc'}' />">
                学生番号 ${sort_key == 'no' ? (sort_order == 'asc' ? '▲' : '▼') : ''}
              </a>
            </th>
            <th>氏名</th>
            <th>クラス</th>
            <th>在学中</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <c:if test="${empty studentList}">
            <tr>
              <td colspan="6">該当する学生が見つかりませんでした。</td>
            </tr>
          </c:if>
          <c:forEach var="s" items="${studentList}">
            <tr>
              <td>${s.entYear}</td>
              <td>${s.no}</td>
              <td>${s.name}</td>
              <td>${s.classNum}</td>
              <td>${s.isAttend ? '○' : '×'}</td>
              <td>
                <a href=<c:url value='/StudentUpdate.action?no=${s.no}' />>変更</a>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>

<jsp:include page="../base/footer.html" />
