package org.yourcompany.yourproject;

import java.util.List;

import org.yourcompany.yourproject.models.Appointment;
import org.yourcompany.yourproject.models.Doctor;
import org.yourcompany.yourproject.models.Patient;

public class HospitalView {

    public void showMenu() {
        System.out.println("""
                Виберіть дію:
                1 - Додати лікаря
                2 - Додати пацієнта
                3 - Додати прийом
                4 - Переглянути лікарів
                5 - Переглянути пацієнтів
                6 - Переглянути прийоми
                7 - Пошук пацієнта за іменем
                8 - Пошук пацієнта за діагнозом
                9 - Зберегти дані у файл
                10 - Видалити лікаря
                11 - Видалити пацієнта
                12 - Видалити прийом
                13 - Оновити лікаря
                14 - Оновити пацієнта
                15 - Оновити прийом
                16 - Імпорт даних 
                0 - Вихід
                """);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showDoctors(List<Doctor> doctors) {
        for (Doctor doctor : doctors) {
            System.out.println(doctor);
        }
    }

    public void showPatients(List<Patient> patients) {
        for (Patient patient : patients) {
            System.out.println(patient);
        }
    }

    public void showAppointments(List<Appointment> appointments) {
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }
}
