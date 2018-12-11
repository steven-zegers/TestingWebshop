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
        <h1><span>Prato book shop</span></h1>
        <nav>
            <ul>
                <li><a href="Controller">Home</a></li>
                <li><a href="Controller?action=ProductOverview">Books</a></li>
                <li><a href="Controller?action=SearchBook">Search a book</a></li>
                <li id="actual"><a href="Controller?action=ShowCart">Shopping cart</a></li>
                <li><a href="Controller?action=SignUp">Sign up</a></li>
            </ul>
        </nav>
        <h2>
            Shopping cart
        </h2>
    </header>
    <main>

        <c:if test="${notifications != null}">
            <div class="notification">
                <ul>
                    <c:forEach items="${notifications}" var="notification">
                        <li>${notification}</li>
                    </c:forEach>

                </ul>
            </div>
        </c:if>

        <c:if test="${errors != null}">
            <div class="alert-danger">
                <ul>
                    <c:forEach items="${errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>

                </ul>
            </div>
        </c:if>
        <c:choose>
            <c:when test="${not empty shopCart}">
                <table>
                    <tr>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Amount</th>
                    </tr>
                    <c:forEach var="cartItem" items="${shopCart}">
                    <tr>
                        <td>${cartItem.product.title}</td>
                        <td>${cartItem.product.price}</td>
                        <td>${cartItem.quantity}</td>
                        <form action="Controller?action=ChangeQuantity&id=${cartItem.product.title}" method="post">
                            <td><input type="number" value="1" name="quantity"></td>
                            <td><input type="submit" value="Change quantity"></td>
                        </form>
                        <td><a href="Controller?action=DeleteFromCart&id=${cartItem.product.title}">Delete from cart</a></td>
                    </tr>
                    </c:forEach>
                    <p>The total price is: ${total}</p>
                    <caption>Product Overview</caption>
                </table>
                <c:choose>
                    <c:when test="${sessionScope.person != null}">
                        <form method="post" action="Controller?action=Order" novalidate="novalidate">
                            <p><input type="submit" id="Order" value="Place order"></p>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <div class="alert-danger">
                            <h3>If you would like to order these items please sign in <a href="Controller?action=Home">here</a> if you already have an account.</h3>
                            <h3>If you do not yet have an account you can <a href="Controller?action=SignUp">sign up</a>!</h3>
                        </div>
                    </c:otherwise>
                </c:choose>

            </c:when>
            <c:otherwise>
                <h3>You currently do not have any items in your shopping cart.</h3>
                <p>If you would like to check out our selection of books click <a href="Controller?action=ProductOverview">here</a></p>
                <p>If you have a certain book in mind, you can check if we currently have this book for sale by clicking <a href="Controller?action=SearchBook">here</a></p>
            </c:otherwise>
        </c:choose>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>