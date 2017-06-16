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
	<div class="main-contents">

		利用者証番号: ${userCardNumber } <br />
		氏名: ${userName } <br />
		住所: ${userAddress } <br />
		電話番号: ${userTel } <br />
		メールアドレス: ${userMail } <br />
		パスワード: ${userPassword } <br />
		受取図書館: ${userLibraryId } <br />
		権限: ${userIsAdmin } <br />

		<form action="user-confirm" method="post"><br />
			<input type="submit" value="登録" /><br />
		</form>
	</div>
</body>
</html>