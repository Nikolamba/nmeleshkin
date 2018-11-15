package carstore;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Melehskin Nikolay (sol.of.f@mail.ru)
 * @version 0.1
 */
public class CarTest {

    private final SessionFactory sessionFactory =
            new Configuration().configure("/hibernate_car.cfg.xml").buildSessionFactory();
    private Carbody carbody = new Carbody(1, "sedan", "red");
    private Engine engine = new Engine(1, "good engine", 70);
    private Transmission transmission = new Transmission(1, "four speed transmission");
    private Car car = new Car("car_1", engine, transmission, carbody);

    @Test
    public void whenCreateCar() {
        this.addCarToDatabase();

        this.wrapperConsumer(session -> {
            Car getCar = session.get(Car.class, car.getId());
            assertThat(getCar.getName(), is("car_1"));
            assertThat(getCar.getCarbody().getColor(), is("red"));
            assertThat(getCar.getEngine().getPower(), is(70));
            assertThat(getCar.getTransmission().getName(), is("four speed transmission"));
        });
    }

    @Test
    public void whenEditCar() {
        this.addCarToDatabase();

        this.wrapperConsumer(session -> {
            Carbody newCarbody = new Carbody(2, "universal", "black");
            car.setCarbody(newCarbody);
            session.saveOrUpdate(newCarbody);
            session.update(car);
        });

        this.wrapperConsumer(session -> {
            Car getCar = session.get(Car.class, car.getId());
            assertThat(getCar.getCarbody().getName(), is("universal"));
            assertThat(getCar.getCarbody().getColor(), is("black"));
        });
    }

    @Test
    public void whenDeleteCar() {
        addCarToDatabase();

        this.wrapperConsumer(session -> {
            session.delete(car);
        });

        this.wrapperConsumer(session -> {
            Car getCar = session.get(Car.class, car.getId());
            assertNull(getCar);
        });
    }

    private void wrapperConsumer(Consumer<Session> command) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            command.accept(session);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    private void addCarToDatabase() {
        this.wrapperConsumer(session -> {
            session.saveOrUpdate(carbody);
            session.saveOrUpdate(engine);
            session.saveOrUpdate(transmission);
            session.saveOrUpdate(car);
        });
    }
}