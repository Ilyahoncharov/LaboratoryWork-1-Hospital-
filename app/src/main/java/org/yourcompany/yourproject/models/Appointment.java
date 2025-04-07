package org.yourcompany.yourproject.models;

import java.util.Objects;

public class Appointment {
    private static int idCounter = 1;
    private int id;
    private String date;
    private Doctor doctor;
    private Patient patient;

    public Appointment(String date, Doctor doctor, Patient patient) {
        this.id = idCounter++;
        this.date = date;
        this.doctor = doctor;
        this.patient = patient;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appointment)) return false;
        Appointment that = (Appointment) o;
        return doctor.equals(that.doctor) && patient.equals(that.patient) && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctor, patient, date);
    }

    @Override
    public String toString() {
        return "Appointment{" + "id=" + id + ", date='" + date + "', doctor=" + doctor.getName() + ", patient=" + patient.getName() + "}";
    }
}