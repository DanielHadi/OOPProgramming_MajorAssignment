/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objek;

import java.time.LocalDate;
import java.sql.*;

/**
 *
 * @author omen
 */
public class Transaksi {
    private String id_transaksi;
    private int harga_pengiriman;
    private LocalDate tanggal_kirim;
    private String resi;
    private String nama_pengirim;
    private boolean status_pengiriman;
    private Barang[] arrayBarang;

    public Transaksi(String nama_pengirim) {
        this.nama_pengirim = nama_pengirim;
    }
    private void TambahBarang(Barang[] barang){
        
    }

    public int getHarga_pengiriman() {
        
        return harga_pengiriman;
    }

    public LocalDate getTanggal_kirim() {
        return tanggal_kirim;
    }

    public String getResi() {
        return resi;
    }
    
    public String generateID(){
        try {
            int count= 0;
            String url = "jdbc:mysql://localhost:3306/tubespbo";
            String username = "root";
            String password = "";
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                System.out.println("Connected");
                // Execute a query
                Statement stmt = conn.createStatement();
                ResultSet resultSet = stmt.executeQuery("SELECT COUNT(*) FROM transaksi WHERE username = '"+nama_pengirim+"'");
                if (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
                // Process the results
                stmt.close();
                conn.close();
                this.id_transaksi = String.format("%s_%d", nama_pengirim,(count+1));
                System.out.println(id_transaksi);
                return String.format("%s_%d", nama_pengirim,(count+1));
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e);
        }
        return null;
    }

    public String getNama_pengirim() {
        return nama_pengirim;
    }

    public boolean isStatus_pengiriman() {
        return status_pengiriman;
    }

    public Barang[] getArrayBarang() {
        return arrayBarang;
    }

    public String getId_transaksi() {
        return id_transaksi;
    }
    
    public String toString(){
        return this.id_transaksi;
    }
    
}
