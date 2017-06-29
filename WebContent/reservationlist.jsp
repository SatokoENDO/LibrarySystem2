<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page isELIgnored="false"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/common.css" rel="stylesheet" type="text/css">
<title>予約</title>
</head>
<body>
<center><a href="http://localhost:8080/LibrarySystem2/"><img src="Tottori-Library.png" alt="TAG index" border="0"></a></center>
<a href="admin">戻る</a>
<c:if test="${loginUser != null}" >
<div align="right"><a href="logout" class = "logout-btn">ログアウト</a></div>
<marquee behavior="alternate"><font color="#000000" face="メイリオ" size="5"><b>ログイン中です<br>席を離れるときは必ずログアウトしてください</b></font></marquee>
</c:if>

<form action = "reservationlist" method = "post">
<br>
<table class="reservationlist">
	<tr>
		<th>一連番号</th><td><input type="text" name="bookId"/><br></td>
	</tr>
</table><center><div class="login"><br><input type = "submit" value ="確認"></div></center><br/><br>
</form>

</body>
</html>