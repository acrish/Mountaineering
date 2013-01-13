<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head><title>Add new expedition</title></head>
<body>
<h1>Adding new Expedition</h1>
<form:form method="post" action="saveExpedition.htm" modelAttribute="newExp">
    <div>
        Name:<input name="name" value="${newExp.name}"/><br/>
        Description:<input name="description" value="${newExp.description}"/><br/>
        Mountain:<form:select path="mountainName" items="${maps}"/><br/>
        Start Village:<input name="startVillage" value="${newExp.startVillage}"/>
        End Village:<input name="startVillage" value="${newExp.endVillage}"/>
    </div>
<br/>

<input type="submit" value="Save" />

</form:form>
<hr/>
<a href="<c:url value='/hello.htm'/>">Home</a>
</body>
</html>