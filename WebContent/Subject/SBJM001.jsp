<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
  request.setAttribute("bodyClass", "menu-body");
%>
<style>

    .right-align {
      text-align: right; /* テキストを右寄せ */
    }

  </style>
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
        <div class="main col-md-10 py-4" style="margin-bottom: 12rem;">
<div class="bg-light border px-3 py-2 mb-3 fw-bold">科目管理</div>
   <div class="right-align">
  <a href="<%= request.getContextPath() %>/SubjectCreate">新規登録</a></div>
    <!-- 一覧表のテーブル（見出し） -->
    <table class="table table-hover">
      <thead>
        <tr>
           <th >科目コード</th>
           <th>科目名</th>
           <th></th>
           <th></th>

        </tr>
      </thead>

      <tbody>
         <c:forEach items="${subjects}" var="subj" varStatus="st">
      <tr>
        <td>${subj.cd}</td>
        <td>${subj.name}</td>
        <td><a href="<%= request.getContextPath() %>/SubjectEdit?id=${subj.cd}">変更</a></td>
        <td><a href="<%= request.getContextPath() %>/SubjectDelete?id=${subj.cd}">削除</a></td>



      </tr>
    </c:forEach>
      </tbody>
    </table>


  </div>
</div>
</div>

</div>
<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
    <jsp:include page="../base/footer.jsp" />
</div>
