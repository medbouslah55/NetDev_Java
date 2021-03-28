/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.AbonnementUser;
import utils.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Firas
 */
public class AbonnementUserCRUD {
    
    public void ajouterAbonnementUser(AbonnementUser a) {
        String requete = "insert into abonnementuser(nom,prenom,nbmois,dateD,type)" + "values(?,?,?,?,?)";
        try {
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(requete);
            ps.setString(1, a.getNom());
            ps.setString(2, a.getPrenom());
            ps.setString(5, a.getType());
            ps.setInt(3, a.getNb_mois());
            ps.setDate(4, a.getDateD());
            ps.executeUpdate();
            System.out.println("Abonnement Acheter avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
