/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.entities.Evenement;
import com.esprit.entities.Publication;
import com.esprit.tools.DataSource;
import com.sun.istack.internal.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author mehdi
 */
public  class ServicePublication implements IStatus<Evenement>{ 
    
     Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Evenement t) {
     
        String requete = "INSERT INTO evenement (id_pub, date_pub,date_even,image,sujet) VALUES (?,?,?,?,?)";
         try {
           
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,t.getId_pub());
            pst.setDate(2, t.getDate_pub());
            pst.setDate(3, t.getDate_even());
            pst.setString(4, t.getImage());
            pst.setString(5, t.getSujet());
            pst.executeUpdate();
            System.out.println("Evenement Ajoutée !");
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }    
        }

    @Override
    public void supprimer(Evenement t) {
        try {
            String requete = "DELETE FROM evenement WHERE id_pub=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId_pub());
            pst.executeUpdate();
            System.out.println("Evenement Supprimée !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Evenement t) {
        try {
            String requete = "UPDATE evenement SET date_pub=?, date_even=?, image=?, sujet =? WHERE id_pub=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(5, t.getId_pub());
            pst.setDate(1, t.getDate_pub());
            pst.setDate(2, t.getDate_even());
            pst.setString(3, t.getImage());
            pst.setString(4, t.getSujet());
            pst.executeUpdate();
            System.out.println("evenement Modfié !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Evenement> afficher() {
        
       ObservableList<Evenement> list = FXCollections.observableArrayList(); 
        
        
        
        try {
            String requete = "SELECT * FROM evenement";
            Statement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new Evenement( rs.getDate(2),rs.getString(4),rs.getString(5),rs.getInt(1),rs.getDate(3) )); 
                
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
   

public List<Evenement> RecherchePardate_pub(String search) {
        String req = "SELECT * FROM evenement WHERE date_pub="+(char)34+search+(char)34;
        List<Evenement> list = new ArrayList<>();
        try {
            PreparedStatement pre = cnx.prepareStatement(req);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                 list.add(new Evenement( rs.getDate(1),rs.getDate(2),rs.getString(3),rs.getString(4) )); 
            }
            System.out.println(" evenement trouvé !");

        } catch (SQLException ex) {
            Logger.getLogger(ServicePublication.class.getClass()).log(Level.SEVERE, null, ex);
        }
        return list;
}
    
    
}
    
   
    

