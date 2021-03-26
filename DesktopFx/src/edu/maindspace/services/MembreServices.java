/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.maindspace.services;

import edu.maindspace.entities.Membre;
import edu.maindspace.interfaces.IUser;
import edu.maindspace.tools.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mohamedbouslah
 */
public class MembreServices implements IUser<Membre>{

    @Override
    public void ajouter(Membre t) {
        String requete="INSERT INTO membre(cin,nom,prenom,sexe,datee,taille,poids,email,password,telephone)"
               + "VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst =
                    new MyConnection().cn.prepareStatement(requete);
            pst.setInt(1, t.getCin());
            pst.setString(2, t.getNom());
            pst.setString(3, t.getPrenom());
            pst.setString(4, t.getSexe());
            pst.setDate(5, t.getDatee());
            pst.setFloat(6, t.getTaille());
            pst.setFloat(7, t.getPoids());
            pst.setString(8, t.getEmail());
            pst.setString(9, t.getPassword());
            pst.setInt(10, t.getTelephone());
            pst.executeUpdate();
            System.out.println("Membre ajoutee !");

            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Membre t) {
        String requete = "DELETE FROM membre WHERE cin=?";
        try {
            PreparedStatement pst = 
                    new MyConnection().cn.prepareStatement(requete);
            pst.setInt(1, t.getCin());
            pst.executeUpdate();
            System.out.println("Membre Supprimée !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Membre t) {
        String requete = "UPDATE membre SET nom=?,prenom=? ,password=? ,email=? ,telephone=?,taille=? ,poids=? WHERE cin=?";
        try {
            PreparedStatement pst = 
                    new MyConnection().cn.prepareStatement(requete);
            pst.setInt(8, t.getCin());
            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            //pst.setDate(3, t.getDatee());
            pst.setString(3, t.getPassword());
            pst.setString(4, t.getEmail());
            pst.setInt(5, t.getTelephone());
            pst.setFloat(6, t.getTaille());
            pst.setFloat(7, t.getPoids());
            pst.executeUpdate();
            System.out.println("Membre Modfié !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Membre> afficher() {
        ObservableList<Membre> list = FXCollections.observableArrayList();
        
        String requete = "SELECT * FROM membre";
        try {
            
            PreparedStatement pst = 
                    new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new Membre(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getFloat(6),rs.getFloat(7),rs.getString(8),rs.getString(9),rs.getInt(10))); 
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public ObservableList<Membre> userListe() {
        ObservableList<Membre> list = FXCollections.observableArrayList();
        
        String requete = "SELECT * FROM membre";
        try {
            
            PreparedStatement pst = 
                    new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Membre m =new Membre();
                m.setCin(rs.getInt("cin"));
                m.setNom(rs.getString("nom"));
                m.setPrenom(rs.getString("prenom"));
                m.setSexe(rs.getString("sexe"));
                m.setDatee(rs.getDate("datee"));
                m.setTaille(rs.getFloat("taille"));
                m.setPoids(rs.getFloat("poids"));
                m.setEmail(rs.getString("email"));
                m.setPassword(rs.getString("password"));
                m.setTelephone(rs.getInt("telephone"));
                
                
                list.add(m);
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public ObservableList<Membre> TrieParNom() {
        ObservableList<Membre> list = this.userListe();
        Collections.sort(list, new Membre());
        Collections.reverse(list);
        return list;
    }

//    @Override
//    public Membre ChercherUserParNom(String nom) {
//        List<Membre> list = this.userListe();
//        for( int i=0;i<list.size();i++){
//            if(list.get(i).getNom().equals(nom))
//                return list.get(i);
//            System.out.println("utilisateur trouver ");        
//        }
//        System.out.println("n");
//        return null;
//    }
    public int loginMembre(String email,String password){
        String requete="SELECT * FROM membre where email=? and password=? ";
        ResultSet res;
        try {
            PreparedStatement pst =
                    new MyConnection().cn.prepareStatement(requete);
            
            pst.setString(1, email);
            pst.setString(2, password);
            
            res= pst.executeQuery();
            if (res.last())
            {
                System.out.println("loged Mmebre");
                return 1;
            }
            

            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    public int RecupPwd(int cin){
        String requete="SELECT * FROM membre where cin=? ";
        ResultSet res;
        try {
            PreparedStatement pst =
                    new MyConnection().cn.prepareStatement(requete);
            
            pst.setInt(1, cin);
            
            
            res= pst.executeQuery();
            if (res.last())
            {
                System.out.println("cin Trouver");
                return 1;
            }
            

            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    public Membre getUserById(String email) {
        Membre user = null;
        String requete="SELECT * FROM membre where email=?";
        ResultSet rs;

        try {
            
            PreparedStatement pst =
                    new MyConnection().cn.prepareStatement(requete);
            pst.setString(1, email);
            rs = pst.executeQuery();
            if (rs.last())//kan il9a il user
            {
                user = new Membre(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getFloat(6), rs.getFloat(7), rs.getString(8), rs.getString(9), rs.getInt(10));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return user;
    }
    
    public Membre getUserBycin(int cin) {
        Membre user = null;
        String requete="SELECT * FROM membre where cin=?";
        ResultSet rs;

        try {
            
            PreparedStatement pst =
                    new MyConnection().cn.prepareStatement(requete);
            pst.setInt(1, cin);
            rs = pst.executeQuery();
            if (rs.last())//kan il9a il user
            {
                user = new Membre(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getFloat(6), rs.getFloat(7), rs.getString(8), rs.getString(9), rs.getInt(10));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return user;
    }
    
    public List<Membre> afficherPDF() {
        List<Membre> list =new ArrayList();
        
        String requete = "SELECT * FROM membre";
        try {
            
            PreparedStatement pst = 
                    new MyConnection().cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Membre m =new Membre();
                m.setCin(rs.getInt("cin"));
                m.setNom(rs.getString("nom"));
                m.setPrenom(rs.getString("prenom"));
                m.setSexe(rs.getString("sexe"));
                m.setDatee(rs.getDate("datee"));
                m.setTaille(rs.getFloat("taille"));
                m.setPoids(rs.getFloat("poids"));
                m.setEmail(rs.getString("email"));
                m.setPassword(rs.getString("password"));
                m.setTelephone(rs.getInt("telephone"));
                
                
                list.add(m);
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
    
      public List<Membre> ChercherListActParNom(String categorie) {
          List<Membre> l = this.afficherPDF();
          List<Membre> nl = new ArrayList<Membre>();
           for(int i=0 ; i<l.size() ; i++){
        if(l.get(i).getNom().toUpperCase().contains(categorie.toUpperCase()))
            nl.add(l.get(i)); 
       }
      return nl ;
          
    }
    
    
    
    
}
