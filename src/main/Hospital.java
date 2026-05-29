package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hospital {
    private static final Hospital Instance = new Hospital();
    private static final Random r = new Random();

    private String hospitalName;
    private String location;
    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<Staff> staffs;
    private List<Appointment> appointments;
    private List<Bill> bills;
    private List<MedicalRecord> medicalRecords;

    private Hospital() {
        this.hospitalName = "City Hospital";
        this.location = "Townsville";
        this.patients = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.staffs = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.bills = new ArrayList<>();
        this.medicalRecords = new ArrayList<>();
    }

    public static Hospital getInstance() {
        return Instance;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void registerPatient(int age, String gender, String phno, String email, String name, String patientId, String bloodGroup) {
        if (!searchByphno(phno)) {
            Patient p = new Patient(age, gender, phno, email, name, patientId, bloodGroup);
            patients.add(p);
            System.out.println("Patient added");
        } else {
            System.out.println("Patient already exists");
        }
    }

    public boolean searchByphno(String phno) {
        for (Patient p : patients) {
            if (p.getPhno().equals(phno)) {
                return true;
            }
        }
        return false;
    }

    public List<Patient> getAllPatients() {
        return patients;
    }

    public Patient findPatient(String patientId) {
        for (Patient p : patients) {
            if (p.getPatientId().equals(patientId)) {
                return p;
            }
        }
        return null;
    }

    public void addDoctor(int age, String gender, String phno, String email, String name, String doctorId, double consultationFee, String qualification, String specialization) {
        if (!findDoctorByemail(email)) {
            Doctor doctor = new Doctor(age, gender, phno, email, name, doctorId, consultationFee, qualification, specialization);
            doctors.add(doctor);
            System.out.println("Welcome on board doctor " + name);
        } else {
            System.out.println("Doctor exists");
        }
    }

    public Doctor findDoctorById(String id) {
        for (Doctor d : doctors) {
            if (d.getDoctorId().equals(id)) {
                return d;
            }
        }
        return null;
    }

    public boolean findDoctorByemail(String email) {
        for (Doctor d : doctors) {
            if (d.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void addStaff(int age, String gender, String phno, String email, String name, String staffId, String role, String department, double salary) {
        if (!findStaffByemail(email)) {
            Staff staff = new Staff(age, gender, phno, email, name, staffId, role, department, salary);
            staffs.add(staff);
            System.out.println("Welcome on board staff " + name);
        } else {
            System.out.println("Staff exists");
        }
    }

    private boolean findStaffByemail(String email) {
        for (Staff s : staffs) {
            if (s.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public List<Staff> getStaffs() {
        return staffs;
    }

    public Appointment bookAppointment(String patientId, String doctorId, String time) {
        Patient p = findPatient(patientId);
        if (p == null) {
            System.out.println("Patient not found");
            return null;
        }
        Doctor d = findDoctorById(doctorId);
        if (d == null) {
            System.out.println("Doctor not found");
            return null;
        }
        String a_id = String.valueOf(r.nextInt(Integer.MAX_VALUE));
        Appointment a = new Appointment(a_id, patientId, doctorId, time);
        d.addAppointment(a);
        appointments.add(a);
        System.out.println("Appointment booked");
        return a;
    }

    public String cancelAppointment(String id) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equals(id)) {
                appointment.setStatus("Canceled");
                System.out.println("Appointment canceled");
                return "Canceled";
            }
        }
        System.out.println("Appointment not found");
        return "";
    }

    public void addMedicalRecord(String patientId, String doctorId, String diagnosis, String prescription) {
        Patient p = findPatient(patientId);
        if (p == null) {
            System.out.println("Patient not found");
            return;
        }
        String recordId = String.valueOf(r.nextInt(Integer.MAX_VALUE));
        MedicalRecord m = new MedicalRecord(recordId, patientId, doctorId, diagnosis, prescription);
        medicalRecords.add(m);
        p.addMedicalRecord(m);
        System.out.println("Medical record added");
    }

    public void generateBill(String patientId, String doctorID, double medicine_fee, double room_fee) {
        Doctor d = findDoctorById(doctorID);
        double consult = d != null ? d.getConsultationFee() : 0.0;
        Bill b = new Bill(String.valueOf(r.nextInt(Integer.MAX_VALUE)), patientId, consult, medicine_fee, room_fee);
        bills.add(b);
        b.display();
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }
}
