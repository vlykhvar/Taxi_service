<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All drivers</title>
</head>
<body>
<div style="text-align: center;"><h1>All Drivers</h1>
<table border="1" style="align-content: center">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>License Number</th>
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
