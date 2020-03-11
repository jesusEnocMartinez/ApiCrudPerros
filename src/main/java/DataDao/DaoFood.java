package DataDao;

import Data.HibernateUtil;
import DataDto.Food;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;


public class DaoFood implements IDao<Food, Integer>
{
    private final SessionFactory factory;
    private Session session;

    public DaoFood()
    {
        factory = HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean insert(Food entity)
    {
        try
        {
            session = factory.openSession();
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
            
            return true;
        }
        catch (HibernateException e)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
            
            return false;
        }
        finally
        {
            session.close();
        }
    }

    @Override
    public boolean update(Food entity)
    {
        try
        {
            session = factory.openSession();
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();

            return true;
        }
        catch (HibernateException e)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);

            return false;
        }
        finally
        {
            session.close();
        }
    }

    @Override
    public boolean delete(Integer id)
    {
        try
        {
            session = factory.openSession();

            session.beginTransaction();
            session.delete(new Food(id));
            session.getTransaction().commit();

            return true;
        }
        catch (HibernateException e)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);

            return false;
        }
        finally
        {
            session.close();
        }
    }

    @Override
    public Food find(Integer id)
    {
        try
        {
            session = factory.openSession();

            return session.find(Food.class, id);
        }
        catch (HibernateException e)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);

            return null;
        }
        finally
        {
            session.close();
        }
    }

    @Override
    public List<Food> findAll()
    {       
        try
        {
            session = factory.openSession();

            return session.createQuery("SELECT f FROM Food f").list();
        }
        catch (HibernateException e)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);

            return new ArrayList<>();
        }
        finally
        {
            session.close();
        }
    }
}
