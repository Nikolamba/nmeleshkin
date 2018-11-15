package carstore;

/**
 * @author Melehskin Nikolay (sol.of.f@mail.ru)
 * @version 0.1
 */
public class Engine {
    private int id;
    private String name;
    private int power;

    public Engine() { }

    public Engine(int id, String name, int power) {
        this.id = id;
        this.name = name;
        this.power = power;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
