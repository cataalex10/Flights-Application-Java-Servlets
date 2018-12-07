package controllers;

import dataacces.CityDAO;
import dataacces.FlightDAO;
import dataacces.UserDAO;
import models.CityEntity;
import models.FlightEntity;
import utils.HibernateFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    private static final long  serialVersionUTD = 1L;
    private FlightDAO flightDAO;
    private UserDAO userDAO;
    private CityDAO cityDAO;
    @Override
    public void init() throws ServletException {
        flightDAO = new FlightDAO(HibernateFactory.createSessionFactory());
        cityDAO = new CityDAO(HibernateFactory.createSessionFactory());
        userDAO = new UserDAO(HibernateFactory.createSessionFactory());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action.equals("createFlight")){
             FlightEntity flight = new FlightEntity();
            String airplaneType = request.getParameter("airplaneType");
            String departureCityN = request.getParameter("departureCity");
            String arrivalCityN = request.getParameter("arrivalCity");
            String departureDate = request.getParameter("departureDate");
            String arrivalDate = request.getParameter("arrivalDate");
            flight.setAirplaneType(airplaneType);
            flight.setCityByDepartureCity(cityDAO.findCity(departureCityN));
            flight.setCityByArrivalCity(cityDAO.findCity(arrivalCityN));
            Timestamp timeD = Timestamp.valueOf(departureDate);
            Timestamp timeA = Timestamp.valueOf(arrivalDate);
            flight.setDepartureTime(timeD);
            flight.setArrivalTime(timeA);
            flightDAO.createFlight(flight);
            request.getSession().setAttribute("flights",flightDAO.findFlights());
            request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
        }
        else if(action.equals("updateFlight")){
            String flightNo = request.getParameter("flightNo");
            String airplaneType = request.getParameter("airplaneType");
            String departureTime = request.getParameter("departureTime");
            String arrivalTime = request.getParameter("arrivalTime");

            flightDAO.updateFlight(Integer.parseInt(flightNo),airplaneType,departureTime,arrivalTime);
            request.getSession().setAttribute("flights",flightDAO.findFlights());
            request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);

        }
        else if(action.equals("deleteFlight")){
            String flightNo = request.getParameter("flightNo");
            flightDAO.deleteFlight(Integer.parseInt(flightNo));
            request.getSession().setAttribute("flights",flightDAO.findFlights());
            request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            List<FlightEntity> flights = flightDAO.findFlights();
            request.getSession().setAttribute("flights",flights);
        request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);





    }
}
