    Configuration <b>${id}</b>:
    <hr>
    <pre>${configuration}</pre>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>
        var jsonStr = $("pre").text();
        var jsonObj = JSON.parse(jsonStr);
        var jsonPretty = JSON.stringify(jsonObj, null, '\t');

        $("pre").text(jsonPretty);
    </script>