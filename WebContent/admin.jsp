<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/common.css" rel="stylesheet" type="text/css">
<title>運営業務</title>
</head>
<body>
<center><a href="http://localhost:8080/LibrarySystem2/"><img src="Tottori-Library.png" alt="TAG index" border="0"></a></center>
<a href="./"  class="prev-btn">←戻る</a> <div align="right"><a href="logout" class = "logout-btn">ログアウト</a></div>
<br>

<a href="lend" class="lend-btn">貸出</a>&nbsp;
<a href="return" class="return-btn">返却</a>&nbsp;&nbsp;<img src="rakuda6.gif"><img src="rakuda6.gif">&nbsp;

<br>


<div align="right">
<a href="registration" class="register-btn">資料登録</a>&nbsp;
<a href="signup" class="register-btn">利用者登録</a>&nbsp;
<a href="reservationlist" class="reservation-btn">連絡・配送関連</a>&nbsp;
<a href="inquiry" class="inquiry-btn">利用者照会</a>&nbsp;
<<<<<<< HEAD
<a href="getbooklist" class="inquiry-btn">受取リスト</a>&nbsp;
=======
<a href="getbooklist" class="demand-btn">受け取り資料リスト</a>&nbsp;
>>>>>>> 2ae716772f84e7b2e4dc64a554fb95d932eca12d
<a href="demand" class="demand-btn">督促</a>&nbsp;



</div>
</body>
</html>