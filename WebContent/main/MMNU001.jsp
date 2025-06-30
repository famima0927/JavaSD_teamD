<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  request.setAttribute("bodyClass", "menu-body");
%>

<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
    <jsp:include page="../base/header.jsp" />
</div>


<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
	<div class="container-fluid">
	    <div class="row">
	        <%-- サイドバをインクルード --%>
	        <%-- この testtest.jsp の中身が <div class="col-md-2 ..."> で始まっている想定です --%>
	        <jsp:include page="../base/base.jsp" />

	    <!-- メイン -->
		   <div class="col-md-10 py-4" style="margin-bottom: 18rem;"">
		      <div class="bg-light border px-3 py-2 mb-4 fw-bold">メニュー</div>

		      <div class="d-flex gap-4 justify-content-start">
		        <!-- 学生管理 -->
		        <div class="card shadow-sm border-0" style="width: 180px; height: 100px; background-color: #eacaca;">
		          <div class="card-body d-flex align-items-center justify-content-center">
		            <a href="${pageContext.request.contextPath}/StudentList" class="fw-bold text-decoration-none text-primary">学生管理</a>
		          </div>
		        </div>

		        <!-- 成績管理 -->
		<div class="card shadow-sm border-0" style="width: 200px; background-color: #cde3cd;">
		  <div class="card-body d-flex flex-column align-items-center">
		    <div class="fw-bold mb-2">成績管理</div>

		    <!-- 成績登録リンク -->
		    <a href="${pageContext.request.contextPath}/servlet/TestRegistController"
		       class="fw-bold text-decoration-none text-primary mb-1">
		      成績登録
		    </a>

		    <!-- 成績参照リンク -->
		    <a href="${pageContext.request.contextPath}/servlet/TestListController"
		       class="fw-bold text-decoration-none text-primary">
		      成績参照
    </a>
  </div>
</div>

		        <!-- 科目管理 -->
		        <div class="card shadow-sm border-0" style="width: 180px; height: 100px; background-color: #cfcfe7;">
		          <div class="card-body d-flex align-items-center justify-content-center">
		            <a href="${pageContext.request.contextPath}/SubjectListController" class="fw-bold text-decoration-none text-primary">科目管理</a>
		          </div>
		        </div>
		      </div>
		    </div>
	  </div>
	</div>
</div>
<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
    <jsp:include page="../base/footer.jsp" />
</div>

