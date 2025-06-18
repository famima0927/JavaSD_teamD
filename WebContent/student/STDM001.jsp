<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    request.setAttribute("bodyClass", "menu-body");
%>

<jsp:include page="../base/header.jsp"></jsp:include>
<link rel="stylesheet" href="<c:url value='/css/style.css' />">

<div class="container">
    <div class="side-bar">
        <jsp:include page="../base/base.jsp"></jsp:include>
    </div>
    <div class="main">
        <h2>学生管理</h2>

        <form action="<c:url value='/StudentList' />" method="get" class="filter-form">
            <input type="hidden" name="sort" value="${sort}">
            <div class="form-group">
                <label for="entYear">入学年度</label>
                <select name="f1" id="entYear">
                    <option value="0">--------</option>
                    <c:forEach var="year" items="${ent_year_set}">
                        <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="classNum">クラス</label>
                <select name="f2" id="classNum">
                    <option value="">--------</option>
                    <c:forEach var="classNum" items="${class_num_set}">
                        <option value="${classNum}" <c:if test="${classNum == f2}">selected</c:if>>${classNum}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <input type="checkbox" name="f3" id="isAttend" value="true" <c:if test="${f3}">checked</c:if>>
                <label for="isAttend">在学中</label>
            </div>
            <div class="actions">
                <button type="submit" class="btn btn-primary">絞り込み</button>
                <a href="<c:url value='/StudentCreate' />" class="btn btn-success">新規登録</a>
            </div>
        </form>

        <div class="results-info">
            検索結果：${studentList.size()}件
        </div>

        <table class="student-table">
            <thead>
                <tr>
                    <%-- ▼▼▼ 原因切り分けのため、一時的にシンプルな表示に変更 ▼▼▼ --%>
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
        <%-- (ページネーション部分は省略) --%>
    </div>
</div>
<jsp:include page="../base/footer.html"></jsp:include>