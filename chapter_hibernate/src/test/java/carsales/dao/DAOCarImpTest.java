package carsales.dao;

import carsales.models.Car;
import carsales.models.User;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.junit.Assert.*;

public class DAOCarImpTest {

    private final DAOCarImp daoCar = DAOCarImp.getInstance();
    private final DAOUserImp daoUser = DAOUserImp.getInstance();
    private Car car1 = new Car();
    private Car car2 = new Car();

    @Test
    public void testAddCar() {
        car1.setColor("color");
        daoCar.add(car1);
        Car _car = daoCar.wrapperMethod((Function<Session, Car>)  session -> session.get(Car.class, car1.getId()));
        assertEquals("color", _car.getColor());
        daoCar.wrapperMethod((Consumer<Session>)  session -> session.createQuery("delete from Car").executeUpdate());
    }

    @Test
    public void testEditCar() {
        car1.setColor("color");
        daoCar.add(car1);
        car1.setColor("new color");
        daoCar.edit(car1);
        Car _car = daoCar.wrapperMethod((Function<Session, Car>)  session -> session.get(Car.class, car1.getId()));
        assertEquals("new color", _car.getColor());
        daoCar.wrapperMethod((Consumer<Session>)  session -> session.createQuery("delete from Car").executeUpdate());
    }

    @Test
    public void testDeleteCar() {
        daoCar.add(car1);
        daoCar.delete(car1);
        Car _car = daoCar.wrapperMethod((Function<Session, Car>)  session -> session.get(Car.class, car1.getId()));
        assertNull(_car);
    }

    @Test
    public void testFindCarById() {
        car1.setColor("color_1");
        car2.setColor("color_2");
        daoCar.add(car1);
        daoCar.add(car2);
        Car _car = daoCar.findById(car2.getId());
        assertEquals(car2, _car);
        daoCar.wrapperMethod((Consumer<Session>)  session -> session.createQuery("delete from Car").executeUpdate());
    }

    @Test
    public void testFindAllCars() {
        car1.setColor("color_1");
        car2.setColor("color_2");
        daoCar.add(car1);
        daoCar.add(car2);
        List<Car> cars = daoCar.findAll();
        assertEquals(2, cars.size());
        assertTrue(cars.contains(car1));
        assertTrue(cars.contains(car2));
        daoCar.wrapperMethod((Consumer<Session>)  session -> session.createQuery("delete from Car").executeUpdate());
    }

    @Test
    public void testFindCarByUser() {
        User user1 = new User("user_1", "pass_1");
        User user2 = new User("user_2", "pass_2");
        daoUser.add(user1);
        daoUser.add(user2);
        car1.setColor("color_1");
        car1.setSeller(user1);
        car2.setColor("color_2");
        car2.setSeller(user2);
        daoCar.add(car1);
        daoCar.add(car2);
        List<Car> _cars = daoCar.findByUser(user2);
        assertEquals(1, _cars.size());
        assertTrue(_cars.contains(car2));
        daoCar.wrapperMethod((Consumer<Session>)  session -> session.createQuery("delete from Car").executeUpdate());
    }

}