/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objek;

import java.time.LocalDate;
import java.sql.*;
import java.util.ArrayList;
import tubes_pbo.C_sender;

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
    private ArrayList<Barang> arrayBarang;

    public Transaksi(String nama_pengirim) {
        this.nama_pengirim = nama_pengirim;
        generateID();
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
    
    public void generateID(){
        try {
            String url = "jdbc:mysql://localhost:3306/tubespbo";
            String username = "root";
            String password = "";
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                System.out.println("Connected generateID");
                // Execute a query
                Statement stmt = conn.createStatement();
                ResultSet resultSet = stmt.executeQuery("SELECT jumlah FROM jmltransaksi WHERE username = '"+nama_pengirim+"'");
                if (resultSet.next()) {
                    this.id_transaksi = String.format("%s_%d", nama_pengirim,(resultSet.getInt(1)+1));
                    addJmlTransaksi(nama_pengirim, resultSet.getInt(1));
                }
                
                // Process the results
                stmt.close();
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e);
        }
    }
    
    public void addJmlTransaksi(String pengirim, int now){
        try {
            String url = "jdbc:mysql://localhost:3306/tubespbo";
            String username = "root";
            String password = "";
            int current = now;
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                System.out.println("Connected add jumlah transaksi");
                // Execute a query
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(String.format("UPDATE jmltransaksi SET jumlah = %d WHERE username = '%s'",current+1,pengirim));
                // Process the results
                stmt.close();
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e);
        }
    }

    public String getNama_pengirim() {
        return nama_pengirim;
    }

    public boolean isStatus_pengiriman() {
        return status_pengiriman;
    }

    public ArrayList<Barang> getArrayBarang() {
        return arrayBarang;
    }

    public String getId_transaksi() {
        return String.valueOf(this.id_transaksi);
    }
    
    @Override
    public String toString(){
        return String.format("%s", this.id_transaksi) ;
    }
    
}
