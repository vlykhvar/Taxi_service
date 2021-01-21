<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Manufacture</title>
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
    table {
        width: 300px;
        border: 2px solid darkgreen;
        margin: auto;
    }
    td {
        text-align: center;
    }
</style>
<body>
<div style="text-align: center;"><h1>All Manufacturers</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Country</th>
            <th>Delete Button</th>
        </tr>
        <c:forEach var = "manufacturer" items="${manufacturers}">
            <tr>
                <td>
                    <c:out value="${manufacturer.id}" />
                </td>
                <td>
                    <c:out value="${manufacturer.name}"/>
                </td>
                <td>
                    <c:out value="${manufacturer.country}"/>
                </td>
                <td>
                    <a href ="${pageContext.request.contextPath}/manufacturers/delete?id=${manufacturer.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
