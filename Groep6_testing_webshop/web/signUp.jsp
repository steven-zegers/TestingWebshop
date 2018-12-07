<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Sign Up</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
<header>
<h1><span>Prato book shop</span></h1>
<nav>
<ul>
    <li><a href="Controller">Home</a></li>
    <li><a href="Controller?action=ProductOverview">Books</a></li>
    <li><a href="Controller?action=SearchBook">Search a book</a></li>
    <li><a href="Controller?action=ShowCart">Shopping cart</a></li>
    <li id="actual"><a href="Controller?action=SignUp">Sign up</a></li>
</ul>
</nav>
<h2>
Sign Up
</h2>

</header><main>
    <c:if test="${errors!=null}">
        <div class="alert-danger">
            <c:forEach var = "error" items="${errors}">
                <ul>
                    <li>${error}</li>
                </ul>
            </c:forEach>
        </div>
    </c:if>


    <form method="post" action="Controller?action=Submit" novalidate="novalidate">
    	<!-- novalidate in order to be able to run tests correctly -->
        <p><label for="userId">Login name:</label><input type="text" id="userId" name="userId"
        required value="<c:out value='${param.userId}'/>"></p>
        <p><label for="firstName">First Name:</label><input type="text" id="firstName" name="firstName"
         required value="<c:out value ='${param.firstName}'/>"> </p>
        <p><label for="lastName">Last Name:</label><input type="text" id="lastName" name="lastName"
         required value="${param.lastName}"> </p>
        <p>
            <label for="streetName">Street Name:</label><input type="text" id="streetName" name="streetName"
                                                              required value="<c:out value ='${param.streetName}'/>">
        </p>
        <p>
            <label for="streetNumber">Street Number:</label><input type="number" id="streetNumber" name="streetNumber"
                                                                                      required value="<c:out value ='${param.streetNumber}'/>">
        </p>
        <p>
            <label for="city">City:</label><input type="text" id="city" name="city" required value="<c:out value ='${param.city}'/>">
        </p>
        <p><label for="password">Password:</label><input type="password" id="password"  name="password"
         required value="<c:out value='${param.password}'/>"> </p>
        <p><input type="submit" id="signUp" value="Sign Up"></p>
    </form>
</main>
<footer>
&copy; Webontwikkeling 3, UC Leuven-Limburg
</footer>
</div>
</body>
</html>
