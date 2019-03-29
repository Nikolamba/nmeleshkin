package carsales.dao;

import carsales.models.BodyType;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.junit.Assert.*;

public class DAOBodyTypeImpTest {

    private final DAOBodyTypeImp daoBodyType = DAOBodyTypeImp.getInstance();
    private BodyType type1 = new BodyType("type_1");
    private BodyType type2 = new BodyType("type_2");

    @Test
    @SuppressWarnings("unchecked")
    public void testAddBodyType() {
        daoBodyType.add(type1);
        List<BodyType> types = daoBodyType.wrapperMethod((Function<Session, List<BodyType>>) session -> session.createQuery("from BodyType").list());
        assertEquals(1, types.size());
        assertTrue(types.contains(type1));
        //daoBodyType.wrapperMethod((Consumer<Session>) session -> session.createQuery("delete from BodyType ").executeUpdate());
    }

    @Test
    public void testEditBodyType() {
        daoBodyType.add(type1);
        type1.setName("type_11");
        daoBodyType.edit(type1);
        BodyType _bodyType = daoBodyType.wrapperMethod((Function<Session, BodyType>) session -> session.get(BodyType.class, type1.getId()));
        assertEquals(type1, _bodyType);
        //daoBodyType.wrapperMethod((Consumer<Session>) session -> session.createQuery("delete from BodyType ").executeUpdate());
    }

    @Test
    public void testDeleteBodyType() {
        daoBodyType.add(type1);
        daoBodyType.delete(type1);
        BodyType _bodyType = daoBodyType.wrapperMethod((Function<Session, BodyType>) session -> session.get(BodyType.class, type1.getId()));
        assertNull(_bodyType);
    }

    @Test
    public void testFindById() {
        daoBodyType.add(type1);
        daoBodyType.add(type2);
        assertEquals(type2, daoBodyType.findById(type2.getId()));
        //daoBodyType.wrapperMethod((Consumer<Session>) session -> session.createQuery("delete from BodyType ").executeUpdate());
    }

    @Test
    public void testFindByName() {
        daoBodyType.add(type1);
        daoBodyType.add(type2);
        assertTrue(daoBodyType.findByName("type_2"));
        assertFalse(daoBodyType.findByName("type_3"));
        //daoBodyType.wrapperMethod((Consumer<Session>) session -> session.createQuery("delete from BodyType ").executeUpdate());
    }

    @Test
    public void testFindAll() {
        daoBodyType.add(type1);
        daoBodyType.add(type2);
        List<BodyType> types = daoBodyType.findAll();
        assertTrue(types.contains(type1));
        assertTrue(types.contains(type2));
    }
}