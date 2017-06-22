<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/common.css" rel="stylesheet" type="text/css">
<title>トップ</title>
</head>
<body>

<center><a href="http://localhost:8080/LibrarySystem2/"><img src="Tottori-Library.png" alt="TAG index" border="0"></a></center>
<c:if test="${loginUser == null}" >
<a href="login">ログイン</a>
<br>
<br>
<a href="search" class="search-btn">資料検索</a>&nbsp;<img src="rakuda6.gif"><img src="rakuda6.gif"><img src="rakuda6.gif">
</c:if>
<c:if test="${loginUser != null}" >
<div align="right"><a href="logout">ログアウト</a></div>
<marquee behavior="alternate"><font color="#000000" face="メイリオ" size="5"><b>ログイン中です<br>席を離れるときは必ずログアウトしてください</b></font></marquee>

</c:if>
<br>
<br>

<c:if test="${loginUser != null}" >
<a href="search" class="search-btn">資料検索</a>
<a href="status" class="status-btn">利用状況確認</a> <c:if test="${loginUser.isAdmin==1}" >
<a href="admin"  class="admin-btn">運営業務</a>

</c:if>

</c:if>







</body>
</html>