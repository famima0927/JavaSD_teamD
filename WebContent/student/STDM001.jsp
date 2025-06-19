<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setAttribute("bodyClass", "menu-body"); %>

<jsp:include page="../base/header.jsp"></jsp:include>

<%-- ★ mb-5 を追加して、フッターとの間に余白を設けます --%>
<div class="container-fluid mb-5">
  <div class="row">
    <!-- サイドバー（変更なし） -->
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

    <%-- メインコンテンツエリア --%>
    <div class="col-md-10 py-3">
      <%-- ① 「学生管理」タイトルバー --%>
      <%-- ボタンとの間隔を調整するため、下のマージンを mb-3 に変更 --%>
      <div class="bg-light border px-3 py-2 mb-3 fw-bold">
        学生管理
      </div>

      <%-- ② 「新規登録」ボタンを右寄せで配置 --%>
      <%-- text-endで右寄せし、下の検索フォームとの間隔を mb-4 で確保 --%>
      <div class="text-end mb-4">
        <a href="<c:url value='/StudentCreate' />">新規登録</a>
      </div>

      <%-- ③ 検索フォーム --%>
      <div class="p-3 mb-4 border rounded bg-light">
          <form action="<c:url value='/StudentList' />" method="get">
              <div class="row g-3 align-items-end">
                  <%-- 入学年度 --%>
                  <div class="col-md">
                      <label for="entYear" class="form-label">入学年度</label>
                      <select name="f1" id="entYear" class="form-select">
                          <option value="0">--------</option>
                          <c:forEach var="year" items="${ent_year_set}">
                              <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
                          </c:forEach>
                      </select>
                  </div>
                  <%-- クラス --%>
                  <div class="col-md">
                      <label for="classNum" class="form-label">クラス</label>
                      <select name="f2" id="classNum" class="form-select">
                          <option value="">--------</option>
                          <c:forEach var="classNum" items="${class_num_set}">
                              <option value="${classNum}" <c:if test="${classNum == f2}">selected</c:if>>${classNum}</option>
                          </c:forEach>
                      </select>
                  </div>
                  <%-- 在学中チェックボックス --%>
                  <div class="col-md-auto d-flex align-items-end">
                      <div class="form-check">
                          <input type="checkbox" name="f3" id="isAttend" value="true" class="form-check-input" <c:if test="${f3}">checked</c:if>>
                          <label for="isAttend" class="form-check-label">在学中</label>
                      </div>
                  </div>
                  <%-- ボタン --%>
                  <div class="col-md-auto">
                      <button type="submit" class="btn btn-primary">絞り込み</button>
                  </div>
              </div>
          </form>
      </div>

      <%-- ④ 結果表示エリア --%>
      <div class="mb-3">
          <span class="text-muted">検索結果：${not empty studentList ? studentList.size() : 0}件</span>
      </div>

      <table class="table table-hover">
          <thead>
              <tr>
                  <th>入学年度</th>
                  <th>学生番号</th>
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
                      <td><a href="<c:url value='/StudentUpdate?no=${s.no}' />">変更</a></td>
                  </tr>
              </c:forEach>
          </tbody>
      </table>
    </div>
  </div>
</div>

<jsp:include page="../base/footer.html"></jsp:include>