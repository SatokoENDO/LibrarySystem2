<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/common.css" rel="stylesheet" type="text/css">
<title>利用者新規登録</title>
</head>
<body>
<center><a href="http://localhost:8080/LibrarySystem2/"><img src="Tottori-Library.png" alt="TAG index" border="0"></a></center>
<a href="admin"  class="prev-btn">←戻る</a>

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

<form action="signup" method="post"><br />
<table class="signup">
	<tr>
		<th>氏名</th><td><input type="text" name="name" value="${errorUser.name}" /></td>
	</tr>
		<tr>
		<th>住所</th><td><input type="text" name="address" value="${errorUser.address}"/></td>
	</tr>
		<tr>
		<th>電話番号</th><td><input type="text" name="tel" value="${errorUser.tel}"/></td>
	</tr>
		<tr>
		<th>メールアドレス</th><td><input type="text" name="mail" value="${errorUser.mail}"/></td>
	</tr>
		<tr>
		<th>権限</th><td><input type="checkbox" name="isAdmin" value="1">運営</td>
	</tr>

		<tr>
	<th>受取図書館</th><td><select name="libraryId">
				<c:forEach items="${libraries}" var="library">
				<c:if test = "${errorUser.libraryId == library.id}">
						<option value="${library.id}"selected>
							<c:out value="${library.name}" />
						</option>
						</c:if>
				<c:if test = "${errorUser.libraryId != library.id}">
						<option value="${library.id}">
							<c:out value="${library.name}" />
						</option>
						</c:if>
				</c:forEach>
			</select></td>
	</tr>
	</table>
<br><center><input type = "submit" value ="入力内容確認" style="cursor: hand; cursor:pointer;"></center>
</form>
</div>
</body>
</html>