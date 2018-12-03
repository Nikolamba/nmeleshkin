package carsales.dao;

import carsales.models.Brand;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DAOBrandImpTest {

    private final DAOBrandImp daoBrand = DAOBrandImp.getInstance();
    private Brand brand1 = new Brand("brand_1");
    private Brand brand2 = new Brand("brand_2");

    @Test
    @SuppressWarnings("unchecked")
    public void testAddBrand() {
        daoBrand.add(brand1);
        Brand _brand = daoBrand.wrapperMethod((Function<Session, Brand>)  session -> session.get(Brand.class, brand1.getId()));
        assertEquals(brand1, _brand);
        daoBrand.wrapperMethod((Consumer<Session>) session -> session.createQuery("delete from Brand ").executeUpdate());
    }

    @Test
    public void testEditBrand() {
        daoBrand.add(brand1);
        brand1.setName("brand_11");
        daoBrand.edit(brand1);
        Brand _brand = daoBrand.wrapperMethod((Function<Session, Brand>)  session -> session.get(Brand.class, brand1.getId()));
        assertEquals("brand_11", _brand.getName());
        daoBrand.wrapperMethod((Consumer<Session>) session -> session.createQuery("delete from Brand ").executeUpdate());
    }

    @Test
    public void testDeleteBrand() {
        daoBrand.add(brand1);
        daoBrand.delete(brand1);
        Brand _brand = daoBrand.wrapperMethod((Function<Session, Brand>)  session -> session.get(Brand.class, brand1.getId()));
        assertNull(_brand);
    }

    @Test
    public void testFindById() {
        daoBrand.add(brand1);
        daoBrand.add(brand2);
        assertEquals(brand2, daoBrand.findById(brand2.getId()));
        daoBrand.wrapperMethod((Consumer<Session>) session -> session.createQuery("delete from Brand ").executeUpdate());
    }

    @Test
    public void testFindByName() {
        daoBrand.add(brand1);
        daoBrand.add(brand2);
        assertTrue(daoBrand.findByName("brand_2"));
        assertFalse(daoBrand.findByName("brand_3"));
        daoBrand.wrapperMethod((Consumer<Session>) session -> session.createQuery("delete from Brand ").executeUpdate());
    }

    @Test
    public void testFindAll() {
        daoBrand.add(brand1);
        daoBrand.add(brand2);
        List<Brand> brands = daoBrand.findAll();
        assertTrue(brands.contains(brand1));
        assertTrue(brands.contains(brand2));
    }
}