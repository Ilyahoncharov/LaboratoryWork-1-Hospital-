package org.yourcompany.yourproject;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.yourcompany.yourproject.models.Appointment;
import org.yourcompany.yourproject.models.Doctor;
import org.yourcompany.yourproject.models.Hospital;
import org.yourcompany.yourproject.models.Patient;


public class HospitalPresenter {
    private final Hospital model;
    private final HospitalView view;
    private final Scanner scanner;

    public HospitalPresenter(Hospital model, HospitalView view) {
        this.model = model;
        this.view = view;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            view.showMenu(); 

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> addDoctor();
                case "2" -> addPatient();
                case "3" -> addAppointment();
                case "4" -> viewDoctors();
                case "5" -> viewPatients();
                case "6" -> viewAppointments();
                case "7" -> searchPatientsByName();
                case "8" -> searchPatientsByDiagnosis();
                case "9" -> saveDataToFile();
                case "10" -> deleteDoctor();
                case "11" -> deletePatient();
                case "12" -> deleteAppointment();
                case "13" -> updateDoctor();
                case "14" -> updatePatient();
                case "15" -> updateAppointment();
                case "16" -> importDataFromFile();
                case "0" -> {
                    view.showMessage("Вихід з програми...");
                    return;
                }
                default -> view.showMessage("Невірний вибір! Спробуйте ще раз.");
            }
        }
    }

    private void addDoctor() {
        view.showMessage("Введіть ім'я лікаря:");
        String name = scanner.nextLine();
        view.showMessage("Введіть спеціалізацію лікаря:");
        String specialization = scanner.nextLine();
        model.addDoctor(new Doctor(name, specialization));
        view.showMessage("Лікаря додано.");
    }

    private void addPatient() {
        view.showMessage("Введіть ім'я пацієнта:");
        String name = scanner.nextLine();
        view.showMessage("Введіть діагноз пацієнта:");
        String diagnosis = scanner.nextLine();
        model.addPatient(new Patient(name, diagnosis));
        view.showMessage("Пацієнта додано.");
    }

    private void addAppointment() {
        viewDoctors();
        view.showMessage("Введіть ID лікаря:");
        int doctorId = Integer.parseInt(scanner.nextLine());
        Doctor doctor = model.getDoctorById(doctorId);
        if (doctor == null) {
            view.showMessage("Лікаря не знайдено.");
            return;
        }
        viewPatients();
        view.showMessage("Введіть ID пацієнта:");
        int patientId = Integer.parseInt(scanner.nextLine());
        Patient patient = model.getPatientById(patientId);
        if (patient == null) {
            view.showMessage("Пацієнта не знайдено.");
            return;
        }
        view.showMessage("Введіть дату прийому:");
        String date = scanner.nextLine();
        model.addAppointment(new Appointment(date, doctor, patient));
        view.showMessage("Прийом додано.");
    }

    private void viewDoctors() {
        view.showDoctors(model.getDoctors());
    }

    private void viewPatients() {
        view.showPatients(model.getPatients());
    }

    private void viewAppointments() {
        view.showAppointments(model.getAppointments());
    }

    private void searchPatientsByName() {
        view.showMessage("Введіть ім'я пацієнта для пошуку:");
        String name = scanner.nextLine();
        for (Patient patient : model.getPatients()) {
            if (patient.getName().equalsIgnoreCase(name)) {
                view.showMessage("Знайдено пацієнта: " + patient);
                return;
            }
        }
        view.showMessage("Пацієнта не знайдено.");
    }

    private void searchPatientsByDiagnosis() {
        view.showMessage("Введіть діагноз для пошуку:");
        String diagnosis = scanner.nextLine();
        for (Patient patient : model.getPatients()) {
            if (patient.getDiagnosis().equalsIgnoreCase(diagnosis)) {
                view.showMessage("Знайдено пацієнта: " + patient);
                return;
            }
        }
        view.showMessage("Пацієнта з таким діагнозом не знайдено.");
    }

    private void saveDataToFile() {
            view.showMessage("--- Збереження даних у файл ---");
            view.showMessage("Що бажаєте зберегти?");
            view.showMessage("1. Пацієнтів");
            view.showMessage("2. Лікарів");
            view.showMessage("3. Прийоми");
            view.showMessage("Ваш вибір: ");
            String saveChoice = scanner.nextLine().trim();

            switch (saveChoice) {
                case "1":
                    view.showMessage("Фільтрація пацієнтів:");
                    view.showMessage("1. Усі");
                    view.showMessage("2. За іменем");
                    view.showMessage("3. За діагнозом");
                    view.showMessage("Ваш вибір: ");
                    String patientFilter = scanner.nextLine().trim();

                    List<Patient> filteredPatients = new ArrayList<>(model.getPatients());

                    switch (patientFilter) {
                        case "2":
                            view.showMessage("Введіть ім’я: ");
                            String nameFilter = scanner.nextLine().trim().toLowerCase();
                            filteredPatients = filteredPatients.stream()
                                .filter(p -> p.getName().toLowerCase().contains(nameFilter))
                                .toList();
                            break;
                        case "3":
                            view.showMessage("Введіть діагноз: ");
                            String diagFilter = scanner.nextLine().trim().toLowerCase();
                            filteredPatients = filteredPatients.stream()
                                .filter(p -> p.getDiagnosis().toLowerCase().contains(diagFilter))
                                .toList();
                            break;
                    }

                    try (PrintWriter writer = new PrintWriter("patients.txt")) {
                        for (Patient p : filteredPatients) {
                            writer.println(p);
                        }
                        view.showMessage("Пацієнти збережені у файл 'patients.txt'");
                    } catch (IOException e) {
                        view.showMessage("Помилка при записі у файл: " + e.getMessage());
                    }
                    break;

                case "2":
                    view.showMessage("Фільтрація лікарів:");
                    view.showMessage("1. Усі");
                    view.showMessage("2. За іменем");
                    view.showMessage("3. За спеціальністю");
                    view.showMessage("Ваш вибір: ");
                    String doctorFilter = scanner.nextLine().trim();

                    List<Doctor> filteredDoctors = new ArrayList<>(model.getDoctors());

                    switch (doctorFilter) {
                        case "2":
                            view.showMessage("Введіть ім’я: ");
                            String nameDocFilter = scanner.nextLine().trim().toLowerCase();
                            filteredDoctors = filteredDoctors.stream()
                                .filter(d -> d.getName().toLowerCase().contains(nameDocFilter))
                                .toList();
                            break;
                        case "3":
                            view.showMessage("Введіть спеціальність: ");
                            String specFilter = scanner.nextLine().trim().toLowerCase();
                            filteredDoctors = filteredDoctors.stream()
                                .filter(d -> d.getSpecialization().toLowerCase().contains(specFilter))
                                .toList();
                            break;
                    }

                    try (PrintWriter writer = new PrintWriter("doctors.txt")) {
                        for (Doctor d : filteredDoctors) {
                            writer.println(d);
                        }
                        view.showMessage("Лікарі збережені у файл 'doctors.txt'");
                    } catch (IOException e) {
                        view.showMessage("Помилка при записі у файл: " + e.getMessage());
                    }
                    break;

                case "3":
                    view.showMessage("Фільтрація прийомів:");
                    view.showMessage("1. Усі");
                    view.showMessage("2. За іменем пацієнта");
                    view.showMessage("3. За іменем лікаря");
                    view.showMessage("Ваш вибір: ");
                    String appFilter = scanner.nextLine().trim();

                    List<Appointment> filteredAppointments = new ArrayList<>(model.getAppointments());

                    switch (appFilter) {
                        case "2":
                            view.showMessage("Введіть ім’я пацієнта: ");
                            String patientNameInput = scanner.nextLine().trim().toLowerCase();
                            filteredAppointments = filteredAppointments.stream()
                                .filter(a -> a.getPatient().getName().toLowerCase().contains(patientNameInput))
                                .toList();
                            break;
                        case "3":
                            view.showMessage("Введіть ім’я лікаря: ");
                            String doctorNameInput = scanner.nextLine().trim().toLowerCase();
                            filteredAppointments = filteredAppointments.stream()
                                .filter(a -> a.getDoctor().getName().toLowerCase().contains(doctorNameInput))
                                .toList();
                            break;
                    }

                    try (PrintWriter writer = new PrintWriter("appointments.txt")) {
                        for (Appointment a : filteredAppointments) {
                            writer.println(a);
                        }
                        view.showMessage("Прийоми збережені у файл 'appointments.txt'");
                    } catch (IOException e) {
                        view.showMessage("Помилка при записі у файл: " + e.getMessage());
                    }
                    break;

                default:
                    view.showMessage("Невірний вибір.");
                    break;
            }
        }

    private void importDataFromFile() {
        view.showMessage("--- Імпорт даних з файлів ---");
        view.showMessage("Що бажаєте імпортувати?");
        view.showMessage("1. Пацієнтів");
        view.showMessage("2. Лікарів");
        view.showMessage("3. Прийоми");
        view.showMessage("Ваш вибір: ");
        String importChoice = scanner.nextLine().trim();

        switch (importChoice) {
            case "1":
                importPatientsFromFile();
                break;
            case "2":
                importDoctorsFromFile();
                break;
            case "3":
                importAppointmentsFromFile();
                break;
            default:
                view.showMessage("Невірний вибір.");
                break;
        }
    }

    private void importPatientsFromFile() {
        try (Scanner fileScanner = new Scanner(new File("patients.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] parts = line.split(", ");
                    if (parts.length == 3) {
                        int id = Integer.parseInt(parts[0].split("=")[1].trim());
                        String name = parts[1].split("=")[1].trim().replace("'", "");
                        String diagnosis = parts[2].split("=")[1].trim().replace("'", "");

                        Patient patient = new Patient(name, diagnosis);

                        model.addPatient(patient);
                    }
                }
            }
            view.showMessage("Пацієнти успішно імпортовані.");
        } catch (IOException e) {
            view.showMessage("Помилка при імпорті пацієнтів: " + e.getMessage());
        }
    }


    private void importDoctorsFromFile() {
        try (Scanner fileScanner = new Scanner(new File("doctors.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] parts = line.split(", ");
                    if (parts.length == 3) {
                        int id = Integer.parseInt(parts[0].split("=")[1].trim());
                        String name = parts[1].split("=")[1].trim().replace("'", "");
                        String specialization = parts[2].split("=")[1].trim().replace("'", "").replace("}", "");

                        Doctor doctor = new Doctor(name, specialization);

                        model.addDoctor(doctor);
                    }
                }
            }
            view.showMessage("Лікарі успішно імпортовані.");
        } catch (IOException e) {
            view.showMessage("Помилка при імпорті лікарів: " + e.getMessage());
        }
    }




    private void importAppointmentsFromFile() {
        try (Scanner fileScanner = new Scanner(new File("appointments.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] parts = line.split(", ");
                    if (parts.length == 4) {
                        int id = Integer.parseInt(parts[0].split("=")[1].trim());
                        String date = parts[1].split("=")[1].trim().replace("'", "");
                        String doctorName = parts[2].split("=")[1].trim().replace("'", "");
                        String patientName = parts[3].split("=")[1].trim().replace("'", "").replace("}", "");

                        Doctor doctor = model.getDoctors().stream()
                                .filter(d -> d.getName().equalsIgnoreCase(doctorName))
                                .findFirst().orElse(null);
                        Patient patient = model.getPatients().stream()
                                .filter(p -> p.getName().equalsIgnoreCase(patientName))
                                .findFirst().orElse(null);

                        if (doctor != null && patient != null) {
                            Appointment appointment = new Appointment(date, doctor, patient);

                            model.addAppointment(appointment);
                        } else {
                            view.showMessage("Помилка: Лікар або Пацієнт не знайдений.");
                        }
                    }
                }
            }
            view.showMessage("Прийоми успішно імпортовані.");
        } catch (IOException e) {
            view.showMessage("Помилка при імпорті прийомів: " + e.getMessage());
        }
    }

    private void deleteDoctor() {
        viewDoctors();
        view.showMessage("Введіть ID лікаря для видалення:");
        int id = Integer.parseInt(scanner.nextLine());
        boolean removed = model.getDoctors().removeIf(d -> d.getId() == id);
        view.showMessage(removed ? "Лікаря видалено." : "Лікаря не знайдено.");
    }

    private void deletePatient() {
        viewPatients();
        view.showMessage("Введіть ID пацієнта для видалення:");
        int id = Integer.parseInt(scanner.nextLine());
        boolean removed = model.getPatients().removeIf(p -> p.getId() == id);
        view.showMessage(removed ? "Пацієнта видалено." : "Пацієнта не знайдено.");
    }

    private void deleteAppointment() {
        viewAppointments();
        view.showMessage("Введіть ID прийому для видалення:");
        int id = Integer.parseInt(scanner.nextLine());
        boolean removed = model.getAppointments().removeIf(a -> a.getId() == id);
        view.showMessage(removed ? "Прийом видалено." : "Прийом не знайдено.");
    }

    private void updateDoctor() {
        viewDoctors();
        view.showMessage("Введіть ID лікаря для оновлення:");
        int id = Integer.parseInt(scanner.nextLine());
        Doctor doctor = model.getDoctorById(id);
        if (doctor != null) {
            view.showMessage("Нове ім'я (залиш порожнім, якщо не змінюється):");
            String name = scanner.nextLine();
            view.showMessage("Нова спеціалізація (залиш порожнім, якщо не змінюється):");
            String spec = scanner.nextLine();
            if (!name.isEmpty()) doctor.setName(name);
            if (!spec.isEmpty()) doctor.setSpecialization(spec);
            view.showMessage("Дані лікаря оновлено.");
        } else {
            view.showMessage("Лікаря не знайдено.");
        }
    }

    private void updatePatient() {
        viewPatients();
        view.showMessage("Введіть ID пацієнта для оновлення:");
        int id = Integer.parseInt(scanner.nextLine());
        Patient patient = model.getPatientById(id);
        if (patient != null) {
            view.showMessage("Нове ім'я (залиш порожнім, якщо не змінюється):");
            String name = scanner.nextLine();
            view.showMessage("Новий діагноз (залиш порожнім, якщо не змінюється):");
            String diagnosis = scanner.nextLine();
            if (!name.isEmpty()) patient.setName(name);
            if (!diagnosis.isEmpty()) patient.setDiagnosis(diagnosis);
            view.showMessage("Дані пацієнта оновлено.");
        } else {
            view.showMessage("Пацієнта не знайдено.");
        }
    }

    private void updateAppointment() {
        viewAppointments();
        view.showMessage("Введіть ID прийому для оновлення:");
        int id = Integer.parseInt(scanner.nextLine());
        Appointment app = model.getAppointmentById(id);
        if (app != null) {
            view.showMessage("Нова дата (залиш порожнім, якщо не змінюється):");
            String date = scanner.nextLine();
            if (!date.isEmpty()) app.setDate(date);
            view.showMessage("Прийом оновлено.");
        } else {
            view.showMessage("Прийом не знайдено.");
        }
    }
}
