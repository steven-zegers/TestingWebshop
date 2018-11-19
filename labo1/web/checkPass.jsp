
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Check password</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>Check pass</span></h1>
        <nav>
            <ul>
                <li><a href="Controller">Home</a></li>
                <li><a href="Controller?action=PersonOverview">Overview</a></li>
                <li><a href="Controller?action=SignUp">Sign up</a></li>
                <li><a href="Controller?action=ProductOverview">Product overview</a></li>
            </ul>
        </nav>
        <h2>
            Password Checker
        </h2>

    </header>
    <main>
        <p>${tekst}</p>

        <form method="post" action="Controller?action=CheckPass&userid=${person.userid}" novalidate="novalidate">
            <p><label for="password">Password</label><input type="password" id="password" name="password" required></p>
            <p><input type="submit" id="check" value="Check"></p>
        </form>

    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>