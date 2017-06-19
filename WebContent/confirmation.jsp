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

	<c:out value="${userLibraryId.name}" /></td>
	</tr>
	</table>
		<form action="user-confirm" method="post"><br />
					<center><input type="submit" value="登録" /></center><br />
				</form>
			</c:when>
			<c:otherwise>
	<table class="registration">
	<tr>
	<th>図書館番号</th><td><select name="libraryId">
				<c:forEach items="${libraries}" var="library">
						<option value="${bookLibraryId }">
							<c:out value="${library.name}" />
						</option>
				</c:forEach>
			</select></td>
	</tr>
	<tr>
	<th>棚番号</th><td><select name="shelfId">
				<c:forEach items="${shelves}" var="shelf">
						<option value="${shelfId}">
							<c:out value="${shelf.name}" />
						</option>
				</c:forEach>
			</select></td>
	</tr>
	<tr>
		<th>ISBN</th><td><input type="text" name="ISBN" value="${ISBN}"/></td>
	</tr>
	<tr>
		<th>書籍名</th><td><input type="text" name="name" value="${bookName}" /></td>
	</tr>
	<tr>
	<th>著者名</th><td><input type="text" name="author" value="${author}" /></td>
	<tr>
	<th>出版社名</th><td><input type="text" name="publisher" value="${publisher}" /></td>
	<tr>
	<th>書類種類</th><td><select name="kind">
				<c:forEach items="${kinds}" var="kind">
						<option value="${kind}">
							<c:out value="${kind.name}" />
						</option>
				</c:forEach>
			</select></td>
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