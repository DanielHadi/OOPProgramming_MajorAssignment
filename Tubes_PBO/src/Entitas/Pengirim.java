/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitas;

import Abstraksi.User;

/**
 *
 * @author omen
 */
public class Pengirim extends User {
    private String password;
    public Pengirim(String username, String password, String nama, String no_handphone){
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.no_handphone = no_handphone;
    }
    
    @Override
    public void login() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void logout() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void tampilkanTransaksiPribadi(Admin admin){
        
    }
    public void tambahTransaksiPribadi(Admin admin){
        
    }
    
}
