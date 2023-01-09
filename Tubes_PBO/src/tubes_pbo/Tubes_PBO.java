/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes_pbo;

import objek.Transaksi;

/**
 *
 * @author Steph Ghari
 */
public class Tubes_PBO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        v_login frame = new v_login();
        java.awt.EventQueue.invokeLater(() -> {
            new v_login().setVisible(true);
        });
    }
    
}
