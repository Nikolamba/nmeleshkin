package ru.job4j.profession;

public class Patient {
    private String name;
    private String diagnosis;

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
