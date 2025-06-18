<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  request.setAttribute("bodyClass", "menu-body");
%>

<jsp:include page="../base/header.jsp"></jsp:include>
	<div class="container">
		<div class="side-bar">
			<jsp:include page="../base/base.jsp"></jsp:include>
		</div>
		<div class="main">
			<%-- ① 成績参照 --%>
			<div class="bg-light p-3">
		        <h5 class="mb-0 fw-bold">成績一覧(科目)</h5>
		    </div>
			<%-- ⑮ 全体を囲むボックス --%>
			<div class="card p-4 mb-4 border rounded">

				<%-- 科目情報での検索フォーム --%>
				<form action="TestListController" method="get">
					<%-- ⑮ 科目情報識別コード (hidden) --%>
					<input type="hidden" name="f" value="sj">

					<%-- ② 科目情報 ラベル --%>
					<h5 class="mb-3">科目情報</h5>

					<div class="row g-3 align-items-end">
						<%-- ③ 入学年度 --%>
						<div class="col-md">
							<label for="entYearSelect" class="form-label">入学年度</label>
							<%-- ⑥ 入学年度セレクトボックス --%>
							<select id="entYearSelect" name="f1_ent_year" class="form-select">
								<option value="0">--------</option>
								<c:forEach var="y" items="${ent_year_set}">
									<option value="${y}" <c:if test="${y == ent_year}">selected</c:if>>${y}</option>
								</c:forEach>
							</select>
						</div>

						<%-- ④ クラス --%>
						<div class="col-md">
							<label for="classNumSelect" class="form-label">クラス</label>
							<%-- ⑦ クラスセレクトボックス --%>
							<select id="classNumSelect" name="f2_class_num" class="form-select">
								<option value="0">--------</option>
								<c:forEach var="c" items="${class_num_set}">
									<option value="${c}" <c:if test="${c == class_num}">selected</c:if>>${c}</option>
								</c:forEach>
							</select>
						</div>

						<%-- ⑤ 科目 --%>
						<div class="col-md">
							<label for="subjectSelect" class="form-label">科目</label>
							<%-- ⑧ 科目セレクトボックス --%>
							<select id="subjectSelect" name="f3_subject" class="form-select">
								<option value="0">--------</option>
								<c:forEach var="s" items="${subjects}">
									<option value="${s.cd}" <c:if test="${s.cd == subject_cd}">selected</c:if>>${s.name}</option>
								</c:forEach>
							</select>
						</div>

						<%-- ⑨ 検索ボタン --%>
						<div class="col-md-auto">
							<button type="submit" name="search_action" value="subject_search" class="btn btn-secondary">検索</button>
						</div>
					</div>

					<%-- 検索条件不足エラーメッセージ表示エリア【変数変更お願いします】 --%>
					<c:if test="${not empty criteria_error}">
						<div class="text-danger mt-2">
							${criteria_error}
						</div>
					</c:if>
				</form>

				<hr class="my-4">

				<%-- 学生番号での検索フォーム --%>
				<form action="TestListController" method="get">
					<%-- ⑩ 学生情報 ラベル --%>
					<h5 class="mb-3">学生情報</h5>
					<div class="row g-3 align-items-end">
						<%-- ⑪ 学生番号 --%>
						<div class="col-md-5">
							<label for="studentNoInput" class="form-label">学生番号</label>
							<%-- ⑫ 学生番号入力欄 --%>
							<input id="studentNoInput" type="text" name="f5_student_no" class="form-control" placeholder="学生番号を入力" required value="${f5_student_no}">
						</div>

						<%-- ⑬ 検索ボタン --%>
						<div class="col-md-auto">
							<button type="submit" name="search_action" value="student_search" class="btn btn-secondary">検索</button>
						</div>
					</div>
				</form>
			</div>

			<%-- ⑭ 利用方法案内メッセージ --%>
			<p class="text-info">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>

			<%-- 検索結果なしエラーメッセージ表示エリア【変数変更お願いします】 --%>
						<c:if test="${not empty no_results_error}">
							<p class="text-danger">
								${no_results_error}
							</p>
						</c:if>

			<%-- 検索結果がある場合のみ区切り線を表示 --%>
			<c:if test="${not empty subject_search_results or not empty student_search_results}">
				<hr>
			</c:if>

			<%-- ★★★ 科目検索の結果表示エリア ★★★ --%>
			<c:if test="${not empty subject_search_results}">
				<div class="content-box">
					<h4>検索結果：科目別成績</h4>
					<table class="table table-hover"> <%-- Bootstrapのテーブルクラスを適用 --%>
						<thead>
							<tr><th>入学年度</th><th>クラス</th><th>学生番号</th><th>氏名</th><th>1回目</th><th>2回目</th></tr>
						</thead>
						<tbody>
							<c:forEach var="student" items="${subject_search_results}">
								<tr>
									<td>${student.entYear}</td>
									<td>${student.classNum}</td>
									<td>${student.studentNo}</td>
									<td>${student.studentName}</td>
									<td>${student.getScore(1) != null ? student.getScore(1) : '-'}</td>
									<td>${student.getScore(2) != null ? student.getScore(2) : '-'}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>

		</div>
	</div>
<jsp:include page="../base/footer.html"></jsp:include>