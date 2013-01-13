<%--
  Created by IntelliJ IDEA.
  User: Hac
  Date: 1/5/13
  Time: 5:52 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add new map</title>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
    <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
    <script type="text/javascript">
        $(function(){
            $( '#issueDate' ).datepicker();
        });
    </script>
</head>
<body>
<h1>Add a map</h1>
<form:form method="post" action="saveMap.htm" modelAttribute="newMap">
    <div>
        Mountain:<input name="mountain" value="${newMap.mountain}"/><br/>
        Since:<input id="issueDate" name="issueDate" value="${newMap.issueDate}"/><br/>
        Picture url:<input name="picUrl" value="${newMap.picUrl}"/>
    </div>
<br/>

<input type="submit" value="Save" />

</form:form>
<hr/>
<a href="<c:url value='/hello.htm'/>">Home</a>
</body>
</html>