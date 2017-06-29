<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page isELIgnored="false"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/common.css" rel="stylesheet" type="text/css">
<title>予約リスト</title>
</head>
<body>
<center><a href="http://localhost:8080/LibrarySystem2/"><img src="Tottori-Library.png" alt="TAG index" border="0"></a></center>
<a href="admin"  class="prev">戻る</a>
<c:if test="${loginUser != null}" >
<div align="right"><a href="logout" class = "logout-btn">ログアウト</a></div>
<marquee behavior="alternate"><font color="#000000" face="メイリオ" size="5"><b>ログイン中です<br>席を離れるときは必ずログアウトしてください</b></font></marquee>
</c:if>

<table class="reservationList">
<tr>
	<th>受取または返却確認</th>
	<th>一連番号</th>
	<th>予約資料</th>
	<th>予約者</th>
	<th>メールアドレス</th>
	<th>電話番号</th>
	<th>住所</th>
	<th>連絡確認</th>
	<th>取消ボタン</th>
</tr>
<c:forEach var="reservedBookList" items="${reservedBookList }" varStatus="status">
	<tr>
		<td></td>
		<td>${reservedBookList.id }</td>
		<td>${reservedBookList.name }</td>
		<td>${reservedBookUserList[status.index].name }</td>
		<td>${reservedBookUserList[status.index].mail }</td>
		<td>${reservedBookUserList[status.index].tel }</td>
		<td>${reservedBookUserList[status.index].address }</td>
		<td>(工事中)</td>
		<td>(工事中)</td>
	</tr>
</c:forEach>

</table>

</body>
</html>