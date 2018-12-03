package carsales.dao;

import carsales.models.Brand;
import carsales.models.Model;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.junit.Assert.*;

public class DAOModelImpTest {

    private final DAOModelImp daoModel = DAOModelImp.getInstance();
    private final DAOBrandImp daoBrand = DAOBrandImp.getInstance();
    private Model model = new Model("model_1", null);
    private Model model2 = new Model("model_2", null);

    @Test
    public void testAddModel() {
        daoModel.add(model);
        Model _model = daoModel.wrapperMethod((Function<Session, Model>) session -> session.get(Model.class, model.getId()));
        assertEquals(_model.getName(), model.getName());
        daoModel.wrapperMethod((Consumer<Session>) session -> session.createQuery("delete from Model ").executeUpdate());
    }

    @Test
    public void testEditModel() {
        daoModel.add(model);
        model.setName("model_11");
        daoModel.edit(model);
        Model _model = daoModel.wrapperMethod((Function<Session, Model>) session -> session.get(Model.class, model.getId()));
        assertEquals(model.getName(), _model.getName());
        daoModel.wrapperMethod((Consumer<Session>) session -> session.createQuery("delete from Model ").executeUpdate());
    }

    @Test
    public void testDeleteModel() {
        daoModel.add(model);
        daoModel.delete(model);
        Model _model = daoModel.wrapperMethod((Function<Session, Model>) session -> session.get(Model.class, model.getId()));
        assertNull(_model);
    }

    @Test
    public void testFindAllModels() {
        daoModel.add(model);
        daoModel.add(model2);
        List<Model> _models = daoModel.findAll();
        assertEquals(2, _models.size());
        assertTrue(_models.contains(model));
        assertTrue(_models.contains(model2));
        daoModel.wrapperMethod((Consumer<Session>) session -> session.createQuery("delete from Model ").executeUpdate());
    }

    @Test
    public void testFindModelById() {
        daoModel.add(model);
        daoModel.add(model2);
        Model _model = daoModel.findById(model2.getId());
        assertEquals("model_2", _model.getName());
    }

    @Test
    public void testFindModelByBrand() {
        Brand brand1 = new Brand("brand_1");
        Brand brand2 = new Brand("brand_2");
        model.setBrand(brand1);
        model2.setBrand(brand2);
        daoBrand.add(brand1);
        daoBrand.add(brand2);
        daoModel.add(model);
        daoModel.add(model2);
        List<Model> _models = daoModel.findByBrand(brand2);
        assertEquals(1, _models.size());
        assertTrue(_models.contains(model2));
        daoModel.wrapperMethod((Consumer<Session>) session -> session.createQuery("delete from Model ").executeUpdate());
    }

}