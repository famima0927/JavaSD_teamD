<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 更新モードかどうかを判定するフラグを作成 --%>
<c:set var="isUpdateMode" value="${not empty student.no}" />

<%
    request.setAttribute("bodyClass", "menu-body");
%>

<jsp:include page="../base/header.jsp"></jsp:include>
<link rel="stylesheet" href="<c:url value='/css/style.css' />">
<div class="container-fluid">
    <div class="row">
        <%-- サイドバをインクルード --%>
        <%-- この testtest.jsp の中身が <div class="col-md-2 ..."> で始まっている想定です --%>
        <jsp:include page="../base/base.jsp" />

        <%-- ★★★ 修正点: mainクラスに col-md-10 を追加 ★★★ --%>
        <div class="main col-md-10 py-4">
         <%-- モードによってタイトルを変更 --%>
        <c:choose>
            <c:when test="${isUpdateMode}">
            <div class="bg-light border px-3 py-2 mb-3 fw-bold">学生情報変更</div>
       		</c:when>
            <c:otherwise>
                <div class="bg-light border px-3 py-2 mb-3 fw-bold">学生新規変更</div>
            </c:otherwise>
        </c:choose>

        <%-- .エラーメッセージ表示欄 --%>
        <c:if test="${not empty errors}">
            <div class="error-message">
                <ul><c:forEach var="e" items="${errors}"><li>${e}</li></c:forEach></ul>
            </div>
        </c:if>

        <div class="form-container">
            <%-- ▼▼▼ ここが修正のポイント ▼▼▼ --%>
            <%-- モードによってフォームの送信先(action)を切り替える --%>
            <c:choose>
                <c:when test="${isUpdateMode}">
                    <form action="<c:url value='/StudentUpdateExecute' />" method="post">
                </c:when>
                <c:otherwise>
                    <%-- 新規登録時は StudentRegister.action を指定 --%>
                    <form action="<c:url value='/StudentCreate' />" method="post">
                </c:otherwise>
            </c:choose>

                <%-- 更新モードの場合のみ、学生番号をhiddenで送信 --%>
                <c:if test="${isUpdateMode}">
                    <input type="hidden" name="no" value="${student.no}">
                </c:if>

                <div class="form-row">
                    <label for="entYear">入学年度</label>
                    <c:choose>
                        <c:when test="${isUpdateMode}"><p class="static-data">${student.entYear}</p></c:when>
                        <c:otherwise>
                            <select id="entYear" name="ent_year" required>
                                <option value="">--------</option>
                                <c:forEach var="year" items="${ent_year_set}"><option value="${year}" <c:if test="${year == entYearStr}">selected</c:if>>${year}</option></c:forEach>
                            </select>
                            <div class="form-error">${entYearError}</div>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="form-row">
                    <label for="studentNo">学生番号</label>
                     <c:choose>
                        <c:when test="${isUpdateMode}"><p class="static-data">${student.no}</p></c:when>
                        <c:otherwise>
                            <input type="text" id="studentNo" name="no" value="${no}" required>
                            <div class="form-error">${noError} ${noDuplicateError}</div>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="form-row">
                    <label for="studentName">氏名</label>
                    <input type="text" id="studentName" name="name" value="${student.name}" required>
                    <div class="form-error">${nameError} ${nameDuplicateError}</div>
                </div>

                <div class="form-row">
                    <label for="classNum">クラス</label>
                    <select id="classNum" name="class_num" required>
                        <option value="">--------</option>
                        <c:forEach var="num" items="${class_num_set}"><option value="${num}" <c:if test="${num == student.classNum}">selected</c:if>>${num}</option></c:forEach>
                    </select>
                </div>

                <c:if test="${isUpdateMode}">
                    <div class="form-row">
                        <label>在学中</label>
                        <div class="is-attend-group">
                            <input type="checkbox" id="isAttend" name="is_attend" value="true" <c:if test="${student.isAttend}">checked</c:if>>
                            <label for="isAttend">○</label>
                        </div>
                    </div>
                </c:if>

                <div class="form-buttons">
                    <c:choose>
                        <c:when test="${isUpdateMode}"><button type="submit" class="btn btn-primary">変更</button></c:when>
                        <c:otherwise><button type="submit" class="btn btn-primary">登録</button></c:otherwise>
                    </c:choose>
                    <a href="<c:url value='/StudentList' />" class="btn btn-secondary">戻る</a>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../base/footer.html"></jsp:include>