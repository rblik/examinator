<%--suppress HtmlUnknownTarget --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" href="resources/images/favicon.ico">
    <title>Registration</title>
</head>
<body>
<h2>${login? 'Login' : 'Register'}</h2>
<br>
<div>
    <a href="${pageContext.request.contextPath}/${login? 'register': 'login'}"><h4>${login? 'Register': 'Login'}</h4></a>
</div>
<hr>
<c:if test="${error}">
    <div class="error">
        <p style="color: red">
            ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
        </p>
    </div>
</c:if>
<c:if test="${not empty message}">
    <div class="message">
    <p style="color: ${fn:startsWith(message, 'User with')? 'red':'green'}">${message}</p>
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
