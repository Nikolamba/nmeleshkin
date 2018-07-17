package ru.job4j.nonblocking;

import java.util.Objects;

public class Model {
    private final int id;
    private String name;
    private volatile int version;

    public Model(int id, String name) {
        this.id = id;
        this.name = name;
        this.version = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Model model = (Model) o;
        return id == model.id && version == model.version
                && Objects.equals(name, model.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, version);
    }
}
