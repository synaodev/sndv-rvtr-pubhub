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
		<h1>Search Results for<c:out value="${query}"/></h1>
	</header>
	<main>
		<a href="/">Back</a>
		<ul>
			<c:forEach var="it" items="${results}">
				<li>
					<a href="/book/${it.isbn13}"><c:out value="${it.title}"/></a>
				</li>
			</c:forEach>
		</ul>
	</main>
</body>
</html>
