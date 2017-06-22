<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/common.css" rel="stylesheet" type="text/css">
<title>新規書籍登録</title>
</head>
<body>
<center><a href="http://localhost:8080/LibrarySystem2/"><img src="Tottori-Library.png" alt="TAG index" border="0"></a></center>
	<c:if test = "${not empty errorMessages}">
		<div class ="errorMessages">
			<ul>
				<c:forEach items = "${errorMessages}" var = "message">
					<li><c:out value = "${message}"/>
				</c:forEach>
			</ul>
		</div>
	<c:remove var = "errorMessages" scope = "session"/>
	</c:if>
	<c:if test = "${empty errorMessages}">
		<div class ="validationMessage">
			<p><c:out value = "${validationMessage}"/></p>
		</div>
	<c:remove var = "validationMessage" scope = "session"/>
	</c:if>
	<div class="main">

		<br><a href = "admin">戻る</a>
<form action="registration" method="post">
<table class="registration">
	<tr>
	<th>登録図書館</th><td><select name="libraryId">
				<c:forEach items="${libraries}" var="library">
						<option value="${library.id}">
							<c:out value="${library.name}" />
						</option>
				</c:forEach>
			</select></td>
	</tr>
	<tr>
	<th>棚番号</th><td><select name="shelfId">
				<c:forEach items="${shelves}" var="shelf">
						<option value="${shelf.id}">
							<c:out value="${shelf.name}" />
						</option>
				</c:forEach>
			</select></td>
	</tr>
	<tr>
		<th>ISBN</th><td><input type="text" name="ISBN" value="${ISBN}"/></td>
	</tr>
	<tr>
		<th>書籍名</th><td><input type="text" name="name" value="${name}" /></td>
	</tr>
	<tr>
	<th>著者名</th><td><input type="text" name="author" value="${author}" /></td>
	<tr>
	<th>出版社名</th><td><input type="text" name="publisher" value="${publisher}" /></td>
	<tr>
	<th>書類種類</th><td><select name="kind">
				<c:forEach items="${kinds}" var="kind">
						<option value="${kind.id}">
							<c:out value="${kind.name}" />
						</option>
				</c:forEach>
			</select></td>
	</tr>
	</table>
<br><center><input type = "submit" value ="入力内容確認" style="cursor: hand; cursor:pointer;"></center><br/><br>
</form>
	</div>

</body>
</html>