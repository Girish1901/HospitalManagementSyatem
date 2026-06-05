package main.DAO;
import main.Bill;
import main.MedicalRecord;
import utility.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDao {
    private Connection con;
    public BillDao() throws SQLException {
        con=DbConnection.getInstance().getConnection();
    }
    public void addBill(Bill b) throws SQLException {
        String query="INSERT INTO BILLS VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement rs=con.prepareStatement(query);
        rs.setString(1,b.getBillId());
        rs.setString(2,b.getPatientId());
        rs.setDouble(3,b.getConsultationFee());
        rs.setDouble(4,b.getMedicineFee());
        rs.setDouble(5,b.getRoomFee());
        rs.setDouble(6,b.getTotalAmount());
        rs.setString(7,b.getStatus());
        rs.setDate(8, Date.valueOf(b.getDate()));

    }
    public Bill getBill(String billId) throws SQLException {
        String query="SELECT * FROM BILLS WHERE BILLI?d=?";
        PreparedStatement p=con.prepareStatement(query);
        p.setString(1,billId);
        ResultSet rs=p.executeQuery();
        while (rs.next()){
            return new Bill(
                    rs.getString("billID"),
                    rs.getString("patientId"),
                    rs.getDouble("ConsoltationFee"),
                    rs.getDouble("medicineFee"),
                    rs.getDouble("roomFee"),
                    rs.getString("Status"),
                    rs.getDouble("TotalAmount"),
                    rs.getDate("date").toLocalDate()
            );
        }
        return null;
    }
    public List<Bill> getAllBillsByPatient(String patientId) throws SQLException {
        String query="SELECT * FROM BILLS WHERE patientId=?";
        PreparedStatement p=con.prepareStatement(query);
        List<Bill> allBillsByPatientId=new ArrayList<>();
        p.setString(1,patientId);
        ResultSet rs=p.executeQuery();
        while (rs.next()){
            allBillsByPatientId.add(new Bill(
                    rs.getString("billID"),
                    rs.getString("patientId"),
                    rs.getDouble("ConsoltationFee"),
                    rs.getDouble("medicineFee"),
                    rs.getDouble("roomFee"),
                    rs.getString("Status"),
                    rs.getDouble("TotalAmount"),
                    rs.getDate("date").toLocalDate()
            ));
        }
        return allBillsByPatientId;
    }
    public List<Bill> getAllBills() throws SQLException {
        String query="SELECT * FROM Bills";
        PreparedStatement p=con.prepareStatement(query);
        List<Bill> allBills=new ArrayList<>();
        ResultSet rs=p.executeQuery();
        while (rs.next()){
            allBills.add(new Bill(
                    rs.getString("billID"),
                    rs.getString("patientId"),
                    rs.getDouble("ConsoltationFee"),
                    rs.getDouble("medicineFee"),
                    rs.getDouble("roomFee"),
                    rs.getString("Status"),
                    rs.getDouble("TotalAmount"),
                    rs.getDate("date").toLocalDate()
            ));
        }
        return allBills;
    }
    public void payBill(String billId) throws SQLException {
        String query="UPDATE BILLS SET STATUS=paid WHERE BILLID=?";
        PreparedStatement p=con.prepareStatement(query);
        p.setString(1,billId);
        int rs=p.executeUpdate();
        if (rs != 0) {
            System.out.println("Update sucsses");
        } else {
            System.out.println("Update failed");
        }

    }
    public void updateStatus(String billId,String status) throws SQLException {
        String query="UPDATE BILLS SET STATUS=? WHERE BILLID=?";
        PreparedStatement p=con.prepareStatement(query);
        p.setString(1,status);
        p.setString(2,billId);
        ResultSet rs=p.executeQuery();
        while (rs.next()){
            System.out.println(rs.getString("billID"));
            System.out.println(rs.getString("patientId"));
            System.out.println(rs.getDouble("ConsoltationFee"));
            System.out.println(rs.getDouble("medicineFee"));
            System.out.println(rs.getDouble("roomFee"));
            System.out.println(rs.getString("Status"));
            System.out.println(rs.getDouble("TotalAmount"));
            System.out.println(rs.getDate("date").toLocalDate());;
        }

    }

}
