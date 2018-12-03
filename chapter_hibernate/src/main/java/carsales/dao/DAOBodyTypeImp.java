package carsales.dao;

import carsales.models.BodyType;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class DAOBodyTypeImp implements DAO<BodyType> {

    private final static String[] BODYTYPE_LIST = {"Sedan", "Universal", "Hatchback", "Coupe"};
    private final static DAOBodyTypeImp INSTANCE = new DAOBodyTypeImp();

    private DAOBodyTypeImp() {
        for (String str : BODYTYPE_LIST) {
            if (!this.findByName(str)) {
                BodyType bodyType = new BodyType();
                bodyType.setName(str);
                this.add(bodyType);
            }
        }
    }

    public static DAOBodyTypeImp getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(BodyType obj) {
        wrapperMethod((Consumer<Session>) session -> session.save(obj));
    }

    @Override
    public void edit(BodyType obj) {
        wrapperMethod((Consumer<Session>) session -> session.update(obj));
    }

    @Override
    public void delete(BodyType obj) {
        wrapperMethod((Consumer<Session>) session -> session.remove(obj));
    }

    @Override
    @SuppressWarnings("unchecked")
    public BodyType findById(int id) {
        return wrapperMethod(session -> {
            Query<BodyType> query = session.createQuery("from BodyType where id = :id");
            query.setParameter("id", id);
            return query.getResultList().stream().findFirst().orElse(null);
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<BodyType> findAll() {
        return wrapperMethod((Function<Session, List<BodyType>>) session -> session.createQuery("from BodyType").list());
    }

    @SuppressWarnings("unchecked")
    public boolean findByName(String name) {
        return wrapperMethod(session -> {
            Query<BodyType> query = session.createQuery("from BodyType where name = :name");
            query.setParameter("name", name);
            return !query.getResultList().isEmpty();
        });
    }
}
