package ru.job4j.glass;

import java.util.Objects;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class Order {
    private final int id; //уникальный идентификатор заявки
    private String book; //эмитент ценной бумаги
    private String type; //add/delete
    private String action; //bid/ask
    private double price; //цена
    private int volume; //объем

    Order(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public String getBook() {
        return book;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getVolume() {
        return volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order request = (Order) o;
        return id == request.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
