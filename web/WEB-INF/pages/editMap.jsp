<%--
  Created by IntelliJ IDEA.
  User: Hac
  Date: 1/13/13
  Time: 10:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit a map</title>
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
<h3>Edit map</h3>
<form:form method="post" action="saveMap.htm?flag=edit" modelAttribute="newMap">
    <div>
        <input type="hidden" name="id" value="${newMap.id}"/>
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