/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import Entidades.Contacto;
import com.roberto.BD.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author recin
 */
public class Contactos {
    Conexion ConexionClase = new Conexion();
    Connection cn;
    Statement st;
    ResultSet rsl;
    DefaultTableModel model;
    int id;
    Conexion con = new Conexion();
    Connection conexionn = con.getConnection();
    public ArrayList <Contacto> ListaContactos (){
        ArrayList<Contacto> listado = null;
        
        
        try {
            listado =  new ArrayList<Contacto>();
            
            CallableStatement cb =  conexionn.prepareCall("select * from contactos");
            ResultSet resultado = cb.executeQuery();//ejecutar la consulta en la clase
            //se puede usar los bucles para recorrerla
        
            while(resultado.next()){//nos ejecuta linea por linea  el resultado de resultset
                //Llamar al objeto de entidades
                Contacto cont =  new Contacto();
               cont.setId(resultado.getInt("idContactos"));
               cont.setEdad(resultado.getInt("Edad"));
               cont.setNombre(resultado.getString("Nombre"));
               cont.setApellido(resultado.getString("Apellido"));
               cont.setEmail(resultado.getString("Email"));
               cont.setNumtelefono(resultado.getInt("Telefono"));
               
               listado.add(cont);
               //esto seria una consulta a la base de datos
            }
        
        } catch (Exception e) {
            System.out.println(e.toString()); 
        }
        
        
        return listado;
    }
    public void agregarcontacto(Contacto contactoo){
         try {
          CallableStatement cb =  conexionn.prepareCall("insert into "
                    + "contactos(Nombre,Apellido,Edad,Email,Telefono) values('"+contactoo.getNombre()+ "',"
                            + "'"+contactoo.getApellido()+"','"+contactoo.getEdad()+"','"+contactoo.getEmail()+"',"
                                    + "'"+contactoo.getNumtelefono()+"')");
            cb.execute();
            
            JOptionPane.showMessageDialog(null, "Contacto agregado","Mensaje sistema",1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error"+ex.toString(),"Mensaje sistema",1);
        }
    }
    
    public void modificar(Contacto contactoo){
        try {
//          CallableStatement cb =  conexionn.prepareCall("update contactos set Nombre='"+contactoo.getNombre()+"',Apellido='"+contactoo.getApellido()+"',"+"Edad="+contactoo.getEdad()+",Email='"+contactoo.getEmail()+"',Telefono="+contactoo.getNumtelefono()+"where idContactos="+contactoo.getId());
         CallableStatement cb = conexionn.prepareCall("Update contactos set Nombre= '"+contactoo.getNombre()+"' ," +
                    "Apellido='"+contactoo.getApellido()+"', Edad='"+ contactoo.getEdad() +
                    "', Email='"+ contactoo.getEmail() +"' , Telefono='"+ contactoo.getNumtelefono()
                    + "' where idContactos =" + contactoo.getId());
       
            cb.execute();
            
            JOptionPane.showMessageDialog(null, "Contacto actualizado","Mensaje sistema",1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error"+ex.toString(),"Mensaje sistema",1);
        }
        
           
    }
    
    public void eliminar(int id){
        try {
            CallableStatement cb = conexionn.prepareCall("delete from contactos where idContactos="+id);
            cb.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Todo fallo "+e);
        }
    }
}
