<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        <%-- サイドバー --%>
        <jsp:include page="../base/base.jsp" />

        <%-- メインコンテンツ --%>
        <div class="main col-md-10 py-4" style="margin-bottom: 18rem;">

            <%-- ① タイトル部分 --%>
            <div class="bg-light border px-3 py-2 mb-3 fw-bold">
                成績管理
            </div>

            <%-- ② 検索フォーム部分 --%>
            <div class="border rounded-bottom p-4 mb-4">
                <form action="TestRegistController" method="get" class="search-form">
                    <%-- (フォームの中身は変更なし) --%>
                    <div class="row g-3 align-items-end">
                        <div class="col">
                            <label for="ent-year-select" class="form-label">入学年度</label>
                            <select name="ent_year" id="ent-year-select" class="form-select">
                                <option value="0">--------</option>
                                <c:forEach var="year" items="${ent_year_set}">
                                    <option value="${year}" <c:if test="${year == ent_year}">selected</c:if>>${year}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col">
                            <label for="class-num-select" class="form-label">クラス</label>
                            <select name="class_num" id="class-num-select" class="form-select">
                                <option value="0">--------</option>
                                <c:forEach var="classNum" items="${class_num_set}">
                                    <option value="${classNum}" <c:if test="${classNum == class_num}">selected</c:if>>${classNum}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col">
                            <label for="subject-cd-select" class="form-label">科目</label>
                            <select name="subject_cd" id="subject-cd-select" class="form-select">
                                <option value="0">--------</option>
                                <c:forEach var="subject" items="${subjects}">
                                    <option value="${subject.cd}" <c:if test="${subject.cd == subject_cd}">selected</c:if>>${subject.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col">
                            <label for="test-no-select" class="form-label">回数</label>
                            <select name="test_no" id="test-no-select" class="form-select">
                                <option value="0">--------</option>
                                <c:forEach var="no" items="${test_no_set}">
                                    <option value="${no}" <c:if test="${no == test_no}">selected</c:if>>${no}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-auto">
                            <button type="submit" class="btn btn-secondary">検索</button>
                        </div>
                    </div>
                </form>
            </div>
            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    ${error}
                </div>
            </c:if>

            <%-- ③ 検索結果表示 & 成績入力・削除フォーム --%>
            <c:if test="${not empty test_list}">
                <div class="mt-4">
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

                        <table class="table table-hover" style="table-layout: fixed; width: 100%;">
                            <thead>
                                <tr>
                                    <%-- ▼▼▼▼▼ ここから修正 ▼▼▼▼▼ --%>
                                    <%-- 各列の幅のバランスを調整します。合計が100%になるようにしてください。--%>
                                    <%-- この値はあくまで一例です。お好みの見た目になるように調整してください。 --%>
                                    <th style="width: 10%;">入学年度</th>
                                    <th style="width: 10%;">クラス</th>
                                    <th style="width: 15%;">学生番号</th>
                                    <th style="width: 20%;">氏名</th>      <%-- ← 氏名列を広く --%>
                                    <th style="width: 20%;">点数</th>      <%-- ← 点数列を狭く --%>
                                    <th class="text-center" style="width: 15%;">削除</th>
                                    <%-- ▲▲▲▲▲ ここまで修正 ▲▲▲▲▲ --%>
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

                                            <div class="small mt-1" style="min-height: 1.3em;">
                                                <c:if test="${not empty errors[student.studentNo]}">
                                                    <span class="text-danger">${errors[student.studentNo]}</span>
                                                </c:if>
                                            </div>
                                        </td>
                                        <td class="text-center">
                                            <input type="checkbox" class="btn-check" name="delete"
                                                   id="deleteCheck${student.studentNo}" value="${student.studentNo}" autocomplete="off">
                                            <label class="btn btn-outline-danger btn-sm" for="deleteCheck${student.studentNo}">
                                                <i class="bi bi-trash"></i> 削除
                                            </label>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                        <div class="mt-3 d-flex justify-content-start gap-3">
                            <button type="submit" name="action" value="regist" class="btn btn-primary px-4">登録して終了</button>
                            <button type="submit" name="action" value="update" class="btn btn-secondary px-4">リストを更新</button>
                        </div>
                    </form>
                </div>
            </c:if>
        </div>
    </div>
</div>
</div>
<<div style="margin-right: 200px; margin-left: 200px; overflow: auto;">
    <jsp:include page="../base/footer.jsp" />
</div>