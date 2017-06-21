<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/common.css" rel="stylesheet" type="text/css">
<title>書籍検索</title>
</head>
<body>

<center><a href="http://localhost:8080/LibrarySystem2/"><img src="Tottori-Library.png" alt="TAG index" border="0"></a></center>
<c:if test="${loginUser == null}" >
<a href="login">ログイン</a>
</c:if>

<c:if test="${loginUser != null}" >
<div align="right"><a href="logout">ログアウト</a></div>
<marquee behavior="alternate"><font color="#000000" face="メイリオ" size="5"><b>ログイン中です<br>席を離れるときは必ずログアウトしてください</b></font></marquee>
</c:if>


<br>
<br>
<div class="search">
	<form action="./" method="get">
		書名:
		<input type = "text" name = "bookName" value = "${bookName }" ><br />
		著者名:
		<input type = "text" name = "author" value = "${author }"><br />
		出版社名:
		<input type = "text" name = "publisher" value = "${publisher }"><br />

		書類種類:<select name="kinds">

				<c:forEach items="${kinds}" var="kind">
						<option value="${kind.id}">
							<c:out value="${kind.name}" />
						</option>
				</c:forEach>
			</select>
		所蔵図書館:<select name="libraryId">
				<c:forEach items="${libraries}" var="library">
						<option value="${library.id}">
							<c:out value="${library.name}" />
						</option>
				</c:forEach>
			</select>
		<input type = "submit" value ="検索">
	</form>
</div>

</body>
</html>