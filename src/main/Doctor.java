package main;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person {
    private String doctorId;
    private String specialization;
    private String qualification;
    private double consultationFee;
    private String password;
    private List<Appointment> appointments = new ArrayList<>();

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Doctor(String doctorId, String name, String email, String phno, String gender, int age, String qualification, String specialization, double consultationFee, String password) {
        super(age, gender, phno, email, name);
        this.doctorId = doctorId;
        this.password=password;
        this.consultationFee = consultationFee;
        this.qualification = qualification;
        this.specialization = specialization;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void addAppointment(Appointment ap) {
        this.appointments.add(ap);
    }

    @Override
    public void displayInfo() {
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("Doctor Id " + this.doctorId);
        System.out.println("Doctor " + this.getName());
        System.out.println("Specialization " + this.specialization);
        System.out.println("Qualification " + this.qualification);
        System.out.println("Consultation Fee " + this.consultationFee);
        System.out.println("----------------------------------------------------------------------------------");
    }
}
