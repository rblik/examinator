<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Error</title>
    <base href="${pageContext.request.contextPath}/"/>
    <link rel="shortcut icon" href="resources/images/favicon.ico">
</head>
<body>
<h2>${exception.message}</h2>
<c:forEach items="${exception.stackTrace}" var="stackTrace">
    ${stackTrace}
</c:forEach>
</body>
</html>
