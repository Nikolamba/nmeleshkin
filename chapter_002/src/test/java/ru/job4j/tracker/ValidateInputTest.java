//package ru.job4j.tracker;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.hamcrest.core.Is.is;
//import static org.junit.Assert.*;
//
///**
// * @author Nikolay Meleshkin (sol.of.f@mail.ru)
// * @version 0.1
// */
//public class ValidateInputTest {
//    private ByteArrayOutputStream out = new ByteArrayOutputStream();
//    private PrintStream stdOut = System.out;
//    private Tracker tracker = new Tracker("jdbc:postgresql://localhost:5432/tracker", "postgres", "123456");
//
//    @Before
//    public void loadOut() {
//        System.setOut(new PrintStream(out));
//    }
//
//    @After
//    public void backOut() {
//        System.setOut(stdOut);
//    }
//
//    @Test
//    public void whenInvalidInputString() {
//        String[] str = new String[] {"invalid", "6"};
//        Input input = new ValidateInput(new StubInput(str));
//        new StartUI(input, tracker).init();
//        assertThat(out.toString(), containsString("Введите корректные данные"));
//    }
//
//    @Test
//    public void whenInvalidInputNumber() {
//        String[] str = new String[] {"20", "6"};
//        Input input = new ValidateInput(new StubInput(str));
//        new StartUI(input, tracker).init();
//        assertThat(out.toString(), containsString("Выберите доступный пункт меню"));
//    }
//
//    @Test
//    public void whenValidateInput() {
//        String[] str = new String[] {"1", "6"};
//        Input input = new ValidateInput(new StubInput(str));
//        new StartUI(input, tracker).init();
//        assertThat(out.toString(), containsString("Список заявок пуст"));
//    }
//}
