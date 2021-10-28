<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Admin Page!!!!</h1>
<%--    <a href="<spring:url value="admin/allUsers"/>">All users</a>--%>
    <a href="${pageContext.request.contextPath}/admin/allUsers">All users</a>
    <a href="/admin/allUsers">All users</a>
    <a href="${pageContext.request.contextPath}">Here</a>
</body>
</html>
