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
  <div class="menu-header">科目一覧</div>
   <div class="right-align">
  <a href="<%= request.getContextPath() %>/main/Insert">新規登録</a></div>
    <!-- 一覧表のテーブル（見出し） -->
    <table>
      <thead>
        <tr>
          <th>科目コード</th>
          <th>科目名</th>
        </tr>
        <hr>
      </thead>

      <tbody>
         <c:forEach items="${subjects}" var="subj" varStatus="st">
      <tr>
        <td>${subj.cd}</td>
        <td>${subj.name}</td>
        <td><a href="<%= request.getContextPath() %>/main/index">変更</a></td>
        <td><a href="<%= request.getContextPath() %>/main/index">削除</a></td>
      </tr>
      <hr>
    </c:forEach>
      </tbody>
    </table>

    <!-- topページ -->
    <a href="<%= request.getContextPath() %>/main/index">Topページへ</a>
  </div>
</div>

<jsp:include page="../base/footer.html"></jsp:include>
