<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<a href="/book">Back</a>
		<p>Tag: <c:out value="${tag.name}"/></p>
		<p>Books:
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
							<td><a href="/book/${book.isbn13}">View</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</p>
	</main>
</body>
</html>
