package services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import interfaces.ReclamationInterface;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javafx.util.Duration;
import models.Reclamation;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.DataSource;

/**
 *
 * @author Firas
 */
public class ReclamationCRUD implements ReclamationInterface<Reclamation> {

    @Override
    //CRUD CREE UNE RECLAMATION
    public void creeReclamation(Reclamation p) {
        String requete = "insert into reclamation(id,nom,prenom,mail,type,date,description)" + "values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(requete);
            ps.setInt(1, p.getId_rec());
            ps.setString(2, p.getNom_rec());
            ps.setString(3, p.getPrenom_rec());
            ps.setString(4, p.getEmail_rec());
            ps.setString(5, p.getType_rec());
            ps.setDate(6, p.getDate_rec());
            ps.setString(7, p.getDescription_rec());
            ps.executeUpdate();
            System.out.println("Reclamation Deposer avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //CRUD Changer etat reclamation
    @Override
    public void changerEtatReclamation(int x) {
        try {
            String s = "Traité";
            String requete = "UPDATE reclamation SET etat ='" + s
                    + "' WHERE id=" + x;
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement(requete);
            //ps.setString(1, p.getEtat_rec());
            //ps.setInt(1, x);
            ps.executeUpdate();
            System.out.println("Etat Reclamation Changer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //CRUD AFFICHER LA LISTE DE TOUTES LES RECLAMATIONS
    @Override
    public List<Reclamation> listeReclamation() {
        List<Reclamation> reclamationList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM reclamation";
            Statement st = DataSource.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Reclamation p = new Reclamation();
                p.setId_rec(rs.getInt("id"));
                p.setNom_rec(rs.getString("nom"));
                p.setType_rec(rs.getString("type"));
                p.setPrenom_rec(rs.getString("prenom"));
                p.setEmail_rec(rs.getString("mail"));
                p.setDate_rec(rs.getDate("date"));
                p.setDescription_rec(rs.getString("description"));
                p.setEtat_rec(rs.getString("etat"));
                reclamationList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamationList;
    }
    //CRUD AFFICHER LA LISTE DE TOUTES LES RECLAMATIONS D'UN UTILISATEUR X
    public List<Reclamation> listeReclamationUser(String n,String pp) {
        ArrayList<Reclamation> listReclamation = new ArrayList<>();
        try {
//            String req = "Select * from reclamation  r , membre m where r.cin_membre = m.cin and cin_membre = ?";
            String req = "Select * from reclamation WHERE nom = ? and prenom = ?";
            PreparedStatement st = DataSource.getInstance().getCnx().prepareStatement(req);
            st.setString(1, n);
            st.setString(2, pp);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Reclamation p = new Reclamation();
                p.setNom_rec(rs.getString(2));
                p.setPrenom_rec(rs.getString(3));
                p.setEmail_rec(rs.getString(4));
                p.setType_rec(rs.getString(5));
                p.setDate_rec(rs.getDate(6));
                p.setDescription_rec(rs.getString(7));
                listReclamation.add(p);
            }
            st.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listReclamation;
    }
    @Override
    public List<Reclamation> chercherReclamationUserDateInf(Date d) {
        ArrayList<Reclamation> listReclamation = new ArrayList<>();
        try {
            String req = "Select * from reclamation";
            PreparedStatement st = DataSource.getInstance().getCnx().prepareStatement(req);
            //st.setDate(1, d);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Reclamation p = new Reclamation();
                p.setNom_rec(rs.getString(2));
                p.setPrenom_rec(rs.getString(3));
                p.setEmail_rec(rs.getString(4));
                p.setType_rec(rs.getString(5));
                p.setDate_rec(rs.getDate(6));
                p.setDescription_rec(rs.getString(7));
                if (p.getDate_rec().compareTo(d) <= 0) {
                    listReclamation.add(p);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (listReclamation.isEmpty()) {
            System.out.println("Il y a aucune réservation dans cette date");
        }
        return listReclamation;
    }

    //afficher les reclamation trier selon date 
    @Override
    public List<Reclamation> trierreclamationDate() {
        ArrayList<Reclamation> listReclamation = new ArrayList<>();
        try {
            String req = "Select * from reclamation";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(req);
            Reclamation re = null;
            while (res.next()) {
                re = new Reclamation(res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getDate(6), res.getString(7), res.getString(8));
                listReclamation.add(re);
            }
            Collections.sort(listReclamation, ReclamationComparatorDate);
            Collections.reverse(listReclamation);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listReclamation;
    }
    
    //Afficher le nombre total des reclamations
    @Override
    public int nombreReclamationTotal() {
        int nb = 0;
        try {
            String req = "Select * from reclamation";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                nb = nb + 1;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb;
    }

    // A FAIRE
    @Override
    public List<Reclamation> chercherReclamationUserPeriode(Date D, Date F) {
        ArrayList<Reclamation> listReclamation = new ArrayList<>();
        try {
            String req = "Select * from reclamation";
            PreparedStatement st = DataSource.getInstance().getCnx().prepareStatement(req);
            //st.setDate(1, d);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Reclamation p = new Reclamation();
                p.setNom_rec(rs.getString(2));
                p.setPrenom_rec(rs.getString(3));
                p.setEmail_rec(rs.getString(4));
                p.setType_rec(rs.getString(5));
                p.setDate_rec(rs.getDate(6));
                p.setDescription_rec(rs.getString(7));
                Boolean a = p.getDate_rec().compareTo(F) <= 0;
                Boolean b = p.getDate_rec().compareTo(D) >= 0;
                if (a == true && b == true) {
                    listReclamation.add(p);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (listReclamation.isEmpty()) {
            System.out.println("Il y a aucune reclamation dans cette date");
        }
        return listReclamation;
    }
    public List<Reclamation> chercherReclamationUserPeriodeX(int cin,Date D, Date F) {
        ArrayList<Reclamation> listReclamation = new ArrayList<>();
        try {
            String req = "Select * from reclamation where cin_membre = ?";
            PreparedStatement st = DataSource.getInstance().getCnx().prepareStatement(req);
            st.setInt(1, cin);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Reclamation p = new Reclamation();
                p.setNom_rec(rs.getString(2));
                p.setPrenom_rec(rs.getString(3));
                p.setEmail_rec(rs.getString(4));
                p.setType_rec(rs.getString(5));
                p.setDate_rec(rs.getDate(6));
                p.setDescription_rec(rs.getString(7));
                Boolean a = p.getDate_rec().compareTo(F) <= 0;
                Boolean b = p.getDate_rec().compareTo(D) >= 0;
                if (a == true && b == true) {
                    listReclamation.add(p);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (listReclamation.isEmpty()) {
            System.out.println();
            notifERROR("Il y a aucune reclamation dans cette date");
        }
        return listReclamation;
    }

    //partie comarator Date
    public static Comparator<Reclamation> ReclamationComparatorDate = (Reclamation s1, Reclamation s2) -> {
        Date d1 = s1.getDate_rec();
        Date d2 = s2.getDate_rec();
        return d1.compareTo(d2);
    };

    public List<Reclamation> chercherReclamationNom(String type, String valeur) {
        ArrayList<Reclamation> listReclamation = new ArrayList<>();
        try {
            String requete = null;
            if (type.equals("Nom")) {
                requete = "SELECT * from reclamation where nom like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Prenom")) {
                requete = "SELECT * from reclamation where prenom like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Email")) {
                requete = "SELECT * from reclamation where mail like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Status")) {
                requete = "SELECT * from reclamation where etat ='" + valeur + "'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Tout")) {
                requete = "SELECT * from reclamation"; //MAJUSCULE NON OBLIGATOIRE 
            }
            PreparedStatement st = DataSource.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Reclamation p = new Reclamation();
                p.setNom_rec(rs.getString(2));
                p.setPrenom_rec(rs.getString(3));
                p.setEmail_rec(rs.getString(4));
                p.setType_rec(rs.getString(5));
                p.setDate_rec(rs.getDate(6));
                p.setDescription_rec(rs.getString(7));
                listReclamation.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (listReclamation.isEmpty()) {
            notifERROR("Il y a aucune réservation");
        }
        return listReclamation;
    }
        public List<Reclamation> chercherReclamationUserX(int cin,String type, String valeur) {
        ArrayList<Reclamation> listReclamation = new ArrayList<>();
        try {
            String requete = null;
            if (type.equals("Nom")) {
                requete = "SELECT * from reclamation where nom like '%" + valeur + "%' and cin_membre ='%" + cin + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Prenom")) {
                requete = "SELECT * from reclamation where prenom like '%" + valeur + "%'and cin_membre ='%" + cin + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Email")) {
                requete = "SELECT * from reclamation where mail like '%" + valeur + "%'and cin_membre ='%" + cin + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Status")) {
                requete = "SELECT * from reclamation where etat ='" + valeur + "'and cin_membre ='%" + cin + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Tout")) {
                requete = "SELECT * from reclamation where and cin_membre ='%" + cin + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            }
            PreparedStatement st = DataSource.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Reclamation p = new Reclamation();
                p.setNom_rec(rs.getString(2));
                p.setPrenom_rec(rs.getString(3));
                p.setEmail_rec(rs.getString(4));
                p.setType_rec(rs.getString(5));
                p.setDate_rec(rs.getDate(6));
                p.setDescription_rec(rs.getString(7));
                listReclamation.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (listReclamation.isEmpty()) {
            notifERROR("Il y a aucune réservation");
        }
        return listReclamation;
    }
    @Override
    public List<Reclamation> chercherReclamationUserDateSup(Date x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Reclamation> trierReclamationall(String type) {
        ArrayList<Reclamation> listReclamation = new ArrayList<>();
        try {
            String requete = null;
            if (type.equals("Nom")) {
                requete = "SELECT * from reclamation order by nom"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Prenom")) {
                requete = "SELECT * from reclamation order by prenom"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Status")) {
                requete = "SELECT * from reclamation order by etat"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Date") | type.equals("Tout")) {
                requete = "SELECT * from reclamation"; //MAJUSCULE NON OBLIGATOIRE 
            }
            PreparedStatement st = DataSource.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Reclamation p = new Reclamation();
                p.setNom_rec(rs.getString(2));
                p.setPrenom_rec(rs.getString(3));
                p.setEmail_rec(rs.getString(4));
                p.setType_rec(rs.getString(5));
                p.setDate_rec(rs.getDate(6));
                p.setDescription_rec(rs.getString(7));
                listReclamation.add(p);
            }
            if (type.equals("Date")) {
                Collections.sort(listReclamation, ReclamationComparatorDate);
                Collections.reverse(listReclamation);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (listReclamation.isEmpty()) {
            notifERROR("Il y a aucune réservation");
        }
        return listReclamation;
    }
    public List<Reclamation> trierReclamationallUserX(int cin,String type) {
        ArrayList<Reclamation> listReclamation = new ArrayList<>();
        try {
            String requete = null;
            if (type.equals("Nom")) {
                requete = "SELECT * from reclamation where cin_membre ='%" + cin + "%' order by nom"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Prenom")) {
                requete = "SELECT * from reclamation where cin_membre ='%" + cin + "%' order by prenom"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Status")) {
                requete = "SELECT * from reclamation where cin_membre ='%" + cin + "%' order by etat"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Date") | type.equals("Tout")) {
                requete = "SELECT * from reclamation where cin_membre ='%" + cin + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            }
            PreparedStatement st = DataSource.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Reclamation p = new Reclamation();
                p.setNom_rec(rs.getString(2));
                p.setPrenom_rec(rs.getString(3));
                p.setEmail_rec(rs.getString(4));
                p.setType_rec(rs.getString(5));
                p.setDate_rec(rs.getDate(6));
                p.setDescription_rec(rs.getString(7));
                listReclamation.add(p);
            }
            if (type.equals("Date")) {
                Collections.sort(listReclamation, ReclamationComparatorDate);
                Collections.reverse(listReclamation);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (listReclamation.isEmpty()) {
            notifERROR("Il y a aucune réservation");
        }
        return listReclamation;
    }
    private void notifERROR(String message){
        String title = "Congratulations";
		TrayNotification tray = new TrayNotification();
		tray.setTitle(title);
		tray.setMessage(message);
		tray.setNotificationType(NotificationType.ERROR);
		tray.showAndDismiss(Duration.seconds(3));
    }

    @Override
    public List<Reclamation> listeReclamationUser(int x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
