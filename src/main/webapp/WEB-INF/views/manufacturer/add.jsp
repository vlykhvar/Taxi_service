<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add manufacture</title>
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
<div style="text-align: center;"><h1>Add Manufacture</h1>
    <h4>${message}</h4>
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
