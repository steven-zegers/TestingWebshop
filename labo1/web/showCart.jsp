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
            Shopping cart
        </h2>
    </header>
    <main>
        <table>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
            </tr>
            <c:if test="${shopCart!=null}">
                <c:forEach var="cartItem" items="${shopCart}">
                    <tr>
                        <td>${cartItem.product.name}</td>
                        <td>${cartItem.product.price}</td>
                        <td>${cartItem.quantity}</td>
                    </tr>
                </c:forEach>
            </c:if>
            <p>The total price is: ${total}</p>
            <caption>Product Overview</caption>
        </table>
        <form method="post" action="Controller?action=Order" novalidate="novalidate">
        <!-- novalidate in order to be able to run tests correctly -->
        <p><input type="submit" id="Order" value="Place order"></p>

    </form>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>