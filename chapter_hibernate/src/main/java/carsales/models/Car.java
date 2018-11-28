package carsales.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "year")
    private int year;

    @Column(name = "status")
    private boolean status;

    @Column(name = "color")
    private String color;

    @Column(name = "picture_path")
    private String picturePath;

    @Column(name = "data")
    private Date data;

    @ManyToOne
    @JoinColumn(name = "car_model")
    private Model model;

    @ManyToOne
    @JoinColumn(name = "body_type")
    private BodyType bodyType;

    @ManyToOne
    @JoinColumn(name = "seller")
    private User seller;

    public Car() {
        data = new Date();
    }

    public Car(int year, String color, Model model, BodyType bodyType, User seller) {
        this.year = year;
        this.color = color;
        this.model = model;
        this.bodyType = bodyType;
        this.seller = seller;
        this.status = false;
        this.picturePath = "";
        data = new Date();
    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public boolean isStatus() {
        return status;
    }

    public String getColor() {
        return color;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public Model getModel() {
        return model;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public User getSeller() {
        return seller;
    }

    public Date getData() {
        return data;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
