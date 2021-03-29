/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Coach;
import utils.DataSource;

/**
 *
 * @author mohamedbouslah
 */
public class CoachServices implements IUser<Coach> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Coach t) {
        String requete = "INSERT INTO coach(cin,nom,prenom,sexe,datee)"
                + "VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getCin());
            pst.setString(2, t.getNom());
            pst.setString(3, t.getPrenom());
            pst.setString(4, t.getSexe());
            pst.setDate(5, t.getDatee());
            pst.executeUpdate();
            System.out.println("Coach ajoutee !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Coach t) {
        String requete = "DELETE FROM coach WHERE cin=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getCin());
            pst.executeUpdate();
            System.out.println("Coach Supprimée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Coach t) {
        String requete = "UPDATE coach SET nom=?,prenom=?  WHERE cin=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(3, t.getCin());
            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());

            pst.executeUpdate();
            System.out.println("Coach Modfié !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Coach> afficher() {
        ObservableList<Coach> list = FXCollections.observableArrayList();

        String requete = "SELECT * FROM coach";
        try {

            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new Coach(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public ObservableList<Coach> userListe() {
        ObservableList<Coach> list = FXCollections.observableArrayList();

        String requete = "SELECT * FROM coach";
        try {

            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Coach c = new Coach();
                c.setCin(rs.getInt("cin"));
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("prenom"));
                c.setSexe(rs.getString("sexe"));
                c.setDatee(rs.getDate("datee"));

                list.add(c);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public ObservableList<Coach> TrieParNom() {
        ObservableList<Coach> list = this.userListe();
        Collections.sort(list, new Coach());
        Collections.reverse(list);
        return list;
    }

}
