package ru.job4j.Profession;

public class Profession {
    private String userName;
    private Patent patent;
    private String type;
    private int experience;
    private int salary;

    public int coaching(Profession intern) {
        this.experience += 1;
        return this.experience;
    }

    public int getExperience() {
        return this.experience;
    }
    public void setExperience(int experience) {
        this.experience = experience;
    }
    public String getName() {
        return this.userName;
    }
    public void setUserName(String name) {
        this.userName = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
