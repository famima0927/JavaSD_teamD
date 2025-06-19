<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%

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

    <%-- メインコンテンツエリア --%>
    <div class="container-fluid p-4">

        <%-- ① タイトル --%>
        <h2 class="border-bottom pb-2 mb-4">学生情報登録</h2>

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

            <%-- ⑥, ⑦ 氏名 --%>
            <div class="mb-3">
                <label for="studentName" class="form-label fw-bold">氏名</label>
                <input type="text" class="form-control" id="studentName" name="name" placeholder="氏名を入力してください" value="${name}">
                <c:if test="${not empty nameError}">
                    <div class="text-danger mt-1">${nameError}</div>
                </c:if>
            </div>

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