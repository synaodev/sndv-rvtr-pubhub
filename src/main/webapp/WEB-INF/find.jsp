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
		<h1>Search Results for <c:out value="${search}"/></h1>
	</header>
	<main>
		<a href="/">Back</a>
		<ul>
			<c:forEach var="it" items="${results}">
				<li>
					<a href="/tag/${it.id}"><c:out value="${it.name}"/></a>
				</li>
			</c:forEach>
		</ul>
	</main>
</body>
</html>
