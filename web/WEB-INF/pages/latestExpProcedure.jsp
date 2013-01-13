<%--
  Created by IntelliJ IDEA.
  User: Hac
  Date: 1/13/13
  Time: 8:05 PM
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Latest Expeditions Procedure</title>
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
<body><%--<h1>Procedure code</h1>
<div>CREATE DEFINER=`root`@`localhost` PROCEDURE `latestExpeditions`()
BEGIN

     DECLARE pid INT;
     DECLARE eid INT;
     DECLARE mid INT;
     DECLARE rstart DATE;
     DECLARE rend DATE;
     DECLARE mname VARCHAR(255);
     DECLARE ename VARCHAR(255);
     DECLARE expstart VARCHAR(255);
     DECLARE expend VARCHAR(255);
     DECLARE pname VARCHAR(255);
     DECLARE pdate DATE;
     DECLARE exit_loop BOOLEAN;

     DECLARE regs CURSOR FOR
             SELECT expedition_id, participant_id, start_date, end_date
             FROM registries
             WHERE start_date > STR_TO_DATE('2012-09-01', '%Y-%m-%d');

     DECLARE CONTINUE HANDLER FOR NOT FOUND SET exit_loop = TRUE;

     OPEN regs;

     DELETE FROM latest_exp;
     regs_loop: LOOP
                IF  exit_loop THEN
                    LEAVE regs_loop;
                END IF;

                FETCH regs INTO eid, pid, rstart, rend;
                SELECT name, birth_date INTO pname, pdate
                FROM participants
                WHERE participant_id = pid;

                SELECT expname, start_village, end_village, map_id
                       INTO ename, expstart, expend, mid
                FROM expeditions
                WHERE expid = eid;

                SELECT mountain INTO mname
                FROM expeditionmaps
                WHERE mid = map_id;

                INSERT INTO latest_exp(mname, ename, expstart, expend, pname, pdate)
                       VALUES (mname, ename, expstart, expend, pname, pdate);

     END LOOP regs_loop;
     CLOSE regs;


END</div>--%>
<h2>Display latest expeditions</h2>
<hr/>
<h6>Expeditions since sept 2012 and participants</h6>
<table>
    <tr>
        <th>Mountain Name</th>
        <th>Expedition Name</th>
        <th>Expedition start village</th>
        <th>Expedition end village</th>
        <th>Participant name</th>
        <th>Participant birth date</th>
    </tr>
    <c:forEach items="${latests}" var="item">
        <tr>
            <td><c:out value="${item.mname}"/></td>
            <td><c:out value="${item.ename}"/></td>
            <td><c:out value="${item.expstart}"/></td>
            <td><c:out value="${item.expend}"/></td>
            <td><c:out value="${item.pname}"/></td>
            <td><c:out value="${item.pdate}"/></td>
        </tr>
    </c:forEach>
</table>
<a href="<c:url value='/hello.htm'/>">Home</a>
</body>
</html>