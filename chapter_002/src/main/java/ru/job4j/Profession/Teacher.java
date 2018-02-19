package ru.job4j.Profession;

public class Teacher extends Profession {
    private School placeWork;

    public int teach(Student student) {
        this.setSalary(100);
        return this.getSalary();
    }

    public String improveQual(Teacher mentor) {
        this.setType("Super Teacher");
        return this.getType();
    }

    public int changePlaceWork(School newSchool) {
        this.placeWork = newSchool;
        this.setSalary(150);
        return this.getSalary();
    }
}
