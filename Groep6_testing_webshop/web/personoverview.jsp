
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Overview</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
<header>
<h1><span>Web shop</span></h1>
    <nav>
        <ul>
            <li><a href="Controller">Home</a></li>
            <li><a href="Controller?action=ProductOverview">Books</a></li>
            <li><a href="Controller?action=ShoppingCart">Shopping cart</a></li>
            <li><a href="Controller?action=SignUp">Sign up</a></li>
        </ul>
    </nav>
<h2>
User Overview
</h2>

</header><main>
<table>
    <form action="Controller?action=Sort" method="post" novalidate="novalidate">
        <label for="sort">Options:</label>
        <select name="sort" id="sort">
            <option value="fname" <c:if test="${sort=='fname'}">selected</c:if>>First Name</option>
            <option value="lname" <c:if test="${sort=='lname'}">selected</c:if>>Last Name</option>
        </select>
        <input type="submit" name="submit" value="Submit"/>
    </form>

    <tr>
            <th>First name</th>
            <th>Last name</th>
            <th>Street name</th>
            <th>Street number</th>
            <th>City</th>
        </tr>
    <c:forEach var="person" items="${persons}">
        <tr>
            <td>${person.firstName}</td>
            <td>${person.lastName}</td>
            <td>${person.streetName}</td>
            <td>${person.streetNumber}</td>
            <td>${person.city}</td>
            <td><a href="Controller?action=CheckPassword&id=${person.userid}">Check</a></td>
        </tr>
    </c:forEach>


<caption>Users Overview</caption>
</table>
</main>
<footer>
&copy; Webontwikkeling 3, UC Leuven-Limburg
</footer>
</div>
</body>
</html>