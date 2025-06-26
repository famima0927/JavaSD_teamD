<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    request.setAttribute("bodyClass", "menu-body");
%>
<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
    <jsp:include page="../base/header.jsp" />
</div>


<%-- ★★★ ページ全体のレイアウト構造 ★★★ --%>

<div class="container-fluid">
    <div class="row">
        <%-- サイドバーをインクルード --%>
        <%-- この testtest.jsp の中身が <div class="col-md-2 ..."> で始まっている想定です --%>
        <jsp:include page="../base/base.jsp" />

        <%-- ★★★ 修正点: mainクラスに col-md-10 を追加 ★★★ --%>
        <div class="main col-md-10 py-4">
            <div class="bg-light border px-3 py-2 mb-3 fw-bold">成績参照</div>

            <%-- エラーメッセージ表示エリア --%>
            <c:if test="${not empty criteria_error or not empty no_results_error}">
                <div class="alert alert-danger" role="alert">
                    ${criteria_error} ${no_results_error}
                </div>
            </c:if>

            <div class="card p-4 mb-4 border rounded">
                <%-- 科目情報での検索フォーム --%>
                <form action="TestListController" method="get">
                    <h5 class="mb-3">科目情報</h5>
                    <div class="row g-3 align-items-end">
                        <div class="col-md">
                            <label for="entYearSelect" class="form-label">入学年度</label>
                            <select id="entYearSelect" name="f1_ent_year" class="form-select">
                                <option value="0">--------</option>
                                <c:forEach var="y" items="${ent_year_set}"><option value="${y}" <c:if test="${y == ent_year}">selected</c:if>>${y}</option></c:forEach>
                            </select>
                        </div>
                        <div class="col-md">
                            <label for="classNumSelect" class="form-label">クラス</label>
                            <select id="classNumSelect" name="f2_class_num" class="form-select">
                                <option value="0">--------</option>
                                <c:forEach var="c" items="${class_num_set}"><option value="${c}" <c:if test="${c == class_num}">selected</c:if>>${c}</option></c:forEach>
                            </select>
                        </div>
                        <div class="col-md">
                            <label for="subjectSelect" class="form-label">科目</label>
                            <select id="subjectSelect" name="f3_subject" class="form-select">
                                <option value="0">--------</option>
                                <c:forEach var="s" items="${subjects}"><option value="${s.cd}" <c:if test="${s.cd == subject_cd}">selected</c:if>>${s.name}</option></c:forEach>
                            </select>
                        </div>
                        <div class="col-md-auto">
                            <button type="submit" name="search_action" value="subject_search" class="btn btn-secondary">検索</button>
                        </div>
                    </div>
                </form>

                <hr class="my-4">

                <%-- 学生番号での検索フォーム --%>
                <form action="TestListController" method="get">
                    <h5 class="mb-3">学生情報</h5>
                    <div class="row g-3 align-items-end">
                        <div class="col-md-5">
                            <label for="studentNoInput" class="form-label">学生番号</label>
                            <input id="studentNoInput" type="text" name="f5_student_no" class="form-control" placeholder="学生番号を入力" value="${f5_student_no}">
                        </div>
                        <div class="col-md-auto">
                            <button type="submit" name="search_action" value="student_search" class="btn btn-secondary">検索</button>
                        </div>
                    </div>
                </form>
            </div>

            <p class="text-info">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>

            <c:if test="${not empty subject_search_results or not empty student_search_results}">
                <hr>
            </c:if>

            <%-- 科目検索の結果表示エリア --%>
            <c:if test="${not empty subject_search_results}">
                <div class="content-box">
                    <h4>検索結果：科目別成績</h4>
                    <table class="table table-hover">
                        <thead><tr><th>入学年度</th><th>クラス</th><th>学生番号</th><th>氏名</th><th>1回目</th><th>2回目</th></tr></thead>
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

            <%-- 学生番号検索の結果表示エリア --%>
            <c:if test="${not empty student_search_results}">
                <div class="content-box">
                    <h4>検索結果：${student.name}さんの成績</h4>
                    <table class="table table-hover">
                        <thead><tr><th>科目コード</th><th>科目名</th><th>回数</th><th>点数</th></tr></thead>
                        <tbody>
                            <c:forEach var="test" items="${student_search_results}">
                                <tr>
                                    <td>${test.subjectCd}</td>
                                    <td>${test.subjectName}</td>
                                    <td>${test.num}</td>
                                    <td>${test.point}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>
    </div>
</div>

<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
    <jsp:include page="../base/footer.jsp" />
</div>
<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
    <jsp:include page="../base/footer.jsp" />
</div>
