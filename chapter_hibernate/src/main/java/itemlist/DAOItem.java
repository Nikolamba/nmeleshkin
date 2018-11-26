package itemlist;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

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

    private <T> T wrapperMethod(Function<Session, T> command) {
        Transaction transaction = null;
        T result = null;
        try (Session session = sessionFactory.openSession())  {
            transaction = session.beginTransaction();
            result = command.apply(session);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }

    private void wrapperMethod(Consumer<Session> command) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession())  {
            transaction = session.beginTransaction();
            command.accept(session);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void create(Item obj) {
        this.wrapperMethod((Consumer<Session>) session -> session.save(obj));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Item> findAll() {
        List<Item> result = wrapperMethod((Function<Session, List<Item>>) session -> session.createQuery("from Item").list());
        trimField(result);
        return result;
    }

    @SuppressWarnings("unchecked")
    public List<Item> findNotDone() {
        List<Item> result = wrapperMethod((Function<Session, List<Item>>) session ->
                session.createQuery("from Item where done = false").list());
        trimField(result);
        return result;
    }

    @Override
    public void update(Item obj) {
        wrapperMethod((Consumer<Session>) session -> session.update(obj));
    }
    @Override
    public void delete(int id) {
        wrapperMethod(session -> {
            Item item = new Item();
            item.setId(id);
            session.delete(item);
        });
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
