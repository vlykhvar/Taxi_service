 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<style>
    body {
        background-color: rebeccapurple;
    }
    a:link {
        color: black;
    }
    a:visited {
        color: black;
    }
    a:hover {
        color: black;
    }
    a:active {
        color: black;
    }
</style>
<body>

<h1>Login page</h1>

<form action="${pageContext.request.contextPath}/drivers/login" method="post">
    Please provide you login = <input type="text" name = "login">
    Please provide you password = <input type="password" name = "pass">
    <button type="submit">Login</button>
    <a href ="${pageContext.request.contextPath}/drivers/add">Registration</a>
</form>
</body>
</html>
