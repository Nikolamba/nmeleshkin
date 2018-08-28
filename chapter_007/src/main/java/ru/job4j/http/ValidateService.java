package ru.job4j.http;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class ValidateService {

    private final static ValidateService INSTANCE = new ValidateService();
    private final Store store = DbStore.getInstance();

    @SuppressWarnings("unchecked")
    private ValidateService() { }

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    boolean add(HttpServletRequest request) {
        boolean result = false;
        if (validateField(request, "id")
                && validateField(request, "name")) {

            int id = Integer.valueOf(request.getParameter("id"));
            if (store.findById(id) == null) {
                store.add(new User(id, request.getParameter("name"),
                        request.getParameter("login"),
                        request.getParameter("password"),
                        request.getParameter("email"),
                        new Role(request.getParameter("autorization"))
                        ));
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
                store.update(id, request.getParameter("name"), request.getParameter("login"),
                        request.getParameter("email"), request.getParameter("autorization"));
                result = true;
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
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
        return (User) store.findById(id);
    }

    public User findByLogin(String login) {
        return (User) store.findByLogin(login);
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return store.findAll();
    }

    private boolean validateField(HttpServletRequest req, String field) {
        return  (req.getParameterMap().containsKey(field)
                && !req.getParameter(field).equals(""));
    }

    public boolean isCredentional(String login, String password) {
        boolean exist = false;
        for (User user : this.findAll()) {
            String userLogin = user.getLogin().trim();
            String userPass = user.getPassword().trim();
            if (userLogin.equals(login) && userPass.equals(password)) {
                exist = true;
                break;
            }
        }
        return exist;
    }
}
