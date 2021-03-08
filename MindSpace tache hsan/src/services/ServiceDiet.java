/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.Diet;
import utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author trabe
 */
public class ServiceDiet implements IService<Diet>{
    
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Diet t) {
        try {
            String requete = "INSERT INTO diet (id_category, food, calories, quantite, image) VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId_categorie());
            pst.setString(2, t.getNom());
            pst.setInt(3, t.getCalories());
            pst.setInt(4, t.getQuantite());
            pst.setString(5, t.getImage());
            pst.executeUpdate();
            System.out.println("Aliment Ajoutée!");
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Diet t) {
        try {
            String requete = "DELETE FROM diet WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Aliment Supprimée !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Diet t) {
        try {
            String requete = "UPDATE diet SET id_category=?, food=?, calories=?, quantite=?, image=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId_categorie());
            pst.setString(2, t.getNom());
            pst.setInt(3, t.getCalories());
            pst.setInt(4, t.getQuantite());
            pst.setString(5, t.getImage());
            pst.setInt(6, t.getId());
            pst.executeUpdate();
            System.out.println("Aliment Modfié !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Diet> afficher() {
        ObservableList<Diet> list = FXCollections.observableArrayList();
        
        try {
            String requete = "SELECT * FROM diet";
            Statement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new Diet(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7))); 
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public Diet rechercheParId(int id) {
        Diet d = new Diet();
        try {
            String req = "SELECT * FROM diet where id = '"+id+"'";
            PreparedStatement pre = cnx.prepareStatement(req);
            ResultSet rs = pre.executeQuery();
            while (rs.next()){
                d.setId(rs.getInt(1));
                d.setId_categorie(rs.getInt(6));
                d.setNom(rs.getString(2));
                d.setCalories(rs.getInt(3));
                d.setQuantite(rs.getInt(4));
            }
        
        if(d.getNom() == null){
            System.out.println("Aliement n'existe pas!");
        } else {
            System.out.println("Aliemnt de nom "+d.getNom()+" Trouvé!");
        }
        
        } catch(SQLException ex) {
           System.out.println("erreur de requette "+ex);
       }
       return d;
    }
    
    public List<Diet> RechercheParNom(String search) {
        String req = "SELECT * FROM diet WHERE food="+(char)34+search+(char)34;
        List<Diet> list = new ArrayList<>();
        try {
            PreparedStatement pre = cnx.prepareStatement(req);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                list.add(new Diet(rs.getInt("id"), rs.getInt("id_category"), rs.getString("food"), rs.getInt("calories"), rs.getInt("quantite")));
            }
            
            if(list.isEmpty()){
                System.out.println("N'existe pas dans la base de donnée");
            }else{
                System.out.println("Aliment Trouvé!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceDiet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<Diet> getTrierParNom() throws SQLException {
        List<Diet> arr = new ArrayList<>();
        String req ="select * from diet ORDER BY food ASC";
        PreparedStatement pre = cnx.prepareStatement(req);
        ResultSet rs = pre.executeQuery();
         while (rs.next()) {                
            int id = rs.getInt(1);
            int id_cat = rs.getInt(6);
            String nom = rs.getString(2);
            int calories = rs.getInt(3);
            int quantite = rs.getInt(4);
            Diet p = new Diet(id,id_cat,nom,calories,quantite);
            arr.add(p);
        }
         
        System.out.println("trié avec succés ");
        return arr;
    }
}
