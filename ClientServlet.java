package controllers;

import dataacces.CityDAO;
import dataacces.FlightDAO;
import models.FlightEntity;
import org.xml.sax.SAXException;
import utils.HibernateFactory;
import utils.TimeManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/ClientServlet")
public class ClientServlet extends HttpServlet {
    private FlightDAO flightDAO;
    @Override
    public void init() throws ServletException {
        flightDAO = new FlightDAO(HibernateFactory.createSessionFactory());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("localTimeZone")) {
            List<FlightEntity> flights = flightDAO.findFlights();
            PrintWriter out = response.getWriter();
            out.println("<table style=\"width:100%\" margin-left=150px margin-top=50px border=\"1\">");
            out.println("<tr><th>Fight number</th><th>Airplane type</th><th>Departure city</th><th>Departure date</th>"
                    + "<th>Departure local time</th>"
                    + "<th>Arrival city</th>"
                    + "<th>Arrival date</th>"
                    + "<th>Arrival local time</th>"
                    + "</tr>");

            for (FlightEntity flight : flights) {
                String tableContent  = "";
                String departureLatitude = flight.getCityByDepartureCity().getLatitude() +"";
                String departureLongitude = flight.getCityByDepartureCity().getLongitude() +"";
                String departureTime = null;
                try {
                    departureTime = TimeManager.getTimeZone(departureLatitude, departureLongitude);
                } catch (ParserConfigurationException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SAXException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                String arrivalLatitude = flight.getCityByDepartureCity().getLatitude() +"";
                String arrivalLongitude = flight.getCityByDepartureCity().getLongitude() +"";
                String arrivalTime = null;
                try {
                    arrivalTime = TimeManager.getTimeZone(arrivalLatitude, arrivalLongitude);
                } catch (ParserConfigurationException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SAXException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                tableContent = tableContent + "<tr><td align=\"center\">" + flight.getFlightNo() + "</td>";
                tableContent = tableContent + "<td align=\"center\">" + flight.getAirplaneType() + "</td>";
                tableContent = tableContent + "<td align=\"center\">" + flight.getCityByDepartureCity().getName() + "</td>";
                tableContent = tableContent + "<td align=\"center\">" + flight.getDepartureTime() + "</td>";
                tableContent = tableContent + "<td align=\"center\">" + departureTime + "</td>";
                tableContent = tableContent + "<td align=\"center\">" + flight.getCityByArrivalCity().getName() + "</td>";
                tableContent = tableContent + "<td align=\"center\">" + flight.getArrivalTime() + "</td>";
                tableContent = tableContent + "<td align=\"center\">" + arrivalTime + "</td></tr>";
                out.println(tableContent);
            }

            out.println("</table>");
        }

        }
    }

