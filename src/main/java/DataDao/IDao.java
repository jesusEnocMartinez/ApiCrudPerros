package DataDao;

import java.util.List;


public interface IDao<T, V>
{
    public boolean insert(T entity);
    public boolean update(T entity);
    public boolean delete(V id);
    public T find(V id);
    public List<T> findAll();
}
