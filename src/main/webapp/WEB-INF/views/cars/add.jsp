<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Cars</title>
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

<div style="text-align: center;"><h1>Add Cars</h1>
    <h4>${message}</h4>
    <form method="post" action="${pageContext.request.contextPath}/cars/add">
        Please, provide car's model:
        <label>
            <input type="text" name = model> <br>
        </label>
        Please, provide manufacture' id:
        <label>
            <input type="number" name = manufacturerId> <br>
        </label>
        <button type="submit">Add</button> <br>
    </form>
</div>
</body>
</html>
