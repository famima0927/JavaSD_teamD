<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    request.setAttribute("bodyClass", "menu-body");
%>

<%-- 共通ヘッダーの読み込み --%>
<jsp:include page="../base/header.jsp"></jsp:include>
<link rel="stylesheet" href="<c:url value='/css/style.css' />">
    request.setAttribute("bodyClass", "menu-body");
%>
<%-- ヘッダーとフッターは別ファイルのため、ここでは記述しません --%>
<jsp:include page="../base/header.jsp"></jsp:include>
<%--
  d-flexを使ってサイドバーとメインコンテンツを横並びにします。
  ヘッダー、フッター、サイドバーが共通レイアウトを構成していることを想定しています。
--%>
<div class="d-flex">
    <%-- サイドバー --%>
    <div class="side-bar">
        <jsp:include page="../base/base.jsp"></jsp:include>
    </div>

<div class="container">
    <div class="side-bar">
        <%-- 共通サイドメニューの読み込み --%>
        <jsp:include page="../base/base.jsp"></jsp:include>
    </div>
    <div class="main">
        <h2>学生新規登録</h2>
    <%-- メインコンテンツエリア --%>
    <div class="container-fluid p-4">

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
        <%-- ① タイトル --%>
        <h2 class="border-bottom pb-2 mb-4">学生情報登録</h2>

          HEAD
        <div class="form-container">
            <%-- ★★★ 修正点：送信先(action)を正しいサーブレットに変更 ★★★ --%>
            <form action="<c:url value='/StudentRegister.action' />" method="post">

        <form action="registerStudent" method="post">
            <%-- ②, ③ 入学年度 --%>
            <div class="mb-3">
                <label for="entYear" class="form-label fw-bold">入学年度</label>
                <select class="form-select" id="entYear" name="entYear">
                    <option value="">-------</option>
                    <option value="2022" <c:if test="${entYear == '2022'}">selected</c:if>>2022</option>
                    <option value="2023" <c:if test="${entYear == '2023'}">selected</c:if>>2023</option>
                    <option value="2024" <c:if test="${entYear == '2024'}">selected</c:if>>2024</option>
                </select>
                <c:if test="${not empty entYearError}">
                    <div class="text-danger mt-1">${entYearError}</div>
                </c:if>
            </div>

              HEAD
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

            <%-- ④, ⑤ 学生番号 --%>
            <div class="mb-3">
                <label for="studentNo" class="form-label fw-bold">学生番号</label>
                <input type="text" class="form-control" id="studentNo" name="no" placeholder="学生番号を入力してください" value="${no}">
                <c:if test="${not empty noError}">
                    <div class="text-danger mt-1">${noError}</div>
                </c:if>
                <c:if test="${not empty noDuplicateError}">
                    <div class="text-danger mt-1">${noDuplicateError}</div>
                </c:if>
            </div>

                <div class="form-row">
                    <label for="studentNo">学生番号</label>
                    <input type="text" id="studentNo" name="no" value="${no}" required placeholder="例: 2231111">
                    <div class="form-error">${noError} ${noDuplicateError}</div>
                </div>
            <%-- ⑥, ⑦ 氏名 --%>
            <div class="mb-3">
                <label for="studentName" class="form-label fw-bold">氏名</label>
                <input type="text" class="form-control" id="studentName" name="name" placeholder="氏名を入力してください" value="${name}">
                <c:if test="${not empty nameError}">
                    <div class="text-danger mt-1">${nameError}</div>
                </c:if>
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
            <%-- ⑧, ⑨ クラス --%>
            <div class="mb-3">
                <label for="classNum" class="form-label fw-bold">クラス</label>
                <select class="form-select" id="classNum" name="classNum">
                    <option value="101" <c:if test="${classNum == '101'}">selected</c:if>>101</option>
                    <option value="102" <c:if test="${classNum == '102'}">selected</c:if>>102</option>
                </select>
            </div>

            <%-- ⑩ 登録ボタン --%>
            <div class="mt-4">
                <button type="submit" class="btn btn-secondary">登録して終了</button>
            </div>
        </form>

        <%-- ⑪ 戻るリンク --%>
        <%--
           2枚目の画像ではこのリンクが左のメニュー下にありますが、
           元のコードの構造に基づき、フォームの下に配置しています。
           もしサイドバーに配置したい場合は、このリンクを side-bar の div の中に移動させてください。
        --%>
        <div class="mt-4">
             <a href="/JavaSD/main/MMNU001.jsp">戻る</a>
        </div>
    </div>
</div>

<jsp:include page="../base/footer.html"></jsp:include>