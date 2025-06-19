<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    request.setAttribute("bodyClass", "menu-body");
%>

<%-- 共通ヘッダーの読み込み --%>
<jsp:include page="../base/header.jsp"></jsp:include>
<link rel="stylesheet" href="<c:url value='/css/style.css' />">

<div class="container">
    <div class="side-bar">
        <%-- 共通サイドメニューの読み込み --%>
        <jsp:include page="../base/base.jsp"></jsp:include>
    </div>
    <div class="main">
        <h2>学生新規登録</h2>

        <%-- エラーがある場合にまとめて表示する欄 --%>
        <c:if test="${not empty errors}">
            <div class="error-message">
                <ul>
                    <c:forEach var="e" items="${errors}">
                        <li>${e}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <div class="form-container">
            <%-- ★★★ 修正点：送信先(action)を正しいサーブレットに変更 ★★★ --%>
            <form action="<c:url value='/StudentRegister.action' />" method="post">

                <div class="form-row">
                    <label for="entYear">入学年度</label>
                    <%-- ★★★ 修正点：選択肢を動的に生成 ★★★ --%>
                    <select id="entYear" name="ent_year" required>
                        <option value="">--------</option>
                        <c:forEach var="year" items="${ent_year_set}">
                            <option value="${year}" <c:if test="${year == entYearStr}">selected</c:if>>${year}</option>
                        </c:forEach>
                    </select>
                    <div class="form-error">${entYearError}</div>
                </div>

                <div class="form-row">
                    <label for="studentNo">学生番号</label>
                    <input type="text" id="studentNo" name="no" value="${no}" required placeholder="例: 2231111">
                    <div class="form-error">${noError} ${noDuplicateError}</div>
                </div>

                <div class="form-row">
                    <label for="studentName">氏名</label>
                    <input type="text" id="studentName" name="name" value="${name}" required placeholder="例: 大原 太郎">
                    <div class="form-error">${nameError} ${nameDuplicateError}</div>
                </div>

                <div class="form-row">
                    <label for="classNum">クラス</label>
                     <%-- ★★★ 修正点：選択肢を動的に生成 ★★★ --%>
                    <select id="classNum" name="class_num" required>
                        <option value="">--------</option>
                        <c:forEach var="num" items="${class_num_set}">
                            <option value="${num}" <c:if test="${num == classNum}">selected</c:if>>${num}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-buttons">
                    <button type="submit" class="btn btn-primary">登録</button>
                    <a href="<c:url value='/StudentList' />" class="btn btn-secondary">戻る</a>
                </div>
            </form>
        </div>
    </div>
</div>

<%-- 共通フッターの読み込み --%>
<jsp:include page="../base/footer.html"></jsp:include>