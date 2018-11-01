package ru.job4j.hallservice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class Logic {

    private final OperationsHall source = OperationsHall.getInstance();
    private final static Logic INSTANCE = new Logic();

    private Logic() { }

    public static Logic getInstance() {
        return INSTANCE;
    }

    public List<Place> getAllPlaces() {
        return this.source.getAllPlaces();
    }

    public boolean addAccount(HttpServletRequest req) {
        boolean result = false;
        if (checkNumberParam("row", req) && checkNumberParam("place", req)
                && checkStringParam("username", req) && checkStringParam("phone", req)) {
            int row = Integer.valueOf(req.getParameter("row"));
            int place = Integer.valueOf(req.getParameter("place"));
            if (!this.source.isTaken(row, place)) {
                result = this.source.addAccount(row, place,
                        new Account(req.getParameter("username"), req.getParameter("phone")));
            }

        }
        return result;
    }

    public boolean checkNumberParam(String param, HttpServletRequest req) {
        if (req.getParameter(param) == null) {
            return false;
        }
        try {
            Integer.valueOf(req.getParameter(param).trim());
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public boolean checkStringParam(String param, HttpServletRequest req) {
        return req.getParameter(param) != null;
    }
}
