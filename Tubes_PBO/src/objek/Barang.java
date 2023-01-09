/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objek;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author omen
 */
public class Barang {
    protected final int BIAYA_PER_KG = 3000;
    public String nama_barang;
    public double berat_kiriman;
    private String resi;
    private String nama_penerima;
    private boolean siapDiKirim;
    private double jarak;
    private String alamat_pengiriman;
    private String id_barang;

    public void setSiapDiKirim() {
        this.siapDiKirim = true;
    }

    public Barang(String nama_barang, double berat_kiriman, String nama_penerima, double jarak, String alamat_pengiriman) {
        this.nama_barang = nama_barang;
        this.berat_kiriman = berat_kiriman;
        this.nama_penerima = nama_penerima;
        this.jarak = jarak;
        this.alamat_pengiriman = alamat_pengiriman;
        this.siapDiKirim = false;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public String getId_barang() {
        try {
            String url = "jdbc:mysql://localhost:3306/tubespbo";
            String username = "root";
            String password = "";
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                System.out.println("Connected get Id barang");
                // Execute a query
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(String.format("SELECT id_barang FROM barang where nama_barang ='%s'",nama_barang));
                // Process the results
                while(rs.next()){
                    return rs.getString("id_barang");   
                }
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e);
        }
        return null;
    }

    public double getBerat_kiriman() {
        return berat_kiriman;
    }

    public String getNama_penerima() {
        return nama_penerima;
    }

    public boolean getStatus() {
        return siapDiKirim;
    }

    public double getJarak() {
        return jarak;
    }

    public String getAlamat_pengiriman() {
        return alamat_pengiriman;
    }
    
    @Override
    public String toString(){
        return this.nama_barang;
    }
}
