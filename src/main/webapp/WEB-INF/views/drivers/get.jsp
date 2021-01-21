<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All drivers</title>
</head>
<style>
    body {
        background-color: rebeccapurple;
    }
    h1 {
        background-color: blueviolet;
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
    table {
        width: 300px; /* Ширина таблицы */
        border: 1px solid green; /* Рамка вокруг таблицы */
        margin: auto; /* Выравниваем таблицу по центру окна  */
    }
    td {
        text-align: center; /* Выравниваем текст по центру ячейки */
    }
    p {
        color: rgb(49, 151, 116); /* Цвет текста */
    }
</style>
<body>
<div style="text-align: center;"><h1>All Drivers</h1>
    <h4>${message}</h4>
    <table border="1" style="align-content: center">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>License Number</th>
            <th>Delete Button</th>
        </tr>
        <c:forEach var = "driver" items="${drivers}">
            <tr>
                <td>
                    <c:out value="${driver.id}" />
                </td>
                <td>
                    <c:out value="${driver.name}"/>
                </td>
                <td>
                    <c:out value="${driver.licenseNumber}"/>
                </td>
                <td>
                    <a href ="${pageContext.request.contextPath}/drivers/delete?id=${driver.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
