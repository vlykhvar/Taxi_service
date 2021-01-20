<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add manufacture</title>
</head>
<body>
<div style="text-align: center;"><h1>Add Manufacture</h1>
<form method="post" action="${pageContext.request.contextPath}/manufacturer/add">
   Please provide manufacturer's name: <label>
    <input type="text" name = name> <br>
</label>
    Please provide manufacturer's country: <label>
    <input type="text" name = country> <br>
</label>
    <button type="submit">Add</button> <br>
</form>
</div>
</body>
</html>
