<%--
  Created by IntelliJ IDEA.
  User: Hac
  Date: 1/5/13
  Time: 5:11 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add new hut</title>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
    <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
    <script type="text/javascript">
        $(function(){
            $( '#buildDate' ).datepicker();
        });
    </script>
</head>
<body>
<h1>Add a hut</h1>
<form:form method="post" action="saveHut.htm" modelAttribute="newHut">
    <div>
        Name:<input name="name" value="${newHut.name}"/><br/>
        Phone:<input name="phone" value="${newHut.phone}"/><br/>
        Coordinates:<input name="coordinates" value="${newHut.coordinates}"/><br/>
        Since:<input id="buildDate" name="buildDate" value="${newHut.buildDate}"/><br/>
        Map id:<form:select path="expMap" items="${maps}"/><br/>
        Picture url:<input name="picUrl" value="${newHut.picUrl}"/><br/>
        Capacity:<input name="capacity" value="${newHut.capacity}"/><br/>
        Price/person/night(RON):<input name="price" value="${newHut.price}"/><br/>
    </div>
<br/>

<input type="submit" value="Save" />

</form:form>
<hr/>
<a href="<c:url value='/hello.htm'/>">Home</a>
</body>
</html>