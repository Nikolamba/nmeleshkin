package ru.job4j.profession;

public class Teacher extends Profession {
    private School placeWork;

    public int teach(Student student) {
        this.setSalary(100);
        System.out.println("Учитель " + this.getName() + " учит " + student.getName());
        return this.getSalary();
    }

    public String improveQual(Teacher mentor) {
        this.setType("Super Teacher");
        System.out.println("Учитель " + this.getName() + " повышает квалификацию у учителя " + mentor.getName());
        System.out.println("Учитель " + this.getName() + " повысил квалификацию до " + this.getType());
        return this.getType();
    }

    public int changePlaceWork(School newSchool) {
        this.placeWork = newSchool;
        this.setSalary(150);
        System.out.println("Теперь учитель " + this.getName() + " работает в " + newSchool.getName());
        System.out.println("Новая зарплата учителя " + this.getName() + " = " + this.getSalary());
        return this.getSalary();
    }
}
