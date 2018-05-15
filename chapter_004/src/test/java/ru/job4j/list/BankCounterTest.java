package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class BankCounterTest {

    private BankCounter bankCounter = new BankCounter();

    @Test
    public void whenAddVisitorsShouldReturnMaxCountFour() {
        bankCounter.add(new BankVisitor(1, 7, 12));
        bankCounter.add(new BankVisitor(2, 8, 9));
        bankCounter.add(new BankVisitor(3, 10, 16));
        bankCounter.add(new BankVisitor(4, 10, 17));
        bankCounter.add(new BankVisitor(5, 12, 14));
        bankCounter.add(new BankVisitor(6, 13, 15));
        System.out.println(bankCounter.getTime());
        assertThat(bankCounter.getMaxCount(), is(4));
    }

    @Test
    public void whenAddVisitorsShouldReturnMaxCountFive() {
        bankCounter.add(new BankVisitor(1, 8, 17));
        bankCounter.add(new BankVisitor(2, 9, 16));
        bankCounter.add(new BankVisitor(3, 10, 15));
        bankCounter.add(new BankVisitor(4, 11, 14));
        bankCounter.add(new BankVisitor(5, 12, 13));
        System.out.println(bankCounter.getTime());
        assertThat(bankCounter.getMaxCount(), is(5));
    }

}