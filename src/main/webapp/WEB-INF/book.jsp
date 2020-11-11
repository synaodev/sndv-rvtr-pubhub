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
		<h1><c:out value="${book.title}"/></h1>
	</header>
	<main>
		<a href="/">Back</a>
		<p>ISBN: <c:out value="${book.isbn13}"/></p>
		<p>Author: <c:out value="${book.author}"/></p>
		<p>Publish Date: <c:out value="${book.publishDate}"/></p>
		<p>Price: <c:out value="${book.price}"/></p>
		<p>Tags:
			<ul>
				<c:forEach var="it" items="${book.tags}">
					<li>
						<c:out value="${it.name}"/>
						<button><a href="/tag/${it.id}">View</a></button>
						<form action="/tag/${it.id}/${book.isbn13}" method="DELETE">
							<input type="submit" value="Remove">
						</form>
					</li>
				</c:forEach>
			</ul>
		</p>
		<p>Add Tag:
			<form:form action="/tag/${book.isbn13}" method="POST" modelAttribute="form-tag">
				<form:errors path="name"/><br>
				<form:label path="name">Name:</form:label>
				<form:input path="name"/><br>

				<input type="submit" value="Add"/>
			</form:form>
		</p>
	</main>
</body>
</html>
