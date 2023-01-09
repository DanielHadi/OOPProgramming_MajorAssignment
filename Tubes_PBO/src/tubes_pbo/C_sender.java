/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes_pbo;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import objek.*;
/**
 *
 * @author Steph Ghari
 */
public class C_sender {
    private final DefaultListModel<Transaksi> listModelTransaksi = new DefaultListModel<>();
    private final DefaultListModel<Barang> listModelBarang = new DefaultListModel<>();
    C_login log = new C_login();
    
    public void loadDataTransaksi(){//load data untuk ditampilkan pada jList
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
            
            int current = getJmlTransaksi();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                System.out.println("Connected load transaksi");
                // Execute a query
                Statement stmt = conn.createStatement();
                String sql = String.format("SELECT * FROM transaksi WHERE username = '%s'",C_login.getNama());
                ResultSet rs = stmt.executeQuery(sql);

                // Process the results
                setZeroTransaksi(C_login.getNama());
                while (rs.next()) {
                    Transaksi trs = new Transaksi(rs.getString("username"));
                    listModelTransaksi.addElement(trs);
                }
//                stmt.executeUpdate(String.format("UPDATE jmltransaksi SET jumlah = %d WHERE username = '%s'",current, C_login.getNama()));
                
                stmt.close();
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e);
        }
    }
    public void setZeroTransaksi(String name){
        try {
            String url = "jdbc:mysql://localhost:3306/tubespbo";
            String username = "root";
            String password = "";
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                System.out.println("Connected set zero transaksi");
                // Execute a query
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(String.format("UPDATE jmltransaksi SET jumlah = 0 WHERE username = '%s'",name));
                stmt.close();
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e);
        }
    }
    public int getJmlTransaksi(){
        try {
            String url = "jdbc:mysql://localhost:3306/tubespbo";
            String username = "root";
            String password = "";
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                System.out.println("Connected get jumlah transaksi");
                // Execute a query
                Statement stmt = conn.createStatement();
                ResultSet rs2 = stmt.executeQuery(String.format("SELECT jumlah FROM jmltransaksi WHERE username = '%s'",C_login.getNama()));
                while (rs2.next()){
                    return rs2.getInt(1);
                    
                }
                stmt.close();
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e);
        }
        return 0;
    }
    
    public DefaultListModel<Transaksi> getListModelTransaksi() {
        return listModelTransaksi;
    }
    
    public void loadDataBarang(String id_transaksi){//load data untuk ditampilkan pada jList
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
                System.out.println("Connected load barang");
                // Execute a query
                Statement stmt = conn.createStatement();
                String sql = String.format("SELECT * FROM barang WHERE id_transaksi = '%s'",id_transaksi);
                ResultSet rs = stmt.executeQuery(sql);
                // Process the results
                while (rs.next()) {
                    Barang brg1 = new Barang(rs.getString("nama_barang"),rs.getDouble("berat"),rs.getString("nama_penerima"),rs.getDouble("jarak"),rs.getString("alamat_pengiriman"));
                    listModelBarang.addElement(brg1);
                }
                stmt.close();
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e);
        }
    }
    
    public DefaultListModel<Barang> getListModelBarang() {
        return listModelBarang;
    }
    
    public void insertDataBarang(){
        try {
            String url = "jdbc:mysql://localhost:3306/tubespbo";
            String username = "root";
            String password = "";
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                System.out.println("Connected insert barang");
                // Execute a query
                Statement stmt = conn.createStatement();
                String sql = String.format("INSERT INTO barang (id_transaksi, nama_barang, berat, nama_penerima, jarak, alamat_pengiriman) "
                        + "SELECT id_transaksi, nama_barang, berat, nama_penerima, jarak, alamat_pengiriman FROM wrap");

                stmt.executeUpdate(sql);
//                Barang dummy = new Barang(nama_barang, berat, penerima, jarak, alamat);
//                brg.add(dummy);
                this.listModelBarang.clear();
                
                // Process the results
                stmt.close();
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e);
        }
        
    }
    
    public void insertDataWrap(String nama_barang, double berat, String penerima, Double jarak, String alamat){
        try {
            String url = "jdbc:mysql://localhost:3306/tubespbo";
            String username = "root";
            String password = "";
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                System.out.println("Connected insert wrap");
                // Execute a query
                Statement stmt = conn.createStatement();
                String sql = String.format("INSERT INTO wrap (nama_barang, berat, nama_penerima, jarak, alamat_pengiriman) "
                        + "VALUES ('%s',%f,'%s',%f,'%s');",nama_barang, berat, penerima, jarak, alamat);

                stmt.executeUpdate(sql);       
                // Process the results
                stmt.close();
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e);
        }
    }
    
    public void setWraptoEmpty(){
        try {
            String url = "jdbc:mysql://localhost:3306/tubespbo";
            String username = "root";
            String password = "";
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                System.out.println("Connected set empty wrap");
                // Execute a query
                Statement stmt = conn.createStatement();
                String sql = String.format("TRUNCATE TABLE wrap");
                stmt.executeUpdate(sql);
                // Process the results
                stmt.close();
                conn.close();
                
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e);
        }
    }
    
    public void checkOut(String id_transaksi, int harga, String pengirim){
        try {
            String url = "jdbc:mysql://localhost:3306/tubespbo";
            String username = "root";
            String password = "";
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                System.out.println("Connected checkout");
                // Execute a query
                Statement stmt = conn.createStatement();
                String sql = String.format("INSERT INTO transaksi (id_transaksi, harga_pengiriman, username)"
                        + "VALUES ('%s', %d, '%s');",id_transaksi, harga, pengirim);

                stmt.executeUpdate(sql);
                
                this.listModelTransaksi.clear();
                this.loadDataTransaksi();
                // Process the results
                stmt.close();
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e);
        }
    }
    
    public void updateIDTransaksi(String id_transaksi){
        try {
            String url = "jdbc:mysql://localhost:3306/tubespbo";
            String username = "root";
            String password = "";
            int current = getJmlTransaksi();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                System.out.println("Connected update ID Transaksi di barang");
                // Execute a query

                Statement stmt = conn.createStatement();
                String sql = String.format("UPDATE wrap SET id_transaksi = '%s'",id_transaksi);
                stmt.executeUpdate(sql);
                // Process the results
                stmt.close();
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e);
        }
    }
}
