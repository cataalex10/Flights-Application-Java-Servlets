package dataacces;

import models.CityEntity;
import models.FlightEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Query;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;



public class FlightDAO {
    private SessionFactory factory;
    public FlightDAO(SessionFactory sessionFactory){
        this.factory = sessionFactory;
    }

    public void createFlight(FlightEntity flight){
        Session session=factory.openSession();
        Transaction t = null;
        try{
            t=session.beginTransaction();
            session.save(flight);
            t.commit();
        }catch (HibernateException ex){
            if(t != null){
                t.rollback();
            }
        }finally {
            session.save(flight);
            session.close();
        }

    }

    public List<FlightEntity> findFlights(){
        Session session = factory.openSession();
        Transaction t = null;
        List<FlightEntity> flights = null;
        try{
            t=session.beginTransaction();
            flights = session.createQuery("FROM FlightEntity ").list();
            t.commit();
        }catch (HibernateException ex){
            if(t!=null){
                t.rollback();
            }
        }finally {
            session.close();
        }
        return flights;
    }

    public FlightEntity findFlight(int flightNo){
        Session session = factory.openSession();
        Transaction t = null;
        List<FlightEntity> flights = null;
        try{
            t = session.beginTransaction();
            Query query = session.createQuery("FROM FlightEntity WHERE flightNo=:flightNo");
            query.setParameter("flightNo",flightNo);
            flights = query.list();
            t.commit();
        }catch (HibernateException ex){
            if(t!=null){
                t.rollback();
            }
        }finally {
            session.close();
        }
        return flights != null && !flights.isEmpty() ? flights.get(0) : null;

    }

    public void updateFlight(int flightNo,String airplaneType,String departureTime,String arrivalTime){
        Session session = factory.openSession();
        Transaction t = null;
        try{
            t = session.beginTransaction();
            Query query = session.createQuery("UPDATE FlightEntity set airplaneType = :airplaneType, departureTime = :departureTime," +
                    "arrivalTime = :arrivalTime WHERE flightNo = :flightNo");
            query.setParameter("airplaneType",airplaneType);
            query.setParameter("flightNo",flightNo);
            Timestamp td = Timestamp.valueOf(departureTime);
            Timestamp ta = Timestamp.valueOf(arrivalTime);
            query.setParameter("departureTime",departureTime);
            query.setParameter("arrivalTime",arrivalTime);
            t.commit();
        }catch (HibernateException ex){
            if(t!=null){
                t.rollback();
            }
        }finally {
            session.close();
        }
    }
    public void deleteFlight(int flightNo){
        Session session  = factory.openSession();
        Transaction t = null;
        FlightEntity flightEntity = null;
        try{
            t =session.beginTransaction();
           flightEntity = findFlight(flightNo);
           session.delete(flightEntity);
            t.commit();
        }catch (HibernateException ex){
            if(t!=null){
                t.rollback();;
            }
        }finally {
            session.close();
        }

    }



    public List<FlightEntity> findFlightsOfUser(int userId){
        Session session = factory.openSession();
        Transaction t = null;
        List<FlightEntity> flights = null;
        try{
            t = session.beginTransaction();
            flights= session.createQuery("FROM FlightEntity WHERE usersByIdUser=:userId").setParameter("userId",userId).list();
            t.commit();
        }catch (HibernateException ex){
            if(t!=null){
                t.rollback();
            }
        }finally {
            session.close();
        }
        return  flights;
    }
}
