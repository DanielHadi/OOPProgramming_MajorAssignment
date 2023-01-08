/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes_pbo;
import objek.Barang;
import java.sql.*;
import javax.swing.DefaultListModel;

/**
 *
 * @author Steph Ghari
 */
public class C_kurir {
    private final DefaultListModel<Barang> listModel = new DefaultListModel<>();
    
    public void loadDataBarang(){//load data untuk ditampilkan pada jList
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver: " + e);
        }

        // Connect to the database
        try {
            String url = "jdbc:mysql://localhost:3306/tubespbo";
            String username = "root";
            String password = "";
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                System.out.println("Connected");
                // Execute a query
                Statement stmt = conn.createStatement();
                String sql = "SELECT * FROM barang";
                ResultSet rs = stmt.executeQuery(sql);
                // Process the results
                while (rs.next()) {
                    Barang brg = new Barang(rs.getString("nama_barang"),rs.getDouble("berat"),rs.getString("nama_penerima"),rs.getDouble("jarak"),rs.getString("alamat_pengiriman"));
                    listModel.addElement(brg);
                }
                stmt.close();
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e);
        }
    }
    
    public DefaultListModel<Barang> getListModel() {
        return listModel;
    }
    
    public void kirimBarang(C_admin admin, String pengirim, String ID){
        
    }
}
