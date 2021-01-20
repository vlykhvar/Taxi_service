<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add driver</title>
</head>
<body>
<div style="text-align: center;"><h1>Add Driver</h1>
<form method="post" action="${pageContext.request.contextPath}/drivers/add">
   Please provide driver's name: <label>
    <input type="text" name = name> <br>
</label>
    Please provide driver's licence number: <label>
    <input type="text" name = licenceNumber> <br>
</label>
    <button type="submit">Add</button> <br>
</form>
</div>
</body>
</html>
