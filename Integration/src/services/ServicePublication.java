/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IStatus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Evenement;
import utils.DataSource;

/**
 *
 * @author mehdi
 */
public class ServicePublication implements IStatus<Evenement> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Evenement t) {

        String requete = "INSERT INTO evenement (id_pub, date_pub,date_even,image,sujet,id_centre) VALUES (?,?,?,?,?,?)";
        try {

            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId_pub());
            pst.setDate(2, t.getDate_pub());
            pst.setDate(3, t.getDate_even());
            pst.setString(4, t.getImage());
            pst.setString(5, t.getSujet());
            pst.setInt(6, t.getId_centre());
            pst.executeUpdate();
            System.out.println("Evenement Ajoutée !");

        } catch (SQLException ex) {
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
        } catch (SQLException ex) {
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
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Evenement> afficher() {

        ObservableList<Evenement> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT date_pub,date_even,image,sujet,id_centre,id_pub  FROM evenement";
            Statement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Evenement x = new Evenement(rs.getDate(2), rs.getString(4), rs.getString(3), rs.getInt(5), rs.getDate(1));
                x.setId_pub(rs.getInt(6));
                list.add(x);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public List<Evenement> RecherchePardate_pub(String search) {
        String req = "SELECT * FROM evenement WHERE date_pub=" + (char) 34 + search + (char) 34;
        List<Evenement> list = new ArrayList<>();
        try {
            PreparedStatement pre = cnx.prepareStatement(req);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                list.add(new Evenement(rs.getDate(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
            }
            System.out.println("Evenement trouvé !");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }

    /*
public List<Evenement> FindReservationBydate(Date d)  {
  ArrayList<Reservation> listReservation = new ArrayList<>();   
   try {
          String requete= "select * from Evenement ";
        PreparedStatement pst = Myconnection.getInstance().getCnx().prepareStatement(requete);
        ResultSet res = pst.executeQuery();
        Reservation com = null;
        while (res.next()) { 
            com = new Reservation(res.getInt(1),res.getInt(2),res.getDate(3),res.getInt(4));
            if (com.getDate_act().compareTo(d)==0){
                        Evenement.add(com);
                    }
        }
        } catch (SQLException ex) {
            Logger.getLogger(Evenement.class.getName()).log(Level.SEVERE, null, ex);
        }
                 return listReservation ;
 
    }
     */
    public ObservableList<Evenement> getTrierParDATE_PUB() throws SQLException {
        ObservableList<Evenement> arr = FXCollections.observableArrayList();
        String req = "select * from evenement ORDER BY date_pub DESC ";
        PreparedStatement pre = cnx.prepareStatement(req);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {

            Evenement e = new Evenement(rs.getDate(2), rs.getString(4), rs.getString(5), rs.getInt(1), rs.getDate(3));
            arr.add(e);
        }

        System.out.println("trié avec succés!! ");
        return arr;
    }

    public void sendPDF() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Evenement> afficherPDF() {

        ArrayList<Evenement> list = new ArrayList<>();

        try {
            String requete = "SELECT date_pub,date_even,image,sujet,id_centre  FROM evenement";
            Statement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new Evenement(rs.getDate(2), rs.getString(4), rs.getString(3), rs.getInt(5), rs.getDate(1)));

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
    
    public ObservableList<Evenement> userListe() {
        ObservableList<Evenement> list = FXCollections.observableArrayList();
        
        String requete = "SELECT * FROM evenement";
        try {
            
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new Evenement(rs.getDate(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
    
    public ObservableList<Evenement> TrieParNom() {
        ObservableList<Evenement> list = this.userListe();
        Collections.sort(list, new Evenement());
        Collections.reverse(list);
        return list;
    }

}
