package main.DAO;
import main.Appointment;
import main.MedicalRecord;
import utility.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDao {
    private Connection con;
    public MedicalRecordDao()throws SQLException {
        con=DbConnection.getInstance().getConnection();
    }
    public void addMedicalRecord(MedicalRecord mr) throws SQLException {
        String query="INSERT INTO APPOINTMENTS VALUES(?,?,?,?,?,?,?)";
        PreparedStatement rs=con.prepareStatement(query);
        rs.setString(1,mr.getRecordId());
        rs.setString(2,mr.getPatientId());
        rs.setString(3,mr.getDoctorId());
        rs.setString(4,mr.getDiagnosis());
        rs.setString(5,mr.getPrescription());
        rs.setString(6,new String(String.valueOf(mr.getSymptoms())));
        rs.setDate(7, Date.valueOf(mr.getDate()));

    }
    public MedicalRecord getMedicalrecordById(String rocordId) throws SQLException {
        String query="SELECT * FROM MEDICALRECORD WHERE RECORDID=?";
        PreparedStatement p=con.prepareStatement(query);
        p.setString(1,rocordId);
        ResultSet rs=p.executeQuery();
        while (rs.next()){
            return new MedicalRecord(
                    rs.getString("RECORDID"),
                    rs.getString("patientId"),
                    rs.getString("doctorId"),
                    rs.getString("diagnosis"),
                    rs.getString("prescription"),
                    rs.getString("symptoms"),
                    rs.getDate("Date"));
        }
        return null;
    }
    public List<MedicalRecord> getAllMedicalRecordsByPatient(String Patientid) throws SQLException {
        String query="SELECT * FROM MEDICALRECORD WHERE PATIENTID=?";
        PreparedStatement p=con.prepareStatement(query);
        List<MedicalRecord> allMedicalRecordsByPatient=new ArrayList<>();
        p.setString(1,Patientid);
        ResultSet rs=p.executeQuery();
        while (rs.next()){
            allMedicalRecordsByPatient.add(new MedicalRecord(
                    rs.getString("RECORDID"),
                    rs.getString("patientId"),
                    rs.getString("doctorId"),
                    rs.getString("diagnosis"),
                    rs.getString("prescription"),
                    rs.getString("symptoms"),
                    rs.getDate("Date")));
        }
        return allMedicalRecordsByPatient;
    }
    public List<MedicalRecord> getAllMedicalRecordsByDoctor(String doctorId) throws SQLException {
        String query="SELECT * FROM MEDICALRECORD WHERE DOCTORID=?";
        PreparedStatement p=con.prepareStatement(query);
        List<MedicalRecord> allMedicalRecordsByDoctor=new ArrayList<>();
        p.setString(1,doctorId);
        ResultSet rs=p.executeQuery();
        while (rs.next()){
            allMedicalRecordsByDoctor.add(new MedicalRecord(
                    rs.getString("RECORDID"),
                    rs.getString("patientId"),
                    rs.getString("doctorId"),
                    rs.getString("diagnosis"),
                    rs.getString("prescription"),
                    rs.getString("symptoms"),
                    rs.getDate("Date")));
        }
        return allMedicalRecordsByDoctor;
    }
    public void updateRecord(String recordId,String diagnosis,String prescription,String symptomd) throws SQLException {
        String query="UPDATE MEDICALRECORDS SET DIAGNOSIS=?,PRESCRIPTION=?,SYMPTOMS=? WHERE RECORDID=?";
        PreparedStatement p=con.prepareStatement(query);
        p.setString(1,diagnosis);
        p.setString(2,prescription);
        p.setString(3,symptomd);
        p.setString(4,recordId);
        int rs=p.executeUpdate();
        if (rs != 0) {
            System.out.println("Update sucsses");
        } else {
            System.out.println("Update failed");
        }

    }
}
