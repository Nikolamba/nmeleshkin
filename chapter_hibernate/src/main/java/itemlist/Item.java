package itemlist;

import java.sql.Timestamp;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class Item implements Comparable<Item> {
    private int id;
    private String desc;
    private Timestamp created;
    private boolean done;

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public Timestamp getCreated() {
        return created;
    }

    public boolean isDone() {
        return done;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public int compareTo(Item i) {
        if (i != null) {
            return desc.compareTo(i.desc);
        } else {
            throw new NullPointerException("null object to compare");
        }
    }
}
