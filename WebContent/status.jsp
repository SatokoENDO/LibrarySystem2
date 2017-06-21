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
<title>利用状況</title>
</head>
<body>
<table class="status">
<tr>
	<th>貸出中</th>
	<th>返却日</th>
</tr>
<c:forEach var="borrowBookList" items="${borrowBooks }">
	<tr>
		<td>${borrowBookList.name }</td>
		<td><fmt:formatDate value="${borrowBookList.dueDate }" pattern="yyyy年MM月dd日（E）"/></td>
	</tr>
</c:forEach>

</table>
<table class="reservation">
<tr>
	<th>予約中</th>
	<th>返却予定日</th>
	<th>取消</th>
</tr>

</table>
</body>
</html>