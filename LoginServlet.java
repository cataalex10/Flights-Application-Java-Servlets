package controllers;

import dataacces.CityDAO;
import dataacces.FlightDAO;
import dataacces.UserDAO;
import models.FlightEntity;
import models.UsersEntity;
import utils.HibernateFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
    private FlightDAO flightDAO;
    private CityDAO cityDAO;
    public static int idUser;
    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO(HibernateFactory.createSessionFactory());
        flightDAO = new FlightDAO(HibernateFactory.createSessionFactory());
        cityDAO = new CityDAO(HibernateFactory.createSessionFactory());

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsersEntity usersEntity = userDAO.findUserIfExist(username,password);
        if(usersEntity != null){
            String userType = usersEntity.getType();
            if(userType.equals("admin")){
                HttpSession session = request.getSession();
                session.setAttribute("admin",usersEntity.getUsername());
                session.setAttribute("role",usersEntity.getType());
                session.setMaxInactiveInterval(120);
                Cookie userName = new Cookie("Admin",usersEntity.getUsername());
                userName.setMaxAge(120);
                response.addCookie(userName);
                List<FlightEntity> flights = flightDAO.findFlights();
                request.getSession().setAttribute("flights",flights);
                response.sendRedirect("adminDashboard.jsp");
            } else if( userType.equals("regular")){
                HttpSession session = request.getSession();
                idUser=usersEntity.getId();
                session.setAttribute("regular",usersEntity.getUsername());
                session.setAttribute("role",usersEntity.getType());
                session.setMaxInactiveInterval(120);
                Cookie userName = new Cookie("Regular",usersEntity.getUsername());
                userName.setMaxAge(120);
                response.addCookie(userName);
                List<FlightEntity> flights = flightDAO.findFlights();
                request.getSession().setAttribute("flights",flights);
                response.sendRedirect("regular.jsp");
            }

        }else{
            request.setAttribute("message", "Account's Invalid");
            request.getRequestDispatcher("login.jsp").forward(request, response);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
