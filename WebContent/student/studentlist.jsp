<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    // このページ用のbodyクラスを設定
    request.setAttribute("bodyClass", "menu-body");
%>

<%-- ヘッダーの読み込み --%>
<jsp:include page="../base/header.jsp"></jsp:include>

<%-- 外部CSSファイルを読み込む --%>
<link rel="stylesheet" href="<c:url value='/css/style.css' />"> <%-- プロジェクト共通のCSSを想定 --%>


<div class="container">
    <div class="side-bar">
        <%-- サイドメニューの読み込み --%>
        <jsp:include page="../base/base.jsp"></jsp:include>
    </div>
    <div class="main">
        <h2>学生管理</h2>

        <%-- 絞り込みフォーム --%>
        <form action="StudentList.action" method="get" class="filter-form">
            <%-- hiddenフィールドで現在のソート順を維持する --%>
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
                <a href="StudentCreate.action" class="btn btn-success">新規登録</a>
            </div>
        </form>

        <%-- 検索結果件数 --%>
        <div class="results-info">
            検索結果：${totalCount}件
        </div>

        <%-- 学生リストテーブル --%>
        <table class="student-table">
            <thead>
                <tr>
                    <%-- ▼▼▼ ソート機能のためのリンクを追加 ▼▼▼ --%>
                    <th><a href="StudentList.action?f1=${f1}&f2=${f2}&f3=${f3}&sort=ent_year_${sort == 'ent_year_asc' ? 'desc' : 'asc'}">入学年度 ${sort == 'ent_year_asc' ? '▲' : (sort == 'ent_year_desc' ? '▼' : '')}</a></th>
                    <th><a href="StudentList.action?f1=${f1}&f2=${f2}&f3=${f3}&sort=no_${sort == 'no_asc' ? 'desc' : 'asc'}">学生番号 ${sort == 'no_asc' ? '▲' : (sort == 'no_desc' ? '▼' : '')}</a></th>
                    <th>氏名</th>
                    <th>クラス</th>
                    <th>在学中</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <%-- ▼▼▼ 検索結果が0件の場合の表示 ▼▼▼ --%>
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
                        <td><a href="StudentUpdate.action?no=${s.no}">変更</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <%-- ▼▼▼ ページネーション ▼▼▼ --%>
        <div class="pagination">
            <c:if test="${currentPage > 1}">
                <a href="StudentList.action?f1=${f1}&f2=${f2}&f3=${f3}&sort=${sort}&page=${currentPage - 1}">&lt; 前へ</a>
            </c:if>

            <c:forEach var="i" begin="1" end="${totalPages}">
                <c:choose>
                    <c:when test="${i == currentPage}">
                        <span class="current-page">${i}</span>
                    </c:when>
                    <c:otherwise>
                        <a href="StudentList.action?f1=${f1}&f2=${f2}&f3=${f3}&sort=${sort}&page=${i}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${currentPage < totalPages}">
                <a href="StudentList.action?f1=${f1}&f2=${f2}&f3=${f3}&sort=${sort}&page=${currentPage + 1}">次へ &gt;</a>
            </c:if>
        </div>

    </div>
</div>

<%-- フッターの読み込み --%>
<jsp:include page="../base/footer.html"></jsp:include>