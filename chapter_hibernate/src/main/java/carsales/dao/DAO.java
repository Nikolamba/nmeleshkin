package carsales.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public interface DAO<T> {
    SessionFactory SESSION_FACTORY = new Configuration().configure("/hibernate_carsales.cfg.xml").buildSessionFactory();

    void add(T obj);
    void edit(T obj);
    void delete(T obj);
    T findById(int id);
    List<T> findAll();

    default  <V> V wrapperMethod(Function<Session, V> command) {
        Transaction transaction = null;
        V result = null;
        try (Session session = SESSION_FACTORY.openSession()) {
            transaction = session.beginTransaction();
            result = command.apply(session);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return result;
    }

    default void wrapperMethod(Consumer<Session> command) {
        wrapperMethod(session -> {
            command.accept(session);
            return null;
        });
    }
}
