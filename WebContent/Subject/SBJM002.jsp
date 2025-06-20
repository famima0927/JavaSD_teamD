<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
  request.setAttribute("bodyClass", "menu-body");
%>
<style>
    .right-align {
      text-align: right; /* テキストを右寄せ */
    }
    .right-align a {
      display: inline-block; /* インラインブロック要素として表示 */
    }
  </style>
<jsp:include page="../base/header.jsp"></jsp:include>

<div class="container">
  <div class="side-bar">
    <jsp:include page="../base/base.jsp"></jsp:include>
  </div>

<div class="main p-4">

  <%-- ① 見出し --%>
  <div class="bg-light border px-3 py-2 mb-3 fw-bold">科目情報登録</div>

  <form action="${pageContext.request.contextPath}/SubjectCreateExecute" method="post">

    <%-- ②, ③ 科目コード（縦並び） --%>
    <div class="mb-3">
      <label for="subjectCd" class="form-label">科目コード</label>
      <input type="text" class="form-control" id="subjectCd" name="cd" placeholder="科目コードを入力してください" required>
    </div>

    <%-- ④, ⑤ 科目名（縦並び） --%>
    <div class="mb-3">
      <label for="subjectName" class="form-label">科目名</label>
      <input type="text" class="form-control" id="subjectName" name="name" placeholder="科目名を入力してください" required>
    </div>

    <%-- ⑥ 登録ボタン（左寄せ） --%>
    <div class="mt-4">
      <button type="submit" class="btn btn-primary">登録</button>
    </div>

    <%-- ⑦ 戻るリンク（左寄せ） --%>
    <div class="mt-2">
      <a href="${pageContext.request.contextPath}/SubjectListController">戻る</a>
    </div>

  </form>

  <%-- エラーメッセージ --%>
  <c:if test="${not empty error}">
    <div class="alert alert-danger mt-4" role="alert">
      ${error}
    </div>
  </c:if>

</div>
</div>