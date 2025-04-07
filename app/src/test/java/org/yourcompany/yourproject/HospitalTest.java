package org.yourcompany.yourproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yourcompany.yourproject.models.Appointment;
import org.yourcompany.yourproject.models.Doctor;
import org.yourcompany.yourproject.models.Hospital;
import org.yourcompany.yourproject.models.Patient;

class HospitalTest {

    private Hospital hospital;
    private Doctor doctor;
    private Patient patient;
    private Appointment appointment;

    @BeforeEach
    void setUp() {
        hospital = new Hospital();
        doctor = new Doctor("Др. Марченко", "Кардіологія");
        patient = new Patient("Олександр Коваль", "Підвищений тиск");
        appointment = new Appointment("2025-04-10", doctor, patient);
    }

    @Test
    void testAddDoctor() {
        hospital.addDoctor(doctor);
        assertEquals(1, hospital.getDoctors().size());
        assertEquals("Др. Марченко", hospital.getDoctors().get(0).getName());
    }

    @Test
    void testAddPatient() {
        hospital.addPatient(patient);
        assertEquals(1, hospital.getPatients().size());
        assertEquals("Олександр Коваль", hospital.getPatients().get(0).getName());
    }

    @Test
    void testAddAppointment() {
        hospital.addAppointment(appointment);
        assertEquals(1, hospital.getAppointments().size());
        assertEquals("2025-04-10", hospital.getAppointments().get(0).getDate());
    }

    @Test
    void testGetDoctorById() {
        hospital.addDoctor(doctor);
        Doctor found = hospital.getDoctorById(doctor.getId());
        assertNotNull(found);
        assertEquals("Др. Марченко", found.getName());
    }

    @Test
    void testGetPatientById() {
        hospital.addPatient(patient);
        Patient found = hospital.getPatientById(patient.getId());
        assertNotNull(found);
        assertEquals("Олександр Коваль", found.getName());
    }

    @Test
    void testGetAppointmentById() {
        hospital.addAppointment(appointment);
        Appointment found = hospital.getAppointmentById(appointment.getId());
        assertNotNull(found);
        assertEquals("2025-04-10", found.getDate());
    }

    @Test
    void testDoctorEquality() {
        Doctor d1 = new Doctor("Др. Кравець", "Неврологія");
        Doctor d2 = new Doctor("Др. Кравець", "Неврологія");
        assertEquals(d1, d2);
        assertEquals(d1.hashCode(), d2.hashCode());
    }

    @Test
    void testPatientEquality() {
        Patient p1 = new Patient("Марія Іваненко", "Головний біль");
        Patient p2 = new Patient("Марія Іваненко", "Головний біль");
        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    void testAppointmentToString() {
        String expected = "Appointment{" +
                "id=" + appointment.getId() +
                ", date='2025-04-10', doctor=Др. Марченко, patient=Олександр Коваль}";
        assertEquals(expected, appointment.toString());
    }
    
}
