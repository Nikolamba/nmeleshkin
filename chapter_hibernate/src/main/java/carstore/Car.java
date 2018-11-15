package carstore;

/**
 * @author Melehskin Nikolay (sol.of.f@mail.ru)
 * @version 0.1
 */
public class Car {
    private int id;
    private String name;
    private Engine engine;
    private Transmission transmission;
    private Carbody carbody;

    public Car() { }

    public Car(String name, Engine engine, Transmission transmission, Carbody carbody) {
        this.name = name;
        this.engine = engine;
        this.transmission = transmission;
        this.carbody = carbody;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Engine getEngine() {
        return engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public Carbody getCarbody() {
        return carbody;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public void setCarbody(Carbody carbody) {
        this.carbody = carbody;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, Engine power: %s, %s, Carbody color: %s",
                this.id, this.name, this.engine.getPower(), this.transmission.getName(), this.carbody.getColor());
    }
}
