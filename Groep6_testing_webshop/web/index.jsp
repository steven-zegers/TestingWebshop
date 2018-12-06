<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
	<header>
		<h1>
			<span>Web shop</span>
		</h1>
		<nav>
			<ul>
				<li id="actual"><a href="Controller">Home</a></li>
				<li><a href="Controller?action=PersonOverview">Overview</a></li>
				<li><a href="Controller?action=SignUp">Sign up</a></li>
				<li><a href="Controller?action=ProductOverview">Product overview</a></li>
			</ul>
		</nav>
		<h2>Home</h2>

	</header>
	<main> <c:if test="${errors != null}">
		<div class="alert-danger">
			<ul>
				<c:forEach items="${errors}" var="error">
					<li>${error}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
		<p>Welcome to our web shop where you can buy your favorite things!</p>
		<c:choose>
			<c:when test="${login=='yes'}">
				<h3>Welcome, ${person.firstName}</h3>
				<form action="Controller?action=Logout" method="post" novalidate="novalidate">
					<p><input type="submit" id="Logout" value="Log out"></p>
				</form>
			</c:when>
			<c:otherwise>
				<form method="post" action="Controller?action=Login" novalidate="novalidate">
					<!-- novalidate in order to be able to run tests correctly -->
					<p><label for="userid">User id</label><input type="text" id="userid" name="userid"
																 required value="<c:out value ='${param.userId}'/>"> </p>
					<p><label for="password">Password</label><input type="password" id="password"  name="password"
																	required value="${param.password}"> </p>
					<p><input type="submit" id="Login" value="Log in"></p>
				</form>
			</c:otherwise>
		</c:choose>
	</main>
	<footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
</div>
</body>
</html>