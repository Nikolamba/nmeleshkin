package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class StartUITest {

    private Tracker tracker = new Tracker();
    @Test
    public void whenUserAddItem() {
        Input input = new StubInput(new String[] {"0", "first item", "first desription", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("first item"));
    }

    @Test
    public void whenUserShowAllItems() {
        Input input = new StubInput(new String[] {"0", "first item", "first description",
                "0", "second item", "second description",
                "0", "third item", "third description", "6"});
        new StartUI(input, tracker).init();
        Item[] items = tracker.findAll();
        assertThat(items[0].getName(), is("first item"));
        assertThat(items[1].getName(), is("second item"));
        assertThat(items[2].getName(), is("third item"));
    }

    @Test
    public void whenUserEditItem() {
        Item item = new Item("first item", "first description");
        tracker.add(item);
        Input input = new StubInput(new String[] {"2", item.getId(), "second item", "second desription", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("second item"));
    }

    @Test
    public void whenUserDeleteItem() {
        Item item1 = new Item("first item", "first description");
        Item item2 = new Item("second item", "second description");
        Item item3 = new Item("third item", "third description");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        Input input = new StubInput(new String[]{"3", tracker.findAll()[1].getId(), "6"});
        new StartUI(input, tracker).init();
        Item[] items = tracker.findAll();
        assertThat(items[0].getName(), is("first item"));
        assertThat(items[1].getName(), is("third item"));
    }

    @Test
    public void whenUserFindItemId() {
        Item item1 = new Item("first item", "first description");
        Item item2 = new Item("second item", "second description");
        Item item3 = new Item("third item", "third description");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        Item item = tracker.findById(tracker.findAll()[1].getId());
        assertThat(item.getName(), is("second item"));
    }

    @Test
    public void whenUserFindName() {
        Item item1 = new Item("first item", "first description");
        Item item2 = new Item("second item", "second description");
        Item item3 = new Item("first item", "third description");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        Item[] items = tracker.findByName("first item");
        assertThat(items[0].getDescription(), is("first description"));
        assertThat(items[1].getDescription(), is("third description"));
    }
}