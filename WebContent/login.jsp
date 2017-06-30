<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored = "false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/common.css" rel="stylesheet" type="text/css">
<title>ログイン</title>

</head>
<body>

<div class = "main-contents">
<center><a href="http://localhost:8080/LibrarySystem2/"><img src="Tottori-Library.png" alt="TAG index" border="0"></a></center>
<a href="./"  class="prev-btn">←戻る</a>

<c:if test = "${not empty errorMessages}">
	<div class = "errorMessages">
		<ul>
			<c:forEach items = "${errorMessages}" var = "message">
				<li><c:out value = "${message}"/>
			</c:forEach>
		</ul>
	</div>
	<c:remove var = "errorMessages" scope = "session"/>
</c:if>

<form action = "login" method = "post">
<br>
<table class="login">
	<tr>
		<th>利用者証番号</th><td><input type="text" name="cardNumber"/><br>(半角数字8文字)</td>
	</tr>
	<tr>
		<th>パスワード</th><td><input type="password" name="password" /></td>
	</tr>
</table><br><center><div class="login"><br><input type = "submit" value ="ログイン"></div></center><br/><br>
</form>
</div>



</body>
</html>