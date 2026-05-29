package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecord {
    private String recordId;
    private String patientId;
    private String doctorId;
    private String diagnosis;
    private String prescription;
    private LocalDate date;
    private List<String> symptoms;

    public MedicalRecord(String recordId, String patientId, String doctorId, String diagnosis, String prescription) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.date = LocalDate.now();
        this.symptoms = new ArrayList<>();
    }

    public String getRecordId() {
        return recordId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getPrescription() {
        return prescription;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<String> getSymptoms() {
        return symptoms;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public void addSymptom(String symptom) {
        symptoms.add(symptom);
    }

    public void display() {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("RecordId: " + this.recordId);
        System.out.println("PatientId: " + this.patientId);
        System.out.println("DoctorId: " + this.doctorId);
        System.out.println("Diagnosis: " + this.diagnosis);
        System.out.println("Prescription: " + this.prescription);
        System.out.println("Date: " + this.date);
        System.out.println("Symptoms: " + this.symptoms);
        System.out.println("------------------------------------------------------------------------");
    }
}
