package dataacces;

import models.UsersEntity;
import org.hibernate.*;

import java.util.List;

public class UserDAO {
        private SessionFactory factory;
    public UserDAO(SessionFactory sessionFactory){
        this.factory = sessionFactory;
    }
        public UsersEntity createUser(String username, String password)
        {
            Session session = factory.openSession();
            Transaction t = null;
            UsersEntity user = new UsersEntity();
            try{
                t=session.beginTransaction();
                user.setUsername(username);
                user.setPassword(password);
                user.setType("Regular");
                t.commit();

            }catch (HibernateException ex){
                if(t!=null){
                    t.rollback();
                }
            }finally {
                session.save(user);
                session.close();
            }
            return user;
        }

        public List<UsersEntity> findAll(){
            Session session = factory.openSession();
            Transaction t = null;
            List<UsersEntity> users = null;
            try{
                t = session.beginTransaction();
                users = session.createQuery("FROM UsersEntity ").list();
                t.commit();
            }catch (HibernateException ex){
                if(t!=null){
                    t.rollback();
                }
            }finally {
                session.close();
            }
            return users;
        }

        public UsersEntity findUserIfExist(String username,String password){
            Session session = factory.openSession();
            Transaction t = null;
            List<UsersEntity> users = null;
            try{
                t=session.beginTransaction();
                Query query = session.createQuery("FROM UsersEntity u WHERE u.username=:username AND u.password=:password");
                query.setParameter("username",username);
                query.setParameter("password",password);
                users = query.list();
                t.commit();
            }catch (HibernateException ex){
                if(t!=null){
                    t.rollback();

                }
            }finally {
                session.close();
            }
            return users != null && !users.isEmpty() ? users.get(0) : null;

        }

        public int deleteUser(int id){
            int result =0;
            Session session = factory.openSession();
            Transaction t = null;
            try{
                t=session.beginTransaction();
                Query query = session.createQuery("DELETE FROM UsersEntity WHERE id=:id");
                query.setParameter("id",id);
                result=query.executeUpdate();
                t.commit();
            }catch (HibernateException ex){
                if(t!=null){
                    result=0;
                    t.rollback();
                }
            }finally {
                session.close();
            }
            return result;
        }
}
