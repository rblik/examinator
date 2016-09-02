<p>
    Configuration <b>${id}</b>:
    <hr>
    <pre>${configuration}</pre>
    <script>
        var jsonStr = $("pre").text();
        var jsonObj = JSON.parse(jsonStr);
        var jsonPretty = JSON.stringify(jsonObj, null, '\t');

        $("pre").text(jsonPretty);
    </script>
</p>