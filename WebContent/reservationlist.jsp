<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page isELIgnored="false"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/common.css" rel="stylesheet" type="text/css">
<title>予約</title>
</head>
<body>
<center><a href="http://localhost:8080/LibrarySystem2/"><img src="Tottori-Library.png" alt="TAG index" border="0"></a></center>
<a href="admin"  class="prev">戻る</a>
<c:if test="${loginUser != null}" >
<div align="right"><a href="logout" class = "logout-btn">ログアウト</a></div>
<marquee behavior="alternate"><font color="#000000" face="メイリオ" size="5"><b>ログイン中です<br>席を離れるときは必ずログアウトしてください</b></font></marquee>
</c:if>

<c:if test="${bookId == 0 }">
	<form action = "reservationlist" method = "get">
	<br>
	<table class="reservationlist">
		<tr>
			<th>一連番号</th><td><input type="text" name="bookId"/><br></td>
		</tr>
	</table><center><div class="login"><br><input type = "submit" value ="確認"></div></center><br/><br>
	</form>
</c:if>

<c:if test="${bookId != 0 }">
	<table class="signup">
	<tr>
		<th>資料名</th><td><c:out value="${book.name }" /></td>
	</tr>
	<tr>
		<th>著者</th><td><c:out value="${book.author }" /></td>
	</tr>
	<c:if test = "${book.notificationTime != null }">
	<tr>
		<th>連絡日時</th><td><fmt:formatDate value="${book.notificationTime }" pattern="yyyy年MM月dd日（E）"/>
		</td>
	</tr>
	<tr>
		<th>予約削除</th><td>
			<form action = "reservationlist" method = "post">
				<input type = "hidden" name = reservedUser value = "${reservedUser.id }">
				<input type = "hidden" name = deleteReservation value = "${book.id }">
				<center><input type = "submit" value = "予約削除"></center>
			</form>
		</td>
	</tr>
	</c:if>
	<c:if test = "${reservedUser != null }">
	<tr>
	<th>受取図書館</th><td>
				<c:forEach items="${libraries}" var="library">
					<c:if test = "${reservedUser.libraryId  == library.id}">
						<c:out  value="${library.name}" />
					</c:if>
				</c:forEach>
			</td>
	</tr>
	<tr>
		<th>予約者</th><td><c:out value="${reservedUser.name }" /></td>
	</tr>
	<tr>
		<th>メール</th><td><c:out value="${reservedUser.mail }" /></td>
	</tr>
	<tr>
		<th>電話番号</th><td><c:out value="${reservedUser.tel }" /></td>
	</tr>
	<tr>
		<th>住所</th><td><c:out value="${reservedUser.address }" /></td>
	</tr>
	</c:if>
	</table>
<br>
<c:if test="${(reservedUser == null) && (book.libraryId == loginUser.libraryId) }">
	<form action = "reservationlist" method = "post">
		<input type = "hidden" name = returnBookId value = "${book.id }">
		<center><input type = "submit" value = "受取確認"></center>
	</form>
</c:if>
<c:if test = "${(reservedUser != null) && (reservedUser.libraryId == loginUser.libraryId )}">
<form action = "reservationlist" method = "post">
	<input type = "hidden" name = reservationBookId value = "${book.id }">
	<center><input type = "submit" value = "連絡日時更新"></center>
</form>
</c:if>
<c:if test = "${(reservedUser == null) && (book.libraryId != loginUser.libraryId) }">
	<form action = "reservationlist" method = "post">
		<input type = "hidden" name = reservationBookIdTo value = "${book.id }">
		<center><input type = "submit" value = "配送確認"></center>
	</form>
</c:if>

<c:if test = "${(reservedUser != null) && (reservedUser.libraryId != loginUser.libraryId) }">
	<form action = "reservationlist" method = "post">
		<input type = "hidden" name = reservationBookIdTo value = "${book.id }">
		<center><input type = "submit" value = "配送確認"></center>
	</form>
</c:if>


</c:if>

</body>
</html>