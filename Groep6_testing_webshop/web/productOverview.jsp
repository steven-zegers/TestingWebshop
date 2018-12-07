
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
                <li id="actual"><a href="Controller?action=ProductOverview">Books</a></li>
                <li><a href="Controller?action=SearchBook">Search a book</a></li>
                <li><a href="Controller?action=ShowCart">Shopping cart</a></li>
                <li><a href="Controller?action=SignUp">Sign up</a></li>
            </ul>
        </nav>
        <h2>
            Product Overview
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

        <p><a href="Controller?action=ShowCart">Show Cart</a></p>

        <form action="Controller?action=Sort" method="post" novalidate="novalidate">
            <label for="sort">Sort by:</label>
            <select name="sort" id="sort">
                <option value="title"  <c:if test="${sort=='title'}">selected</c:if>>Title</option>
                <option value="price"  <c:if test="${sort=='price'}">selected</c:if>>Price</option>
                <option value="fname" <c:if test="${sort=='fname'}">selected</c:if>>First name of author</option>
                <option value="lname" <c:if test="${sort=='lname'}">selected</c:if>>Last anme of author</option>
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