<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

	<meta charset="UTF-8">
	<%-- 変数：param.titleに格納されている値をタイトルとして使う --%>
	<title>${ param.title }</title>
</head>
<body>
<%--tableで二分割に --%>
<table style=height:100%;width:100%;padding:5px; border="1">
 <tr>
 <td style=height:100%;width:15%;padding:5px; valign="top">
<c:import url="/menu.jsp"/>
<br>
</td>
<td style=height:100%;padding:10px; valign="top">
<%-- 変数：param.bodyに格納されている値をタイトルとして使う --%>
${ param.body }
</td>
</table>
</body>
</html>