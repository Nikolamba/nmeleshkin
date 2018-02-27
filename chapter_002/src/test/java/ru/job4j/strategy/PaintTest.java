package ru.job4j.strategy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version $Id$
 */
public class PaintTest {
    private static final PrintStream STDOUT = System.out;
    private static final ByteArrayOutputStream OUT = new ByteArrayOutputStream();

    @Before
    public void loadOut() {
        System.setOut(new PrintStream(this.OUT));
    }
    @After
    public void backOut() {
        OUT.reset();
        System.setOut(STDOUT);
    }
    @Test
    public void whenDrawSquare() {
        new Paint().draw(new Square());
        assertThat(new String(this.OUT.toByteArray()), is(new StringBuilder()
                                                .append("+++++")
                                                .append(System.lineSeparator())
                                                .append("+   +")
                                                .append(System.lineSeparator())
                                                .append("+   +")
                                                .append(System.lineSeparator())
                                                .append("+++++")
                                                .append(System.lineSeparator())
                                                .toString()));
    }

    @Test
    public void whenDrawTriangle() {
        new Paint().draw(new Triangle());
        assertThat(new String(this.OUT.toByteArray()), is(new StringBuilder()
                                                .append("  +  ")
                                                .append(System.lineSeparator())
                                                .append(" +++ ")
                                                .append(System.lineSeparator())
                                                .append("+++++")
                                                .append(System.lineSeparator())
                                                .toString()));
    }
}