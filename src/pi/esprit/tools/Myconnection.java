/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import pi.esprit.entities.Reservation;

/**
 *
 * @author HAMZA
 */
public class Myconnection {
    public String url="jdbc:mysql://localhost:3306/pi";
    public String login="root";
    public String pwd="";
    public Connection cn;
    public static Myconnection instance;

    public Myconnection() {
        try { 
            cn = DriverManager.getConnection(url,login,pwd);
            System.out.println("Connexion etablie!");
        } catch (SQLException ex) {
            System.out.println("Erreur de connexion");
            System.out.println(ex.getMessage());        }
    }
     public Connection getCnx() {
        return cn;
    }
    
    
    public static Myconnection getInstance(){
        if(instance == null){
            instance = new Myconnection();
        }
        return instance;
    }
    Connection conn = null;
    public static Connection ConnectDb(){
        try {
            
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pi","root","");
           // JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    public static ObservableList<Reservation> getDataReservation(){
        Connection conn = ConnectDb();
        ObservableList<Reservation> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from Reservation");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Reservation(rs.getInt(1) ,rs.getInt(2), rs.getDate(3), rs.getInt(4)));
            }
        } catch (Exception e) {
        }
        return list;
    
    
    
    
    
    
}}
