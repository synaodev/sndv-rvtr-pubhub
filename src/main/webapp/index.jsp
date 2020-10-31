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
	<div class="container">
		<p><form:errors path="book.*" /></p>
		<form:form action="/api/book/post" method="POST" modelAttribute="book">
			<p>
				<form:label path="isbn13">ISBN</form:label>
				<form:input path="isbn13"/>
			</p>
			<p>
				<form:label path="title">Title</form:label>
				<form:input path="title"/>
			</p>
			<p>
				<form:label path="author">Author</form:label>
				<form:input path="author"/>
			</p>
			<p>
				<form:label path="price">Price</form:label>
				<form:input path="price"/>
			</p>
			<p>
				<form:label path="publishDate">Publish Date</form:label>
				<form:input path="publishDate"/>
			</p>
			<p>
				<form:label path="publishDate">Publish Date</form:label>
				<form:input path="publishDate"/>
			</p>
			<p>
				<input type="submit" value="Add"/>
			</p>
		</form:form>
	</div>
	<main>
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
				<c:forEach var="book" items="${books}">
					<tr>
						<td><c:out value="${book.isbn13}" /></td>
						<td><c:out value="${book.title}" /></td>
						<td><c:out value="${book.author}" /></td>
						<td><c:out value="${book.publishDate}" /></td>
						<td><c:out value="${book.price}" /></td>
						<td><a href="/book/${book.id}">View</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</main>
</body>
</html>
