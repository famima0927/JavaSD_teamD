<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>学生情報登録</title>
    <style>
        .error {
            color: red;
            font-size: 0.9em;
        }
    </style>
</head>
<body>

<h2>学生情報登録</h2>

<form action="registerStudent" method="post">
    <label>入学年度</label><br>
    <select name="entYear">
        <option value="">選択してください</option>
        <option value="2022" <c:if test="${entYear == '2022'}">selected</c:if>>2022</option>
        <option value="2023" <c:if test="${entYear == '2023'}">selected</c:if>>2023</option>
        <option value="2024" <c:if test="${entYear == '2024'}">selected</c:if>>2024</option>
    </select><br>
    <c:if test="${not empty entYearError}">
        <div class="error">${entYearError}</div>
    </c:if>
    <br>

    <label>学生番号</label><br>
    <input type="text" name="no" placeholder="学生番号を入力してください" value="${no}"><br>
    <c:if test="${not empty noError}">
        <div class="error">${noError}</div>
    </c:if>
    <c:if test="${not empty noDuplicateError}">
        <div class="error">${noDuplicateError}</div>
    </c:if>
    <br>

    <label>氏名</label><br>
    <input type="text" name="name" placeholder="氏名を入力してください" value="${name}"><br>
    <c:if test="${not empty nameError}">
        <div class="error">${nameError}</div>
    </c:if>
    <br>

    <label>クラス</label><br>
    <select name="classNum">
        <option value="101" <c:if test="${classNum == '101'}">selected</c:if>>101</option>
        <option value="102" <c:if test="${classNum == '102'}">selected</c:if>>102</option>
    </select><br><br>

    <button type="submit">登録して終了</button>
</form>

<br>
<a href="menu.jsp">戻る</a>

</body>
</html>
