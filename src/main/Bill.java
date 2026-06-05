package main;

import utility.Billable;

import java.time.LocalDate;

public class Bill implements Billable {
    private String billId;
    private String patientId;
    private double consultationFee;
    private double medicineFee;
    private double roomFee;
    private String status;
    private double totalAmount;
    private LocalDate date;

    public Bill(String billId, String patientId, double consultationFee, double medicineFee, double roomFee) {
        this.billId = billId;
        this.patientId = patientId;
        this.consultationFee = consultationFee;
        this.medicineFee = medicineFee;
        this.roomFee = roomFee;
        this.status = "UNPAID";
        this.totalAmount = calculateTotal(consultationFee, medicineFee, roomFee);
        this.date = LocalDate.now();
    }
    public Bill(String billId, String patientId, double consultationFee, double medicineFee, double roomFee, String status,double totalAmount,LocalDate date) {
        this.billId = billId;
        this.patientId = patientId;
        this.consultationFee = consultationFee;
        this.medicineFee = medicineFee;
        this.roomFee = roomFee;
        this.status = status;
        this.totalAmount = totalAmount;
        this.date = date;
    }


    public String getBillId() {
        return billId;
    }

    public String getPatientId() {
        return patientId;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public double getMedicineFee() {
        return medicineFee;
    }

    public double getRoomFee() {
        return roomFee;
    }

    public String getStatus() {
        return status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public LocalDate getDate() {
        return date;
    }

    public double calculateTotal(double consultationFee, double medicineFee, double roomFee) {
        return consultationFee + medicineFee + roomFee;
    }

    @Override
    public void display() {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Bill id: " + getBillId());
        System.out.println("Patient id: " + getPatientId());
        System.out.println("Consultation fee: " + getConsultationFee());
        System.out.println("Room fee: " + getRoomFee());
        System.out.println("Medicine fee: " + getMedicineFee());
        System.out.println("Amount: " + getTotalAmount());
        System.out.println("Status: " + getStatus());
        System.out.println("Date: " + getDate());
    }
}
