/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.ICentre;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Centre;
import utils.DataSource;

/**
 *
 * @author DELL
 */
public class CentreService implements ICentre<Centre> {

    @Override
    public void ajouterCentre(Centre t) {
        try {
            String req = "insert into  centre (nom_centre,tel_centre,mail_centre,adr_centre,type_centre)"
                    + "values('" + t.getNom_centre() + "','" + t.getTel_centre() + "','" + t.getMail_centre() + "','" + t.getAdr_centre() + "','" + t.getType_centre()
                    + "')";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Centre Ajouté");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void ajouterCentre2(Centre t) {
        try {
            String requete = "INSERT INTO centre (nom_centre,tel_centre,mail_centre,adr_centre,type_centre)"
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, t.getNom_centre());
            pst.setString(2, t.getTel_centre());
            pst.setString(3, t.getMail_centre());
            pst.setString(4, t.getAdr_centre());
            pst.setString(5, t.getType_centre());
            pst.executeUpdate();
            System.out.println("Centre ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerCentre(int t) {
        try {
            String requete = "delete from centre where id_centre=?";
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, t);
            pst.executeUpdate();
            System.out.println("Centre supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerCentre(Centre t) {
        try {
            String requete = "delete from centre where id_centre=?";
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, t.getId_centre());
            pst.executeUpdate();
            System.out.println("Centre supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierCentre(Centre t) {
        try {
            String req = "update centre set nom_centre='" + t.getNom_centre() + "', tel_centre='" + t.getTel_centre() + "',mail_centre='" + t.getMail_centre() + "',adr_centre='" + t.getAdr_centre() + "',type_centre='" + t.getType_centre()
                    + "' where id_centre=" + t.getId_centre();
            Statement st = DataSource.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Centre modifié");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Centre> CentreListe() {
        List<Centre> mylist = new ArrayList<Centre>();
        try {

            String req = "SELECT * from centre";
            // Statement st = new MyConnection().getConx().createStatement();

            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Centre c = new Centre();
                c.setId_centre(rs.getInt("id_centre"));
                c.setAdr_centre(rs.getString("adr_centre"));
                c.setMail_centre(rs.getString("mail_centre"));
                c.setTel_centre(rs.getString("tel_centre"));
                c.setType_centre(rs.getString("type_centre"));
                c.setNom_centre(rs.getString("nom_centre"));

                mylist.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return mylist;
    }

    @Override
    public Centre getCentre(int id) {
        Centre c = new Centre();
        try {

            String req = "SELECT * from centre where id_centre=" + id;
            // Statement st = new MyConnection().getConx().createStatement();

            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                c.setId_centre(rs.getInt("id_centre"));
                c.setAdr_centre(rs.getString("adr_centre"));
                c.setMail_centre(rs.getString("mail_centre"));
                c.setTel_centre(rs.getString("tel_centre"));
                c.setType_centre(rs.getString("type_centre"));
                c.setNom_centre(rs.getString("nom_centre"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return c;
    }

    @Override //n'est pas testé
    public Centre ChercherCentreParNom(String nom) {
        List<Centre> l = this.CentreListe();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getNom_centre().equals(nom)) {
                return l.get(i);
            }
        }
        return null;
    }

    @Override //n'est pas testé 
    public List<Centre> CentreListeTrierParNom() {
        List<Centre> l = this.CentreListe();
        Collections.sort(l, new Centre());
        return l;
    }

    @Override
    public List<Centre> CentreListeTrierParNomReverse() {
        List<Centre> l = this.CentreListe();
        Collections.sort(l, new Centre());
        Collections.reverse(l);
        return l;
    }

    @Override
    public List<Centre> ChercherListCentreParNom(String categorie) {
        List<Centre> l = this.CentreListe();
        List<Centre> nl = new ArrayList<Centre>();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getNom_centre().toUpperCase().contains(categorie.toUpperCase())) {
                nl.add(l.get(i));
            }
        }
        return nl;

    }

    @Override
    public List<String> getMails() {
         List<Centre> l = this.CentreListe();
          List<String> nl = new ArrayList<String>();
           for(int i=0 ; i<l.size() ; i++){
           nl.add(l.get(i).getMail_centre()); 
       }
      return nl ;
    }

    @Override
    public Centre ChercherCentreParMail(String mail) {
         List<Centre> l = this.CentreListe() ;
      for(int i=0 ; i<l.size() ; i++){
        if(l.get(i).getMail_centre().equals(mail))
            return l.get(i); 
       }
      return null ;
    }

    @Override
    public Centre ChercherCentreParMail(String mail, int id) {
      List<Centre> l = this.CentreListe() ;
      for(int i=0 ; i<l.size() ; i++){
        if(l.get(i).getId_centre()!=id)
        {if(l.get(i).getMail_centre().equals(mail))
            return l.get(i); }
       }
      return null ;
    }

    @Override
    public Centre ChercherCentreParNom(String nom, int id) {
      List<Centre> l = this.CentreListe() ;
      for(int i=0 ; i<l.size() ; i++){
        if(l.get(i).getId_centre()!=id)
        {if(l.get(i).getNom_centre().equals(nom))
            return l.get(i); }
       }
      return null ;
    }
}
