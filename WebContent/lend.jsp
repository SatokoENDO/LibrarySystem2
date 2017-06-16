<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>貸出画面</title>
</head>
<body>
	<div class="main-contents">
		<form action="lend" method="post">
			<label for="id">貸出本ID:</label>
			<input name="id" id="id" /><br />

			<label for="cardNumber">利用者証番号:</label>
			<input name="cardNumber" id="id" /><br />

			<input type = "submit" value ="登録">
		</form>
	</div>
</body>
</html>