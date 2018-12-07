<html>
<head>
    <TITLE>Delete Flight</TITLE>
</head>


<body>
<div style="margin-left:800px;">
    <h1>Delete Flight</h1>
    <form method="post" action="AdminServlet?action=deleteFlight">
        <label><b>Flight Number</b></label>
        <br>
        <input class="inputStyle" type="text" placeholder="Flight Number" name="flightNo">
        <br>
        <br>
        <input class="button" type="submit" value="Delete" name="submit">
    </form>
</div>
</body>
</html>