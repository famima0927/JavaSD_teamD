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

  <div class="main">
  <div class="menu-header">科目情報登録</div>

   <form action="${pageContext.request.contextPath}/SubjectRegisterServlet" method="post" >
<%-- 学生番号 --%>
      <%-- 学生名の入力（必須） --%>
      <label>科目コード</label><br>
      <label><input type="text" name="cd" value="科目コードを入力してください" required></label><br>
      <%-- コース選択（必須） --%>
      <label>科目名</label><br>
      <label><input type="text" name="name" value="科目名を入力してください" required></label><br>
      <%-- 送信ボタン --%><br>
      <button type="submit">登録</button>

  </form>
  <a href="${pageContext.request.contextPath}/SubjectListServlet">戻る</a>


    <%--
      エラーメッセージ表示。
      error が存在する場合に表示（主にバリデーションエラーや例外時）。
    --%>
    <c:if test="${not empty error}">
      <p style="color: red">${error}</p>
    </c:if>

 </div>
 </div>