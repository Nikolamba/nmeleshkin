package ru.job4j.http;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class Role {
    private String name;
    private List<String> operations;

    public Role(String name) {
        this.name = name;
        this.operations = new ArrayList<>();
        if (name.equals("administrator")) {
            operations.add("createNewAccount");
            operations.add("changingOurAccount");
            operations.add("changingAnotherAccount");
            operations.add("changingOurRole");
            operations.add("changingAnotherRole");
        } else if (name.equals("user")) {
            operations.add("changingOurAccount");
        }
    }

    public String getName() {
        return name;
    }

    public List<String> getOperations() {
        return operations;
    }
}
