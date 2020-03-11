package DataDao;

import Data.HibernateUtil;
import DataDto.Store;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class DaoStore implements IDao<Store, Integer> {
    private final SessionFactory factory;
    private Session session;

    public DaoStore() {
        factory = HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean insert(Store entity) {
        try {
            session = factory.openSession();
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();

            return true;
        }
        catch (HibernateException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);

            return false;
        }
        finally {
            session.close();
        }
    }

    @Override
    public boolean update(Store entity) {
        try {
            session = factory.openSession();
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();

            return true;
        }
        catch (HibernateException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);

            return false;
        }
        finally {
            session.close();
        }
    }

    @Override
    public boolean delete(Integer id) {
        try {
            session = factory.openSession();

            session.beginTransaction();
            session.delete(new Store(id));
            session.getTransaction().commit();

            return true;
        }
        catch (HibernateException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);

            return false;
        }
        finally {
            session.close();
        }
    }

    @Override
    public Store find(Integer id) {
        try {
            session = factory.openSession();

            return session.find(Store.class, id);
        }
        catch (HibernateException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);

            return null;
        }
        finally {
            session.close();
        }
    }

    @Override
    public List<Store> findAll() {
        try {
            session = factory.openSession();

            return session.createQuery("SELECT s FROM Store s").list();
        }
        catch (HibernateException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);

            return new ArrayList<>();
        }
        finally {
            session.close();
        }
    }
}
