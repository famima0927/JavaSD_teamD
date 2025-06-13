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
<link rel="stylesheet" href="<c:url value='/css/style.css' />"> <%-- プロジェクト共通のCSS --%>

<div class="container">
    <div class="side-bar">
        <%-- サイドメニューの読み込み --%>
        <jsp:include page="../base/base.jsp"></jsp:include>
    </div>
    <div class="main">
        <%-- ① 画面タイトル --%>
        <h2>学生情報変更</h2>

        <%-- エラーメッセージ表示欄 --%>
        <c:if test="${not empty errors}">
            <div class="error-message">
                <ul>
                    <c:forEach var="error" items="${errors}">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <div class="form-container">
            <form action="StudentUpdateExecute.action" method="post">
                <%-- 更新対象の学生番号をサーバーに送るための隠しフィールド --%>
                <input type="hidden" name="no" value="${student.no}">

                <%-- ②,③ 入学年度 (編集不可) --%>
                <div class="form-row">
                    <label>入学年度</label>
                    <p class="static-data">${student.entYear}</p>
                </div>

                <%-- ④,⑤ 学生番号 (編集不可) --%>
                <div class="form-row">
                    <label>学生番号</label>
                    <p class="static-data">${student.no}</p>
                </div>

                <%-- ⑥,⑦ 氏名 (編集可能) --%>
                <div class="form-row">
                    <label for="studentName">氏名</label>
                    <input type="text" id="studentName" name="name" value="${student.name}" required>
                </div>

                <%-- ⑧,⑨ クラス (編集可能) --%>
                <div class="form-row">
                    <label for="classNum">クラス</label>
                    <select id="classNum" name="class_num">
                        <c:forEach var="num" items="${class_num_set}">
                            <%-- 学生の現在のクラスをデフォルトで選択状態にする --%>
                            <option value="${num}" <c:if test="${num == student.classNum}">selected</c:if>>${num}</option>
                        </c:forEach>
                    </select>
                </div>

                <%-- ⑩,⑪ 在学状況 (編集可能) --%>
                <div class="form-row">
                    <label>在学状況</label>
                    <div class="is-attend-group">
                        <%-- 学生の現在の在学状況をデフォルトでチェック状態にする --%>
                        <input type="checkbox" id="isAttend" name="is_attend" value="true" <c:if test="${student.isAttend}">checked</c:if>>
                        <label for="isAttend">在学中</label>
                    </div>
                </div>

                <%-- ⑫,⑬ ボタン --%>
                <div class="form-buttons">
                    <button type="submit" class="btn btn-primary">変更</button>

                    <%-- ★★★ 「戻る」ボタンのリンク先を修正 ★★★ --%>
                    <a href="<c:url value='/StudentList' />" class="btn btn-secondary">戻る</a>
                </div>
            </form>
        </div>
    </div>
</div>

<%-- フッターの読み込み --%>
<jsp:include page="../base/footer.html"></jsp:include>