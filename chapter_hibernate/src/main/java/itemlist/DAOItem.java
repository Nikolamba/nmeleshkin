package itemlist;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class DAOItem implements DAO<Item> {

    private final SessionFactory sessionFactory;
    private final static DAOItem INSTANCE = new DAOItem();

    private DAOItem() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public void create(Item obj) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession())  {
            transaction = session.beginTransaction();
            session.save(obj);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = null;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession())  {
            transaction = session.beginTransaction();
            result = session.createQuery("from Item").list();
            this.trimField(result);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }

    public List<Item> findNotDone() {
        List<Item> result = null;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession())  {
            transaction = session.beginTransaction();
            result = session.createQuery("from Item where done = false").list();
            this.trimField(result);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void update(Item obj) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession())  {
            transaction = session.beginTransaction();
            session.update(obj);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Item item = new Item();
            item.setId(id);
            session.delete(item);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    private void trimField(List<Item> list) {
        for (Item item : list) {
            item.setDesc(item.getDesc().trim());
        }
    }

    public static DAOItem getINSTANCE() {
        return INSTANCE;
    }
}
