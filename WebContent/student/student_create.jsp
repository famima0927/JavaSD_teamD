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
        <h2>学生新規登録</h2>

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
            <%-- 送信先は StudentRegister.action に設定 --%>
            <form action="StudentRegister.action" method="post">

                <div class="form-row">
                    <label for="entYear">入学年度</label>
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
                    <input type="text" id="studentNo" name="no" value="${no}" required>
                    <div class="form-error">${noError} ${noDuplicateError}</div>
                </div>

                <div class="form-row">
                    <label for="studentName">氏名</label>
                    <input type="text" id="studentName" name="name" value="${name}" required>
                    <div class="form-error">${nameError}</div>
                </div>

                <div class="form-row">
                    <label for="classNum">クラス</label>
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

<jsp:include page="../base/footer.html"></jsp:include>