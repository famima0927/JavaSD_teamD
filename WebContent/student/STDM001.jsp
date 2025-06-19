<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setAttribute("bodyClass", "menu-body"); %>

<jsp:include page="../base/header.jsp"></jsp:include>
<%-- <link rel="stylesheet" href="<c:url value='/css/style.css' />"> --%> <%-- ← この行は不要になります --%>

<div class="container">
    <div class="row"> <%-- ★レイアウトをグリッドシステムに変更 --%>
        <div class="col-md-2"> <%-- ★サイドバーエリア --%>
            <jsp:include page="../base/base.jsp"></jsp:include>
        </div>
        <div class="col-md-10"> <%-- ★メインコンテンツエリア --%>
            <div class="bg-light p-3 mb-4">
                <h2 class="mb-0 fw-bold">学生管理</h2>
            </div>

            <%-- ① 検索フォーム --%>
            <div class="card p-4 mb-4 border rounded">
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
                            <button type="submit" class="btn btn-secondary">絞り込み</button>
                        </div>
                    </div>
                </form>
            </div>

            <%-- ② 結果表示エリア --%>
            <div class="d-flex justify-content-between align-items-center mb-3">
                <div class="text-muted">検索結果：${not empty studentList ? studentList.size() : 0}件</div>
                <a href="<c:url value='/StudentCreate' />" class="btn btn-primary">新規登録</a>
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
                            <td><a href="<c:url value='/StudentUpdate?no=${s.no}' />" class="btn btn-sm btn-outline-primary">変更</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="../base/footer.html"></jsp:include>