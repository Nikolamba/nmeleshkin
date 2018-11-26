package carsales.dao;

import carsales.models.Brand;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class DAOBrandImp implements DAO<Brand> {

    private final static String[] BRANDS_LIST = {"Audi", "BMW", "Volvo", "Mazda"};
    private final static DAOBrandImp INSTANCE = new DAOBrandImp();

    private DAOBrandImp() {
        for (String str : BRANDS_LIST) {
            if (this.findByName(str)) {
                Brand brand = new Brand();
                brand.setName(str);
                this.add(brand);
            }
        }
    }

    public static DAOBrandImp getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(Brand obj) {
        wrapperMethod((Consumer<Session>) session -> session.save(obj));
    }

    @Override
    public void edit(Brand obj) {
        wrapperMethod((Consumer<Session>) session -> session.update(obj));
    }

    @Override
    public void delete(Brand obj) {
        wrapperMethod((Consumer<Session>) session -> session.remove(obj));
    }

    @Override
    @SuppressWarnings("unchecked")
    public Brand findById(int id) {
        return wrapperMethod(session -> {
            Query<Brand> query = session.createQuery("from Brand where id = :id");
            query.setParameter("id", id);
            return query.getResultList().stream().findFirst().orElse(null);
        });
    }

    @SuppressWarnings("unchecked")
    public boolean findByName(String name) {
        return wrapperMethod(session -> {
            Query<Brand> query = session.createQuery("from Brand where name = :name");
            query.setParameter("name", name);
            return query.getResultList().isEmpty();
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Brand> findAll() {
        return wrapperMethod((Function<Session, List<Brand>>) session -> session.createQuery("from Brand").list());
    }
}
