<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Ligin</h2>
    <form:form  action="${pageContext.request.contextPath}/login" method="post" modelAttribute="userLogin">


    </form:form>
</body>
</html>