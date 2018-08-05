package ru.job4j.http;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class ValidateService {

    private static ValidateService instance = new ValidateService();
    private final Store store = MemoryStore.getInstance();

    private ValidateService() {
        store.add(new User(1, "Nikolay"));
        store.add(new User(2, "Petr"));
        store.add(new User(3, "Misha"));
    }

    public static ValidateService getInstance() {
        return instance;
    }

    boolean add(HttpServletRequest request) {
        boolean result = false;
        if (validateField(request, "id")
                && validateField(request, "name")) {

            int id = Integer.valueOf(request.getParameter("id"));
            if (store.findById(id) == null) {
                store.add(new User(id, request.getParameter("name")));
                result = true;
            }
        }
        return result;
    }

    boolean update(HttpServletRequest request) {
        boolean result = false;
        if (validateField(request, "id")
                && validateField(request, "name")) {

            int id = Integer.valueOf(request.getParameter("id"));
            if (store.findById(id) != null) {
                store.update(id, request.getParameter("name"));
                result = true;
            }
        }
        return result;
    }

    boolean delete(HttpServletRequest request) {
        boolean result = false;
        if (validateField(request, "id")) {
            int id = Integer.valueOf(request.getParameter("id"));
            if (this.findById(id) != null) {
                store.delete(store.findById(id));
                result = true;
            }
        }
        return result;
    }

    public User findById(int id) {
        return store.findById(id);
    }

    public List<User> findAll() {
        return store.findAll();
    }

    private boolean validateField(HttpServletRequest req, String field) {
        return  (req.getParameterMap().containsKey(field)
                && !req.getParameter(field).equals(""));
    }
}
