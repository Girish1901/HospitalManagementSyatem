package main.DAO;
import main.Appointment;
import main.Patient;
import utility.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDao {
    private Connection con;
    public AppointmentDao()throws SQLException{
        con = DbConnection.getInstance().getConnection();

    }
    public void addAppointment(Appointment ap) throws SQLException {
        String query="INSERT INTO APPOINTMENTS VALUES(?,?,?,?,?,?)";
        PreparedStatement rs=con.prepareStatement(query);
        rs.setString(1,ap.getAppointmentId());
        rs.setString(2,ap.getPatientId());
        rs.setString(3,ap.getDoctorId());
        rs.setString(4,ap.getDate());
        rs.setString(5,ap.getTime());
        rs.setString(6,"Scheduled");
        rs.executeUpdate();
        System.out.println("Appoint Bokked on "+ap.getDate()+ap.getTime()+" with doctor "+ap.getDoctorId());

    }
    public Appointment getAppointments(String appointmentrId) throws SQLException {
        String query="SELECT * FROM APPOINTMENTS WHERE APPOINTMNETID=?";
        PreparedStatement p=con.prepareStatement(query);
        p.setString(1,appointmentrId);
        ResultSet rs=p.executeQuery();
        while (rs.next()){
            return new Appointment(
                    rs.getString("appointmentId"),
                    rs.getString("doctorId"),
                    rs.getString("patientId"),
                    rs.getString("Date"),
                    rs.getString("time"),
                    rs.getString("status"));
        }
        return null;
    }
    public List<Appointment> getAppointmentsByPatients(String Patientid) throws SQLException {
        String query="SELECT * FROM APPOINTMENTS WHERE PATIENTID=?";
        PreparedStatement p=con.prepareStatement(query);
        List<Appointment> patientsAppointments=new ArrayList<>();
        p.setString(1,Patientid);
        ResultSet rs=p.executeQuery();
        while (rs.next()){
            patientsAppointments.add(new Appointment(
                    rs.getString("appointmentId"),
                    rs.getString("doctorId"),
                    rs.getString("patientId"),
                    rs.getString("Date"),
                    rs.getString("time"),
                    rs.getString("status")));
        }
        return patientsAppointments;
    }
    public List<Appointment> getAppointmentsByDoctorId(String doctorId) throws SQLException {
        String query="SELECT * FROM APPOINTMENTS WHERE DOCTORID=?";
        PreparedStatement p=con.prepareStatement(query);
        List<Appointment> doctorAppointments=new ArrayList<>();
        p.setString(1,doctorId);
        ResultSet rs=p.executeQuery();
        while (rs.next()){
            doctorAppointments.add(new Appointment(
                    rs.getString("appointmentId"),
                    rs.getString("doctorId"),
                    rs.getString("patientId"),
                    rs.getString("Date"),
                    rs.getString("time"),
                    rs.getString("status")));
        }
        return doctorAppointments;
    }
    public void updateStatus(String appointmentId,String status) throws SQLException {
        String query="UPDATE APPOINTMENTS SET STATUS=? WHERE APPOINTMNETID=?";
        PreparedStatement p=con.prepareStatement(query);
        p.setString(1,appointmentId);
        p.setString(2,status);
         int rs=p.executeUpdate();
        if (rs != 0) {
            System.out.println("Update sucsses");
        } else {
            System.out.println("Update failed");
        }

    }
    public void cancelAppointment(String appointmentId) throws SQLException {
        String query="UPDATE APPOINTMENTS SET STATUS=CANCELED WHERE APPOINTMNETID=?";
        PreparedStatement p=con.prepareStatement(query);
        p.setString(1,appointmentId);
        int rs=p.executeUpdate();
        if (rs != 0) {
            System.out.println("Appintment Canceled sucsses");
        }else{
            System.out.println("No appointment Exists");
        }

    }

    public List<Appointment> getAllAppointments() throws SQLException {
        String query="SELECT * FROM APPOINTMENTS";
        PreparedStatement p=con.prepareStatement(query);
        List<Appointment> allAppointments=new ArrayList<>();
        ResultSet rs=p.executeQuery();
        while (rs.next()){
            allAppointments.add(new Appointment(
                    rs.getString("appointmentId"),
                    rs.getString("doctorId"),
                    rs.getString("patientId"),
                    rs.getString("Date"),
                    rs.getString("time"),
                    rs.getString("status")));
        }
        return allAppointments;
    }

}
