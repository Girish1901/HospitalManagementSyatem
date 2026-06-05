package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection instance;
    private Connection conn;
    private static String url="jdbc:mysql://localhost:3306/hospital";
    private static String username="";
    private static String password="";
    private DbConnection(){
        try{
            conn= DriverManager.getConnection(url,username,password);
            System.out.println("Connection suncceful");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static DbConnection getInstance(){
        if (instance==null){
            instance=new DbConnection();
        }
        return instance;
    }

    public Connection getConnection()throws SQLException {
        return conn;
    }

    public void closeConnection() throws SQLException {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Connection Closed");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
