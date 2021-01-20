<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Manufacture</title>
</head>
<body>
<div style="text-align: center;"><h1>All Manufacture</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Country</th>
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
                <a href ="${pageContext.request.contextPath}/manufacturer/delete?id=${manufacturer.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</div>
</body>
</html>
