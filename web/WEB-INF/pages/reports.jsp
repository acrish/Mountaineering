<%--
  Created by IntelliJ IDEA.
  User: Hac
  Date: 1/13/13
  Time: 9:56 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Aggregated Info</title>
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
</head>
<body>
<table>
    <tr>
        <th>Report Description</th>
        <th>Report code</th>
    </tr>
    <tr>
        <td>Huts built in last 200 years</td>
        <td>mysql> select m.mountain Mountain,m.issue_date Map_Issue_Date, h.name Hut, h.build_date Build_Date from huts h, expeditionmaps m where h.build_date > date_sub(c
urdate(), interval 200 year) and m.map_id=h.map_id;</td>
    </tr>
    <tr>
        <td>Fastest expedition in "Piatra Craiului" mountain</td>
        <td>mysql> select p.name Participant, p.birth_date Birth_Date, e.expname Expedition,
 m.mountain Mountain, min(datediff(r.end_date, r.start_date)) Days from expeditionmaps m, expeditions e, registries r, participants p where e.map_id=m.map_id and r.registry_id=p.participant_id and m.mountain='Piatra Craiului' GROUP BY p.nam
e;</td>
    </tr>
    <tr>
        <td>Participants of "Grossglockner" expedition</td>
        <td>mysql> select p.*, e.* from participants p, expeditions e, registries r, expeditionmaps m where r.participant_id=p.participant_id and e.expid=r.expedition_id an
d m.map_id=e.map_id and m.mountain='Grossglockner';</td>
    </tr>
    <%--<c:forEach items="${report1}" var="item">
        <tr>
            <td><c:out value="${item.Mountain}"/></td>
            <td><c:out value="${item.Map_Issue_Date}"/></td>
            <td><c:out value="${item.Hut}"/></td>
            <td><c:out value="${item.Build_Date}"/></td>
        </tr>
    </c:forEach>--%>
</table>
<br/>
<a href="<c:url value='/hello.htm'/>">Home</a>

</body>
</html>