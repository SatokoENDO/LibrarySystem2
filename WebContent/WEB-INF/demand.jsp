<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page isELIgnored="false"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>資料検索結果</title>
</head>
<body>
<center><a href="http://localhost:8080/LibrarySystem2/"><img src="Tottori-Library.png" alt="TAG index" border="0"></a></center>
<a href="search">戻る</a>
<table class="searched">
<tr>
	<th>書名</th>
	<th>著者</th>
	<th>出版社</th>
	<th>書類種類</th>
	<th>所蔵図書館</th>
	<th>利用状況</th>
</tr>
<c:forEach items="${books }" var="delayedBooks">
	<tr>
		<td>${delayedBooks.name }</td>
		<td>${delayedBooks.author }</td>
		<td>${delayedBooks.publisher }</td>
		<td>${delayedBooks.kind }</td>
		<td>${delayedBooks.libraryId }</td>
		<td>${delayedBooks.status }</td>
	</tr>
</c:forEach>

</table>
<br><br>

</body>
</html>