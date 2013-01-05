<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
    <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
    <script type="text/javascript">
        $(function(){
            $( '#birthDate' ).datepicker();
        });
    </script>
</head>
<body>
<h1>Adding new Participant</h1>
<form:form method="post" action="saveParticipant.htm" modelAttribute="newParticipant"
        onsubmit="">
    <div>
        Name:<input name="name" value="${newParticipant.name}"/><br/>
        Birth Date:<input type="text" name="birthDate" id="birthDate"
                          value="${newParticipant.birthDate}"/><br/>
    </div>
<br/>

<input type="submit" value="Save" />

</form:form>
<hr/>
<a href="<c:url value='/hello.htm'/>">Home</a>
</body>
</html>