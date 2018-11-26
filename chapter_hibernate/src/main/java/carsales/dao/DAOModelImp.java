package carsales.dao;

import carsales.models.Brand;
import carsales.models.Model;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class DAOModelImp implements DAO<Model> {

    private final static DAOModelImp INSTANCE = new DAOModelImp();

    private DAOModelImp() { }

    public static DAOModelImp getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(Model obj) {
        wrapperMethod((Consumer<Session>) session -> session.save(obj));
    }

    @Override
    public void edit(Model obj) {
        wrapperMethod((Consumer<Session>) session -> session.update(obj));
    }

    @Override
    public void delete(Model obj) {
        wrapperMethod((Consumer<Session>) session -> session.remove(obj));
    }

    @Override
    @SuppressWarnings("unchecked")
    public Model findById(int id) {
        return wrapperMethod(session -> {
            Query<Model> query = session.createQuery("from Model where id = :id");
            query.setParameter("id", id);
            return query.getResultList().stream().findFirst().orElse(null);
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Model> findAll() {
        return wrapperMethod((Function<Session, List<Model>>) session -> session.createQuery("from Model").list());
    }

    @SuppressWarnings("unchecked")
    public List<Model> findByBrand(Brand brand) {
         return wrapperMethod(session -> {
            Query<Model> query = session.createQuery("from Model where brand = :brand");
            query.setParameter("brand", brand);
            return query.list();
        });
    }
}
