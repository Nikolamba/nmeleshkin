package carsales;

import carsales.dao.*;
import carsales.models.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class LogicLayer {

    private final DAOCarImp daoCar = DAOCarImp.getInstance();
    private final DAOModelImp daoModel = DAOModelImp.getInstance();
    private final DAOBodyTypeImp daoBodyType = DAOBodyTypeImp.getInstance();
    private final DAOBrandImp daoBrand = DAOBrandImp.getInstance();
    private final DAOUserImp daoUser = DAOUserImp.getInstance();
    private final static LogicLayer INSTANCE = new LogicLayer();

    private LogicLayer() { }

    public static LogicLayer getInstance() {
        return INSTANCE;
    }

    public boolean addCar(HttpServletRequest req, Map<String, String> fields) {
        if (fields.get("year") == null || fields.get("color") == null
                || fields.get("models") == null || fields.get("body_type") == null
                || req.getSession().getAttribute("user") == null) {
            req.setAttribute("error", "adding error");
            return false;
        } else {
            Car car = new Car(Integer.valueOf(fields.get("year")),
                    fields.get("color"),
                    daoModel.findById(Integer.valueOf(fields.get("models"))),
                    daoBodyType.findById(Integer.valueOf(fields.get("body_type"))),
                    (User) req.getSession().getAttribute("user")
            );
            car.setPicturePath(fields.get("picturePath"));
            daoCar.add(car);
            return true;
        }
    }

    public List<Brand> findAllBrands() {
        return daoBrand.findAll();
    }

    public List<BodyType> findAllBodyType() {
        return daoBodyType.findAll();
    }

    public List<Car> findAllCars() {
        return daoCar.findAll();
    }

    public List<Model> findModelsByBrand(Brand brand) {
        return daoModel.findByBrand(brand);
    }

    public List<Car> findCarsByUser(User user) {
        return daoCar.findByUser(user);
    }

    public List<User> findAllUsers() {
        return daoUser.findAll();
    }

    public Brand findBrandById(int id) {
        return daoBrand.findById(id);
    }

    public List<Car> useFilters(Map<String, Integer> filters) {
        return daoCar.enableFilters(filters);
    }

    public void addUser(User obj) {
        daoUser.add(obj);
    }

    public boolean setSessionUser(HttpServletRequest req, HttpServletResponse resp) {
        boolean result = false;
        if (checkStringParam("name", req) && checkStringParam("pass", req)) {
            String name = req.getParameter("name");
            String password = req.getParameter("pass");
            if (daoUser.isCredentional(name, password)) {
                HttpSession session = req.getSession();
                session.setAttribute("user", daoUser.findByName(name));
                result = true;
            } else {
                req.setAttribute("error", "Credentional invalid");
            }
        }
        return result;
    }

    public boolean isUserCredentional(String name, String pass) {
        return daoUser.isCredentional(name, pass);
    }

    public void changeStatus(HttpServletRequest req) {
        if (checkStringParam("carId", req)) {
            Car car = daoCar.findById(Integer.valueOf(req.getParameter("carId")));
            car.setStatus(!car.isStatus());
            daoCar.edit(car);
        }
    }

    public boolean checkStringParam(String param, HttpServletRequest req) {
        if (req.getParameter(param) == null) {
            req.getServletContext().setAttribute("error", String.format("%s isn't set", param));
            return false;
        }
        return true;
    }
}
