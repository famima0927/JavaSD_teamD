<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    request.setAttribute("bodyClass", "menu-body");
%>


<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
    <jsp:include page="../base/header.jsp" />
</div>



<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
<div class="container-fluid">
    <div class="row">
        <%-- .サイドバをインクルード --%>
        <%-- この testtest.jsp の中身が <div class="col-md-2 ..."> で始まっている想定です --%>
        <jsp:include page="../base/base.jsp" />

        <%-- ★★★ 修正点: mainクラスに col-md-10 を追加 ★★★ --%>
        <div class="main col-md-10 py-4">
      <div class="bg-light border px-3 py-2 mb-3 fw-bold fs-5">
        学生管理
      </div>

      <div class="text-end mb-2">
        <a href="${pageContext.request.contextPath}/StudentCreate">新規登録</a>
      </div>
		<div class="border rounded-bottom p-2 mb-1">
      <form action="${pageContext.request.contextPath}/StudentList" method="get" class="mb-2">
        <div class="row g-3 align-items-end">
          <div class="col-md-4">
            <label for="entYear" class="form-label">入学年度</label>
            <select name="f1" id="entYear" class="form-select">
              <option value="0">--------</option>
              <c:forEach var="year" items="${ent_year_set}">
                <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
              </c:forEach>
            </select>
          </div>
          <div class="col-md-4">
            <label for="classNum" class="form-label">クラス</label>
            <select name="f2" id="classNum" class="form-select">
              <option value="">--------</option>
              <c:forEach var="classNum" items="${class_num_set}">
                <option value="${classNum}" <c:if test="${classNum == f2}">selected</c:if>>${classNum}</option>
              </c:forEach>
            </select>
          </div>
          <div class="col-md-2 ms-3">
            <div class="form-check">
              <input type="checkbox" name="f3" id="isAttend" value="true" class="form-check-input" <c:if test="${f3}">checked</c:if>>
              <label for="isAttend" class="form-check-label">在学中</label>
            </div>
          </div>
          <div class="col-md-auto pb-2">
            <button type="submit" class="btn btn-secondary">絞込み</button>
          </div>
        </div>
      </form>
      </div>
      			<%-- 絞り込みで検索かけたときに学生情報がエラーになる仕組み(未完成) --%>
      					<c:if test="${not empty no_results_error}">
    <div class="alert alert-warning">
        ${no_results_error}
    </div>
</c:if>


      <div class="mb-3">
        <span class="text-muted">検索結果：${not empty studentList ? studentList.size() : 0}件</span>
      </div>

      <table class="table table-hover student-table">
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
          <c:forEach var="s" items="${studentList}">
            <tr>
              <td>${s.entYear}</td>
              <td>${s.no}</td>
              <td>${s.name}</td>
              <td>${s.classNum}</td>
              <td>
                <c:choose>
                  <c:when test="${s.isAttend}">○</c:when>
                  <c:otherwise>×</c:otherwise>
                </c:choose>
              </td>
              <td><a href="${pageContext.request.contextPath}/StudentUpdate?no=${s.no}">変更</a></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>
</div>
<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
    <jsp:include page="../base/footer.jsp" />
</div>