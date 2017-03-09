<%--suppress HtmlUnknownTarget --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" href="resources/images/favicon.ico">
    <title>Registration</title>
    <style>
        .register {
            display: none;
        }
    </style>
</head>
<body>
<h2>${login? 'Login' : 'Register'}</h2>
<br>
<div class="${login? 'login': 'register'}">
    <a href="${pageContext.request.contextPath}/register"><h4>Register</h4></a>
</div>
<hr>
<c:if test="${error}">
    <div class="error">
            ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
    </div>
</c:if>
<c:if test="${not empty message}">
    <div class="message">${message}
    </div>
</c:if>
<br>
<div>
    <form id="teacher" action="${login? 'spring_security_check':'register'}" method="post">
        <input id="${login? 'j_username':'userName'}" placeholder="username" name="${login? 'j_username':'userName'}" type="text" value="">
        <input id="${login? 'j_password': 'password'}" placeholder="password" name="${login? 'j_password': 'password'}" type="password" value="">
        <hr>
        <button type="submit">Submit</button>
    </form>
</div>

</body>
</html>
