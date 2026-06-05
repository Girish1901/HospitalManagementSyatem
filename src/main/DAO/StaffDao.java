package main.DAO;
import main.Staff;
import org.mindrot.jbcrypt.BCrypt;
import utility.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffDao {
    private Connection con;

    public StaffDao() throws SQLException {
        con= DbConnection.getInstance().getConnection();
    }

    public void  addStaff(Staff p) throws SQLException {
        String query="INSERT INTO STAFFS VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setString(1,p.getStaffId());
        ps.setString(2,p.getName());
        ps.setString(3,p.getEmail());
        ps.setString(4,p.getPhno());
        ps.setString(5, p.getGender());
        ps.setInt(6,p.getAge());
        ps.setString(7,p.getRole());
        ps.setString(8,p.getDepartment());
        ps.setDouble(9,p.getSalary());
        ps.executeUpdate();
        System.out.println("patient addied shbjbbn bn");
    }
    public Staff getStaff(String staffId) throws SQLException {
        String query="SELECT * FROM STAFFS WHERE STAFFID=?";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setString(1,staffId);
        ResultSet rs=ps.executeQuery();
        while (rs.next()){
            Staff p=new Staff(
                    rs.getString("StaffId"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("gender"),
                    rs.getInt("age"),
                    rs.getString("role"),
                    rs.getString("department"),
                    rs.getDouble("salary"));
            return p;
        }
        return null;
    }
    public List<Staff> getAllStaff() throws SQLException {
        String query="SELECT * FROM STAFFS";
        PreparedStatement p=con.prepareStatement(query);
        List<Staff> patients=new ArrayList<>();
        ResultSet rs=p.executeQuery();
        while (rs.next()){
            patients.add(new Staff(
                    rs.getString("StaffId"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("gender"),
                    rs.getInt("age"),
                    rs.getString("role"),
                    rs.getString("department"),
                    rs.getDouble("salary")));
        }
        return patients;
    }
    public void updateStff(Staff p)throws SQLException{
        String query="UPDATE STAFFS SET NAME=?,EMAIL=?,PHONE=?,GENDER=?,AGE=?,ROLE=?,DEPARTMENT=?,SALARY=? WHERE STAFFID=?";
        Staff a=getStaff(p.getStaffId());
        PreparedStatement ps=con.prepareStatement(query);
        ps.setString(1,p.getName()!=""?p.getName():a.getName());
        ps.setString(2,p.getEmail()!=""?p.getEmail():a.getEmail());
        ps.setString(3,p.getPhno()!=""?p.getPhno():a.getPhno());
        ps.setString(4,p.getGender()!=""?p.getGender():a.getGender());
        ps.setInt(5,p.getAge()>0?p.getAge():a.getAge());
        ps.setString(6,p.getRole()!=""?p.getRole():a.getRole());
        ps.setString(7,p.getDepartment()!=""?p.getDepartment():a.getDepartment());
        ps.setDouble(8,p.getSalary()>0?p.getSalary():a.getSalary());
        ps.setString(9,p.getStaffId());
        ResultSet rs=ps.executeQuery();
        while (rs.next()){
            System.out.println("Updated Staffs");
            System.out.println("Staff Id: "+rs.getString("patientId"));
            System.out.println("name: "+rs.getString("name"));
            System.out.println("email: "+rs.getString("email"));
            System.out.println("phone: "+rs.getString("phone"));
            System.out.println("gender: "+rs.getString("gender"));
            System.out.println("age: "+rs.getInt("age"));
            System.out.println("role: "+rs.getString("role"));
            System.out.println("Department: "+rs.getString("Department"));
            System.out.println("Salary: "+rs.getDouble("salary"));

        }

    }
    public void deleteStaff(String staffId) throws SQLException {
        String query = "DELETE FROM STAFFS WHERE STAFFID=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, staffId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println("deleted Staffs");
            System.out.println("Staff Id: " + rs.getString("patientId"));
            System.out.println("name: " + rs.getString("name"));
            System.out.println("email: " + rs.getString("email"));
            System.out.println("phone: " + rs.getString("phone"));
            System.out.println("gender: " + rs.getString("gender"));
            System.out.println("age: " + rs.getInt("age"));
            System.out.println("role: " + rs.getString("role"));
            System.out.println("Department: " + rs.getString("Department"));
            System.out.println("Salary: " + rs.getDouble("salary"));
        }
    }

}
