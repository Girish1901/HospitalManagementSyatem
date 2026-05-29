package main;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Hospital hospital = Hospital.getInstance();

    public static void main(String[] args) {
        showWelcome();
        runMenu();
        System.out.println("Thank you for using the Hospital OOP App. Goodbye!");
    }

    private static void showWelcome() {
        System.out.println("========================================");
        System.out.println("  Hospital OOP Console App");
        System.out.println("========================================");
        System.out.println("Hospital: " + hospital.getHospitalName());
        System.out.println("Location: " + hospital.getLocation());
        System.out.println();
    }

    private static void runMenu() {
        while (true) {
            System.out.println();
            System.out.println("1. Add doctor");
            System.out.println("2. Add staff");
            System.out.println("3. Register patient");
            System.out.println("4. Book appointment");
            System.out.println("5. Add medical record");
            System.out.println("6. Generate bill");
            System.out.println("7. Show all records");
            System.out.println("8. view all patients details");
            System.out.println("9. view all doctor details");
            System.out.println("10. exit");
            System.out.print("Choose an option: ");

            int option = readInt();
            System.out.println();

            switch (option) {
                case 1 -> addDoctor();
                case 2 -> addStaff();
                case 3 -> registerPatient();
                case 4 -> bookAppointment();
                case 5 -> addMedicalRecord();
                case 6 -> generateBill();
                case 7 -> showAllRecords();
                case 8 -> patientdeatils();
                case 9 -> doctordetails();
                case 10 -> {
                    return;
                }
                default -> System.out.println("Invalid option. Please choose 1-8.");
            }
        }
    }

    private static void addDoctor() {
        System.out.println("-- Add Doctor --");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = readInt();
        System.out.print("Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Doctor ID: ");
        String doctorId = scanner.nextLine();
        System.out.print("Consultation fee: ");
        double fee = readDouble();
        System.out.print("Qualification: ");
        String qualification = scanner.nextLine();
        System.out.print("Specialization: ");
        String specialization = scanner.nextLine();

        hospital.addDoctor(age, gender, phone, email, name, doctorId, fee, qualification, specialization);
    }

    private static void addStaff() {
        System.out.println("-- Add Staff --");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = readInt();
        System.out.print("Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Staff ID: ");
        String staffId = scanner.nextLine();
        System.out.print("Role: ");
        String role = scanner.nextLine();
        System.out.print("Department: ");
        String department = scanner.nextLine();
        System.out.print("Salary: ");
        double salary = readDouble();

        hospital.addStaff(age, gender, phone, email, name, staffId, role, department, salary);
    }

    private static void registerPatient() {
        System.out.println("-- Register Patient --");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = readInt();
        System.out.print("Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Patient ID: ");
        String patientId = scanner.nextLine();
        System.out.print("Blood group: ");
        String bloodGroup = scanner.nextLine();

        hospital.registerPatient(age, gender, phone, email, name, patientId, bloodGroup);
    }

    private static void bookAppointment() {
        System.out.println("-- Book Appointment --");
        System.out.print("Patient ID: ");
        String patientId = scanner.nextLine();
        System.out.print("Doctor ID: ");
        String doctorId = scanner.nextLine();
        System.out.print("Date/time: ");
        String time = scanner.nextLine();

        Appointment appointment = hospital.bookAppointment(patientId, doctorId, time);
        if (appointment != null) {
            System.out.println("Appointment booked successfully.");
            appointment.displayAppointmentDetails();
        } else {
            System.out.println("Failed to book appointment. Check patient or doctor IDs.");
        }
    }

    private static void addMedicalRecord() {
        System.out.println("-- Add Medical Record --");
        System.out.print("Patient ID: ");
        String patientId = scanner.nextLine();
        System.out.print("Doctor ID: ");
        String doctorId = scanner.nextLine();
        System.out.print("Diagnosis: ");
        String diagnosis = scanner.nextLine();
        System.out.print("Prescription: ");
        String prescription = scanner.nextLine();

        hospital.addMedicalRecord(patientId, doctorId, diagnosis, prescription);
    }

    private static void generateBill() {
        System.out.println("-- Generate Bill --");
        System.out.print("Patient ID: ");
        String patientId = scanner.nextLine();
        System.out.print("Doctor ID: ");
        String doctorId = scanner.nextLine();
        System.out.print("Medicine fee: ");
        double medicineFee = readDouble();
        System.out.print("Room fee: ");
        double roomFee = readDouble();

        hospital.generateBill(patientId, doctorId, medicineFee, roomFee);
    }

    private static void showAllRecords() {
        System.out.println("-- All Patients --");
        for (Patient p : hospital.getAllPatients()) {
            p.displayInfo();
        }


        System.out.println("-- All Doctors --");
        for (Doctor d : hospital.getDoctors()) {
            d.displayInfo();
        }

        System.out.println("-- All Staff --");
        for (Staff s : hospital.getStaffs()) {
            s.displayInfo();
        }

        System.out.println("-- All Appointments --");
        for (Appointment a : hospital.getAppointments()) {
            a.displayAppointmentDetails();
        }

        System.out.println("-- All Bills --");
        for (Bill b : hospital.getBills()) {
            b.display();
        }

        System.out.println("-- All Medical Records --");
        for (MedicalRecord r : hospital.getMedicalRecords()) {
            r.display();
        }
    }
    private static void patientdeatils(){
        System.out.println("-- All Patients --");
        for (Patient p : hospital.getAllPatients()) {
            p.displayInfo();
        }
    }
    private static void  doctordetails(){
        System.out.println("-- All Patients --");
        for (Doctor d : hospital.getDoctors()) {
            d.displayInfo();
        }
    }

    private static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    private static double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid decimal value: ");
            }
        }
    }
}
