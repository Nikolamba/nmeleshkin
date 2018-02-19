package ru.job4j.Profession;

public class Ingineer extends Profession {
    private Organization placeWork;

    public int work(Device device) {
        device.setDiagnosis("Breaking");
        this.setSalary(100);
        return this.getSalary();
    }

    public String getNewKnowledge(Ingineer mentor) {
        this.setType("Super Ingineer");
        return this.getType();
    }

    public int finishProject(Device device) {
        this.setExperience(this.getExperience() + 1);
        return this.getExperience();
    }

    public int changePlaceWork(Organization newOrganization) {
        this.placeWork = newOrganization;
        this.setSalary(200);
        return this.getSalary();
    }
}
