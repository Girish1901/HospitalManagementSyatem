package main.DAO;

import main.Admin;
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

public class AdminDao {
    private Connection con;

    public AdminDao() throws SQLException {
        con= DbConnection.getInstance().getConnection();
    }

    public void addAdmin(Admin a) throws SQLException {
        String query="INSERT INTO ADMINS (adminId, name, email, phone, gender, age, password) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setString(1, a.getAdminId());
        ps.setString(2, a.getName());
        ps.setString(3, a.getEmail());
        ps.setString(4, a.getPhno());
        ps.setString(5, a.getGender());
        ps.setInt(6, a.getAge());
        ps.setString(7, BCrypt.hashpw(a.getPassword(), BCrypt.gensalt()));
        ps.executeUpdate();
        System.out.println("Admin added successfully.");
    }
    public Admin getPatient(String adminId) throws SQLException {
        String query="SELECT * FROM ADMINS WHERE ADMINID=?";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setString(1,adminId);
        ResultSet rs=ps.executeQuery();
        while (rs.next()){
            Admin p=new Admin(
                    rs.getString("adminId"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("gender"),
                    rs.getInt("age"),
                    "");
            return p;
        }
        return null;
    }

    public Admin getAdmin(String adminId) throws SQLException {
        return getPatient(adminId);
    }

    public boolean adminLogin(String adminId,String password)throws SQLException{
        String query="SELECT PASSWORD FROM ADMINS WHERE ADMINID=?";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setString(1,adminId);
        ResultSet rs=ps.executeQuery();
        if (rs.next()) {
            String storedPassword = rs.getString("PASSWORD");
            if (PasswordUtil.verifyPassword(password, storedPassword)) {
                if (!PasswordUtil.isHashed(storedPassword)) {
                    updateAdminPasswordHash(adminId, password);
                }
                return true;
            }
        }
        return false;

    }

    private void updateAdminPasswordHash(String adminId, String password) throws SQLException {
        String updateQuery = "UPDATE ADMINS SET PASSWORD=? WHERE ADMINID=?";
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setString(1, PasswordUtil.hashPassword(password));
            ps.setString(2, adminId);
            ps.executeUpdate();
        }
    }

}

