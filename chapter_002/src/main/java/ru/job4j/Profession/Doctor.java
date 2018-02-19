package ru.job4j.Profession;

public class Doctor extends Profession {
    private Hospital placeWork;

    public String internship(Doctor mentor) {
        this.setType("Super doctor");
        return this.getType();
    }

    public int heal(Patient patient) {
        this.setSalary(100);
        patient.setDiagnosis("Diagnosis");
        return this.getSalary();
    }

    public int changePlaceWork(Hospital newHospital) {
        this.placeWork = newHospital;
        this.setSalary(200);
        return this.getSalary();
    }
}





