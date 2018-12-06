
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Product Overview</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>Web shop</span></h1>
        <nav>
            <ul>
                <li><a href="Controller">Home</a></li>
                <li id="actual"><a href="Controller?action=ProductOverview">Books</a></li>
                <li><a href="Controller?action=ShowCart">Shopping cart</a></li>
                <li><a href="Controller?action=SignUp">Sign up</a></li>
            </ul>
        </nav>
        <h2>
            Product Overview
        </h2>
        <p><a href="Controller?action=ShowCart">Show Cart</a></p>
    </header>
    <main>
        <form action="Controller?action=Sort" method="post" novalidate="novalidate">
            <label for="sort">Sort by:</label>
            <select name="sort" id="sort">
                <option value="title"  <c:if test="${sort=='title'}">selected</c:if>>Title</option>
                <option value="price"  <c:if test="${sort=='price'}">selected</c:if>>Price</option>
                <option value="fname" <c:if test="${sort=='fname'}">selected</c:if>>First Name of Author</option>
                <option value="lname" <c:if test="${sort=='lname'}">selected</c:if>>Last Name of Author</option>
            </select>
            <input type="submit" name="submit" value="Submit"/>
        </form>
    <table>
        <tr>
            <th>Title</th>
            <th>Author</th>
            <th>Price</th>
        </tr>
        <c:forEach var="product" items="${producten}">
            <tr>
                <td>${product.title}</td>
                <td>${product.author}</td>
                <td>${product.price}</td>
                <form action="Controller?action=AddToShoppingCart&id=${product.title}" method="post">
                    <td><input type="number" value="1" name="quantity"></td>
                    <td><input type="submit" value="Add to cart"></td>
                </form>
            </tr>
        </c:forEach>


        <caption>Product Overview</caption>
    </table>
</main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>