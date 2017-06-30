<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/common.css" rel="stylesheet" type="text/css">
<title><c:out value= "${book.name }" /></title>
<script type="text/javascript">
<!--

function disp(str){
	if(confirm(str+"を予約しますか？")){
		return true;
	}else{
		return false;
	}
}

function request(book,library){
	if(confirm(book + "を" + library + "へ取り寄せますか？")){
		return true;
	}else{
		return false;
	}
}

// -->
</script>
</head>
<body>
<center><a href="http://localhost:8080/LibrarySystem2/"><img src="Tottori-Library.png" alt="TAG index" border="0"></a></center>
	<div class="main-contents">
	<a href="search"  class="prev-btn">←戻る</a>
	<c:if test="${loginUser != null}" >
<div align="right"><a href="logout" class = "logout-btn">ログアウト</a></div>
<marquee behavior="alternate"><font color="#000000" face="メイリオ" size="5"><b>ログイン中です<br>席を離れるときは必ずログアウトしてください</b></font></marquee>
</c:if>
<table class="bookInfo">
	<tr>
	<th>一連番号</th><td>
		<c:out value="${book.id }" /></td>
	</tr>
	<tr>
	<th>登録図書館</th><td>
			<c:forEach items="${libraries}" var="library">
					<c:if test = "${book.libraryId  == library.id}">
						<c:out value="${library.name}" />
					</c:if>
			</c:forEach>
			</td>
	</tr>

	<tr>
	<th>棚番号</th><td>
			<c:forEach items="${shelf}" var="shelf">
					<c:if test = "${book.shelfId  == shelf.id}">
						<c:out value="${shelf.name}" />
					</c:if>
			</c:forEach>
			</td>
	</tr>


	<tr>
		<th>ISBN</th><td><c:out value="${book.ISBN}"/></td>
	</tr>


	<tr>
		<th>書籍名</th><td><c:out value="${book.name}" /></td>
	</tr>


	<tr>
	<th>著者名</th><td><c:out value="${book.author}" /></td>
	</tr>


	<tr>
	<th>出版社名</th><td><c:out value="${book.publisher}" /></td>
	</tr>


	<tr>
	<th>書類種類</th><td>
			<c:forEach items="${kind}" var="kind">
					<c:if test = "${book.kind  == kind.id}">
						<c:out  value="${kind.name}" />
					</c:if>
				</c:forEach></td>
	</tr>

	<tr>
	<th>利用状況</th>
	<c:choose>
		<c:when test = "${book.status == 1 || book.status ==2}">
			<c:if test = "${book.status == 1 }">
				<td><c:out value="貸出中" /></td>
			</c:if>
			<c:if test = "${book.status == 2 }">
				<td><c:out value="整理中" /></td>
			</c:if>
			<tr>
			<th>予約者数</th><td><c:out value="${book.reservationNumber}人" /></td>
			</tr>
		</c:when>
		<c:when test = "${book.status == 0 }">
			<td><c:out value="貸出可" /></td>
		</c:when>
	</c:choose>

</table>
<c:if test = "${book.status != 0 }">
	<c:choose>
		<c:when test="${loginUser.id == null }">
			<c:out value="予約をするにはログインしてください" /></c:when>
		<c:otherwise>
			<form action="reservation" method="post">
				<br><center>
				<input type = "hidden" name = bookId value = "${book.id }">
				<input type = "submit" value ="予約する" style="cursor: hand; cursor:pointer;" onClick="return disp('${book.name}');"></center>
			</form>
		</c:otherwise>
	</c:choose>
</c:if>
<c:if test = "${book.status == 0 }">
	<c:choose>
		<c:when test="${loginUser.id == null }">
			<c:out value="受取図書館への取り寄せを希望するにはログインしてください" /></c:when>
		<c:otherwise>
			<form action="reservation" method="post">
				<br><center>
				<c:if test = "${book.libraryId != loginUser.libraryId }">
				<input type = "hidden" name = bookId value = "${book.id }">
				<c:forEach items="${libraries}" var="library">
					<c:if test = "${loginUser.libraryId  == library.id}">
						<input type = "submit" value ="受取図書館へ取り寄せる" style="cursor: hand; cursor:pointer;" onClick="return request('${book.name}','${library.name }');">
					</c:if>
				</c:forEach>
				</c:if></center>
			</form>
		</c:otherwise>
	</c:choose>
</c:if>
	</div>
</body>
</html>