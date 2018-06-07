package ru.job4j.tracker;

/**
 * Класс, описывающий заявку
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 */
public class Item {
    private String id;
    private String name;
    private String description;
    private long created;
    private String[] comments;

    Item(String id, String name, String desc, long created) {
        this.id = id;
        this.name = name;
        this.description = desc;
        this.created = created;
    }

    Item(String name, String desc, long created) {
        this.name = name;
        this.description = desc;
        this.created = created;
    }

    Item(String name, String desc) {
        this.name = name;
        this.description = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }
}
