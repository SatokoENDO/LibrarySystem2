<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/common.css" rel="stylesheet" type="text/css">
<title>登録確認画面</title>
</head>
<body>
<center><a href="http://localhost:8080/LibrarySystem2/"><img src="Tottori-Library.png" alt="TAG index" border="0"></a></center>
	<div class="main-contents">
	<a href = "admin">戻る</a>
		<c:choose>
			<c:when test = "${userCardNumber != null }">
			<table class="signup">
			<tr>
		<th>利用者証番号</th><td><c:out value="${userCardNumber }" /></td>
	</tr>
	<tr>
		<th>氏名</th><td><c:out value="${userName }" /></td>
	</tr>
		<tr>
		<th>住所</th><td><c:out value="${userAddress }"/></td>
	</tr>
		<tr>
		<th>電話番号</th><td><c:out value="${userTel }"/></td>
	</tr>
		<tr>
		<th>メールアドレス</th><td><c:out value="${userMail }"/></td>
	</tr>
		<tr>
		<tr>
		<th>初期パスワード</th><td><c:out value="${userPassword }"/></td>
	</tr>
	<tr>
		<th>権限</th><td>
		<c:choose>
			<c:when test = "${userIsAdmin == 0 }">
				<c:out value="一般"/>
			</c:when>
			<c:otherwise>
				<c:out value="運営"/>
			</c:otherwise>

		</c:choose>
			</td>

	</tr>

		<tr>
	<th>受取図書館</th><td>
				<c:forEach items="${libraries}" var="library">
					<c:if test = "${userLibraryId  == library.id}">
						<c:out  value="${library.name}" />
					</c:if>
				</c:forEach>
			</td>
	</tr>
	</table>
		<form action="user-confirm" method="post"><br />
					<center><input type="submit" value="登録" /></center><br />
				</form>
			</c:when>
			<c:otherwise>
	<table class="registration">
	<tr>
		<th>一連番号</th><td><c:out value="${bookId }" /></td>
	</tr>
	<tr>
	<th>登録図書館</th><td>
			<c:forEach items="${libraries}" var="library">
					<c:if test = "${bookLibraryId  == library.id}">
						<c:out value="${library.name}" />
					</c:if>
			</c:forEach>
			</td>
	</tr>
	<tr>
	<th>棚番号</th><td>
			<c:forEach items="${shelves}" var="shelf">
					<c:if test = "${shelfId  == shelf.id}">
						<c:out value="${shelf.name}" />
					</c:if>
			</c:forEach>
			</td>
	</tr>
	<tr>
		<th>ISBN</th><td><c:out value="${ISBN}"/></td>
	</tr>
	<tr>
		<th>書籍名</th><td><c:out value="${bookName}" /></td>
	</tr>
	<tr>
	<th>著者名</th><td><c:out value="${author}" /></td>
	<tr>
	<th>出版社名</th><td><c:out value="${publisher}" /></td>
	<tr>
	<th>書類種類</th><td>
			<c:forEach items="${kinds}" var="kind">
					<c:if test = "${kindId  == kind.id}">
						<c:out  value="${kind.name}" />
					</c:if>
				</c:forEach></td>
	</tr>
	</table>
	<form action="book-confirm" method="post"><br />
					<center><input type = "submit" value="登録" /></center><br />
				</form>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>