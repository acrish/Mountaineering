<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
    <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
    <script type="text/javascript">
        $(function(){
            $( '#startDate' ).datepicker();
        });
        $(function(){
            $( '#endDate' ).datepicker();
        });
    </script>
</head>
<body>
<h1>Adding new Registry</h1>
<form:form method="post" action="saveRegistry.htm" modelAttribute="newReg">
    <div>
        Start Date:<input id="startDate" name="startDate" value="${newReg.startDate}"/><br/>
        End Date:<input id="endDate" name="endDate" value="${newReg.endDate}"/><br/>
        Expedition:<form:select path="exp" items="${expeditions}"/><br/>
        Participant:<form:select path="participant" items="${participants}"/><br/>
    </div>
<br/>

<input type="submit" value="Save" />

</form:form>
<hr/>
<a href="<c:url value='/hello.htm'/>">Home</a>
</body>
</html>