<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/common.css" rel="stylesheet" type="text/css">
<title>貸出画面</title>
</head>
<body>
	<div class="main-contents">
	<center><a href="http://localhost:8080/LibrarySystem2/"><img src="Tottori-Library.png" alt="TAG index" border="0"></a></center>
	<form action="lend" method="post">
	<br>
	<table class="lend">
	<tr>
		<th>一連番号</th><td><input type="text" name="bookId" value="${bookId}"/></td>
	</tr>
	<tr>
		<th>利用者証番号</th><td><input type="text" name="cardNumber" value="${cardNumber}"/></td>
	</tr>
	</table>
<br>
<center><input type = "submit" value ="貸出確認"></center><br/><br>
		</form>
	</div>
</body>
</html>