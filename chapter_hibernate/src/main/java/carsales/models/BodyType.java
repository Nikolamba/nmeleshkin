package carsales.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "body_type")
public class BodyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    public BodyType() {
    }

    public BodyType(String name) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BodyType bodyType = (BodyType) o;
        return id == bodyType.id
                && Objects.equals(name, bodyType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
