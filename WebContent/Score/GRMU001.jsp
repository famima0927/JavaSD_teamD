<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    // このページ専用のCSSクラスをリクエストスコープにセット
    request.setAttribute("bodyClass", "menu-body");
%>

<jsp:include page="../base/header.jsp"></jsp:include>

    <div class="container">
        <div class="side-bar">
            <jsp:include page="../base/base.jsp"></jsp:include>
        </div>
        <div class="main">
            <div class="menu-header">成績管理</div>
            <div class="content-box">
                <%-- ① 検索フォーム --%>
                <form action="TestRegistController" method="get" class="search-form">
                    <table>
                        <thead>
                            <tr>
                                <th>入学年度</th>
                                <th>クラス</th>
                                <th>科目</th>
                                <th>回数</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <select name="ent_year">
                                        <option value="0">--------</option>
                                        <c:forEach var="year" items="${ent_year_set}">
                                            <option value="${year}" <c:if test="${year == ent_year}">selected</c:if>>${year}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select name="class_num">
                                        <option value="0">--------</option>
                                        <c:forEach var="classNum" items="${class_num_set}">
                                            <option value="${classNum}" <c:if test="${classNum == class_num}">selected</c:if>>${classNum}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select name="subject_cd">
                                        <option value="0">--------</option>
                                        <c:forEach var="subject" items="${subjects}">
                                            <option value="${subject.cd}" <c:if test="${subject.cd == subject_cd}">selected</c:if>>${subject.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select name="test_no">
                                        <option value="0">--------</option>
                                        <c:forEach var="no" items="${test_no_set}">
                                            <option value="${no}" <c:if test="${no == test_no}">selected</c:if>>${no}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <button type="submit">検索</button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>

                <%-- ② 検索結果表示 & 成績入力フォーム --%>
                <%-- test_listが空でない場合にのみ表示 --%>
                <c:if test="${not empty test_list}">
                    <form action="TestRegistController" method="post" class="result-form">



						<input type="hidden" name="ent_year" value="${ent_year}">
       					<input type="hidden" name="class_num" value="${class_num}">


                        <%-- どの科目のどの回に対する成績かをコントローラーに伝えるための隠しフィールド --%>
                        <input type="hidden" name="subject_cd" value="${subject_cd}">
                        <input type="hidden" name="test_no" value="${test_no}">

                        <table class="result-table">
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
                                <%-- Controllerから渡された成績リスト(test_list)をループで表示 --%>
                                <c:forEach var="student" items="${test_list}">
                                    <tr>
                                        <td>${student.entYear}</td>
				                        <td>${student.classNum}</td>
				                        <td>${student.studentNo}</td>
				                        <td>${student.studentName}</td>
				                        <td>
				                            <%-- ★ value属性を修正 ★ --%>
				                            <%-- エラーがあった場合は入力値を、なければDBの値を表示 --%>
				                            <input type="text" name="point_${student.studentNo}"
				                                   value="${not empty originalPoints ? originalPoints[student.studentNo] : student.points[test_no]}"
				                                   maxlength="3">

				                            <%-- ★ エラーメッセージ表示部分を追加 ★ --%>
				                            <c:if test="${not empty errors[student.studentNo]}">
				                                <span class="error-message">${errors[student.studentNo]}</span>
				                            </c:if>
				                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <div class="form-actions">
                            <button type="submit">登録</button>
                        </div>
                    </form>
                </c:if>

            </div>
        </div>
    </div>

    <jsp:include page="../base/footer.html"></jsp:include>