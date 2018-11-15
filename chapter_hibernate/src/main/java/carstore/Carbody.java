package carstore;

/**
 * @author Melehskin Nikolay (sol.of.f@mail.ru)
 * @version 0.1
 */
public class Carbody {
    private int id;
    private String name;
    private String color;

    public Carbody() { }

    public Carbody(int id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
