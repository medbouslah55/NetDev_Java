/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.Status;
import interfaces.IStatus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author mehdi
 */
public class ServiceStatus implements IStatus<Status> {

    Connection cnx = DataSource.getInstance().getCnx();
    private Status ew;

    @Override
    public void ajouter(Status t) {

        String requete = "INSERT INTO status (date_pub,text,id_centre) VALUES (?,?,?)";
        try {

            PreparedStatement pst = cnx.prepareStatement(requete);
            // pst.setInt(1,t.getId_pub());
            pst.setDate(1, t.getDate_pub());
            pst.setString(2, t.getText());
            pst.setInt(3, t.getId_centre());
            pst.executeUpdate();
            System.out.println("Status Ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Status t) {
        try {
            String requete = "DELETE FROM status WHERE id_pub=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId_pub());
            pst.executeUpdate();
            System.out.println("Status Supprimée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Status t) {
        try {
            String requete = "UPDATE status SET date_pub=?, text=? WHERE id_pub=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(3, t.getId_pub());
            pst.setDate(1, t.getDate_pub());
            pst.setString(2, t.getText());
            pst.executeUpdate();
            System.out.println("status Modfié !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<Status> afficher() {

        ObservableList<Status> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM Status";
            Statement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                // Status s  =new Status(rs.getInt(1), rs.getDate(2),rs.getString(3)) ;
                Status s = new Status();
                s.setId_pub(rs.getInt("id_pub"));
                s.setDate_pub(rs.getDate("date_pub"));
                s.setText(rs.getString("text"));
//                s.setText(rs.getString("id_centre"));

                list.add(s);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public ObservableList<Status> getTrierParDATE_PUB() throws SQLException {
        ObservableList<Status> arr = FXCollections.observableArrayList();
        String req = "select * from status ORDER BY date_pub DESC ";
        PreparedStatement pre = cnx.prepareStatement(req);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {

            Status s = new Status(rs.getDate(2), rs.getString(3), rs.getInt(1));
            arr.add(s);
        }

        System.out.println("trié avec succés!! ");
        return arr;
    }
}
