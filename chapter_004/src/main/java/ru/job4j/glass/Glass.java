package ru.job4j.glass;

import java.util.*;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 * Класс содержит заявки конкретного эмитента. При добавлении заявок
 * выполняется (по возможности) слияние заявок.
 */
public class Glass {

    private String name;
    private Set<Request> askRequests;
    private Set<Request> bidRequests;

    Glass(String name) {
        this.name = name;
        this.askRequests = new TreeSet<>((o1, o2) -> {
            int result = -(Double.compare(o1.getPrice(), o2.getPrice()));
            if (result == 0) {
                result = Integer.compare(o1.getId(), o2.getId());
            }
            return result;
        });

        this.bidRequests = new TreeSet<>(((o1, o2) -> {
            int result = Double.compare(o1.getPrice(), o2.getPrice());
            if (result == 0) {
                result = Integer.compare(o1.getId(), o2.getId());
            }
            return result;
        }));
    }

    public void add(Request request) {
        if (request.getType().equals("DeleteOrder")) {
            this.deleteOrder(request.getId());
        } else {
            if (request.getAction().equals("BUY")) {
                merge(request, true);
            } else {
                merge(request, false);
            }
        }
    }

    private void deleteOrder(int id) {

        for (Request request : this.askRequests) {
            if (request.getId() == id) {
                this.askRequests.remove(request);
                return;
            }
        }
        for (Request request : this.bidRequests) {
            if (request.getId() == id) {
                this.bidRequests.remove(request);
                return;
            }
        }
    }

    private void merge(Request request, boolean isBuy) {
        List<Request> removeIndex = new ArrayList<>();

        Set<Request> requests = isBuy ? this.bidRequests : this.askRequests;
        for (Request req : requests) {
            boolean condition = isBuy ? req.getPrice() <= request.getPrice() : req.getPrice() >= request.getPrice();
            if (condition) {
                if (req.getVolume() > request.getVolume()) {
                    req.setVolume(req.getVolume() - request.getVolume());
                    request.setVolume(0);
                    break;
                } else if (req.getVolume() < request.getVolume()) {
                    request.setVolume(request.getVolume() - req.getVolume());
                    removeIndex.add(req);
                } else {
                    requests.remove(req);
                    request.setVolume(0);
                    break;
                }
            }
        }
        for (Request req : removeIndex) {
            requests.remove(req);
        }
        if (request.getVolume() != 0) {
            if (isBuy) {
                this.askRequests.add(request);
            } else {
                this.bidRequests.add(request);
            }
        }
    }

    public String toString() {
        Map<Double, Integer> glassOfBid = new TreeMap<>();
        Map<Double, Integer> glassOfAsk = new TreeMap<>();
        StringBuilder string = new StringBuilder();

        fillGlass(glassOfBid, this.bidRequests);
        fillGlass(glassOfAsk, this.askRequests);

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
    private void fillGlass(Map<Double, Integer> glass, Set<Request> requests) {
        for (Request request : requests) {
            double price = request.getPrice();
            if (glass.putIfAbsent(price, request.getVolume()) != null) {
                int newVolume = glass.get(price) + request.getVolume();
                glass.replace(price, newVolume);
            }
        }
    }

}
