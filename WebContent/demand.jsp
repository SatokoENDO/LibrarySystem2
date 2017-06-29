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

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/common.css" rel="stylesheet" type="text/css">
<title>督促</title>
</head>
<body>
<center><a href="http://localhost:8080/LibrarySystem2/"><img src="Tottori-Library.png" alt="TAG index" border="0"></a></center>
<a href="search" class="prev">戻る</a>
<table class="result">
<tr>
	<th> 一連番号</th>
	<th> 資料名 </th>
	<th> 所蔵図書館 </th>
	<th> 返却締め切り日 </th>
	<th> 延滞者名 </th>
	<th>延滞者電話番号</th>
	<th>延滞者メールアドレス</th>
	<th>最新督促日時</th>

</tr>
<c:forEach items="${books }" var="delayedBooks">
	<tr>
		<td>${delayedBooks.id }</td>
		<td>${delayedBooks.name }</td>

		<td><c:forEach items="${libraries}" var="library">
					<c:if test = "${delayedBooks.libraryId  == library.id}">
						<c:out  value="${library.name}" />
					</c:if></c:forEach></td>
		<td><c:forEach items="${users}" var="user">
					<c:if test = "${delayedBooks.borrower  == user.id}">
					<fmt:formatDate value="${delayedBooks.dueDate }" pattern="yyyy年MM月dd日（E）"/>
					</c:if>
			</c:forEach></td>


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
			<td><c:forEach items="${users}" var="user">
					<c:if test = "${delayedBooks.borrower  == user.id}">
						<c:out value="${user.mail}" />
					</c:if>
			</c:forEach></td>
			<td><c:forEach items="${users}" var="user">
					<c:if test = "${delayedBooks.borrower  == user.id}">
						<fmt:formatDate value="${user.demandTime }" pattern="yyyy年MM月dd日（E）"/>
						<form action="demand" method="post">
						<input type="hidden" name="userId" value="${user.id}">
								<p>
						<input type="submit" value="督促日時更新">
						</p>
						</form>
					</c:if>
			</c:forEach></td>



	</tr>
</c:forEach>
</table>
<br><br>

</body>
</html>