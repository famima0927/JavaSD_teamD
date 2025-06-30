<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
  request.setAttribute("bodyClass", "menu-body");
%>
<%--
  header.jsp でBootstrapのCSSが読み込まれていることを前提としています。
  不要になったstyleタグは削除しました。
--%>
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
    <%-- ① ヘッダー部分 --%>
    <div class="bg-light border px-3 py-2 mb-3 fw-bold fs-5">科目情報削除</div>

    <%-- ② 確認メッセージ --%>
    <p>「<strong>${subject.name}</strong>」（<strong>${subject.cd}</strong>）を削除してもよろしいでしょうか？</p>

    <form action="SubjectDeleteExecute" method="post" class="mt-3">
      <%-- 削除対象の情報をhiddenで送信 --%>
      <input type="hidden" name="cd" value="${subject.cd}">
      <input type="hidden" name="name" value="${subject.name}">

      <%-- ③ 削除ボタン --%>
      <input type="submit" value="削除" class="btn btn-danger">
    </form>

    <%-- ④ 戻るリンク --%>
    <div class="mt-3">
        <a href="${pageContext.request.contextPath}/SubjectListController">戻る</a>
    </div>

    <%-- エラーメッセージ表示 (Bootstrapのalertコンポーネントを使用) --%>
    <c:if test="${not empty error}">
      <div class="alert alert-danger mt-4" role="alert">
        ${error}
      </div>
    </c:if>
  </div>
</div>
</div>
</div>
<%-- フッターがあればここにインクルードします --%>
<%-- <jsp:include page="../base/footer.jsp"></jsp:include> --%>
<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
    <jsp:include page="../base/footer.jsp" />
</div>

