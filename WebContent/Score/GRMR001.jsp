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
    			<form action="（ここに送信先のURLを記述）" method="post">
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
				                        <select name="f1">
				                            <option value="">--------</option>
				                            <!-- TODO: JSTL等で選択肢を動的に生成 -->
				                        </select>
				                    </td>
				                    <td>
				                        <!-- ⑦ クラスセレクトボックス -->
				                        <select name="f2">
				                            <option value="">--------</option>
				                            <!-- TODO: JSTL等で選択肢を動的に生成 -->
				                        </select>
				                    </td>
				                    <td>
				                        <!-- ⑧ 科目セレクトボックス -->
				                        <select name="f3">
				                            <option value="">--------</option>
				                            <!-- TODO: JSTL等で選択肢を動的に生成 -->
				                        </select>
				                    </td>
				                    <td>
				                        <!-- ⑨ 検索ボタン (イベント31) -->
				                        <button type="submit" name="op" value="31">検索</button>
				                    </td>
				                </tr>
				            </tbody>
				        </table>
				    </form>

				    <!-- 学生情報での検索フォーム -->
				    <form action="（ここに送信先のURLを記述）" method="post">
				        <!-- ⑩ 学生情報 -->
				        <p>学生情報</p>
				        <!-- ⑯ 学生情報識別コード -->
				        <input type="hidden" name="f" value="st">

				        <!-- ⑪ ヘッダー(学生番号)を含む入力エリア -->
				        <table>
				             <tbody>
				                <tr>
				                    <th>学生番号</th>
				                    <td>
				                        <!-- ⑫ 学生番号入力テキスト -->
				                        <input type="text" name="f4" placeholder="学生番号を入力してください" value="${f4}">
				                    </td>
				                    <td>
				                        <!-- ⑬ 検索ボタン (イベント32) -->
				                        <button type="submit" name="op" value="32">検索</button>
				                    </td>
				                </tr>
				            </tbody>
				        </table>
				    </form>

				    <!-- ⑭ 利用方法案内メッセージ -->
				    <p>科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>

    		</div>
    	</div>
<jsp:include page="../base/footer.html"></jsp:include>