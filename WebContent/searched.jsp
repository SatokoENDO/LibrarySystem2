<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/common.css" rel="stylesheet" type="text/css">
<title>資料検索結果</title>
</head>
<body>
<center><a href="http://localhost:8080/LibrarySystem2/"><img src="Tottori-Library.png" alt="TAG index" border="0"></a></center>
<a href="search">戻る</a>
<div class="refine">
		<form action="searched" method="get">
			<input type ="hidden" name = "bookName" value = "${bookName}">
			<input type ="hidden" name = "author" value = "${author}">
			<input type ="hidden" name = "publisher" value = "${publisher}">
			資料種類:

			<select name="kindId" size = "1">
			<c:if test = "${kindId == 0}">
			<option value = "0" selected >すべて表示</option>
			<c:forEach items = "${kinds}" var = "kind">
				<option value="${kind.id}"><c:out value = "${kind.name}"/></option>
			</c:forEach>
			</c:if>
		<c:if test = "${kindId != null }">
		<c:forEach items = "${kinds}" var = "kind">
			<option value="${kind.id}" <c:if test="${kindId == kind.id}">selected</c:if>>
			<c:out value = "${kind.name}"/></option>
		</c:forEach>
		</c:if>
	</select>
			所蔵図書館:
			<select name="libraryId" size = "1">
			<c:if test = "${libraryId == 0}">
			<option value = "0" selected >すべて表示</option>
			</c:if>
			<c:if test = "${libraryId != null }">
		<c:forEach items = "${libraries}" var = "library">
			<option value="${library.id}" <c:if test="${libraryId == library.id}">selected</c:if>>
			<c:out value = "${library.name}"/></option>
		</c:forEach>
		</c:if>
	</select>
			 <input type="submit" value="絞り込む">
		</form>
	</div>
	<br>
<table class="result">
<tr>
    <th>書名</th>
    <th>著者</th>
    <th>出版社</th>
    <th>資料種類</th>
    <th>所蔵図書館</th>
    <th>利用状況</th>
</tr>
<c:choose>
	<c:when test = "${kindId == 0 && libraryId == 0 }">
		<c:forEach items="${books }" var="searchedBooks">

				<tr>
					<td><a href="bookInfo?id=${searchedBooks.id }">${searchedBooks.name }</a></td>
					<td>${searchedBooks.author }</td>
					<td>${searchedBooks.publisher }</td>
					<td><c:forEach items="${kinds}" var="kind">
							<c:if test="${searchedBooks.kind == kind.id }">
								<c:out value="${kind.name}" />
							</c:if>
						</c:forEach></td>
					<td><c:forEach items="${libraries}" var="library">
							<c:if test="${searchedBooks.libraryId == library.id }">
								<c:out value="${library.name}" />
							</c:if>
						</c:forEach></td>
					<td><c:if test="${searchedBooks.status  == 0}">
							<c:out value="貸出可" />
						</c:if> <c:if test="${searchedBooks.status == 1}">
							<c:out value="貸出中 予約者数:${searchedBooks.reservationNumber }人"  />
						</c:if> <c:if test="${searchedBooks.status == 2}">
							<c:out value="整理中" />
						</c:if></td>
				</tr>
</c:forEach>
</c:when>
	<c:when test = "${kindId != 0 && libraryId == 0 }">
		<c:forEach items="${books }" var="searchedBooks">
			<c:if test = "${kindId == searchedBooks.kind }">
				<tr>
					<td><a href="bookInfo?id=${searchedBooks.id }">${searchedBooks.name }</a></td>
					<td>${searchedBooks.author }</td>
					<td>${searchedBooks.publisher }</td>
					<td><c:forEach items="${kinds}" var="kind">
							<c:if test="${searchedBooks.kind == kind.id }">
								<c:out value="${kind.name}" />
							</c:if>
						</c:forEach></td>
					<td><c:forEach items="${libraries}" var="library">
							<c:if test="${searchedBooks.libraryId == library.id }">
								<c:out value="${library.name}" />
							</c:if>
						</c:forEach></td>
					<td><c:if test="${searchedBooks.status  == 0}">
							<c:out value="貸出可" />
						</c:if> <c:if test="${searchedBooks.status == 1}">
							<c:out value="貸出中" />
						</c:if> <c:if test="${searchedBooks.status == 2}">
							<c:out value="整理中" />
						</c:if></td>
				</tr>
				</c:if>
</c:forEach>
</c:when>
<c:when test = "${kindId == 0 && libraryId != 0 }">
		<c:forEach items="${books }" var="searchedBooks">
			<c:if test = "${libraryId == searchedBooks.libraryId  }">
				<tr>
					<td><a href="bookInfo?id=${searchedBooks.id }">${searchedBooks.name }</a></td>
					<td>${searchedBooks.author }</td>
					<td>${searchedBooks.publisher }</td>
					<td><c:forEach items="${kinds}" var="kind">
							<c:if test="${searchedBooks.kind == kind.id }">
								<c:out value="${kind.name}" />
							</c:if>
						</c:forEach></td>
					<td><c:forEach items="${libraries}" var="library">
							<c:if test="${searchedBooks.libraryId == library.id }">
								<c:out value="${library.name}" />
							</c:if>
						</c:forEach></td>
					<td><c:if test="${searchedBooks.status  == 0}">
							<c:out value="貸出可" />
						</c:if> <c:if test="${searchedBooks.status == 1}">
							<c:out value="貸出中" />
						</c:if> <c:if test="${searchedBooks.status == 2}">
							<c:out value="整理中" />
						</c:if></td>
				</tr>
				</c:if>
</c:forEach>
</c:when>
<c:when test = "${kindId != 0 && libraryId != 0 }">
		<c:forEach items="${books }" var="searchedBooks">
			<c:if test = "${libraryId == searchedBooks.libraryId && kindId == searchedBooks.kind }">
				<tr>
					<td><a href="bookInfo?id=${searchedBooks.id }">${searchedBooks.name }</a></td>
					<td>${searchedBooks.author }</td>
					<td>${searchedBooks.publisher }</td>
					<td><c:forEach items="${kinds}" var="kind">
							<c:if test="${searchedBooks.kind == kind.id }">
								<c:out value="${kind.name}" />
							</c:if>
						</c:forEach></td>
					<td><c:forEach items="${libraries}" var="library">
							<c:if test="${searchedBooks.libraryId == library.id }">
								<c:out value="${library.name}" />
							</c:if>
						</c:forEach></td>
					<td><c:if test="${searchedBooks.status  == 0}">
							<c:out value="貸出可" />
						</c:if> <c:if test="${searchedBooks.status == 1}">
							<c:out value="貸出中" />
						</c:if> <c:if test="${searchedBooks.status == 2}">
							<c:out value="整理中" />
						</c:if></td>
				</tr>
				</c:if>
</c:forEach>
</c:when>
</c:choose>

</table>
<br><br>

</body>
</html>