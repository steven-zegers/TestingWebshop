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
                <li><a href="Controller?action=ProductOverview">Books</a></li>
                <li id="actual"><a href="Controller?action=ShoppingCart">Shopping cart</a></li>
                <li><a href="Controller?action=SignUp">Sign up</a></li>
            </ul>
        </nav>
        <h2>
            Shopping cart
        </h2>
    </header>
    <main>
        <c:if test="${errors != null}">
            <div class="alert-danger">
                <ul>
                    <c:forEach items="${errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>

                </ul>
            </div>
        </c:if>
        <table>
            <tr>
                <th>Name</th>
                <th>Price</th>
                <th>Amount</th>
            </tr>
            <c:if test="${shopCart!=null}">
                <c:if test="${not empty shopCart}">
                    <c:forEach var="cartItem" items="${shopCart}">
                        <tr>
                            <td>${cartItem.product.name}</td>
                            <td>${cartItem.product.price}</td>
                            <td>${cartItem.quantity}</td>
                            <form action="Controller?action=ChangeQuantity&id=${cartItem.product.productId}" method="post">
                                <td><input type="number" value="1" name="quantity"></td>
                                <td><input type="submit" value="Change quantity"></td>
                            </form>
                            <td><a href="Controller?action=DeleteFromCart&id=${cartItem.product.productId}">Delete from cart</a></td>
                        </tr>
                    </c:forEach>
                </c:if>
            </c:if>
            <p>The total price is: ${total}</p>
            <caption>Product Overview</caption>
        </table>
        <c:if test="${shopCart!=null}">
            <c:if test="${not empty shopCart}">
                <form method="post" action="Controller?action=Order" novalidate="novalidate">
                <!-- novalidate in order to be able to run tests correctly -->
                    <p><input type="submit" id="Order" value="Place order"></p>
                </form>
            </c:if>
        </c:if>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>