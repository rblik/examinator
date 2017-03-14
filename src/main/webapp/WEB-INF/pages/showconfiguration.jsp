<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <link rel="shortcut icon" href="resources/images/favicon.ico">
    </head>
    <body>
    <div style="float: right; padding-right: 25%">
        <form action="j_spring_security_logout" method="post">
            <input type="submit" value="Logout"/>
        </form>
    </div>
    <div>

        <a href="${pageContext.request.contextPath}/">See all configurations</a>
        Configuration <b>${id}</b>:
        <hr>
        <div id="pictureHolder">

        </div>

        <pre>${configuration}</pre>
    </div>


        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script>
            var jsonStr = $("pre").text();
            var jsonObj = JSON.parse(jsonStr);
            var jsonPretty = JSON.stringify(jsonObj, null, '\t');

            $("pre").text(jsonPretty);

            <c:if test='${hasImage}'>
            $("#pictureHolder").prepend('<img src="${pageContext.request.contextPath}/image/${id}" height="180" width="240"/>');
            </c:if>
        </script>
    </body>
</html>