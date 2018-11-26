package carsales.dao;

import carsales.models.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class DAOUserImp implements DAO<User> {

    private final static DAOUserImp INSTANCE = new DAOUserImp();

    private DAOUserImp() { }

    public static DAOUserImp getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(User obj) {
        wrapperMethod((Consumer<Session>) session -> session.save(obj));
    }

    @Override
    public void edit(User obj) {
        wrapperMethod((Consumer<Session>) session -> session.update(obj));
    }

    @Override
    public void delete(User obj) {
        wrapperMethod((Consumer<Session>) session -> session.remove(obj));
    }

    @Override
    @SuppressWarnings("unchecked")
    public User findById(int id) {
        return wrapperMethod(session -> {
            Query<User> query = session.createQuery("from User where id = :id");
            query.setParameter("id", id);
            return query.getResultList().stream().findFirst().orElse(null);
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return wrapperMethod((Function<Session, List<User>>) session -> session.createQuery("from User").list());
    }

    @SuppressWarnings("unchecked")
    public User findByName(String name) {
        return wrapperMethod(session -> {
            List<User> users = session.createQuery("from User where name = :name").setParameter("name", name).getResultList();
            return users.stream().findFirst().orElse(null);
        });

    }

    public boolean isCredentional(String name, String pass) {
        User user = findByName(name);
        return  (user != null && user.getPass().trim().equals(pass));
    }
}
