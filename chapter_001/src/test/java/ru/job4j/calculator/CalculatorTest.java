package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CalculatorTest {

    private double expecterForAdd = 2D;
    private double expectedForSubstract = 3D;
    private double expectedForDiv = 2D;
    private double expectedForMultiple = 6D;

    private double result;
    private Calculator calculator = new Calculator();

    @Test
    public void add() {
        calculator.add(1D, 1D);
        result = calculator.getResult();
        assertThat(result, is(expecterForAdd));
    }

    @Test
    public void subtract() {
        calculator.subtract(5D, 2D);
        result = calculator.getResult();
        assertThat(result, is(expectedForSubstract));
    }

    @Test
    public void div() {
        calculator.div(6D, 3D);
        result = calculator.getResult();
        assertThat(result, is(expectedForDiv));
    }

    @Test
    public void multiple() {
        calculator.multiple(2D, 3D);
        result = calculator.getResult();
        assertThat(result, is(expectedForMultiple));
    }
}