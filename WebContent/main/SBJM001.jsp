<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
  request.setAttribute("bodyClass", "menu-body");
%>

<jsp:include page="../base/header.jsp"></jsp:include>
<style>
  .btn-insert {
    float: right;
  }
</style>
<div class="container">
  <div class="side-bar">
    <jsp:include page="../base/base.jsp"></jsp:include>
  </div>

  <div class="main">
  <a href="<%= request.getContextPath() %>/main/Insert">新規登録</a>
    <!-- 一覧表のテーブル（見出し） -->
    <table>
      <thead>
        <tr>
          <th>科目コード</th>
          <th>科目名</th>
        </tr>
      </thead>
      <tbody>
         <c:forEach items="${subjects}" var="subj" varStatus="st">
      <tr>

        <td>${subj.cd}</td>
        <td>${subj.name}</td>
        <td><a href="<%= request.getContextPath() %>/main/index">変更</a></td>
        <td><a href="<%= request.getContextPath() %>/main/index">削除</a></td>
      </tr>
    </c:forEach>
      </tbody>
    </table>

    <!-- topページ -->
    <a href="<%= request.getContextPath() %>/main/index">Topページへ</a>
  </div>
</div>

<jsp:include page="../base/footer.html"></jsp:include>
