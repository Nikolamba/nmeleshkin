package ru.job4j.glass;

import java.util.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 * Класс содержит заявки конкретного эмитента. При добавлении заявок
 * выполняется (по возможности) слияние заявок.
 */
public class Book {

    private String name;
    private Set<Order> askOrders;
    private Set<Order> bidOrders;

    Book(String name) {
        this.name = name;
        this.askOrders = new TreeSet<>((o1, o2) -> {
            int result = -(Double.compare(o1.getPrice(), o2.getPrice()));
            if (result == 0) {
                result = Integer.compare(o1.getId(), o2.getId());
            }
            return result;
        });

        this.bidOrders = new TreeSet<>(((o1, o2) -> {
            int result = Double.compare(o1.getPrice(), o2.getPrice());
            if (result == 0) {
                result = Integer.compare(o1.getId(), o2.getId());
            }
            return result;
        }));
    }

    public void add(Order order) {
        if (order.getType().equals("DeleteOrder")) {
            this.deleteOrder(order.getId());
        } else {
            if (order.getAction().equals("BUY")) {
                merge(order, true);
            } else {
                merge(order, false);
            }
        }
    }

    private void deleteOrder(int id) {

        for (Order order : this.askOrders) {
            if (order.getId() == id) {
                this.askOrders.remove(order);
                return;
            }
        }
        for (Order order : this.bidOrders) {
            if (order.getId() == id) {
                this.bidOrders.remove(order);
                return;
            }
        }
    }

    private void merge(Order order, boolean isBuy) {
        List<Order> removeIndex = new ArrayList<>();

        Set<Order> orders = isBuy ? this.bidOrders : this.askOrders;
        for (Order ord : orders) {
            boolean condition = isBuy ? ord.getPrice() <= order.getPrice() : ord.getPrice() >= order.getPrice();
            if (condition) {
                if (ord.getVolume() > order.getVolume()) {
                    ord.setVolume(ord.getVolume() - order.getVolume());
                    order.setVolume(0);
                    break;
                } else if (ord.getVolume() < order.getVolume()) {
                    order.setVolume(order.getVolume() - ord.getVolume());
                    removeIndex.add(ord);
                } else {
                    orders.remove(ord);
                    order.setVolume(0);
                    break;
                }
            }
        }
        for (Order req : removeIndex) {
            orders.remove(req);
        }
        if (order.getVolume() != 0) {
            if (isBuy) {
                this.askOrders.add(order);
            } else {
                this.bidOrders.add(order);
            }
        }
    }

    public String toString() {
        Map<Double, Integer> glassOfBid = new TreeMap<>();
        Map<Double, Integer> glassOfAsk = new TreeMap<>();
        StringBuilder string = new StringBuilder();

        fillBook(glassOfBid, this.bidOrders);
        fillBook(glassOfAsk, this.askOrders);

        string.append("Стакан котировок для " + this.name);
        string.append(System.lineSeparator());
        string.append("ASK" + '\t' + '\t' + "PRICE" + '\t' + '\t' + "BID");
        string.append(System.lineSeparator());

        for (Map.Entry<Double, Integer> entry : glassOfAsk.entrySet()) {
            string.append(String.valueOf(entry.getValue()) + '\t' + '\t');
            string.append(String.valueOf(entry.getKey()));
            string.append(System.lineSeparator());
        }

        for (Map.Entry<Double, Integer> entry : glassOfBid.entrySet()) {
            string.append('\t');
            string.append('\t');
            string.append(String.valueOf(entry.getKey()) + '\t' + '\t');
            string.append(String.valueOf(entry.getValue()));
            string.append(System.lineSeparator());
        }
        //return String.format("%s %s %s %s %s %s", "ASK", askRequests, "BID", bidRequests, "GLASS", glassOfBid);
        return string.toString();
    }

    //Суммирует объемы заявок, у которых одинаковая цена
    private void fillBook(Map<Double, Integer> glass, Set<Order> orders) {
        for (Order order : orders) {
            double price = order.getPrice();
            if (glass.putIfAbsent(price, order.getVolume()) != null) {
                int newVolume = glass.get(price) + order.getVolume();
                glass.replace(price, newVolume);
            }
        }
    }

}
