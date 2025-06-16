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
  <div class="menu-header">科目情報変更</div>
  <form action="SubjectEditServlet" method="post">
  <label>科目コード</label>
  <input type="text" name="cd" value="${subject.cd}"readonly ><br>
  <%-- コース選択（必須） --%>
      <label>科目名：</label>
      <input type="text" name="name" required><br>
      <%-- 送信ボタン --%>
      <input type="submit" value="変更">
  </form>



  </div>
  </div>