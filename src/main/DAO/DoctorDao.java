package main.DAO;
import main.Doctor;
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
public class DoctorDao {
    private Connection con;

    public DoctorDao() throws SQLException {
        con = DbConnection.getInstance().getConnection();

    }

    public void addDoctor(Doctor d) throws SQLException {
        String query = "INSERT INTO DOCTORS VALUES(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, d.getDoctorId());
        ps.setString(2, d.getName());
        ps.setString(3, d.getEmail());
        ps.setString(4, d.getPhno());
        ps.setString(5, d.getGender());
        ps.setInt(6, d.getAge());
        ps.setString(7, d.getSpecialization());
        ps.setString(8, d.getQualification());
        ps.setDouble(9, d.getConsultationFee());
        ps.setString(10, BCrypt.hashpw(d.getPassword(),BCrypt.gensalt()));
        ps.executeUpdate();
        System.out.println("patient addied shbjbbn bn");
    }

    public Doctor getDoctor(String doctorId) throws SQLException {
        String query = "SELECT * FROM DOCTORS WHERE DOCTORID=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, doctorId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Doctor d = new Doctor(
                    rs.getString("doctorId"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("gender"),
                    rs.getInt("age"),
                    rs.getString("specialization"),
                    rs.getString("qualification"),
                    rs.getShort("consultationFee"),
                    "");
            return d;
        }
        return null;
    }
    public List<Doctor> getAllDoctors() throws SQLException {
        String query="SELECT * FROM DOCTORS";
        PreparedStatement p=con.prepareStatement(query);
        List<Doctor> doctors=new ArrayList<>();
        ResultSet rs=p.executeQuery();
        while (rs.next()){
            doctors.add(new Doctor(
                    rs.getString("doctorId"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("gender"),
                    rs.getInt("age"),
                    rs.getString("specialization"),
                    rs.getString("qualification"),
                    rs.getShort("consultationFee"),
                    ""));
        }
        return doctors;
    }
    public List<Doctor> getDotorsByspecialization(String speialization) throws SQLException {
        String query="SELECT * FROM DOCTORS WHERE SPECIALIZATION=?";
        PreparedStatement p=con.prepareStatement(query);
        List<Doctor> doctors=new ArrayList<>();
        p.setString(1,speialization);
        ResultSet rs=p.executeQuery();
        while (rs.next()){
            doctors.add(new Doctor(
                    rs.getString("doctorId"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("gender"),
                    rs.getInt("age"),
                    rs.getString("specialization"),
                    rs.getString("qualification"),
                    rs.getShort("consultationFee"),
                    ""));
        }
        return doctors;
    }
    public void deleteDoctor(String doctorId) throws SQLException {
        String query="DELETE * FROM DOCTORS WHERE DOCTORID=?";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setString(1,doctorId);
        ResultSet rs=ps.executeQuery();
        while (rs.next()){
            System.out.println("Updated Doctor");
            System.out.println("Doctor Id: "+rs.getString("doctorId"));
            System.out.println("name: "+rs.getString("name"));
            System.out.println("email: "+rs.getString("email"));
            System.out.println("phone: "+rs.getString("phone"));
            System.out.println("gender: "+rs.getString("gender"));
            System.out.println("age: "+rs.getInt("age"));
            System.out.println("specialization: "+rs.getString("specialization"));
            System.out.println("qualification: "+rs.getString("qualification"));
            System.out.println("ConsoltaionFee: "+rs.getDouble("consultationFee"));
        }
    }
    public void updateDoctor(Doctor doctor)throws SQLException{
        String query="UPDATE DOCTORS SET NAME=?,EMAIL=?,PHONE=?,GENDER=?,AGE=?,SPECIALIZATION=?,CONSOLUATIONFEE=? WHERE DOCTORID=?";
        Doctor d=getDoctor(doctor.getDoctorId());
        PreparedStatement ps=con.prepareStatement(query);
        ps.setString(1,doctor.getName()!=""?doctor.getName():d.getName());
        ps.setString(2,doctor.getEmail()!=""?doctor.getEmail():d.getEmail());
        ps.setString(3,doctor.getPhno()!=""?doctor.getPhno():d.getPhno());
        ps.setString(4,doctor.getGender()!=""?doctor.getGender():d.getGender());
        ps.setString(5,doctor.getSpecialization()!=""?doctor.getSpecialization():d.getSpecialization());
        ps.setDouble(7,doctor.getConsultationFee()>0?doctor.getConsultationFee():d.getConsultationFee());
        ps.setInt(8,doctor.getAge()>0?doctor.getAge():doctor.getAge());

        ps.setString(9,doctor.getDoctorId());
        ResultSet rs=ps.executeQuery();
        while (rs.next()){
            System.out.println("Updated Doctor");
            System.out.println("Doctor Id: "+rs.getString("doctorId"));
            System.out.println("name: "+rs.getString("name"));
            System.out.println("email: "+rs.getString("email"));
            System.out.println("phone: "+rs.getString("phone"));
            System.out.println("gender: "+rs.getString("gender"));
            System.out.println("age: "+rs.getInt("age"));
            System.out.println("specialization: "+rs.getString("specialization"));
            System.out.println("qualification: "+rs.getString("qualification"));
            System.out.println("ConsoltaionFee: "+rs.getDouble("consultationFee"));
        }

    }

    public boolean login(String doctorId,String password)throws SQLException{
        String query="SELECT PASSWORD FROM DOCTORS WHERE DOCTORID=?";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setString(1,doctorId);
        ResultSet rs=ps.executeQuery();
        if (rs.next()) {
            String storedPassword = rs.getString("PASSWORD");
            if (PasswordUtil.verifyPassword(password, storedPassword)) {
                if (!PasswordUtil.isHashed(storedPassword)) {
                    updateDoctorPasswordHash(doctorId, password);
                }
                return true;
            }
        }
        return false;
    }

    private void updateDoctorPasswordHash(String doctorId, String password) throws SQLException {
        String updateQuery = "UPDATE DOCTORS SET PASSWORD=? WHERE DOCTORID=?";
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setString(1, PasswordUtil.hashPassword(password));
            ps.setString(2, doctorId);
            ps.executeUpdate();
        }
    }

    public boolean doctorLogin(String doctorId,String password)throws SQLException{
        return login(doctorId,password);
    }

}
