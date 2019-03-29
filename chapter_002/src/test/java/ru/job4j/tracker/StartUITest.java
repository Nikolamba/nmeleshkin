//package ru.job4j.tracker;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//import java.util.List;
//
//import static org.hamcrest.core.Is.is;
//import static org.hamcrest.core.StringContains.containsString;
//import static org.junit.Assert.*;
//
///**
// * @author Nikolay Meleshkin (sol.of.f@mail.ru)
// * @version 0.1
// */
//public class StartUITest {
//    private final static PrintStream STDOUT = System.out;
//    private final static ByteArrayOutputStream OUT = new ByteArrayOutputStream();
//    private Tracker tracker = new Tracker("jdbc:postgresql://localhost:5432/tracker", "postgres", "123456");
//
//    private void loadOut() {
//        System.setOut(new PrintStream(OUT));
//    }
//
//    private void backOut() {
//        OUT.reset();
//        System.setOut(STDOUT);
//    }
//
//    @Test
//    public void whenUserAddItem() {
//        Input input = new StubInput(new String[] {"0", "first item", "first desription", "6"});
//        new StartUI(input, tracker).init();
//        assertThat(tracker.findAll().get(0).getName(), is("first item"));
//    }
//
//    @Test
//    public void whenUserShowAllItems() {
//        //загружаем заявки в трекер
//        Input input = new StubInput(new String[] {"0", "first item", "first description",
//                "0", "second item", "second description",
//                "0", "third item", "third description", "6"});
//        new StartUI(input, tracker).init();
//        //меняем выходной поток
//        this.loadOut();
//        //выводим в выходной поток все заявки
//        input = new StubInput(new String[] {"1", "6"});
//        new StartUI(input, tracker).init();
//        String expectedString1 = String.format("Заявка id: %s, name: %s, description: %s, created: %s",
//                tracker.findAll().get(0).getId(),
//                tracker.findAll().get(0).getName(),
//                tracker.findAll().get(0).getDescription(),
//                tracker.findAll().get(0).getCreated());
//
//        String expectedString2 = String.format("Заявка id: %s, name: %s, description: %s, created: %s",
//                tracker.findAll().get(1).getId(),
//                tracker.findAll().get(1).getName(),
//                tracker.findAll().get(1).getDescription(),
//                tracker.findAll().get(1).getCreated());
//
//        String expectedString3 = String.format("Заявка id: %s, name: %s, description: %s, created: %s",
//                tracker.findAll().get(2).getId(),
//                tracker.findAll().get(2).getName(),
//                tracker.findAll().get(2).getDescription(),
//                tracker.findAll().get(2).getCreated());
//        //ищем, содержатся ли эти заявки в выходном потоке
//        assertThat(OUT.toString(), containsString(expectedString1));
//        assertThat(OUT.toString(), containsString(expectedString2));
//        assertThat(OUT.toString(), containsString(expectedString3));
//        this.backOut();
//    }
//
//    @Test
//    public void whenUserEditItem() {
//        Item item = new Item("first item", "first description");
//        tracker.add(item);
//        Input input = new StubInput(new String[] {"2", item.getId(), "second item", "second desription", "6"});
//        new StartUI(input, tracker).init();
//        assertThat(tracker.findById(item.getId()).getName(), is("second item"));
//    }
//
//    @Test
//    public void whenUserDeleteItem() {
//        Item item1 = new Item("first item", "first description");
//        Item item2 = new Item("second item", "second description");
//        Item item3 = new Item("third item", "third description");
//        tracker.add(item1);
//        tracker.add(item2);
//        tracker.add(item3);
//        Input input = new StubInput(new String[]{"3", tracker.findAll().get(1).getId(), "6"});
//        new StartUI(input, tracker).init();
//        List<Item> items = tracker.findAll();
//        assertThat(items.get(0).getName(), is("first item"));
//        assertThat(items.get(1).getName(), is("third item"));
//    }
//
//    @Test
//    public void whenUserFindItemId() {
//        //загружаем заявки в трекер
//        Input input = new StubInput(new String[] {"0", "first item", "first description",
//                "0", "second item", "second description",
//                "0", "third item", "third description", "6"});
//        new StartUI(input, tracker).init();
//        //меняем выходной поток
//        this.loadOut();
//        //выводим в выходной поток данные по заявке с индексом "1"
//        input = new StubInput(new String[] {"4", tracker.findAll().get(1).getId(), "6"});
//        new StartUI(input, tracker).init();
//
//        String expectedString = String.format("Заявка id: %s, name: %s, description: %s, created: %s",
//                tracker.findAll().get(1).getId(),
//                tracker.findAll().get(1).getName(),
//                tracker.findAll().get(1).getDescription(),
//                tracker.findAll().get(1).getCreated());
//        //проверяем, содержит ли выходной поток информацию по выбранной заявке
//        assertThat(OUT.toString(), containsString(expectedString));
//        this.backOut();
//    }
//
//    @Test
//    public void whenUserFindName() {
//        Item item1 = new Item("first item", "first description");
//        Item item2 = new Item("second item", "second description");
//        Item item3 = new Item("first item", "third description");
//        tracker.add(item1);
//        tracker.add(item2);
//        tracker.add(item3);
//        List<Item> items = tracker.findByName("first item");
//        assertThat(items.get(0).getDescription(), is("first description"));
//        assertThat(items.get(1).getDescription(), is("third description"));
//    }
//}