package ru.job4j.list;

import java.util.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class BankCounter {
    private TreeSet<BankVisitor> visitors = new TreeSet<>();
    private long maxStart;
    private long maxFinish;
    private int maxCount;

    public void add(BankVisitor visitor) {
        this.visitors.add(visitor);

        if (visitor.getEntryTime() >= this.visitors.first().getExitTime()) {
            this.maxFinish = this.visitors.pollFirst().getExitTime();
        }

        if (this.maxCount < this.visitors.size()) {
            this.maxCount = this.visitors.size();
            this.maxStart = visitor.getEntryTime();
            this.maxFinish = 0;
        }
    }

    private void clearVisitors() {
        if (this.maxCount == this.visitors.size()) {
            this.maxFinish = this.visitors.first().getExitTime();
        }
    }

    public int getMaxCount() {
        return maxCount;
    }

    public String getTime() {
        this.clearVisitors();
        return "Время максимального количества посетителей: с " + this.maxStart + " до " + this.maxFinish
                + ". Максимальное количество: " + this.maxCount;
    }
}
