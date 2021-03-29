/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Regime;
import utils.DataSource;

/**
 *
 * @author trabe
 */
public class ServiceRegime implements IService<Regime>{
    
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Regime t) {
        try {
            String requete = "INSERT INTO regime (type, description, image) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getType());
            pst.setString(2, t.getDescription());
            pst.setString(3, t.getImage());
            pst.executeUpdate();
            System.out.println("Regime Ajoutée!");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Regime t) {
        try {
            String requete = "DELETE FROM regime WHERE id_regime=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Régime Supprimée !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Regime t) {
        try {
            String requete = "UPDATE regime SET type=?, description=?, image=? WHERE id_regime=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getType());
            pst.setString(2, t.getDescription());
            pst.setString(3, t.getImage());
            pst.setInt(4, t.getId());
            pst.executeUpdate();
            System.out.println("Régime Modfié !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Regime> afficher() {
        ObservableList<Regime> list = FXCollections.observableArrayList();
        
        try {
            String requete = "SELECT * FROM regime";
            Statement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Image image = new Image("file:/C:/Users/trabe/Desktop/Integration/src/images/" + rs.getString(4)); //creat img
                ImageView imgV = new ImageView(image);
                imgV.setFitHeight(80);
                imgV.setFitWidth(80);
                list.add(new Regime(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4), imgV));
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
    
    public ObservableList<Regime> userListe() {
        ObservableList<Regime> list = FXCollections.observableArrayList();
        
        String requete = "SELECT * FROM regime";
        try {
            
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Image image = new Image("file:/C:/Users/trabe/Desktop/Integration/src/images/" + rs.getString(4)); //creat img
                ImageView imgV = new ImageView(image);
                imgV.setFitHeight(80);
                imgV.setFitWidth(80);
                list.add(new Regime(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4), imgV));
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
    
    public ObservableList<Regime> TrieParNom() {
        ObservableList<Regime> list = this.userListe();
        Collections.sort(list, new Regime());
        Collections.reverse(list);
        return list;
    }
    
}
