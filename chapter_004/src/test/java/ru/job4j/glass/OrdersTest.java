package ru.job4j.glass;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.StringContains.*;

public class OrdersTest {

    private Orders orders = new Orders();

    @Test
    public void whenParseTestFile() {
        orders.parseFile("test1.xml");
        System.out.println(orders.getGlass("book-1"));
        assertThat(orders.getGlass("book-1").toString(), containsString("81"));
        assertThat(orders.getGlass("book-1").toString(), containsString("22"));
    }

    @Test
    public void whenParseOrdersFile() {
        orders.parseFile("orders.xml");
        System.out.println(orders.getGlass("book-1"));
    }

}