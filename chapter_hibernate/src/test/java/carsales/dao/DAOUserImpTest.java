package carsales.dao;

import carsales.models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.junit.Assert.*;

public class DAOUserImpTest {

    private final DAOUserImp daoUser = DAOUserImp.getInstance();
    private User user = new User("Nikolay", "123");
    private User user1 = new User("Igor", "321");

    @Test
    @SuppressWarnings("unchecked")
    public void testAddUser() {
        daoUser.add(user);
        User _user = daoUser.wrapperMethod((Function<Session, User>)  session -> session.get(User.class, user.getId()));
        assertEquals(user, _user);
        daoUser.wrapperMethod((Consumer<Session>) session -> session.createQuery("delete from User").executeUpdate());
    }

    @Test
    public void testEditUser() {
        daoUser.add(user);
        user.setName("Oleg");
        daoUser.edit(user);
        User _user = daoUser.wrapperMethod((Function<Session, User>) session -> session.get(User.class, user.getId()));
        assertEquals("Oleg", _user.getName());
        daoUser.wrapperMethod((Consumer<Session>) session -> session.createQuery("delete from User").executeUpdate());
    }

    @Test
    public void testDeleteUser() {
        daoUser.add(user);
        daoUser.delete(user);
        User _user = daoUser.wrapperMethod((Function<Session, User>) session -> session.get(User.class, user.getId()));
        assertNull(_user);
    }

    @Test
    public void testFindById() {
        daoUser.add(user);
        daoUser.add(user1);
        assertEquals(user, daoUser.findById(user.getId()));
        assertEquals(user1, daoUser.findById(user1.getId()));
        daoUser.wrapperMethod((Consumer<Session>) session -> session.createQuery("delete from User").executeUpdate());
    }

    @Test
    public void testFindAll() {
        daoUser.add(user);
        daoUser.add(user1);
        List<User> list = daoUser.findAll();
        assertEquals(list.size(), 2);
        assertTrue(list.contains(user));
        assertTrue(list.contains(user1));
        daoUser.wrapperMethod((Consumer<Session>) session -> session.createQuery("delete from User").executeUpdate());
    }

    @Test
    public void testFindByName() {
        daoUser.add(user);
        daoUser.add(user1);
        assertEquals(user, daoUser.findByName("Nikolay"));
        assertEquals(user1, daoUser.findByName("Igor"));
        daoUser.wrapperMethod((Consumer<Session>) session -> session.createQuery("delete from User").executeUpdate());
    }

    @Test
    public void testIsCredentional() {
        daoUser.add(user);
        assertTrue(daoUser.isCredentional("Nikolay", "123"));
        assertFalse(daoUser.isCredentional("Nikolay", "122"));
        daoUser.wrapperMethod((Consumer<Session>) session -> session.createQuery("delete from User").executeUpdate());
    }

    @Test
    public void testWrapperMethod() {
        daoUser.add(user);
        User _user = daoUser.wrapperMethod((Function<Session, User>) session -> session.get(User.class, user.getId()));

        User _userExp = null;
        Transaction transaction = null;
        try (Session session = DAOUserImp.SESSION_FACTORY.openSession()) {
            transaction = session.beginTransaction();
            _userExp = session.get(User.class, user.getId());
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

        assertEquals(_userExp, _user);
    }
}