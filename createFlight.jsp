<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>

<center>
    <h1>Flights</h1>
</center>
<div style="margin-left:800px;">
<form method="post" action="AdminServlet?action=createFlight">

    <div>
        <br>
        Airplane Type <br><input type="text" name="airplaneType">
        <br><br>
        Departure City <br><input type="text" name="departureCity">
        <br><br>
        Arrival City <br><input type="text" name="arrivalCity">
        <br><br>
        Departure Date <br><input type="text" name="departureDate">
        <br><br>
        Arrival Date <br><input type="text" name="arrivalDate">
    </div>
    <input class="button" type="submit" value="Add Flight"></button>
</form>
</div>
</body>
</html>