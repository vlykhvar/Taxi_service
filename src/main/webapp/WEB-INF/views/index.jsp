<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="text-align: center;"><h1>Hello Page!</h1>
<a href = "${pageContext.request.contextPath}/manufacturer/add"> Add new manufacturer </a><br>
<a href= "${pageContext.request.contextPath}/manufacturer/"> Get all manufacturer </a><br>
<a href= "${pageContext.request.contextPath}/drivers/add"> Add new driver to our team </a><br>
    <a href = "${pageContext.request.contextPath}/cars/add"> Add new car to our park </a> <br>
<a href= "${pageContext.request.contextPath}/cars/drivers/add"> Add driver to car </a><br>
<a href= "${pageContext.request.contextPath}/drivers/"> Get all drivers </a><br>
</div>
</body>
</html>
