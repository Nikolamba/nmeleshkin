package ru.job4j.strategy;

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
    @Test
    public void whenDrawSquare() {
        PrintStream stdOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Square());
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                                                .append("+++++")
                                                .append(System.lineSeparator())
                                                .append("+   +")
                                                .append(System.lineSeparator())
                                                .append("+   +")
                                                .append(System.lineSeparator())
                                                .append("+++++")
                                                .append(System.lineSeparator())
                                                .toString()));
        System.setOut(stdOut);
    }

    @Test
    public void whenDrawTriangle() {
        PrintStream stdOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Triangle());
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                                                .append("  +  ")
                                                .append(System.lineSeparator())
                                                .append(" +++ ")
                                                .append(System.lineSeparator())
                                                .append("+++++")
                                                .append(System.lineSeparator())
                                                .toString()));
        System.setOut(stdOut);
    }
}