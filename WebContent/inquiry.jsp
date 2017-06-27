<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/common.css" rel="stylesheet" type="text/css">
<title>利用者照会</title>
</head>
<body>
	<center>
		<a href="http://localhost:8080/LibrarySystem2/"><img
			src="Tottori-Library.png" alt="TAG index" border="0"></a>
	</center>
	<c:if test="${loginUser == null}">
		<a href="login" class="login-btn" style="cursor: hand; cursor:pointer;">ログイン</a>
	</c:if>

	<c:if test="${loginUser != null}">
		<div align="right">
			<a href="logout" class="logout-btn">ログアウト</a>
		</div>
		<marquee behavior="alternate">
			<font color="#000000" face="メイリオ" size="5"><b>ログイン中です<br>席を離れるときは必ずログアウトしてください
			</b></font>
		</marquee>
	</c:if>
	<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<li><c:out value="${message}" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>


	<br>
	<br>
	<div class="inquiry">
		<form action="inquiry" method="post">
			氏名: <input type="text" name="name"><br />
			住所: <input type="text" name="address"><br />
			<input type="submit" value="照会">
		</form>
	</div>
</body>
</html>