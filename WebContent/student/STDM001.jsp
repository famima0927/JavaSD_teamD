<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    request.setAttribute("bodyClass", "menu-body");
%>

<jsp:include page="../base/header.jsp"></jsp:include>
<%-- header.jsp にBootstrap5のCSSが含まれていることを前提としています --%>
<link rel="stylesheet" href="<c:url value='/css/style.css' />">

<div class="container">
    <div class="side-bar">
        <jsp:include page="../base/base.jsp"></jsp:include>
    </div>
    <div class="main">
        <%-- タイトルと新規登録リンク --%>
        <%-- d-flexを使ってタイトルとリンクを左右に配置します --%>
       	<div class="bg-light border px-3 py-2 mb-4 fw-bold">学生管理</div>
        <div class="d-flex justify-content-between align-items-center mb-3">

            <%-- 新規登録ボタンを囲い線なしのリンクに変更し、右上に配置 --%>
            <a href="<c:url value='/StudentCreate' />">新規登録</a>
        </div>

        <%-- 検索フォームエリア --%>
        <div class="p-3 mb-4 bg-light border rounded">
            <form action="<c:url value='/StudentList' />" method="get" class="row gx-3 gy-2 align-items-end">
                <input type="hidden" name="sort" value="${sort}">

                <%-- 入学年度: 幅を広げるために col-md-3 を使用 --%>
                <div class="col-md-3">
                    <label for="entYear" class="form-label">入学年度</label>
                    <select name="f1" id="entYear" class="form-select">
                        <option value="0">--------</option>
                        <c:forEach var="year" items="${ent_year_set}">
                            <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
                        </c:forEach>
                    </select>
                </div>

                <%-- クラス: 幅を広げるために col-md-3 を使用 --%>
                <div class="col-md-3">
                    <label for="classNum" class="form-label">クラス</label>
                    <select name="f2" id="classNum" class="form-select">
                        <option value="">--------</option>
                        <c:forEach var="classNum" items="${class_num_set}">
                            <option value="${classNum}" <c:if test="${classNum == f2}">selected</c:if>>${classNum}</option>
                        </c:forEach>
                    </select>
                </div>

                <%-- 在学中チェックボックス --%>
                <div class="col-auto">
                    <div class="form-check pb-1"> <%-- 他の要素と高さを合わせるため微調整 --%>
                        <input type="checkbox" name="f3" id="isAttend" value="true" class="form-check-input" <c:if test="${f3}">checked</c:if>>
                        <label for="isAttend" class="form-check-label">在学中</label>
                    </div>
                </div>

                <%-- 絞り込みボタン (右寄せ) --%>
                <div class="col-auto ms-auto">
                    <button type="submit" class="btn btn-primary">絞り込み</button>
                </div>
            </form>
        </div>

        <%-- 検索結果件数 --%>
        <div class="mb-2">
            検索結果：${studentList.size()}件
        </div>

        <%-- 学生一覧テーブル --%>
        <table class="table table-bordered text-center align-middle">
            <thead class="table-light">
                <tr>
                    <th style="width: 10%;">入学年度</th>
                    <th style="width: 15%;">学生番号</th>
                    <th class="text-start">氏名</th>
                    <th style="width: 10%;">クラス</th>
                    <th style="width: 10%;">在学中</th>
                    <th style="width: 10%;"></th>
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
                        <td class="text-start">${s.name}</td>
                        <td>${s.classNum}</td>
                        <td>${s.isAttend ? '○' : '×'}</td>
                        <%-- 変更ボタンを囲い線なしのリンクに変更 --%>
                        <td><a href="<c:url value='/StudentUpdate?no=${s.no}' />">変更</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <%-- (ページネーション部分は省略) --%>
    </div>
</div>

<jsp:include page="../base/footer.html"></jsp:include>