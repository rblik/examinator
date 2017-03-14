<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Configurations</title>
    <link rel="shortcut icon" href="resources/images/favicon.ico">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
<div style="float: right; padding-right: 25%">
    <form action="j_spring_security_logout" method="post">
        <input type="submit" value="Logout"/>
    </form>
</div>
<h3>Configurations from ${currentUser}</h3>
<hr>
<a href="${pageContext.request.contextPath}/create">Create new configuration</a>
<hr>
<table style="width: 500px; margin: 25px; border-style: ${(list.size()==0)?'none':'dashed'}; text-align: center">
    <tbody>
        <c:forEach items="${list}" var="conf">
            <tr>
                <td colspan="3"><a href="config/${conf.configurationId}">${conf.title}</a></td>
                <td colspan="1"><button style="font-size: 10px" onclick="remove(${conf.configurationId})">Delete</button></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

    <script>
        function remove(id) {
            $.ajax({
                url: "config/" + id,
                type: 'DELETE',
                success: function () {
                    document.location.href = '${pageContext.request.contextPath}/';
                }
            });
        }
    </script>
</body>
</html>
