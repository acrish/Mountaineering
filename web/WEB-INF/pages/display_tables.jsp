<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        table, td, th
        {
            border:1px solid green;
        }
        th
        {
            background-color:green;
            color:white;
        }
    </style>
    <%--<link rel="stylesheet" href="css/display_table.css"/>--%>
    <title>Expeditions - display</title>
</head>
<body>
<h2>Display table EXPEDITIONS</h2>
<hr/>
<table>
    <tr>
        <th>Name</th>
        <th>Description</th>
    </tr>
    <c:forEach items="${expeditions}" var="item">
        <tr>
            <td>
                <c:out value="${item.name}"/>
            </td>
            <td>
                <c:out value="${item.description}"/>
            </td>
        </tr>
    </c:forEach>
</table>
<h2>Display table MAPS</h2>
<table>
    <tr>
        <th>Mountain</th>
        <th>Issue Date</th>
    </tr>
    <c:forEach items="${maps}" var="item">
        <tr>
            <td>
                <c:out value="${item.mountain}"/>
            </td>
            <td>
                <c:out value="${item.issueDate}"/>
            </td>
        </tr>
    </c:forEach>
</table>
<hr/>
<a href="<c:url value='hello.htm'/>">Home</a>
</body>
</html>