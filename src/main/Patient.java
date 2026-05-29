package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {
    private String patientId;
    private String bloodGroup;
    private List<MedicalRecord> medicalHistory;
    private List<Appointment> appointments;

    public Patient(int age, String gender, String phno, String email, String name, String patientId, String bloodGroup) {
        super(age, gender, phno, email, name);
        this.patientId = patientId;
        this.bloodGroup = bloodGroup;
        this.medicalHistory = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }

    public String getPatientId() {
        return patientId;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void addAppointment(Appointment a) {
        this.appointments.add(a);
    }

    public void addMedicalRecord(MedicalRecord record) {
        this.medicalHistory.add(record);
    }

    public void viewMedicalHistory() {
        for (MedicalRecord medicalRecord : medicalHistory) {
            medicalRecord.display();
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("--------------------------------------------------------");
        System.out.println("PatientId: " + this.patientId);
        System.out.println("Patient Name: " + this.getName());
        System.out.println("Gender: " + this.getGender());
        System.out.println("Age: " + this.getAge());
        System.out.println("Phone: " + this.getPhno());
        System.out.println("Email: " + this.getEmail());
        System.out.println("Blood Group: " + this.bloodGroup);
        System.out.println("--------------------------------------------------------");
    }
}
