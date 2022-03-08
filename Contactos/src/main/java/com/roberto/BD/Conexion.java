/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.roberto.BD;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author recin
 */
public class Conexion {
    
    private static Connection ConnectionDb = null;
    
    public Connection getConnection(){
        
        try {
            String url = "jdbc:mysql://localhost:3306/lab";
            String user = "recinos";
            String password = "root";
            
            ConnectionDb = DriverManager.getConnection(url, user, password);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error es mierda no funciona" + e.toString());
        }
        return ConnectionDb;
    }
    
}
