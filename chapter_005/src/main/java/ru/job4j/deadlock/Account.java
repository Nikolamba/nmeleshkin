package ru.job4j.deadlock;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class Account {
    private int amount;

    Account(int amount) {
        this.amount = amount;
    }

    private void setAmount(int amount) {
        this.amount = amount;
    }

    private int getAmount() {
        return amount;
    }

    public void transfer(Account accTo, int amount) throws InterruptedException {
        synchronized (this) {
            Thread.sleep(100);
            synchronized (accTo) {
                this.amount -= amount;
                accTo.setAmount(accTo.getAmount() + amount);
            }
        }
    }
}
