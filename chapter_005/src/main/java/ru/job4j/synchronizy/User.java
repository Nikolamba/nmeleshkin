package ru.job4j.synchronizy;

import net.jcip.annotations.GuardedBy;

import java.util.Objects;


public class User {
    private final int id;
    private int mount;

    User(int id, int mount) {
        this.id = id;
        this.mount = mount;
    }

    public int getId() {
        return id;
    }

    public int getMount() {
        return mount;
    }

    public void setMount(int mount) {
        this.mount = mount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
