package main;

import main.DAO.AdminDao;
import main.DAO.AppointmentDao;
import main.DAO.BillDao;
import main.DAO.DoctorDao;
import main.DAO.MedicalRecordDao;
import main.DAO.PatientDao;
import main.DAO.StaffDao;
import org.mindrot.jbcrypt.BCrypt;
import utility.DbConnection;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        showMainMenu();
    }

    public static void showMainMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        AdminDao adminDao = new AdminDao();
        PatientDao patientDao = new PatientDao();
        DoctorDao doctorDao = new DoctorDao();
        StaffDao staffDao = new StaffDao();
        AppointmentDao appointmentDao = new AppointmentDao();
        MedicalRecordDao medicalRecordDao = new MedicalRecordDao();
        BillDao billDao = new BillDao();
        while (true) {
            System.out.println("\n==================== Hospital Management System ====================");
            System.out.println("1 → Admin Login");
            System.out.println("2 → Patient Login");
            System.out.println("3 → Doctor Login");
            System.out.println("4 → Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> handleAdminLogin(scanner, adminDao, patientDao, doctorDao, staffDao, appointmentDao, medicalRecordDao, billDao);
                case "2" -> handlePatientLogin(scanner, patientDao, medicalRecordDao, appointmentDao, billDao);
                case "3" -> handleDoctorLogin(scanner, doctorDao, medicalRecordDao, appointmentDao);
                case "4" -> {
                    System.out.println("Exiting application.");
                    return;
                }
                default -> System.out.println("Invalid option. Please choose 1-4.");
            }
        }
    }

    private static void handleAdminLogin(Scanner scanner,
                                         AdminDao adminDao,
                                         PatientDao patientDao,
                                         DoctorDao doctorDao,
                                         StaffDao staffDao,
                                         AppointmentDao appointmentDao,
                                         MedicalRecordDao medicalRecordDao,
                                         BillDao billDao) throws SQLException {
        System.out.print("Enter admin id: ");
        String adminId = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        Admin admin = adminDao.getAdmin(adminId);
        if (admin == null) {
            System.out.println("Admin not found");
            return;
        }

        boolean valid = adminDao.adminLogin(adminId, password);
        if (valid) {
            System.out.println("Admin login successful.");
            handleAdminMenu(scanner, admin, patientDao, doctorDao, staffDao, appointmentDao, medicalRecordDao, billDao);
        } else {

            System.out.println("Wrong password");
        }
    }
    private static void createpatient(Scanner scanner,PatientDao patientDao) throws SQLException {
        String id=scanner.nextLine();
        String n=scanner.nextLine();
        String email=scanner.nextLine();
        String phno=scanner.nextLine();
        String gender=scanner.nextLine();
        int age=scanner.nextInt();
        String blood=scanner.nextLine();
        String paa=scanner.nextLine();
        Patient p=new Patient(id,n,email,phno,gender,age,blood,paa);
        patientDao.addPatient(p);

    }
    private static void handlePatientLogin(Scanner scanner,
                                           PatientDao patientDao,
                                           MedicalRecordDao medicalRecordDao,
                                           AppointmentDao appointmentDao,
                                           BillDao billDao) throws SQLException {
        System.out.print("Enter patient id: ");
        String patientId = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Patient patient = patientDao.getPatient(patientId);
        if (patient == null) {
            System.out.println("Patient not found");
            return;
        }

        boolean valid = patientDao.patientLogin(patientId, password);
        if (valid) {
            System.out.println("Patient login successful.");
            handlePatientMenu(scanner, patient, medicalRecordDao, appointmentDao, billDao);
        } else {
            System.out.println("Wrong password");
        }
    }

    private static void handleDoctorLogin(Scanner scanner,
                                          DoctorDao doctorDao,
                                          MedicalRecordDao medicalRecordDao,
                                          AppointmentDao appointmentDao) throws SQLException {
        System.out.print("Enter doctor id: ");
        String doctorId = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        Doctor doctor = doctorDao.getDoctor(doctorId);
        if (doctor == null) {
            System.out.println("Doctor not found");
            return;
        }

        boolean valid = doctorDao.doctorLogin(doctorId, password);
        if (valid) {
            System.out.println("Doctor login successful.");
            handleDoctorMenu(scanner, doctor, medicalRecordDao, appointmentDao);
        } else {
            System.out.println("Wrong password");
        }
    }

    private static void handleAdminMenu(Scanner scanner,
                                        Admin admin,
                                        PatientDao patientDao,
                                        DoctorDao doctorDao,
                                        StaffDao staffDao,
                                        AppointmentDao appointmentDao,
                                        MedicalRecordDao medicalRecordDao,
                                        BillDao billDao) throws SQLException {
        while (true) {
            System.out.println("\n-------------------- Admin Menu --------------------");
            System.out.println("1  → Add Patient");
            System.out.println("2  → Add Doctor");
            System.out.println("3  → Add Staff");
            System.out.println("4  → List Patients");
            System.out.println("5  → List Doctors");
            System.out.println("6  → List Staff");
            System.out.println("7  → Add Appointment");
            System.out.println("8  → Add Medical Record");
            System.out.println("9  → Add Bill");
            System.out.println("10 → Pay Bill");
            System.out.println("11 → List Bills");
            System.out.println("12 → List Appointments");
            System.out.println("13 → Delete Patient");
            System.out.println("14 → Delete Doctor");
            System.out.println("15 → Logout");
            System.out.print("Choose an option: ");
            String option = scanner.nextLine().trim();

            switch (option) {
                case "1" -> addPatient(scanner, patientDao);
                case "2" -> addDoctor(scanner, doctorDao);
                case "3" -> addStaff(scanner, staffDao);
                case "4" -> listPatients(patientDao.getAllPatients());
                case "5" -> listDoctors(doctorDao.getAllDoctors());
                case "6" -> listStaff(staffDao.getAllStaff());
                case "7" -> addAppointment(scanner, appointmentDao);
                case "8" -> addMedicalRecord(scanner, medicalRecordDao);
                case "9" -> addBill(scanner, billDao, doctorDao);
                case "10" -> payBill(scanner, billDao);
                case "11" -> listBills(billDao.getAllBills());
                case "12" -> listAppointments(appointmentDao.getAllAppointments());
                case "13" -> deletePatient(scanner, patientDao);
                case "14" -> deleteDoctor(scanner, doctorDao);
                case "15" -> {
                    System.out.println("Admin logged out.");
                    return;
                }
                default -> System.out.println("Invalid option. Please choose 1-15.");
            }
        }
    }

    private static void handlePatientMenu(Scanner scanner,
                                          Patient patient,
                                          MedicalRecordDao medicalRecordDao,
                                          AppointmentDao appointmentDao,
                                          BillDao billDao) throws SQLException {
        while (true) {
            System.out.println("\n-------------------- Patient Menu --------------------");
            System.out.println("1 → View Appointments");
            System.out.println("2 → View Medical Records");
            System.out.println("3 → View Bills");
            System.out.println("4 → Pay Bill");
            System.out.println("5 → Logout");
            System.out.print("Choose an option: ");
            String option = scanner.nextLine().trim();

            switch (option) {
                case "1" -> listAppointments(appointmentDao.getAppointmentsByPatients(patient.getPatientId()));
                case "2" -> listMedicalRecords(medicalRecordDao.getAllMedicalRecordsByPatient(patient.getPatientId()));
                case "3" -> listBills(billDao.getAllBillsByPatient(patient.getPatientId()));
                case "4" -> payBill(scanner, billDao);
                case "5" -> {
                    System.out.println("Patient logged out.");
                    return;
                }
                default -> System.out.println("Invalid option. Please choose 1-5.");
            }
        }
    }

    private static void handleDoctorMenu(Scanner scanner,
                                         Doctor doctor,
                                         MedicalRecordDao medicalRecordDao,
                                         AppointmentDao appointmentDao) throws SQLException {
        while (true) {
            System.out.println("\n-------------------- Doctor Menu --------------------");
            System.out.println("1 → View Appointments");
            System.out.println("2 → Add Medical Record");
            System.out.println("3 → View Patient Medical Records");
            System.out.println("4 → Logout");
            System.out.print("Choose an option: ");
            String option = scanner.nextLine().trim();

            switch (option) {
                case "1" -> listAppointments(appointmentDao.getAppointmentsByDoctorId(doctor.getDoctorId()));
                case "2" -> addMedicalRecordForDoctor(scanner, doctor, medicalRecordDao);
                case "3" -> {
                    System.out.print("Enter patient id: ");
                    String patientId = scanner.nextLine().trim();
                    listMedicalRecords(medicalRecordDao.getAllMedicalRecordsByPatient(patientId));
                }
                case "4" -> {
                    System.out.println("Doctor logged out.");
                    return;
                }
                default -> System.out.println("Invalid option. Please choose 1-4.");
            }
        }
    }

    private static void addPatient(Scanner scanner, PatientDao patientDao) throws SQLException {
        System.out.print("Patient id: ");
        String patientId = scanner.nextLine().trim();
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Phone: ");
        String phone = scanner.nextLine().trim();
        System.out.print("Gender: ");
        String gender = scanner.nextLine().trim();
        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Blood group: ");
        String bloodGroup = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        Patient patient = new Patient(patientId, name, email, phone, gender, age, bloodGroup, password);
        patientDao.addPatient(patient);
        System.out.println("Patient added successfully.");
    }

    private static void addDoctor(Scanner scanner, DoctorDao doctorDao) throws SQLException {
        System.out.print("Doctor id: ");
        String doctorId = scanner.nextLine().trim();
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Phone: ");
        String phone = scanner.nextLine().trim();
        System.out.print("Gender: ");
        String gender = scanner.nextLine().trim();
        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Qualification: ");
        String qualification = scanner.nextLine().trim();
        System.out.print("Specialization: ");
        String specialization = scanner.nextLine().trim();
        System.out.print("Consultation fee: ");
        double consultationFee = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        Doctor doctor = new Doctor(doctorId, name, email, phone, gender, age, qualification, specialization, consultationFee, password);
        doctorDao.addDoctor(doctor);
        System.out.println("Doctor added successfully.");
    }

    private static void addStaff(Scanner scanner, StaffDao staffDao) throws SQLException {
        System.out.print("Staff id: ");
        String staffId = scanner.nextLine().trim();
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Phone: ");
        String phone = scanner.nextLine().trim();
        System.out.print("Gender: ");
        String gender = scanner.nextLine().trim();
        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Role: ");
        String role = scanner.nextLine().trim();
        System.out.print("Department: ");
        String department = scanner.nextLine().trim();
        System.out.print("Salary: ");
        double salary = Double.parseDouble(scanner.nextLine().trim());

        Staff staff = new Staff(staffId, name, email, phone, gender, age, role, department, salary);
        staffDao.addStaff(staff);
        System.out.println("Staff added successfully.");
    }

    private static void addAppointment(Scanner scanner, AppointmentDao appointmentDao) throws SQLException {
        System.out.print("Patient id: ");
        String patientId = scanner.nextLine().trim();
        System.out.print("Doctor id: ");
        String doctorId = scanner.nextLine().trim();
        System.out.print("Date (yyyy-MM-dd): ");
        String date = scanner.nextLine().trim();
        System.out.print("Time (HH:mm): ");
        String time = scanner.nextLine().trim();
        String appointmentId = "A" + System.currentTimeMillis();
        Appointment appointment = new Appointment(appointmentId, patientId, doctorId, date, time);
        appointmentDao.addAppointment(appointment);
        System.out.println("Appointment created successfully.");
    }

    private static void addMedicalRecord(Scanner scanner, MedicalRecordDao medicalRecordDao) throws SQLException {
        System.out.print("Patient id: ");
        String patientId = scanner.nextLine().trim();
        System.out.print("Doctor id: ");
        String doctorId = scanner.nextLine().trim();
        System.out.print("Diagnosis: ");
        String diagnosis = scanner.nextLine().trim();
        System.out.print("Prescription: ");
        String prescription = scanner.nextLine().trim();
        System.out.print("Symptoms: ");
        String symptoms = scanner.nextLine().trim();
        String recordId = "R" + System.currentTimeMillis();
        MedicalRecord record = new MedicalRecord(recordId, patientId, doctorId, diagnosis, prescription, "", java.util.Date.from(LocalDate.now().atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()));
        record.addSymptom(symptoms);
        medicalRecordDao.addMedicalRecord(record);
        System.out.println("Medical record added successfully.");
    }

    private static void addMedicalRecordForDoctor(Scanner scanner, Doctor doctor, MedicalRecordDao medicalRecordDao) throws SQLException {
        System.out.print("Patient id: ");
        String patientId = scanner.nextLine().trim();
        System.out.print("Diagnosis: ");
        String diagnosis = scanner.nextLine().trim();
        System.out.print("Prescription: ");
        String prescription = scanner.nextLine().trim();
        System.out.print("Symptoms: ");
        String symptoms = scanner.nextLine().trim();
        String recordId = "R" + System.currentTimeMillis();
        MedicalRecord record = new MedicalRecord(recordId, patientId, doctor.getDoctorId(), diagnosis, prescription, "", java.util.Date.from(LocalDate.now().atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()));
        record.addSymptom(symptoms);
        medicalRecordDao.addMedicalRecord(record);
        System.out.println("Medical record added successfully.");
    }

    private static void addBill(Scanner scanner, BillDao billDao, DoctorDao doctorDao) throws SQLException {
        System.out.print("Patient id: ");
        String patientId = scanner.nextLine().trim();
        System.out.print("Doctor id: ");
        String doctorId = scanner.nextLine().trim();
        System.out.print("Medicine fee: ");
        double medicineFee = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Room fee: ");
        double roomFee = Double.parseDouble(scanner.nextLine().trim());
        Doctor doctor = doctorDao.getDoctor(doctorId);
        if (doctor == null) {
            System.out.println("Doctor not found");
            return;
        }
        String billId = "B" + System.currentTimeMillis();
        Bill bill = new Bill(billId, patientId, doctor.getConsultationFee(), medicineFee, roomFee);
        billDao.addBill(bill);
        System.out.println("Bill created successfully.");
    }

    private static void payBill(Scanner scanner, BillDao billDao) throws SQLException {
        System.out.print("Bill id: ");
        String billId = scanner.nextLine().trim();
        billDao.payBill(billId);
    }

    private static void deletePatient(Scanner scanner, PatientDao patientDao) throws SQLException {
        System.out.print("Patient id: ");
        String patientId = scanner.nextLine().trim();
        patientDao.deletPatient(patientId);
        System.out.println("Patient delete requested.");
    }

    private static void deleteDoctor(Scanner scanner, DoctorDao doctorDao) throws SQLException {
        System.out.print("Doctor id: ");
        String doctorId = scanner.nextLine().trim();
        doctorDao.deleteDoctor(doctorId);
        System.out.println("Doctor delete requested.");
    }

    private static void listPatients(List<Patient> patients) {
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }
        for (Patient p : patients) {
            p.displayInfo();
        }
    }

    private static void listDoctors(List<Doctor> doctors) {
        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
            return;
        }
        for (Doctor d : doctors) {
            d.displayInfo();
        }
    }

    private static void listStaff(List<Staff> staffList) {
        if (staffList.isEmpty()) {
            System.out.println("No staff found.");
            return;
        }
        for (Staff s : staffList) {
            s.displayInfo();
        }
    }

    private static void listAppointments(List<Appointment> appointments) {
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }
        for (Appointment a : appointments) {
            a.displayAppointmentDetails();
        }
    }

    private static void listMedicalRecords(List<MedicalRecord> records) {
        if (records.isEmpty()) {
            System.out.println("No medical records found.");
            return;
        }
        for (MedicalRecord r : records) {
            r.display();
        }
    }

    private static void listBills(List<Bill> bills) {
        if (bills.isEmpty()) {
            System.out.println("No bills found.");
            return;
        }
        for (Bill b : bills) {
            b.display();
        }
    }
}
