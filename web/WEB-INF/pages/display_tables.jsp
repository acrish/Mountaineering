<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
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
        td, th {
            margin: 5px;
            padding: 5px;
        }
        .noborder
        {
            border: 0;
        }
    </style>
    <%--<link rel="stylesheet" href="css/display_table.css"/>--%>
    <title>Expeditions - display</title>
</head>
<body>

<h2>Display table MAPS</h2>
<table>
    <tr>
        <th>Id</th>
        <th>Mountain</th>
        <th>Issue Date</th>
    </tr>
    <c:forEach items="${maps}" var="item">
        <tr>
            <td><c:out value="${item.id}"/></td>
            <td><c:out value="${item.mountain}"/></td>
            <td><c:out value="${item.issueDate}"/></td>
        </tr>
    </c:forEach>
</table>
<a href="<c:url value='/addMap.htm'/>">Add Expedition Map</a>
<hr/>
<h2>Display table EXPEDITIONS</h2>
<hr/>
<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Description</th>
        <th>Map reference</th>
        <th>Start village</th>
        <th>End village</th>
    </tr>
    <c:forEach items="${expeditions}" var="item">
        <tr>
            <td><c:out value="${item.expId}"/></td>
            <td><c:out value="${item.name}"/></td>
            <td><c:out value="${item.description}"/></td>
            <td><c:out value="${item.expMap.id}"/></td>
            <td><c:out value="${item.startVillage}"/></td>
            <td><c:out value="${item.endVillage}"/></td>
            <td class="noborder">
                <a href="<c:url value='/remove.htm'/>">Remove</a>
            </td>
        </tr>

    </c:forEach>
</table>
<a href="<c:url value='/addExpedition.htm'/>">Add Expedition</a>
<h2>Display table PARTICIPANTS</h2>
<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Birth Date</th>
    </tr>
    <c:forEach items="${participants}" var="item">
        <tr>
            <td><c:out value="${item.id}"/></td>
            <td><c:out value="${item.name}"/></td>
            <td><c:out value="${item.birthDate}"/></td>
        </tr>
    </c:forEach>
</table>
<a href="<c:url value='/addParticipant.htm'/>">Add Participant</a>
<hr/>
<h2>Display table REGISTRIES</h2>
<table>
    <tr>
        <th>Expedition</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Participant</th>
    </tr>
    <c:forEach items="${registries}" var="item">
        <tr>
            <td><c:out value="${item.exp.expId}"/></td>
            <td><c:out value="${item.startDate}"/></td>
            <td><c:out value="${item.endDate}"/></td>
            <td><c:out value="${item.participant.id}"/></td>
        </tr>
    </c:forEach>
</table>
<a href="<c:url value='/addRegistry.htm'/>">Register new expedition</a>
<hr/>
<h2>Display table Huts</h2>
<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Phone</th>
        <th>Coordinates</th>
        <th>Since</th>
        <th>Map id</th>
        <th>Picture</th>
        <th>Capacity</th>
        <th>Price/person/night(RON)</th>
    </tr>
    <c:forEach items="${huts}" var="item">
        <tr>
            <td><c:out value="${item.id}"/></td>
            <td><c:out value="${item.name}"/></td>
            <td><c:out value="${item.phone}"/></td>
            <td><c:out value="${item.coordinates}"/></td>
            <td><c:out value="${item.buildDate}"/></td>
            <td><c:out value="${item.expMap.id}"/></td>
            <td><c:out value="${item.picUrl}"/></td>
            <td><c:out value="${item.capacity}"/></td>
            <td><c:out value="${item.price}"/></td>
        </tr>
    </c:forEach>
</table>
<a href="<c:url value='/addHut.htm'/>">Add a hut</a>
<hr/>
<a href="<c:url value='/hello.htm'/>">Home</a>
</body>
</html>