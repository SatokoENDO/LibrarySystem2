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
		<Marquee onmouseover=this.stop() onmouseout=this.start()>
		<font size="6" color="#00aced "><b>新規書籍登録</b></font>
	</Marquee>

		<br><br><a href = "top">戻る</a>


		<form action="registration" method="post">
<table class="registration">
<tr>
		<th>図書館番号</th><td><input type="text" name="libraryNumber" value="${libraryNumber}" /></td>
	</tr>
	<tr>
		<th>棚番号</th><td><input type="text" name="shelfNumber" value="${shelfNumber}" /></td>
	</tr>
	<tr>
		<th>ISBN</th><td><input type="text" name="ISBN" value="${ISBN}"/></td>
	</tr>
	<tr>
		<th>書籍名</th><td><input type="text" name="name" value="${name}" /></td>
	</tr>
	<tr>
	<th>著者名</th><td><input type="text" name="authorName" value="${authorName}" /></td>
	<tr>
	<th>出版者名</th><td><input type="text" name="publisherName" value="${publisherName}" /></td>
	<tr>
		<th>書類種類</th><td><input type="text" name="kind" value="${kind}" /></td>
	</tr>
	</table>



<div class="userSubmit"><input type="submit" value="登録"></div>
</form>
	</div>
	<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
</body>
</html>