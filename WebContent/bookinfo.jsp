<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/common.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>
<center><a href="http://localhost:8080/LibrarySystem2/"><img src="Tottori-Library.png" alt="TAG index" border="0"></a></center>
	<div class="main-contents">
	<a href = "admin">戻る</a>
	<c:if test="${loginUser != null}" >
<div align="right"><a href="logout" class = "logout-btn">ログアウト</a></div>
<marquee behavior="alternate"><font color="#000000" face="メイリオ" size="5"><b>ログイン中です<br>席を離れるときは必ずログアウトしてください</b></font></marquee>
</c:if>
<table class="bookInfo">
	<tr>
	<th>一連番号</th><td>
		<c:out value="${book.id }" /></td>
	</tr>
	<tr>
	<th>登録図書館</th><td>
			<c:forEach items="${libraries}" var="library">
					<c:if test = "${book.libraryId  == library.id}">
						<c:out value="${library.name}" />
					</c:if>
			</c:forEach>
			</td>
	</tr>

	<tr>
	<th>棚番号</th><td>
			<c:forEach items="${shelf}" var="shelf">
					<c:if test = "${book.shelfId  == shelf.id}">
						<c:out value="${shelf.name}" />
					</c:if>
			</c:forEach>
			</td>
	</tr>


	<tr>
		<th>ISBN</th><td><c:out value="${book.ISBN}"/></td>
	</tr>


	<tr>
		<th>書籍名</th><td><c:out value="${book.name}" /></td>
	</tr>


	<tr>
	<th>著者名</th><td><c:out value="${book.author}" /></td>
	</tr>


	<tr>
	<th>出版社名</th><td><c:out value="${book.publisher}" /></td>
	</tr>


	<tr>
	<th>書類種類</th><td>
			<c:forEach items="${kind}" var="kind">
					<c:if test = "${book.kind  == kind.id}">
						<c:out  value="${kind.name}" />
					</c:if>
				</c:forEach></td>
	</tr>
</table>
<c:if test = "${status != 0 }">
	<form action="bookInfo" method="post">
		<br><center><input type = "submit" value ="予約する" style="cursor: hand; cursor:pointer;"></center>
</form>
</c:if>


	</div>
</body>
</html>