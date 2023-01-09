/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes_pbo;
import java.sql.*;
/**
 *
 * @author Steph Ghari
 */
public class C_login {
    private static String nama;
    
    public void setNama(String nama){
        C_login.nama = nama;
    }

    public String getNama() {
        return nama;
    }
    
    public boolean checkData(String status, String uname, String pass){
        try {
            String url = "jdbc:mysql://localhost:3306/tubespbo";
            String username = "root";
            String password = "";
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                System.out.println("Connected checkData");
                // Execute a query
                Statement stmt = conn.createStatement();
                String sql = String.format("SELECT * FROM user WHERE username = '%s' AND password = '%s' AND role = '%s'",uname,pass,status);
                ResultSet rs = stmt.executeQuery(sql);
                // Process the results
                if (!rs.next()){
                    return false;
                }else{
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e);
        }
        return false;
    }
    
}
