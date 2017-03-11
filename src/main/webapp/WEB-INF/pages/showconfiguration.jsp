<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <link rel="shortcut icon" href="resources/images/favicon.ico">
    </head>
    <body>
        Configuration <b>${id}</b>:
        <hr>
        <div id="pictureHolder">

        </div>
        <pre>${configuration}</pre>


        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script>
            var jsonStr = $("pre").text();
            var jsonObj = JSON.parse(jsonStr);
            var jsonPretty = JSON.stringify(jsonObj, null, '\t');

            $("pre").text(jsonPretty);

            <c:if test='${!"picture".equals(picName)}'>
                $("#pictureHolder").prepend("<img src='/images/${picName}.jpg' alt='Picture' height='180' width='240'>")
            </c:if>
        </script>
    </body>
</html>