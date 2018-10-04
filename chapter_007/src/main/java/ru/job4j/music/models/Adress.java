package ru.job4j.music.models;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class Adress {

    private int id;
    private int zipCode;
    private String city;
    private String street;
    private int houseNum;

    public Adress(int zipCode, String city, String street, int houseNum) {
        this.zipCode = zipCode;
        this.city = city;
        this.street = street;
        this.houseNum = houseNum;
    }

    public int getId() {
        return id;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getHouseNum() {
        return houseNum;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouseNum(int houseNum) {
        this.houseNum = houseNum;
    }

    public void setId(int id) {
        this.id = id;
    }
}
