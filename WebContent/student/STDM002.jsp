<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    request.setAttribute("bodyClass", "menu-body");
%>

<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
    <jsp:include page="../base/header.jsp" />
</div>

<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<div class="container-fluid">
    <div class="row">
        <%-- .サイドバをインクルード --%>
        <%-- この testtest.jsp の中身が <div class="col-md-2 ..."> で始まっている想定です --%>
        <jsp:include page="../base/base.jsp" />

        <%-- ★★★ 修正点: mainクラスに col-md-10 を追加 ★★★ --%>
        <div class="main col-md-10 py-4">
            <div class="bg-light border px-3 py-2 mb-3 fw-bold fs-5">学生新規登録</div>

        <!-- エラー表示 -->
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
            <form action="StudentCreateExecute" method="post">

                <!-- 入学年度 -->
                <div class="mb-3">
                    <label for="entYear" class="form-label fw-bold">入学年度</label>
                    <select class="form-select" id="entYear" name="entYear" required>
                        <option value="">-------</option>
                        <c:forEach var="year" items="${ent_year_set}">
                            <option value="${year}" <c:if test="${year == entYear}">selected</c:if>>${year}</option>
                        </c:forEach>
                    </select>
                    <c:if test="${not empty entYearError}">
                        <div class="text-danger mt-1">${entYearError}</div>
                    </c:if>
                </div>

                <!-- 学生番号 -->
                <div class="mb-3">
                    <label for="studentNo" class="form-label fw-bold">学生番号</label>
                    <input type="text" class="form-control" id="studentNo" name="no" placeholder="学生番号を入力してください" value="${no}" required>
                    <c:if test="${not empty noError}">
                        <div class="text-danger mt-1">${noError}</div>
                    </c:if>
                    <c:if test="${not empty noDuplicateError}">
                        <div class="text-danger mt-1">${noDuplicateError}</div>
                    </c:if>
                </div>

                <!-- 氏名 -->
                <div class="mb-3">
                    <label for="studentName" class="form-label fw-bold">氏名</label>
                    <input type="text" class="form-control" id="studentName" name="name" placeholder="氏名を入力してください" value="${name}" required>
                    <c:if test="${not empty nameError}">
                        <div class="text-danger mt-1">${nameError}</div>
                    </c:if>
                </div>

                <!-- クラス -->
                <div class="mb-3">
                    <label for="classNum" class="form-label fw-bold">クラス</label>
                    <select class="form-select" id="classNum" name="classNum" required>
                        <option value="">--------</option>
                        <c:forEach var="num" items="${class_num_set}">
                            <option value="${num}" <c:if test="${num == classNum}">selected</c:if>>${num}</option>
                        </c:forEach>
                    </select>
                </div>

                <!-- 登録ボタン -->
                <div class="form-buttons">
                    <button type="submit" class="btn btn-primary">登録</button>
                    <a href="StudentList" class="btn btn-secondary">戻る</a>
                </div>
            </form>

            <!-- 戻るリンク（フォーム外） -->
            <div class="mt-4">
                <a href="${pageContext.request.contextPath}/main/MMNU001.jsp">戻る</a>
            </div>
        </div>
    </div>
</div>
</div>
<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
    <jsp:include page="../base/footer.jsp" />
</div>