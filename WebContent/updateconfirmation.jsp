<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/common.css" rel="stylesheet" type="text/css">
<title>ユーザー編集確認画面</title>
</head>
<body>
<center><a href="http://localhost:8080/LibrarySystem2/"><img src="Tottori-Library.png" alt="TAG index" border="0"></a></center>
	<div class="main-contents">
	<a href = "admin">戻る</a>
	<c:if test="${loginUser != null}" >
<div align="right"><a href="logout">ログアウト</a></div>
<marquee behavior="alternate"><font color="#000000" face="メイリオ" size="5"><b>ログイン中です<br>席を離れるときは必ずログアウトしてください</b></font></marquee>
</c:if>

			<table class="updateconfirm">
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
		<th>パスワード</th><td><c:out value="${userPassword }"/></td>
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
		<form action="update-confirm" method="post"><br />
					<center><input type="submit" value="登録" /></center><br />
				</form>
	</div>