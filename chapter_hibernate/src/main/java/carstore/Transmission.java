package carstore;

/**
 * @author Melehskin Nikolay (sol.of.f@mail.ru)
 * @version 0.1
 */
public class Transmission {
    private int id;
    private String name;

    public Transmission() { }

    public Transmission(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
