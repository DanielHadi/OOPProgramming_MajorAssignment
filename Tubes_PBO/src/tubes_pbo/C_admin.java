/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes_pbo;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import objek.Barang;
import objek.Transaksi;
/**
 *
 * @author Steph Ghari
 */
public class C_admin {
    private final DefaultListModel<Transaksi> listModelTransaksi = new DefaultListModel<>();
    private final DefaultListModel<Barang> listModelBarang = new DefaultListModel<>();
    
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
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                System.out.println("Connected load transaksi");
                // Execute a query
                Statement stmt = conn.createStatement();
                String sql = "SELECT * FROM transaksi";
                ResultSet rs = stmt.executeQuery(sql);
                // Process the results
                while (rs.next()) {
                    Transaksi trs = new Transaksi(rs.getString("username"));
                    listModelTransaksi.addElement(trs);
                }
 
                stmt.close();
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e);
        }
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

        public void modifTransaksi(String id_transaksi, String status, Date tanggal){
            try {
                String url = "jdbc:mysql://localhost:3306/tubespbo";
                String username = "root";
                String password = "";
                try (Connection conn = DriverManager.getConnection(url, username, password)) {
                    System.out.println("Connected");
                    // Execute a query
                    Statement stmt = conn.createStatement();

                    if(!checkStatus(id_transaksi)){
                        String sql = String.format("update transaksi set status_pengiriman = %s, tanggal_kirim = %s WHERE id_transaksi = %s", status, tanggal, id_transaksi);
                        stmt.executeUpdate(sql);
                    }
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
    
    public boolean checkStatus(String id_transaksi){
        try {
            String url = "jdbc:mysql://localhost:3306/tubespbo";
            String username = "root";
            String password = "";
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                System.out.println("Connected checkData");
                // Execute a query
                Statement stmt = conn.createStatement();
                String sql = String.format("SELECT * FROM barang WHERE id_transaksi = '%s' AND terkirim = false",id_transaksi);
                ResultSet rs = stmt.executeQuery(sql);
                // Process the results
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e);
        }
        return false;
    }
    
    public void ubahStatusBarang(String ID_barang){
        try {
            String url = "jdbc:mysql://localhost:3306/tubespbo";
            String username = "root";
            String password = "";
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                System.out.println("Connected ubah status barang");
                // Execute a query
                Statement stmt = conn.createStatement();
                String sql = String.format("update barang set terkirim = true where id_barang = '%s'", ID_barang);
          
                stmt.executeUpdate(sql);
                loadDataBarang(ID_barang);
                this.listModelBarang.clear();
                // Process the results
                stmt.close();
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e);
        }
    }
    public double getHarga(String id_transaksi){//load data untuk ditampilkan pada jList
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
            double harga = 0;
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                System.out.println("Connected load barang");
                // Execute a query
                Statement stmt = conn.createStatement();
                String sql = String.format("SELECT berat,jarak FROM barang WHERE id_transaksi = '%s'",id_transaksi);
                ResultSet rs = stmt.executeQuery(sql);
                // Process the results
                while (rs.next()) {
                    harga = harga + (rs.getDouble("berat") * rs.getDouble("jarak") * Barang.getBIAYA_PER_KG());

                }
                
                stmt.close();
                conn.close();
                return harga;
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e);
        }
        return 0;
    }
    
}
