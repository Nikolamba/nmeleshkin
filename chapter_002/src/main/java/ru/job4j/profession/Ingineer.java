package ru.job4j.profession;

public class Ingineer extends Profession {
    private Organization placeWork;

    public int work(Device device) {
        device.setDiagnosis("Breaking");
        this.setSalary(100);
        System.out.println("инженер " + this.getName() + " ремонтирует " + device.getName());
        return this.getSalary();
    }

    public String getNewKnowledge(Ingineer mentor) {
        this.setType("Super Ingineer");
        System.out.println("Инженер " + this.getName() + " учится у инженера " + mentor.getName());
        System.out.println("Инженер " + this.getName() + " повысил квалификацию до " + this.getType());
        return this.getType();
    }

    public int finishProject(Device device) {
        this.setExperience(this.getExperience() + 1);
        System.out.println("Инженер " + this.getName() + " закончил ремонтировать " + device.getName());
        return this.getExperience();
    }

    public int changePlaceWork(Organization newOrganization) {
        this.placeWork = newOrganization;
        this.setSalary(200);
        System.out.println("Теперь инженер " + this.getName() + " работает в организации " + newOrganization.getName());
        System.out.println("Новая зарплата инженера " + this.getName() + " = " + this.getSalary());
        return this.getSalary();
    }
}
