/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes_pbo;
import java.sql.*;
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
    
    public C_sender(){
    
    }
    
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
                String sql = String.format("SELECT * FROM transaksi WHERE username = '%s'",log.getNama());
                ResultSet rs = stmt.executeQuery(sql);
                // Process the results
                while (rs.next()) {
                    Transaksi trs = new Transaksi(rs.getString("id_transaksi"));
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
                    Barang brg = new Barang(rs.getString("nama_barang"),rs.getDouble("berat"),rs.getString("nama_penerima"),rs.getDouble("jarak"),rs.getString("alamat_pengiriman"));
                    listModelBarang.addElement(brg);
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
    
    public void insertDataBarang(String nama_barang, double berat, String penerima, Double jarak, String alamat){
        try {
            String url = "jdbc:mysql://localhost:3306/tubespbo";
            String username = "root";
            String password = "";
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                System.out.println("Connected insert barang");
                // Execute a query
                Statement stmt = conn.createStatement();
                String sql = String.format("INSERT INTO barang (nama_barang, berat, nama_penerima, jarak, alamat_pengiriman) "
                        + "VALUES ('%s',%f,'%s',%f,'%s');",nama_barang, berat, penerima, jarak, alamat);

                stmt.executeUpdate(sql);
//                this.listModelBarang.clear();
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
                System.out.println("ini jumlah barang :"+listModelBarang.size());
                for (int i=0;i<listModelBarang.size();i++){
                    sql = String.format("UPDATE barang SET id_transaksi = '%s' WHERE id_barang = '%s'", id_transaksi,listModelBarang.get(i).getId_barang());
                    stmt.executeUpdate(sql);
                }
                this.loadDataTransaksi();
                this.listModelBarang.clear();
                // Process the results
                stmt.close();
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e);
        }
    }
}
