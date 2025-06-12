<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
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
    <form action="/grade/search" method="post" class="search-form">
        <table>
            <thead>
                <tr>
                    <!-- ②～⑤ ヘッダー (分類:その他, 種別:th) -->
                    <th>入学年度</th>
                    <th>クラス</th>
                    <th>科目</th>
                    <th>回数</th>
                    <th> </th> <%-- ボタン列のための空ヘッダー --%>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <!-- ⑥ 入学年度セレクトボックス -->
                    <td>
                        <select name="f1">
                            <option value="">--------</option>
                            <%-- Controllerから渡された入学年度リスト(yearList)をループで表示 --%>
                            <c:forEach var="y" items="${yearList}">
                                <option value="${y}" <c:if test="${y == requestScope.year}">selected</c:if>>${y}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <!-- ⑦ クラスセレクトボックス -->
                    <td>
                        <select name="f2">
                            <option value="">--------</option>
                            <%-- Controllerから渡されたクラスリスト(classList)をループで表示 --%>
                            <c:forEach var="c" items="${classList}">
                                <option value="${c.num}" <c:if test="${c.num == requestScope.num}">selected</c:if>>${c.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <!-- ⑧ 科目セレクトボックス -->
                    <td>
                        <select name="f3">
                            <option value="">--------</option>
                            <%-- Controllerから渡された科目リスト(subjectList)をループで表示 --%>
                            <c:forEach var="s" items="${subjectList}">
                                <option value="${s.cd}" <c:if test="${s.cd == requestScope.subjectCd}">selected</c:if>>${s.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <!-- ⑨ 回数セレクトボックス -->
                    <td>
                        <select name="f4">
                            <option value="">--------</option>
                            <%-- 回数用のリスト(countList)をループで表示 --%>
                            <c:forEach var="cnt" items="${countList}">
                                <option value="${cnt}" <c:if test="${cnt == requestScope.count}">selected</c:if>>${cnt}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <!-- ⑩ 検索ボタン -->
                    <td>
                        <button type="submit">検索</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </form>
</div>


		</div>
	</div>
<jsp:include page="../base/footer.html"></jsp:include>