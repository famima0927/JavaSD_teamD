<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    request.setAttribute("bodyClass", "menu-body");
%>
<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
    <jsp:include page="../base/header.jsp" />
</div>

<div class = "allpad">
<div class="container-fluid">
    <div class="row">
        <%-- サイドバーをインクルード --%>
        <jsp:include page="../base/base.jsp" />

        <div class="main col-md-10 py-4" style="margin-bottom: 7rem;">
            <div class="bg-light border px-3 py-2 mb-3 fw-bold fs-5">成績参照</div>

            <%-- エラーメッセージ表示エリア --%>
            <c:if test="${not empty criteria_error or not empty no_results_error}">
                <div class="alert alert-danger" role="alert">
                    ${criteria_error} ${no_results_error}
                </div>
            </c:if>

            <%-- 科目情報での検索フォーム --%>
            <div class="card p-4 mb-3 border rounded">
                <form action="TestListController" method="get">
                    <div class="row g-3 align-items-end">
                        <%-- ★★★ 修正点1: pb-1 (padding-bottom) を追加してテキストを少し持ち上げる ★★★ --%>
                        <div class="col-md-2 fw-bold pb-3">
                            科目情報
                        </div>
                        <%-- 入学年度 --%>
                        <div class="col-md-2">
                            <label for="entYearSelect" class="form-label">入学年度</label>
                            <select id="entYearSelect" name="f1_ent_year" class="form-select">
                                <option value="0">--------</option>
                                <c:forEach var="y" items="${ent_year_set}"><option value="${y}" <c:if test="${y == ent_year}">selected</c:if>>${y}</option></c:forEach>
                            </select>
                        </div>
                        <%-- クラス --%>
                        <div class="col-md-2">
                            <label for="classNumSelect" class="form-label">クラス</label>
                            <select id="classNumSelect" name="f2_class_num" class="form-select">
                                <option value="0">--------</option>
                                <c:forEach var="c" items="${class_num_set}"><option value="${c}" <c:if test="${c == class_num}">selected</c:if>>${c}</option></c:forEach>
                            </select>
                        </div>
                        <%-- 科目 --%>
                        <div class="col-md-4">
                            <label for="subjectSelect" class="form-label">科目</label>
                            <select id="subjectSelect" name="f3_subject" class="form-select">
                                <option value="0">--------</option>
                                <c:forEach var="s" items="${subjects}"><option value="${s.cd}" <c:if test="${s.cd == subject_cd}">selected</c:if>>${s.name}</option></c:forEach>
                            </select>
                        </div>
                        <%-- 検索ボタン --%>
                        <div class="col-md-auto px-4 pb-2">
                            <button type="submit" name="search_action" value="subject_search" class="btn btn-secondary">検索</button>
                        </div>
                    </div>
                </form>

                <hr class="my-4">

                <%-- 学生番号での検索フォーム --%>
                <form action="TestListController" method="get">
                    <div class="row g-3 align-items-end">
                        <%-- ★★★ 修正点2: こちらも同様に pb-1 を追加 ★★★ --%>
                        <div class="col-md-2 fw-bold pb-3">
                            学生情報
                        </div>
                        <%-- 学生番号入力欄 --%>
                        <div class="col-md-5">
                            <label for="studentNoInput" class="form-label">学生番号</label>
                            <input id="studentNoInput" type="text" name="f5_student_no" class="form-control" placeholder="学生番号を入力" value="${f5_student_no}">
                        </div>
                        <%-- 検索ボタン; --%>
                        <div class="col-md-auto px-4 pb-2">
                            <button type="submit" name="search_action" value="student_search" class="btn btn-secondary">検索</button>
                        </div>
                    </div>
                </form>
            </div>

            <p class="text-info">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>

            <%-- (以下、変更なし) --%>
            <c:if test="${not empty subject_search_results or not empty student_search_results}">
                <hr>
            </c:if>

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
</div>

<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
    <jsp:include page="../base/footer.jsp" />
</div>