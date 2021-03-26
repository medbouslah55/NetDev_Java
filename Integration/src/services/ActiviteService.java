/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IActivite;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Activite;
import utils.DataSource;

/**
 *
 * @author DELL
 */
public class ActiviteService implements IActivite<Activite> {
    
    @Override
    public void ajouterActivite(Activite t) {
       try {
            String requete = "INSERT INTO activite (categorie_act,nom_act,prix_reservation,date_act,capacite,description,id_centre,cin_coach)"
                    + "VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, t.getCategorie_act());
            pst.setString(2, t.getNom_act());
            pst.setDouble(3, t.getPrix_reservation());
            pst.setDate(4,t.getDate_act());
            pst.setInt(5, t.getCapacite());
            pst.setString(6, t.getDescription());
            pst.setInt(7, t.getId_centre());
            pst.setInt(8, t.getId_coach());
            pst.executeUpdate();
            System.out.println("Activité ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerActivite(Activite t) {
          try {
            String requete = "delete from activite where id_act=?";
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, t.getId_act());
            pst.executeUpdate();
            System.out.println("Activité supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     @Override
    public void supprimerActivite(int id ) {
        try {
            String requete = "delete from activite where id_act=?";
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Activité supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierActivite(Activite t) {
        try {
            String requete = "update activite set categorie_act='" + t.getCategorie_act() + "', nom_act='" + t.getNom_act()+
                    "',prix_reservation='" + t.getPrix_reservation()+ "',date_act='" + t.getDate_act()+ "',capacite='" 
                    + t.getCapacite()+  "',description='" + t.getDescription() + "',id_centre='"+ t.getId_centre() 
                    + "',cin_coach='" + t.getId_coach() +
                    "' where id_act="+t.getId_act();
            Statement st = DataSource.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("Activité modifiée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List ActiviteListe() {
         List<Activite> mylist = new ArrayList<Activite>();
        try {

            String requete = "SELECT * from activite";
            // Statement st = new MyConnection().getConx().createStatement();

            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            CentreService cs = new CentreService() ;
            while (rs.next()) {
                Activite a = new Activite() ;
                a.setId_act(rs.getInt("id_act"));
                a.setCategorie_act(rs.getString("categorie_act"));
                a.setNom_act(rs.getString("nom_act"));
                a.setPrix_reservation(rs.getDouble("prix_reservation"));
                a.setDate_act(rs.getDate("date_act"));
                a.setDescription(rs.getString("description"));
                a.setId_centre(rs.getInt("id_centre"));
                a.setId_coach(rs.getInt("cin_coach"));
                a.setCapacite(rs.getInt("capacite"));
                
                //nom centre 
                a.setNom_centre(cs.getCentre(rs.getInt("id_centre")).getNom_centre());
             
                 
              
                 //lajout dans la liste
                mylist.add(a);
            }

            int i=0 ;
          while(i<mylist.size()){
            
              //nom coach
                 String req2 = "SELECT * from coach where cin ="+mylist.get(i).getId_coach();
                 String nom = null ;
                 ResultSet rs2 = st.executeQuery(req2);
                 while (rs2.next()) {
                 nom= rs2.getString("nom")+" "+ rs2.getString("prenom");
                  
                 }
                 Activite a = new Activite() ;
                 a= mylist.get(i);    
                 a.setNom_coach(nom);
                 mylist.set(i,a);
                i++ ;
            
            }
            
            
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return mylist;
    }

    @Override
    public Activite getActivite(int id) {
         Activite a = new Activite() ;
        try {

            String requete = "SELECT * from activite where id_act="+id;
            // Statement st = new MyConnection().getConx().createStatement();

            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
          
                a.setId_act(rs.getInt("id_act"));
                a.setCategorie_act(rs.getString("categorie_act"));
                a.setNom_act(rs.getString("nom_act"));
                a.setPrix_reservation(rs.getDouble("prix_reservation"));
                a.setDate_act(rs.getDate("date_act"));
                a.setDescription(rs.getString("description"));
                a.setId_centre(rs.getInt("id_centre"));
                a.setId_coach(rs.getInt("id_coach"));
                a.setCapacite(rs.getInt("capacite"));
             
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return a ;
    }

    @Override
    public Activite ChercherActParNom(String nom) {
          List<Activite> l = this.ActiviteListe();
      for(int i=0 ; i<l.size() ; i++){
        if(l.get(i).getNom_act().equals(nom))
            return l.get(i); 
       }
      return null ;
    }

    
    
    
    //retourner une liste qui contient tt les elemennts qui contains une chaine qlconque
    @Override
    public Activite ChercherActParCategorie(String categorie) {
       List<Activite> l = this.ActiviteListe();
      for(int i=0 ; i<l.size() ; i++){
        if(l.get(i).getCategorie_act().toUpperCase().contains(categorie.toUpperCase()))
            return l.get(i); 
       }
      return null ;
    }

    @Override
    public List<Activite> CentreActTrierParPrix() {
          List<Activite> l = this.ActiviteListe() ;
         Collections.sort(l, new Activite());
         return l ;
    }

    @Override
    public List<Activite> ActListeTrierParPrixReverse() {
          List<Activite> l = this.ActiviteListe() ;
         Collections.sort(l, new Activite());
         Collections.reverse(l);
         return l ;
    }

    @Override
    public List<Activite> ChercherListActParCategorie(String categorie) {
          List<Activite> l = this.ActiviteListe();
          List<Activite> nl = new ArrayList<Activite>();
           for(int i=0 ; i<l.size() ; i++){
        if(l.get(i).getCategorie_act().toUpperCase().contains(categorie.toUpperCase()))
            nl.add(l.get(i)); 
       }
      return nl ;
          
    }

    @Override
    public ResultSet StatCategorie() {
        ResultSet rs = null ;
        try {
            String requete = "SELECT categorie_act , COUNT(categorie_act) as 'total' FROM activite GROUP BY categorie_act";
            // Statement st = new MyConnection().getConx().createStatement();

            Statement st = DataSource.getInstance().getCnx().createStatement();
            rs = st.executeQuery(requete);
            return rs; 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         return rs; 
    }

    @Override
    public List<Activite> listeAct_Par_Centre(int id) {
          List<Activite> l = this.ActiviteListe();
          List<Activite> nl = new ArrayList<Activite>();
           for(int i=0 ; i<l.size() ; i++){
        if(l.get(i).getId_centre()==id)
            nl.add(l.get(i)); 
       }
      return nl ;
    }

    @Override
    public List<Activite> ChercherListActParCategorie(String categorie, List<Activite> l2) {
          List<Activite> l = l2;
          List<Activite> nl = new ArrayList<Activite>();
           for(int i=0 ; i<l.size() ; i++){
        if(l.get(i).getCategorie_act().toUpperCase().contains(categorie.toUpperCase()))
            nl.add(l.get(i)); 
       }
      return nl ;
    }
    
}
