
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
                <li id="actual"><a href="Controller?action=SearchBook">Search a book</a></li>
                <li><a href="Controller?action=ShowCart">Shopping cart</a></li>
                <li><a href="Controller?action=SignUp">Sign up</a></li>
            </ul>
        </nav>
        <h2>
            Search a book
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

        <p><a href="Controller?action=ShowCart">Show Cart</a></p>

        <form action="Controller?action=Search" method="post" novalidate="novalidate">
            <label for="searchString">Keyword: </label>
            <input type="text" value="" name="searchString" id="searchString">
            <label for="method">Search by:</label>
            <select name="method" id="method">
                <option value="title"  <c:if test="${search=='title'}">selected</c:if>>Title</option>
                <option value="author"  <c:if test="${search=='author'}">selected</c:if>>Author</option>
            </select>
            <input type="submit" name="submit" value="Submit"/>
        </form>
        <c:if test="${producten != null}">
            <c:choose>
                <c:when test="${empty producten}">
                    <p>We are very sorry but we did not find any books in our great selection that match your keyword.</p>
                </c:when>
                <c:otherwise>
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
                </c:otherwise>
            </c:choose>
        </c:if>

    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>