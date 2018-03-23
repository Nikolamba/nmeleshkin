package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс описывает банковский счет
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class Account {
    private double value;
    private String requisites;

    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public double getValue() {
        return value;
    }

    public void increaseBy(double amount) {
        this.value += amount;
    }

    public void reduceBy(double amount) {
        this.value -= amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(requisites, account.requisites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisites);
    }
}
