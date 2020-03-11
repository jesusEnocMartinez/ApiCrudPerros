package DataDao;

import Data.HibernateUtil;
import DataDto.Food;
import DataDto.Perros;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;


public class DaoPerros implements IDao<Perros, Integer>
{
    private final SessionFactory factory;
    private Session session;

    public DaoPerros()
    {
        factory = HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean insert(Perros entity)
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
    public boolean update(Perros entity)
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
    public Perros find(Integer id)
    {
        try
        {
            session = factory.openSession();

            return session.find(Perros.class, id);
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
    public List<Perros> findAll()
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
