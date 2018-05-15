package ru.job4j.list;

import java.util.Objects;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class BankVisitor implements Comparable {
    private final int id;
    private long entryTime;
    private long exitTime;

    BankVisitor(int id, long entryTime, long exitTime) {
        this.id = id;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public long getExitTime() {
        return exitTime;
    }

    @Override
    public int compareTo(Object o) {
        BankVisitor visitor = (BankVisitor) o;
        return Long.compare(this.exitTime, visitor.getExitTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BankVisitor visitor = (BankVisitor) o;
        return id == visitor.id && entryTime == visitor.entryTime
                && exitTime == visitor.exitTime;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, entryTime, exitTime);
    }
}
