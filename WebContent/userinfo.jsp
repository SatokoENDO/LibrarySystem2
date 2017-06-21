<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/common.css" rel="stylesheet" type="text/css">
<title>利用者登録情報</title>
</head>
<body>
<center><a href="http://localhost:8080/LibrarySystem2/"><img src="Tottori-Library.png" alt="TAG index" border="0"></a></center>
<a href="./">戻る</a>

<div class="main=contents">

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

<form action="editUser" method="post"><br />
<table class="editUser">
	<tr>
		<th>氏名</th><td>${loginUser.name}</td>
	</tr>
		<tr>
		<th>住所</th><td>${loginUser.address}</td>
	</tr>
		<tr>
		<th>電話番号</th><td>${loginUser.tel}</td>
	</tr>
		<tr>
		<th>メールアドレス</th><td>${loginUser.mail}</td>
	</tr>

		<tr>
		<th>受取図書館</th><td>
				<c:forEach items="${libraries}" var="library">
					<c:if test = "${loginUser.libraryId  == library.id}">
						<c:out  value="${library.name}" />
					</c:if>
				</c:forEach>
			</td>
	</tr>
	</table>
<br><center><input type = "submit" value ="登録情報、パスワードを変更する"></center>
</form>
</div>
</body>
</html>