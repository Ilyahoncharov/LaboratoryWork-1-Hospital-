package org.yourcompany.yourproject.models;

import java.util.Objects;

public class Doctor {
    private static int idCounter = 1;
    private int id;
    private String name;
    private String specialization;

    public Doctor(String name, String specialization) {
        this.id = idCounter++;
        this.name = name;
        this.specialization = specialization;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    @Override
    public boolean equals(Object o) {
        System.out.println("Doctor.equals called");
        if (this == o) return true;
        if (!(o instanceof Doctor)) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(name, doctor.name) &&
                Objects.equals(specialization, doctor.specialization);
    }

    @Override
    public int hashCode() {
        System.out.println("Doctor.hashCode called");
        return Objects.hash(name, specialization);
    }

    @Override
    public String toString() {
        return "Doctor{" + "id=" + id + ", name='" + name + "', specialization='" + specialization + "'}";
    }
}

