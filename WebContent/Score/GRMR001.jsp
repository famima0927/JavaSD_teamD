<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  request.setAttribute("bodyClass", "menu-body");
%>

<jsp:include page="../base/header.jsp"></jsp:include>
	<div class = "container">
		<div class = "side-bar">
			<jsp:include page="../base/base.jsp"></jsp:include>
		</div>
		<div class = "main">
			<div class="menu-header">成績参照</div>

    			<!-- 科目情報での検索フォーム -->
    			<form action="TestListController" method="get" class="search-form">
        		<!-- ② 科目情報 (このセクション全体) -->
        		<!-- ⑮ 科目情報識別コード -->
        		<input type="hidden" name="f" value="sj">

				        <table>
				            <tbody>
				                <tr>
				                    <!-- ③ ヘッダー(入学年度) -->
				                    <th>入学年度</th>
				                    <!-- ④ ヘッダー(クラス) -->
				                    <th>クラス</th>
				                    <!-- ⑤ ヘッダー(科目) -->
				                    <th>科目</th>
				                    <td></td> <!-- ボタン用の空きセル -->
				                </tr>
				                <tr>
				                    <td>
				                        <!-- ⑥ 入学年度セレクトボックス -->
				                         <select name="f1_ent_year">
		                                    <option value="0">--------</option>
		                                    <c:forEach var="y" items="${ent_year_set}"><option value="${y}" <c:if test="${y == ent_year}">selected</c:if>>${y}</option></c:forEach>
		                                 </select>
				                    </td>
				                    <td>
				                        <!-- ⑦ クラスセレクトボックス -->
				                        <select name="f2_class_num">
		                                	<option value="0">--------</option>
		                                    <c:forEach var="c" items="${class_num_set}"><option value="${c}" <c:if test="${c == class_num}">selected</c:if>>${c}</option></c:forEach>
                                		</select>
				                    </td>
				                    <td>
				                        <!-- ⑧ 科目セレクトボックス -->
				                        <select name="f3_subject">
                                  		    <option value="0">--------</option>
                                	   	    <c:forEach var="s" items="${subjects}"><option value="${s.cd}" <c:if test="${s.cd == subject_cd}">selected</c:if>>${s.name}</option></c:forEach>
                                		</select>
				                    </td>
				                    <td>
				                        <!-- ⑨ 検索ボタン (イベント31) -->
				                        <td><button type="submit" name="search_action" value="subject_search">科目で検索</button></td>
				                    </td>
				                </tr>
				            </tbody>
				        </table>
				    </form>

			<%-- ② 学生番号検索フォーム --%>
            <div class="content-box">
                <h4>学生番号で検索</h4>
                <form action="TestListController" method="get" class="search-form">
                    <table>
                        <thead><tr><th>学生番号</th><th></th></tr></thead>
                        <tbody><tr>
                            <td><input type="text" name="f5_student_no" placeholder="学生番号を入力" value="${f5_student_no}"></td>
                            <td><button type="submit" name="search_action" value="student_search">学生番号で検索</button></td>
                        </tr></tbody>
                    </table>
                </form>
            </div>

			<!-- ⑭ 利用方法案内メッセージ -->
			<p>科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>

			<hr>

			<%-- ★★★ 科目検索の結果表示エリア ★★★ --%>
			<c:if test="${not empty subject_search_results}">
			    <div class="content-box">
			        <h4>検索結果：科目別成績</h4>
			        <table class="result-table">
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

            <%-- ★★★ 学生番号検索の結果表示エリア ★★★ --%>
            <c:if test="${not empty student_search_results}">
                <div class="content-box">
                    <h4>検索結果：${student.name}さんの成績</h4>
                    <table class="result-table">
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
<jsp:include page="../base/footer.html"></jsp:include>