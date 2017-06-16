<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>利用者登録画面</title>
</head>
<body>
<center><img src="Tottori-Library.png"></center>
<a href="admin">戻る</a>

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

	<label for="name">名前:</label>
	<input name="name" value="${user.name }" id="name"/><br />

	<label for="address">住所:</label>
	<input name="address" value="${user.address }" id="address"/><br />

	<label for="tel">電話番号:</label>
	<input name="tel" value="${user.tel }" id="tel"/><br />

	<label for="mail">メールアドレス:</label>
	<input name="mail" value="${user.mail }" id="mail"/><br />

	<label for="isAdmin">権限:</label>
	<input type="checkbox" name="isAdmin" value="1">運営<br />

	<label for="libraryId">受取図書館:</label>
	<select name = "libraryId" size = "1">
		<c:forEach items="${libraries}" var="library">
			<option value = "${library.id}" <c:if test = "${user.libraryId == library.id}">selected</c:if>>
				<c:out value ="${library.name}"/>
			</option>
		</c:forEach>
	</select><br />

	<input type="submit" value="登録" /><br />
</form>
</div>
</body>
</html>