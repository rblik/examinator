<html>
    <head>
        <link rel="shortcut icon" href="resources/images/favicon.ico">
    </head>
    <body>
        <p>
            You have created new configuration, its id: <b>${configurationId}</b>. Pass it over to your students.
        <hr>
        <a href="${pageContext.request.contextPath}/create">Create another configuration</a>
        <hr>
        <a href="${pageContext.request.contextPath}/config/${configurationId}">See configuration</a>
        <hr>
        <a href="${pageContext.request.contextPath}/">See all configurations</a>
        </p>
    </body>
</html>