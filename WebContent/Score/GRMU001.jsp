<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    // このページ専用のCSSクラスをリクエストスコープにセット
    request.setAttribute("bodyClass", "menu-body");
%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<jsp:include page="../base/header.jsp"></jsp:include>

    <div class="container">
        <div class="side-bar">
            <jsp:include page="../base/base.jsp"></jsp:include>
        </div>

		<div class="main">
		    <%-- ① タイトル部分 --%>
			<div class="bg-light border px-3 py-2 mb-3 fw-bold">成績管理
		    </div>

		    <%-- ② 検索フォーム部分 --%>
		    <div class="border rounded-bottom p-4">
		        <form action="TestRegistController" method="get">
		            <div class="row g-3 align-items-end">
		                <%-- 入学年度 --%>
		                <div class="col">
		                    <label for="ent-year-select" class="form-label">入学年度</label>
		                    <select name="ent_year" id="ent-year-select" class="form-select">
		                        <option value="0">--------</option>
		                        <c:forEach var="year" items="${ent_year_set}">
		                            <option value="${year}" <c:if test="${year == ent_year}">selected</c:if>>${year}</option>
		                        </c:forEach>
		                    </select>
		                </div>
		                <%-- クラス --%>
		                <div class="col">
		                    <label for="class-num-select" class="form-label">クラス</label>
		                    <select name="class_num" id="class-num-select" class="form-select">
		                        <option value="0">--------</option>
		                        <c:forEach var="classNum" items="${class_num_set}">
		                            <option value="${classNum}" <c:if test="${classNum == class_num}">selected</c:if>>${classNum}</option>
		                        </c:forEach>
		                    </select>
		                </div>
		                <%-- 科目 --%>
		                <div class="col">
		                    <label for="subject-cd-select" class="form-label">科目</label>
		                    <select name="subject_cd" id="subject-cd-select" class="form-select">
		                        <option value="0">--------</option>
		                        <c:forEach var="subject" items="${subjects}">
		                            <option value="${subject.cd}" <c:if test="${subject.cd == subject_cd}">selected</c:if>>${subject.name}</option>
		                        </c:forEach>
		                    </select>
		                </div>
		                <%-- 回数 --%>
		                <div class="col">
		                    <label for="test-no-select" class="form-label">回数</label>
		                    <select name="test_no" id="test-no-select" class="form-select">
		                        <option value="0">--------</option>
		                        <c:forEach var="no" items="${test_no_set}">
		                            <option value="${no}" <c:if test="${no == test_no}">selected</c:if>>${no}</option>
		                        </c:forEach>
		                    </select>
		                </div>
		                <%-- 検索ボタン --%>
		                <div class="col-auto">
		                    <button type="submit" class="btn btn-secondary">検索</button>
		                </div>
		            </div>
		        </form>
		    </div> <%-- フォーム部分の囲みここまで --%>


		<%-- 検索結果表示ブロック --%>
		<c:if test="${not empty test_list}">
		    <div class="mt-5">
		        <%-- 科目と回数を表示 --%>
		        <c:forEach var="subject" items="${subjects}">
		            <c:if test="${subject.cd == subject_cd}">
		                <c:set var="selectedSubjectName" value="${subject.name}" />
		            </c:if>
		        </c:forEach>
		        <h5 class="mb-3">科目：${selectedSubjectName} (${test_no}回)</h5>

		        <form action="TestRegistController" method="post" class="result-form">
		            <input type="hidden" name="ent_year" value="${ent_year}">
		            <input type="hidden" name="class_num" value="${class_num}">
		            <input type="hidden" name="subject_cd" value="${subject_cd}">
		            <input type="hidden" name="test_no" value="${test_no}">

		            <table class="table table-hover">
		                <thead>
		                    <tr>
		                        <th>入学年度</th>
		                        <th>クラス</th>
		                        <th>学生番号</th>
		                        <th>氏名</th>
		                        <th>点数</th>
		                    </tr>
		                </thead>
		                <tbody>
						    <c:forEach var="student" items="${test_list}">
						        <tr>
						            <td>${student.entYear}</td>
						            <td>${student.classNum}</td>
						            <td>${student.studentNo}</td>
						            <td>${student.studentName}</td>
						            <td>
						                <input type="text" class="form-control" name="point_${student.studentNo}"
						                    value="${not empty originalPoints ? originalPoints[student.studentNo] : student.getScore(test_no)}"
						                    maxlength="3" style="max-width: 100px;">
						                <c:if test="${not empty errors[student.studentNo]}">
						                    <div class="text-danger small mt-1">${errors[student.studentNo]}</div>
						                </c:if>
						            </td>
						            <%-- ★★★ 削除用チェックボックスの列を追加 ★★★ --%>
						            <td class="text-center">
						                <input type="checkbox" class="form-check-input" name="delete" value="${student.studentNo}">
						            </td>
						        </tr>
						    </c:forEach>
						</tbody>
		            </table>

		            <div class="mt-3">
		                <button type="submit" class="btn btn-secondary">登録して終了</button>
		            </div>
		        </form>
		    </div>
		</c:if>
            <%-- ★★★ 検索結果ブロックここまで ★★★ --%>

		</div> <%-- div.main の閉じタグ --%>

    </div> <%-- div.container の閉じタグ --%>

    <jsp:include page="../base/footer.html"></jsp:include>