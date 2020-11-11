<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>PubHub</title>
</head>
<body>
	<header>
		<h1>PubHub</h1>
	</header>
	<main>
		<div>
			<form action="/tag" method="GET">
				<label for="name">Search for Tag:</label>
				<input type="text" name="query"><br>
				<input type="submit" value="Go">
			</form>
		</div>
		<hr>
		<div>
			<form:form action="/book" method="POST" modelAttribute="form-book">
				<form:errors path="isbn13"/><br>
				<form:label path="isbn13">ISBN:</form:label>
				<form:input path="isbn13"/><br>

				<form:errors path="title"/><br>
				<form:label path="title">Title:</form:label>
				<form:input path="title"/><br>

				<form:errors path="author"/><br>
				<form:label path="author">Author:</form:label>
				<form:input path="author"/><br>

				<form:errors path="publishDate"/><br>
				<form:label path="publishDate">Publish Date:</form:label>
				<form:input path="publishDate"/><br>

				<form:errors path="price"/><br>
				<form:label path="price">Price:</form:label>
				<form:input path="price"/><br>

				<input type="submit" value="Add Book"/>
			</form:form>
		</div>
		<table>
			<thead>
				<th>ISBN</th>
				<th>Title</th>
				<th>Author</th>
				<th>Publish Date</th>
				<th>Price</th>
				<th>View</th>
			</thead>
			<tbody>
				<c:forEach var="it" items="${books}">
					<tr>
						<td><c:out value="${it.isbn13}" /></td>
						<td><c:out value="${it.title}" /></td>
						<td><c:out value="${it.author}" /></td>
						<td><c:out value="${it.publishDate}" /></td>
						<td><c:out value="${it.price}" /></td>
						<td><a href="/book/${it.isbn13}">View</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</main>
</body>
</html>
