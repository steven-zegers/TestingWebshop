
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
            <li id="actual"><a href="Controller?action=PersonOverview">Overview</a></li>
            <li><a href="Controller?action=SignUp">Sign up</a></li>
            <li><a href="Controller?action=ProductOverview">Product overview</a></li>
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
            <option value="email" <c:if test="${sort=='email'}">selected</c:if>>Email</option>
            <option value="fname" <c:if test="${sort=='fname'}">selected</c:if>>First Name</option>
            <option value="lname" <c:if test="${sort=='lname'}">selected</c:if>>Last Name</option>
        </select>
        <input type="submit" name="submit" value="Submit"/>
    </form>

    <tr>
            <th>Email</th>
            <th>First name</th>
            <th>Last name</th>
        </tr>
    <c:forEach var="person" items="${persons}">
        <tr>
            <td>${person.email}</td>
            <td>${person.firstName}</td>
            <td>${person.lastName}</td>
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