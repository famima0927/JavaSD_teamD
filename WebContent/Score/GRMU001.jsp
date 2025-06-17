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
			<%-- 元のJSPの <div class="main"> の内側をこれに置き換えます --%>
			<div class="main">

			    <%-- ① タイトル部分（枠線なし、背景色あり） --%>
			    <%-- Bootstrapユーティリティで card-header に似たスタイルを再現します --%>
			    <%-- h5とmb-0は見出しのサイズと余白を調整するためです --%>
			    <div class="bg-light p-3">
			        <h5 class="mb-0 fw-bold">成績管理</h5>
			    </div>

			    <%-- ② フォーム部分（枠線あり） --%>
			    <%-- borderで枠線、rounded-bottomで下側だけ角丸、p-4で内側の余白を設定 --%>
			    <div class="border rounded-bottom p-4">

			        <form action="TestRegistController" method="get">
			            <%-- ③ フォーム内の要素をグリッドシステムで均等配置します --%>
			            <%-- row: グリッド行の開始 --%>
			            <%-- g-3: 要素間の隙間を設定 --%>
			            <%-- align-items-end: 要素の下端を揃える --%>
			            <div class="row g-3 align-items-end">

			                <%-- 入学年度のセレクトボックス --%>
			                <%-- col: 利用可能な幅を均等に分割します --%>
			                <div class="col">
			                    <label for="ent-year-select" class="form-label">入学年度</label>
			                    <select name="ent_year" id="ent-year-select" class="form-select">
			                        <option value="0">--------</option>
			                        <c:forEach var="year" items="${ent_year_set}">
			                            <option value="${year}" <c:if test="${year == ent_year}">selected</c:if>>${year}</option>
			                        </c:forEach>
			                    </select>
			                </div>

			                <%-- クラスのセレクトボックス --%>
			                <div class="col">
			                    <label for="class-num-select" class="form-label">クラス</label>
			                    <select name="class_num" id="class-num-select" class="form-select">
			                        <option value="0">--------</option>
			                        <c:forEach var="classNum" items="${class_num_set}">
			                            <option value="${classNum}" <c:if test="${classNum == class_num}">selected</c:if>>${classNum}</option>
			                        </c:forEach>
			                    </select>
			                </div>

			                <%-- 科目のセレクトボックス --%>
			                <div class="col">
			                    <label for="subject-cd-select" class="form-label">科目</label>
			                    <select name="subject_cd" id="subject-cd-select" class="form-select">
			                        <option value="0">--------</option>
			                        <c:forEach var="subject" items="${subjects}">
			                            <option value="${subject.cd}" <c:if test="${subject.cd == subject_cd}">selected</c:if>>${subject.name}</option>
			                        </c:forEach>
			                    </select>
			                </div>

			                <%-- 回数のセレクトボックス --%>
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
			                <%-- col-auto: 中のコンテンツに必要な幅だけを確保します --%>
			                <div class="col-auto">
			                    <button type="submit" class="btn btn-secondary">検索</button>
			                </div>
			            </div>
			        </form>

			    </div> <%-- フォーム部分の囲みここまで --%>
			</div>


    <%-- 検索結果表示部分は変更不要なので、そのまま残します --%>
    <c:if test="${not empty test_list}">
        <div class="card mt-4"> <%-- 結果もカードで囲むと見栄えが良いです --%>
             <div class="card-body">
                <form action="TestRegistController" method="post" class="result-form">

                    <input type="hidden" name="ent_year" value="${ent_year}">
                    <input type="hidden" name="class_num" value="${class_num}">
                    <input type="hidden" name="subject_cd" value="${subject_cd}">
                    <input type="hidden" name="test_no" value="${test_no}">

                    <table class="table table-hover"> <%-- Bootstrapのテーブルスタイルを適用 --%>
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
                                        <%-- inputにもBootstrapのスタイルを適用 --%>
                                        <input type="text" class="form-control" name="point_${student.studentNo}"
                                               value="${not empty originalPoints ? originalPoints[student.studentNo] : student.points[test_no]}"
                                               maxlength="3" style="width: 100px;"> <%-- 幅を少し指定 --%>

                                        <c:if test="${not empty errors[student.studentNo]}">
                                            <div class="text-danger small mt-1">${errors[student.studentNo]}</div> <%-- エラーメッセージのスタイル --%>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="text-end"> <%-- ボタンを右寄せに --%>
                        <button type="submit" class="btn btn-primary">登録</button>
                    </div>
                </form>
            </div>
        </div>
    </c:if>

        </div>
    </div>

    <jsp:include page="../base/footer.html"></jsp:include>