
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
                <li><a href="Controller?action=PersonOverview">Overview</a></li>
                <li><a href="Controller?action=SignUp">Sign up</a></li>
                <li id="actual"><a href="Controller?action=ProductOverview">Product overview</a></li>
            </ul>
        </nav>
        <h2>
            Product Overview
        </h2>
        <p><a href="Controller?action=ShowCart&shopCart">Show Cart</a></p>
    </header>
    <main>
    <table>

        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
        </tr>
        <c:forEach var="product" items="${producten}">
            <tr>
                <td>${product.name}</td>
                <td>${product.description}</td>
                <td>${product.price}</td>
                <td><a href="Controller?action=AddToShoppingCart&id=${product.productId}">Add to cart</a></td>
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