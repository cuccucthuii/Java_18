package util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String URL = "jdbc:postgresql://localhost:5432/productmanagement";
    private static final String USER = "postgres";
    private static final String PASSWORD = "000000";


    // Open connect
    public static Connection openConnection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
        }catch (Exception e){
            e.printStackTrace(); // Print content error
        }
        return conn;
    }




    // Close connection if final woking
    public static void closeConnection(Connection conn, CallableStatement callSt){
        if(conn != null){
            try {
                conn.close();
            }catch (SQLException e){
                throw  new RuntimeException(e);
            }
        }
    }
//
//    public static void main(String[] args) throws SQLException {
//        Connection conn = openConnection();
//        boolean reachable = conn.isValid(10);
//    }
}
