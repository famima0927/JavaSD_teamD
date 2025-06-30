<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
  request.setAttribute("bodyClass", "menu-body");
%>
<%-- header.jspでBootstrapのCSSが読み込まれていることを前提とします --%>
<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
    <jsp:include page="../base/header.jsp" />
</div>

<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
<div class="container-fluid">
    <div class="row">
        <%-- サイドバをインクルード --%>
        <%-- この testtest.jsp の中身が <div class="col-md-2 ..."> で始まっている想定です --%>
        <jsp:include page="../base/base.jsp" />

        <%-- ★★★ 修正点: mainクラスに col-md-10 を追加 ★★★ --%>
        <div class="main col-md-10 py-4">
  <div class="bg-light border px-3 py-2 mb-3 fw-bold fs-5">科目情報変更
	</div>
    <form action="SubjectEditExecute" method="post">

      <%-- 科目コード (表示のみ) --%>
      <div class="mb-3">
        <label for="cd" class="form-label">科目コード</label>
        <%-- ★ここを変更★ classを "form-control" から "form-control-plaintext" に変更 --%>
        <input type="text" class="form-control-plaintext" id="cd" name="cd" value="${subject.cd}" readonly>
      </div>

      <%-- 科目名 (入力必須) --%>
      <div class="mb-3">
        <label for="name" class="form-label">科目名</label>
        <input type="text" class="form-control" id="name" name="name" value="${subject.name}" required>
      </div>

      <%-- エラーメッセージ表示 --%>
      <c:if test="${not empty error}">
        <div class="mb-3">
          <p class="text-danger">${error}</p>
        </div>
      </c:if>

      <%-- 変更ボタン --%>
      <button type="submit" class="btn btn-primary">変更</button>
    </form>

    <%-- 戻るリンク (フォームの外に配置) --%>
    <div class="mt-3">
        <a href="${pageContext.request.contextPath}/SubjectListController">戻る</a>
    </div>

  </div>
</div>
</div>
</div>
<%-- フッターがある場合は、ここにフッターのincludeを記述します --%>
<%-- <jsp:include page="../base/footer.jsp"></jsp:include> --%>
<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
    <jsp:include page="../base/footer.jsp" />
</div>

