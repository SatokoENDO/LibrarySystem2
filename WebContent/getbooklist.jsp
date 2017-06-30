<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>受取申請リスト</title>
</head>
<body>

<table class="result">
<tr>
    <th>書名</th>
    <th>著者</th>
    <th>出版社</th>
    <th>棚番号</th>
</tr>
		<c:forEach items="${reservedBooks }" var="reservedBooks">
<c:if test = "${reservedBooks.libraryId == loginUser.libraryId }">

				<tr>
					<td>${reservedBooks.name }</td>
					<td>${reservedBooks.author }</td>
					<td>${reservedBooks.publisher }</td>
					<td>${reservedBooks.shelfId }</td>

				</tr>
				</c:if>
		</c:forEach>
</table>

<c:if test = "${book == null }" >
	<c:out value= "受け取り申請はありません" />
</c:if>
</body>
</html>