package itemlist;

import java.util.List;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public interface DAO<T> {
    public void create(T obj);
    public List<T> findAll();
    public void update(T obj);
    public void delete(int id);
}
