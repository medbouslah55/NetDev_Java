/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.Abonnement;
import interfaces.AbonnementInterface;
import utils.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Firas
 */
public class AbonnementCRUD implements AbonnementInterface<Abonnement> {

    @Override
    //CRUD AJOUTER ABONNEMENT ADMIN
    public void ajouterAbonnement(Abonnement a) {
        String requete = "insert into abonnement(titre,type,prix,descr_ab)" + "values(?,?,?,?)";
        try {
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(requete);
            ps.setString(1, a.getTitre_ab());
            ps.setString(2, a.getType_ab());
            ps.setFloat(3, a.getPrix_ab());
            ps.setString(4, a.getDesc_ab());
            ps.executeUpdate();
            System.out.println("Abonnement deposer avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //CRUD SUPPRIMER ABONNEMENT ADMIN
    @Override
    public void supprimerAbonnement(Abonnement a) {
        try {
            String requete = "delete from abonnement where id=?";
            PreparedStatement pst = DataSource.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, a.getId_ab());
            pst.executeUpdate();
            System.out.println("Abonnement supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //CRUD SUPPRIMER ABONNEMENT A TRAVERS ID
    @Override
    public void supprimerAbonnementById(int a) {
        try {
            String requete = "delete from abonnement where id=?";
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, a);
            pst.executeUpdate();
            System.out.println("Abonnement supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //CRUD MODIFIER ABONNEMENT ADMIN
    @Override
    public void modifierAbonnement(Abonnement t, int y) {
        try {
            String requete = "UPDATE abonnement SET titre='" + t.getTitre_ab()
                    + "',type='" + t.getType_ab()
                    + "',prix='" + t.getPrix_ab()
                    + "',descr_ab='" + t.getDesc_ab()
                    + "' WHERE id=" + y;
            PreparedStatement pst = DataSource.getInstance().getCnx()
                    .prepareStatement(requete);
            int rowsUpdated = pst.executeUpdate(requete);
            if (rowsUpdated > 0) {
                System.out.println("La modification a été éffectué");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    //CRUD AFFICHER LA LISTE DE TOUTES LES ABONNEMENTS ADMIN

    @Override
    public List<Abonnement> listAbonnement() {
        List<Abonnement> abonnementList = new ArrayList<>();
        try {
            String requete = "select * from abonnement";
            Statement ab = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = ab.executeQuery(requete);
            while (rs.next()) {
                Abonnement a = new Abonnement();
                a.setId_ab(rs.getInt("id"));
                a.setTitre_ab(rs.getString("titre"));
                a.setType_ab(rs.getString("type"));
                a.setPrix_ab(rs.getFloat("prix"));
                a.setDesc_ab(rs.getString("descr_ab"));
                abonnementList.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return abonnementList;
    }

    //CRUD AFFICHER LISTE DES ABONNEMENTS SELON TYPE
    @Override
    public List<Abonnement> afficherAbonnementDeTypeX(String x) {
        ArrayList<Abonnement> listAbonnementTypeX = new ArrayList<>();
        try {
            String req = "Select * from abonnement";
            PreparedStatement st = DataSource.getInstance().getCnx().prepareStatement(req);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Abonnement a = new Abonnement();
                a.setTitre_ab(rs.getString(2));
                a.setType_ab(rs.getString(3));
                a.setPrix_ab(rs.getFloat(4));
                a.setDesc_ab(rs.getString(5));
                if (a.getType_ab().contains(x)) {
                    listAbonnementTypeX.add(a);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (listAbonnementTypeX.isEmpty()) {
            System.out.println("Il y a aucune abonnement de ce type");
        }
        return listAbonnementTypeX;
    }

    //CRUD AFFICHER NOMBRE TOTAL DES ABONNEMENT
    @Override
    public int nbAbonnementTotal() {
        int nbtotal = 0;
        try {
            String req = "Select * from abonnement";
            PreparedStatement st = DataSource.getInstance().getCnx().prepareStatement(req);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                nbtotal = nbtotal + 1;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (nbtotal == 0) {
            System.out.println("Il y a aucune abonnement");
        }
        return nbtotal;
    }

    // A FAIRE
    @Override
    public int nbAbonnementTypeX(String x) {
        int nb = 0;
        try {
            String req = "Select * from abonnement";
            PreparedStatement st = DataSource.getInstance().getCnx().prepareStatement(req);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                if (rs.getString(3).compareTo(x) == 0) {
                    nb = nb + 1;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (nb == 0) {
            System.out.println("Il y a aucune abonnement de ce type");
        }
        return nb;
    }

    @Override
    public Float budgetAbonnement() {
        float nb = 0;
        try {
            String req = "Select * from abonnement";
            PreparedStatement st = DataSource.getInstance().getCnx().prepareStatement(req);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                nb = nb + rs.getFloat(6);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (nb == 0) {
            System.out.println("Le budget est 0");
        }
        return nb;
    }

    public int deleteabonnement(int id_res) throws SQLException {

        Statement ste = DataSource.getInstance().getCnx().createStatement();
        String sql = "delete from abonnement where id=" + id_res;
        int i;
        i = ste.executeUpdate(sql);
        return i;
    }
    public List<Abonnement> TrierAbonnementTous(String type) {
        ArrayList<Abonnement> listReclamation = new ArrayList<>();
        try {
            String requete = null;
            switch (type) {
                case "Titre":
                    requete = "SELECT * from abonnement order by titre"; //MAJUSCULE NON OBLIGATOIRE 
                    break;
                case "Prix":
                    requete = "SELECT * from abonnement order by prix"; //MAJUSCULE NON OBLIGATOIRE 
                    break;
                case "Type":
                    requete = "SELECT * from abonnement order by type"; //MAJUSCULE NON OBLIGATOIRE 
                    break;
                case "Tout":
                    requete = "SELECT * from abonnement"; //MAJUSCULE NON OBLIGATOIRE 
                    break;
                default:
                    break;
            }
            PreparedStatement st = DataSource.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Abonnement a = new Abonnement();
                a.setTitre_ab(rs.getString(2));
                a.setType_ab(rs.getString(3));
                a.setPrix_ab(rs.getFloat(4));
                a.setDesc_ab(rs.getString(5));
                listReclamation.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (listReclamation.isEmpty()) {
            System.out.println("Il y a aucune Abonnement");
        }
        return listReclamation;
    }
}
