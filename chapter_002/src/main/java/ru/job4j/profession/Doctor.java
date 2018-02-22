package ru.job4j.profession;

public class Doctor extends Profession {
    private Hospital placeWork;

    public String internship(Doctor mentor) {
        this.setType("Super doctor");
        System.out.println("Доктор " + this.getName() + " учится у доктора " + mentor.getName());
        System.out.println("Доктор " + this.getName() + " повысил квалификацию до " + this.getType());
        return this.getType();
    }

    public int heal(Patient patient) {
        this.setSalary(100);
        patient.setDiagnosis("Diagnosis");
        System.out.println("Доктор " + this.getName() + " лечит " + patient.getName());
        return this.getSalary();
    }

    public int changePlaceWork(Hospital newHospital) {
        this.placeWork = newHospital;
        this.setSalary(200);
        System.out.println("Теперь доктор " + this.getName() + " работает в " + newHospital.getName());
        System.out.println("Новая зарплата доктора " + this.getName() + " = " + this.getSalary());
        return this.getSalary();
    }
}





