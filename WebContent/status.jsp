<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page isELIgnored="false"%>
    <%@ page import="java.util.Calendar" %>
    <%@ page import="java.text.SimpleDateFormat" %>
    <%@ page import="java.util.List" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/common.css" rel="stylesheet" type="text/css">
<title>利用状況</title>
</head>
<body>
<center><a href="http://localhost:8080/LibrarySystem2/"><img src="Tottori-Library.png" alt="TAG index" border="0"></a></center>
<a href="admin"  class="prev">戻る</a>
<a href="userinfo">登録情報の確認・変更</a>
<c:if test="${loginUser != null}" >
<div align="right"><a href="logout" class = "logout-btn">ログアウト</a></div>
<marquee behavior="alternate"><font color="#000000" face="メイリオ" size="5"><b>ログイン中です<br>席を離れるときは必ずログアウトしてください</b></font></marquee>
</c:if>
<table class="status">
<tr>
	<th>貸出資料</th>
	<th>返却日</th>
</tr>
<c:forEach var="borrowBookList" items="${borrowBooks }">
	<tr>
		<td>${borrowBookList.name }</td>
		<td><fmt:formatDate value="${borrowBookList.dueDate }" pattern="yyyy年MM月dd日（E）"/></td>
	</tr>
</c:forEach>

</table>
<br><br>

<table class="delivery">
<tr>
	<th>受取申請資料</th>
	<th>著者</th>
</tr>
<c:forEach var="deliverBookList" items="${deliverBookList }">
	<tr>
		<td>${deliverBookList.name }</td>
		<td>${deliverBookList.author }</td>
	</tr>
</c:forEach>
</table>

<br><br>
<table class="reservation">
<tr>
	<th>予約資料</th>
	<th>著者</th>
	<th>予約順</th>
	<th></th>
</tr>

<c:forEach var="reservedBook" items="${reservedBookList }" varStatus="status" >

	<tr>
		<td><center>${reservedBook.name }</center></td>
		<td><center>${reservedBook.author }</center></td>
		<td><center>${reservationNumber[status.index] + 1 }番目</center></td>
		<td><center>
		<form action="status" method="post">
		<input type="hidden" name="bookId" value="${reservedBook.id }"/>
		<input type="submit" value="予約取消" />
		</form></center>
	</tr>

</c:forEach>

</table>
</body>
</html>