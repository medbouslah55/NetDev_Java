/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IService;
import utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Menu;

/**
 *
 * @author trabe
 */
public class ServiceMenu implements IService<Menu> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Menu t) {
        try {
            String requete = "INSERT INTO menu (description, num_jour, matin, matin_img, dejeuner, dejeuner_img, dinner, dinner_img, total_calories, id_regime) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getDescirption());
            pst.setInt(2, t.getNum_jour());
            pst.setString(3, t.getMatin());
            pst.setString(4, t.getMatin_img());
            pst.setString(5, t.getDejeuner());
            pst.setString(6, t.getDejeuner_img());
            pst.setString(7, t.getDinner());
            pst.setString(8, t.getDinner_img());
            pst.setInt(9, t.getTotal_calories());
            pst.setInt(10, t.getId_regime());
            pst.executeUpdate();
            System.out.println("Menu Ajoutée!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Menu t) {
        try {
            String requete = "DELETE FROM menu WHERE id_menu=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Menu Supprimée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Menu t) {
        try {
            String requete = "UPDATE menu SET description=?, num_jour=?, matin=?, matin_img=?, dejeuner=?, dejeuner_img=?, dinner=?, dinner_img=?, total_calories=?, id_regime=? WHERE id_menu=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getDescirption());
            pst.setInt(2, t.getNum_jour());
            pst.setString(3, t.getMatin());
            pst.setString(4, t.getMatin_img());
            pst.setString(5, t.getDejeuner());
            pst.setString(6, t.getDejeuner_img());
            pst.setString(7, t.getDinner());
            pst.setString(8, t.getDinner_img());
            pst.setInt(9, t.getTotal_calories());
            pst.setInt(10, t.getId_regime());
            pst.setInt(11, t.getId());
            pst.executeUpdate();
            System.out.println("Menu Modfié !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Menu> afficher() {
        ObservableList<Menu> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM menu";
            Statement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);

            while (rs.next()) {
                list.add(new Menu(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public List<Menu> afficherPDF() {
        List<Menu> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM menu";
            Statement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);

            while (rs.next()) {
                list.add(new Menu(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public List<Menu> ChercherListActParCategorie(String categorie) {
        List<Menu> l = this.afficherPDF();
        List<Menu> nl = new ArrayList<>();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getDescirption().toUpperCase().contains(categorie.toUpperCase())) {
                nl.add(l.get(i));
            }
        }
        return nl;

    }
}
