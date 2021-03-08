/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.CategorieDiet;

/**
 *
 * @author trabe
 */
public class ServiceCategorieDiet implements IService<CategorieDiet>{
    
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(CategorieDiet t) {
        try {
            String requete = "INSERT INTO category_diet (nom, description, image, date) VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getDescription());
            pst.setString(3, t.getImage());
            pst.setDate(4, t.getDate());
            pst.executeUpdate();
            System.out.println("Categorie Ajoutée!");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void update_total_calorie(int total, int id) {
        try {
            String requete = "UPDATE category_diet SET total_calories=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, total);
            pst.setInt(2, id);
            pst.executeUpdate();
            System.out.println("Calorie updated!");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(CategorieDiet t) {
        try {
            String requete = "DELETE FROM category_diet WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Categorie Supprimée !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(CategorieDiet t) {
        try {
            String requete = "UPDATE category_diet SET nom=?, description=?, image=?, date=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(5, t.getId());
            pst.setString(1, t.getNom());
            pst.setString(2, t.getDescription());
            pst.setString(3, t.getImage());
            pst.setDate(4, t.getDate());
            pst.executeUpdate();
            System.out.println("Categorie Modfié !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<CategorieDiet> afficher() {
        ObservableList<CategorieDiet> list = FXCollections.observableArrayList();
        
        try {
            String requete = "SELECT * FROM category_diet";
            Statement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new CategorieDiet(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getInt(4),rs.getString(5), rs.getDate(6)));
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
}
