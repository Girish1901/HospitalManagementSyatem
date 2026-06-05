package main;

public class Appointment {
    private String appointmentId;
    private String patientId;
    private String doctorId;
    private String date;
    private String time;
    private String status;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Appointment(String appointmentId, String patientId, String doctorId, String date, String time) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date=date;
        this.time = time;
        this.status = "SCHEDULED";
    }
    public Appointment(String appointmentId, String patientId, String doctorId, String date, String time,String status) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date=date;
        this.time = time;
        this.status = status;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void displayAppointmentDetails() {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Appointment Details");
        System.out.println("Appointment id: " + this.appointmentId);
        System.out.println("Patient id: " + this.patientId);
        System.out.println("Doctor id: " + this.doctorId);
        System.out.println("Time: " + this.time);
        System.out.println("Status: " + this.status);
    }
}
