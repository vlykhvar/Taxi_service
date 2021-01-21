<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
<div style="text-align: center;"><h1>Hello Page!</h1>
<a href = "${pageContext.request.contextPath}/manufacturers/add"> Add new manufacturer </a><br>
<a href= "${pageContext.request.contextPath}/manufacturers/"> Get all manufacturer </a><br>
<a href= "${pageContext.request.contextPath}/drivers/add"> Add new driver to our team </a><br>
<a href = "${pageContext.request.contextPath}/cars/add"> Add new car to our park </a> <br>
<a href= "${pageContext.request.contextPath}/cars/drivers/add"> Add driver to car </a><br>
<a href= "${pageContext.request.contextPath}/drivers/"> Get all drivers </a><br>
</div>
</body>
</html>
