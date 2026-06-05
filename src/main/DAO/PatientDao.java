package main.DAO;
import main.Patient;
import org.mindrot.jbcrypt.BCrypt;
import utility.DbConnection;
import utility.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDao {
    private Connection con;

    public PatientDao() throws SQLException {
        con=DbConnection.getInstance().getConnection();
    }

    public void addPatient(Patient p) throws SQLException {
        String query = "INSERT INTO PATIENTS (patientId, name, email, phone, gender, age, bloodGroup, password) VALUES(?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, p.getPatientId());
            ps.setString(2, p.getName());
            ps.setString(3, p.getEmail());
            ps.setString(4, p.getPhno());
            ps.setString(5, p.getGender());
            ps.setInt(6, p.getAge());
            ps.setString(7, p.getBloodGroup());
            ps.setString(8, BCrypt.hashpw(p.getPassword(),BCrypt.gensalt()));
            ps.executeUpdate();
        }
    }
    public Patient getPatient(String patientId) throws SQLException {
        String query="SELECT * FROM PATIENTS WHERE PATIENTID=?";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setString(1,patientId);
        ResultSet rs=ps.executeQuery();
        while (rs.next()){
            Patient p=new Patient(
                    rs.getString("patientId"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("gender"),
                    rs.getInt("age"),
                    rs.getString("bloodGroup"),
                    "");
            return p;
        }
        return null;
    }
    public List<Patient>getAllPatients() throws SQLException {
        String query="SELECT * FROM PATIENTS";
        PreparedStatement p=con.prepareStatement(query);
        List<Patient> patients=new ArrayList<>();
        ResultSet rs=p.executeQuery();
        while (rs.next()){
            patients.add(new Patient(
                    rs.getString("patientId"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("gender"),
                    rs.getInt("age"),
                    rs.getString("bloodGroup"),
                    ""));
        }
        return patients;
    }
    public void updatePatients(Patient p)throws SQLException{
        String query="UPDATE PATIENTS SET NAME=?,EMAIL=?,PHONE=?,GENDER=?,AGE=? WHERE PATIENTID=?";
        Patient a=getPatient(p.getPatientId());
        try (PreparedStatement ps=con.prepareStatement(query)) {
            ps.setString(1,p.getName()!=""?p.getName():a.getName());
            ps.setString(2,p.getEmail()!=""?p.getEmail():a.getEmail());
            ps.setString(3,p.getPhno()!=""?p.getPhno():a.getPhno());
            ps.setString(4,p.getGender()!=""?p.getGender():a.getGender());
            ps.setInt(5,p.getAge()>0?p.getAge():a.getAge());
            ps.setString(6,p.getPatientId());
            int updated = ps.executeUpdate();
            if (updated > 0) {
                System.out.println("Patient updated successfully.");
            } else {
                System.out.println("No patient was updated.");
            }
        }
    }
    public void deletPatient(String patientId) throws SQLException {
        String query="DELETE FROM PATIENTS WHERE PATIENTID=?";
        try (PreparedStatement ps=con.prepareStatement(query)) {
            ps.setString(1,patientId);
            int deleted = ps.executeUpdate();
            if (deleted > 0) {
                System.out.println("Patient deleted successfully.");
            } else {
                System.out.println("No patient found with id " + patientId);
            }
        }
    }

    public boolean patientLogin(String patientId,String password)throws SQLException{
        String query="SELECT PASSWORD FROM PATIENTS WHERE PATIENTID=?";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setString(1,patientId);
        ResultSet rs=ps.executeQuery();
        if (rs.next()) {
            String storedPassword = rs.getString("PASSWORD");
            if (PasswordUtil.verifyPassword(password, storedPassword)) {
                if (!PasswordUtil.isHashed(storedPassword)) {
                    updatePatientPasswordHash(patientId, password);
                }
                return true;
            }
        }
        return false;
    }

    private void updatePatientPasswordHash(String patientId, String password) throws SQLException {
        String updateQuery = "UPDATE PATIENTS SET PASSWORD=? WHERE PATIENTID=?";
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setString(1, PasswordUtil.hashPassword(password));
            ps.setString(2, patientId);
            ps.executeUpdate();
        }
    }

}
