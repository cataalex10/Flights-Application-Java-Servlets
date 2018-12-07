package dataacces;
import models.CityEntity;
import org.hibernate.*;

import java.util.List;

public class CityDAO {

    private SessionFactory factory;
    public CityDAO(SessionFactory sessionFactory){
        this.factory = sessionFactory;
    }
    public CityEntity createCity(String name,double latitude,double longitude){
        Session session = factory.openSession();
        Transaction t = null;
        CityEntity cityEntity = new CityEntity();
        try{
            t = session.beginTransaction();
            cityEntity.setName(name);
            cityEntity.setLatitude(latitude);
            cityEntity.setLongitude(longitude);
            t.commit();
        }catch (HibernateException ex){
            if(t != null){
                t.rollback();
            }
        }finally {
            session.save(cityEntity);
            session.close();
        }
        return cityEntity;
    }

    public List<CityEntity> findAll(){
        Session session = factory.openSession();
        Transaction t = null;
        List<CityEntity> cities = null;
        try{
            t = session.beginTransaction();
            cities = session.createQuery("FROM CityEntity ").list();
            t.commit();

        }catch(HibernateException ex){
            if(t != null){
                t.rollback();
            }
        }finally {
            session.close();
        }
        return cities;
    }

    public CityEntity findCity(String name){
        Session session = factory.openSession();
        Transaction t = null;
        List<CityEntity> cities = null;
        try{
            t=session.beginTransaction();
            Query  query = session.createQuery("FROM CityEntity WHERE name = :name");
            query.setParameter("name",name);
            cities=query.list();
            t.commit();
        }catch(HibernateException ex ){
            if(t!=null){
                t.rollback();
            }
        }finally {
            session.close();
        }
        return cities != null && !cities.isEmpty() ? cities.get(0) : null;
    }

    public int deleteCity(String name){
        int result = 0;
        Session session = factory.openSession();
        Transaction t = null;
        try{
            t= session.beginTransaction();
            Query query = session.createQuery("DELETE FROM CityEntity WHERE name=:name");
            result = query.executeUpdate();
            t.commit();
        }catch (HibernateException ex){
            if(t !=null){
                t.rollback();
            }
        }finally {
            session.close();
        }
        return result;
    }
}
