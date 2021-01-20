<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Cars</title>
</head>
<body>

<div style="text-align: center;"><h1>Add Cars</h1>
<form method="post" action="${pageContext.request.contextPath}/cars/add">
    Please, provide car's model: <label>
    <input type="text" name = model> <br>
</label>
    Please, provide manufacture' id: <label>
    <input type="text" name = manufacturerId> <br>
</label>
    <button type="submit">Add</button> <br>
</form>
</div>
</body>
</html>