package carsales.dao;

import carsales.models.Car;
import carsales.models.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class DAOCarImp implements DAO<Car> {

    private final static DAOCarImp INSTANCE = new DAOCarImp();

    private DAOCarImp() { }

    public static DAOCarImp getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(Car car) {
        this.wrapperMethod((Consumer<Session>) session -> session.save(car));
    }

    @Override
    public void edit(Car car) {
        this.wrapperMethod((Consumer<Session>) session -> session.update(car));
    }

    @Override
    public void delete(Car car) {
        this.wrapperMethod((Consumer<Session>) session -> session.remove(car));
    }

    @Override
    @SuppressWarnings("unchecked")
    public Car findById(int id) {
        return this.wrapperMethod(session -> {
            Query<Car> query = session.createQuery("from Car where id = :id");
            query.setParameter("id", id);
            return query.getResultList().stream().findFirst().orElse(null);
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> findAll() {
        return this.wrapperMethod((Function<Session, List<Car>>) session -> session.createQuery("from Car").list());
    }

    @SuppressWarnings("unchecked")
    public List<Car> findByUser(User user) {
        return wrapperMethod((Function<Session, List<Car>>) session ->
                session.createQuery("from Car where seller = :user").setParameter("user", user).list());
    }

    @SuppressWarnings("unchecked")
    public List<Car> findSold() {
        return this.wrapperMethod((Function<Session, List<Car>>) session -> session.createQuery("from Car where status = true").list());
    }

    @SuppressWarnings("unchecked")
    public List<Car> findNotSold() {
        return this.wrapperMethod((Function<Session, List<Car>>) session -> session.createQuery("from Car where status = false").list());
    }
}
