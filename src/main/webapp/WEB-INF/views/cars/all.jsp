<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cars</title>
</head>
<body>
<div>
    <h1>Cars</h1>
        <tr>
            <th>id</th>
            <th>model</th>
            <th>manufacturer</th>
            <th>drivers</th>
        </tr>
        <c:forEach var="car" items="${cars}">
            <tr>
                <td>
                    <c:out value="${car.id}"/>
                </td>
                <td>
                    <c:out value="${car.model}"/>
                </td>
                <td>
                    <c:out value="${car.manufacturer.name}"/>
                </td>
                <td>
                    <table border="0">
                        <c:choose>
                            <c:when test="${car.drivers.size() > 0}">
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                </tr>
                                <c:forEach var="driver" items="${car.drivers}">
                                    <tr>
                                        <td>
                                            <c:out value="${driver.id}"/>
                                        </td>
                                        <td>
                                            <c:out value="${driver.name}"/>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td>No drivers</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </table>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>