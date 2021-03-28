package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Admin;
import utils.DataSource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author mohamedbouslah
 */
public class AdminServices {

    Connection cnx = DataSource.getInstance().getCnx();

    public void modifier(Admin t) {
        String requete = "UPDATE admin SET nom=?,prenom=? ,password=? ,email=? ,telephone=? WHERE cin=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(5, t.getCin());
            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.setString(3, t.getPassword());
            pst.setString(4, t.getEmail());

            pst.executeUpdate();
            System.out.println("Coach Modfié !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<Admin> afficher() {
        List<Admin> list = new ArrayList<>();

        String requete = "SELECT * FROM admin";
        try {

            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new Admin(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public int loginAdmin(String email, String password) {
        String requete = "SELECT * FROM admin where email=? and password=? ";
        ResultSet res;
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, email);
            pst.setString(2, password);

            res = pst.executeQuery();
            if (res.last()) {
                System.out.println("loged Admin");
                return 1;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

}
