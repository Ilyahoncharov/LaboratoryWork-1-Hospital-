package org.yourcompany.yourproject.models;

import java.util.Objects;

public class Patient {
    private static int idCounter = 1;
    private int id;
    private String name;
    private String diagnosis;

    public Patient(String name, String diagnosis) {
        this.id = idCounter++;
        this.name = name;
        this.diagnosis = diagnosis;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("equals called");
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return  
            Objects.equals(name, patient.name) && 
            Objects.equals(diagnosis, patient.diagnosis);
    }

    @Override
    public int hashCode() {
        System.out.println("hashCode called");
        return Objects.hash(name, diagnosis);
    }

    @Override
    public String toString() {
        return "Patient{" + "id=" + id + ", name='" + name + "', diagnosis='" + diagnosis + "'}";
    }
}

