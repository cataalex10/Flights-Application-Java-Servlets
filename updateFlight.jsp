<html>
<head>
    <TITLE>Update Flight</TITLE>
</head>
<body>
<div style="margin-left:800px;">
    <h1>Update Flight</h1>

    <form method="post" action="AdminServlet?action=updateFlight">
        <label><b>Flight No</b></label>
        <br>
        <input class="inputStyle" type="text" placeholder="Flight Number" name="flightNo">
        <br>
        <br>
        <label><b>Airplane Type</b></label>
        <br>
        <input class="inputStyle" type="text" placeholder="Airplane Type" name="airplaneType">
        <br>
        <br>
        <label><b>Departure Date</b></label>
        <br>
        <input class="inputStyle" type="text" id="departureDateTimeUpdate" placeholder="Departure Date" name="departureTime">
        <br>
        <br>
        <label><b>Arrival Date</b></label>
        <br>
        <input class="inputStyle" type="text" id="arrivalDateTimeUpdate" placeholder="Arrival Date" name="arrivalTime">
        <br>
        <br>
        <input class="button" type="submit" value="Save" name="submit">

    </form>
</div>
</body>
</html>