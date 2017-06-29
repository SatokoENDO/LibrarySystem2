<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/common.css" rel="stylesheet" type="text/css">
<title>利用者登録</title>
</head>
<body>
<center><a href="http://localhost:8080/LibrarySystem2/"><img src="Tottori-Library.png" alt="TAG index" border="0"></a></center>
<a href="admin"  class="prev">戻る</a>
<c:if test="${loginUser != null}" >
<div align="right"><a href="logout" class = "logout-btn">ログアウト</a></div>
<marquee behavior="alternate"><font color="#000000" face="メイリオ" size="5"><b>ログイン中です<br>席を離れるときは必ずログアウトしてください</b></font></marquee>
</c:if>

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

<form action="update-confirm" method="post"><br />
<table class="update-confirm">
	<tr>
		<th>氏名</th><td><input type="text" name="name" value="${editUser.name}" /></td>
	</tr>
		<tr>
		<th>住所</th><td><input type="text" name="address" value="${editUser.address}"/></td>
	</tr>
		<tr>
		<th>電話番号</th><td><input type="text" name="tel" value="${editUser.tel}"/></td>
	</tr>
		<tr>
		<th>メールアドレス</th><td><input type="text" name="mail" value="${editUser.mail}"/></td>
	</tr>
		<tr>
		<th>パスワード </th><td><input type="password" name="password" value=""></td>
	</tr>
	<tr>
		<th>パスワード(確認)</th><td><input type="password" name="checkPassword" /></td>
	</tr>

		<tr>
	<th>受取図書館</th><td><select name="libraryId">
				<c:forEach items="${libraries}" var="library">
				<c:if test = "${editUser.libraryId == library.id}">
						<option value="${library.id}"selected>
							<c:out value="${library.name}" />
						</option>
						</c:if>
				<c:if test = "${editUser.libraryId != library.id}">
						<option value="${library.id}">
							<c:out value="${library.name}" />
						</option>
						</c:if>
				</c:forEach>
			</select></td>
	</tr>
	</table>
<br><center><input type = "submit" value ="登録" style="cursor: hand; cursor:pointer;"></center>
</form>
</div>
</body>
</html>