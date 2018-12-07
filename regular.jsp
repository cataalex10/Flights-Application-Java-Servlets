
<!DOCTYPE html>
<%@ page import="models.FlightEntity" %>
<%@ page import="java.util.List" %>
<head>
    <meta charset="ISO-8859-1">
    <title>AIRPORT FLIGHTS</title>
    <link rel="stylesheet" href="/resources/resources/css/font-awesome.min.css">
    <!-- bootstrap.min -->
    <link rel="stylesheet" href="/resources/resources/css/jquery.fancybox.css">
    <!-- bootstrap.min -->
    <link rel="stylesheet" href="/resources/resources/css/bootstrap.min.css">
    <!-- bootstrap.min -->
    <link rel="stylesheet" href="/resources/resources/css/owl.carousel.css">
    <!-- bootstrap.min -->
    <link rel="stylesheet" href="/resources/resources/css/slit-slider.css">
    <!-- bootstrap.min -->
    <link rel="stylesheet" href="/resources/resources/css/animate.css">
    <!-- Main Stylesheet -->
    <link rel="stylesheet" href="/resources/resources/css/main.css">

</head>
<body>
<h2 class="text-center">Flights</h2>
<h4 class="text-center">Welcome <%= session.getAttribute("regular") %>!!</h4>
<div>
    <form  method="get" action="ClientServlet">
        <button name="action" value="localTimeZone">Local TimeZone</button>
    </form>
        <div class="table-responsive">
            <table  class="table table-borderless">
                <thead class="tableHead">
                <tr>
                    <th>Flight No.</th>
                    <th>Airplane Type</th>
                    <th>Departure City</th>
                    <th>Departure Date</th>
                    <th>Arrival City</th>
                    <th>Arrival Date</th>
                </tr>
                </thead>
                <tbody>
                    <%
                     List<FlightEntity> flightEntityList = (List<FlightEntity>) session.getAttribute("flights");
                     for (FlightEntity flightEntity : flightEntityList) {
                 %>
                <tr>
                    <td><%=flightEntity.getFlightNo()%>
                    </td>
                    <td><%=flightEntity.getAirplaneType()%>
                    </td>
                    <td><%=flightEntity.getCityByDepartureCity().getName()%>
                    </td>
                    <td><%=flightEntity.getDepartureTime()%>
                    </td>
                    <td><%=flightEntity.getCityByArrivalCity().getName()%>
                    </td>
                    <td><%=flightEntity.getArrivalTime()%>
                    </td>
                </tr>
                    <%}%>
                <tbody>
            </table>
        </div>

    </div>
<script src="/resources/resources/js/jquery-1.11.1.min.js"></script>
<!-- Twitter Bootstrap -->
<script src="/resources/resources/js/bootstrap.min.js"></script>
<!-- Single Page Nav -->
<script src="/resources/resources/js/jquery.singlePageNav.min.js"></script>
<!-- jquery.fancybox.pack -->
<script src="/resources/resources/js/jquery.fancybox.pack.js"></script>
<!-- Google Map API -->
<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
<!-- Owl Carousel -->
<script src="/resources/resources/js/owl.carousel.min.js"></script>
<!-- jquery easing -->
<script src="/resources/resources/js/jquery.easing.min.js"></script>
<!-- Fullscreen slider -->
<script src="/resources/resources/js/jquery.slitslider.js"></script>
<script src="/resources/resources/js/jquery.ba-cond.min.js"></script>
<!-- onscroll animation -->
<script src="/resources/resources/js/wow.min.js"></script>
<!-- Custom Functions -->
<script src="/resources/resources/js/main.js"></script>
    </body>
    </html>
</body>
</html>