<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/common.css" rel="stylesheet" type="text/css">
<title>延滞書籍一覧</title>
</head>
<body>
<center><a href="http://localhost:8080/LibrarySystem2/"><img src="Tottori-Library.png" alt="TAG index" border="0"></a></center>
<a href="search">戻る</a>
<table class="searched">
<tr>
	<th> 書名 </th>
	<th> 著者 </th>
	<th> 出版社 </th>
	<th> 資料種類 </th>
	<th> 所蔵図書館 </th>
	<th> 延滞者 </th>
	<th>延滞者電話番号</th>
</tr>
<c:forEach items="${books }" var="delayedBooks">
	<tr>
		<td>${delayedBooks.name }</td>
		<td>${delayedBooks.author }</td>
		<td>${delayedBooks.publisher }</td>
		<td><c:forEach items="${kinds}" var="kind">
					<c:if test = "${delayedBooks.kind  == kind.id}">
						<c:out  value="${kind.name}" />
					</c:if></c:forEach></td>
		<td><c:forEach items="${libraries}" var="library">
					<c:if test = "${delayedBooks.libraryId  == library.id}">
						<c:out  value="${library.name}" />
					</c:if></c:forEach></td>
		<td><c:forEach items="${users}" var="user">
					<c:if test = "${delayedBooks.borrower  == user.id}">
						<c:out value="${user.name}" />
					</c:if>
			</c:forEach></td>
			<td><c:forEach items="${users}" var="user">
					<c:if test = "${delayedBooks.borrower  == user.id}">
						<c:out value="${user.tel}" />
					</c:if>
			</c:forEach></td>

	</tr>
</c:forEach>

</table>
<br><br>

</body>
</html>