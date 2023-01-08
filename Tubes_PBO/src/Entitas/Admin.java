/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitas;

import Abstraksi.User;
import objek.Transaksi;

/**
 *
 * @author omen
 */
public class Admin extends User{
    private String password;
    private Transaksi[] arrayTransaksi;
    
    public Admin(String username, String password, String nama, String no_handphone){
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.no_handphone = no_handphone;
    }

    @Override
    public void login() {
        this.active = true;
    }

    @Override
    public void logout() {
        this.active = false;
    }
    
    public void tampilTransaksi(String nama){
        
    }
    public void tambahTransaksi(Transaksi transaksi,String nama){
        
    }
    public void ubahStatusBarang(String nama, String id){
        
    }
    public void modifyTransaksi(){
        
    }

    public Transaksi[] getArrayTransaksi() {
        return arrayTransaksi;
    }
    
}
