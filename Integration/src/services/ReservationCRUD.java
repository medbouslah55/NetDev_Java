/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.Reservationinterface;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Reservation;
import utils.DataSource;

/**
 *
 * @author HAMZA
 */
public class ReservationCRUD implements Reservationinterface{
//    public void addreservation (Reservation r){
//    String requete = "INSERT INTO Reservation (nom,prenom,cin_membre,date_act,nb_place)" + "VALUES(?,?,?,?,?)";
//        try {
//            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(requete);          
//            pst.setString(1, r.getNom());
//            pst.setString(2, r.getPrenom());
//            pst.setInt(3, r.getCin_membre());
//            pst.setDate(4,r.getDate_act());
//            pst.setInt(5, r.getNb_place());
//            pst.executeUpdate();
//            System.out.println("Reservation ajoutee");
//        } catch (SQLException ex) {
//            Logger.getLogger(ReservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
//        }   
//}
    public void addreservationn (Reservation r){
    String requete = "INSERT INTO Reservation (nom,prenom,date_act,nb_place)" + "VALUES(?,?,?,?)";
        try {
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(requete);          
            pst.setString(1, r.getNom());
            pst.setString(2, r.getPrenom());
            
            pst.setDate(3,r.getDate_act());
            pst.setInt(4, r.getNb_place());
            pst.executeUpdate();
            System.out.println("Reservation ajoutee");
        } catch (SQLException ex) {
            Logger.getLogger(ReservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }   
}
    @Override
    public void delete(int id) {
     try {
            String requete = "DELETE FROM Reservation where id_reservation =?";
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,id );
            pst.executeUpdate();
            System.out.println("Reservation supprimer");

        } catch (SQLException ex) {
            Logger.getLogger(ReservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    @Override
    public void deletee(Reservation uu) {
     try {
            String requete = "DELETE FROM Reservation where id_reservation =?";
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,uu.getId_reservation() );
            pst.executeUpdate();
            System.out.println("Reservation supprimer");

        } catch (SQLException ex) {
            Logger.getLogger(ReservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
//    public int deleteReservation(int id_res) throws SQLException {
//        int i = 0;
//      
//            Statement ste = cn.createStatement();
//            String sql = "delete from Reservation where id_reservation=" + id_res;
//            i = ste.executeUpdate(sql);
//        
//        return i;
//    }
    

    public void modifierreservation (Reservation r) {
        try {
            String requete = "UPDATE Reservation SET id_reservation='"+r.getId_reservation()
                    + "',nom='"+r.getNom()
                    + "',prenom='"+r.getPrenom()
                    // + "',cin_membre='"+r.getCin_membre()
                    + "',date_act='"+r.getDate_act()
                    +"',nb_place='"+r.getNb_place()
                    + "' WHERE id_reservation=" + r.getId_reservation();
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(requete);             
            int rowsUpdated = pst.executeUpdate(requete);
            if (rowsUpdated > 0) {
            }
            System.out.println("Reservation modifier");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public List<Reservation> listReservationbyid() {
        List<Reservation> ReservationList = new ArrayList<>();
        try {
            String requete = "select * from Reservation";
            Statement ab = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = ab.executeQuery(requete);
            while(rs.next()){
                Reservation r = new Reservation();
                r.setId_reservation(rs.getInt(1));
                r.setNom(rs.getString(2));
                r.setPrenom(rs.getString(3));
                r.setCin_membre(rs.getInt(4));
                r.setDate_act(rs.getDate(5));
                r.setNb_place(rs.getInt(6));
                ReservationList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ReservationList;
    }
    public List<Reservation> listReservationbyiduser(String n,String p) {
        List<Reservation> ReservationList = new ArrayList<>();
        try {
            String requete = "select * from Reservation where nom ='%" + n + "%' and prenom = '%" + p + "%'" ;
            Statement ab = DataSource.getInstance().getCnx().createStatement();
           
            ResultSet rs = ab.executeQuery(requete);
            while(rs.next()){
                Reservation r = new Reservation();
                r.setId_reservation(rs.getInt(1));
                r.setNom(rs.getString(2));
                r.setPrenom(rs.getString(3));
                r.setCin_membre(rs.getInt(4));
                r.setDate_act(rs.getDate(5));
                r.setNb_place(rs.getInt(6));
                ReservationList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ReservationList;
    }
    @Override
    public java.util.List<Reservation> getAll() {
        ArrayList<Reservation> listReservation = new ArrayList<>();
        try {
          String requete= "GETALL FROM `Reservation` WHERE `Reservations`.`id_reservation` = ? ";

            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(requete);              // howa el boustaji eli yhez requete ywasalha  lel sever w men server yjib el rep   nasn3ou a travers el cn  +s
            ResultSet rs = pst.executeQuery("Select * from Reservation");
            while (rs.next()) {
                listReservation.add(new Reservation(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getDate(5),
                        rs.getInt(6)
                ));              
            }
          pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listReservation;
    }
    //faire la recherche d'une reservation selon la cin 
    @Override
    public ArrayList<Reservation> FindReservationBycin(int cinInt)  {
  ArrayList<Reservation> listReservation = new ArrayList<>();   
   try {
          String requete= "select * from Reservation ";
        PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(requete);
        ResultSet res = pst.executeQuery();
        Reservation com = null;
        while (res.next()) { 
            com = new Reservation(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getDate(5),res.getInt(6));
            if (com.getCin_membre()-cinInt==0){
                        listReservation.add(com);
                    }
        }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
                 return listReservation ;
 
    }
    //faire la recherche d'une reservation selon la date 
     public ArrayList<Reservation> FindReservationBydate(Date d)  {
     ArrayList<Reservation> listReservation = new ArrayList<>();   
     try {
          String requete= "select * from Reservation ";
        PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(requete);
        ResultSet res = pst.executeQuery();
        Reservation com = null;
        while (res.next()) { 
            com = new Reservation(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getDate(5),res.getInt(6));
            if (com.getDate_act().compareTo(d)==0){
                        listReservation.add(com);
                    }
        }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
                 return listReservation ;
 
    }
   //faire la recherche d'une reservation selon la date de debut et date de finish  
     public ArrayList<Reservation> FindReservationfromdatetodate(Date deb,Date fi)  {
        ArrayList<Reservation> listReservation = new ArrayList<>();   
        try {
          String requete= "select * from Reservation ";
        PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(requete);
        ResultSet res = pst.executeQuery();
        Reservation com = null;
        while (res.next()) { 
            com = new Reservation(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getDate(5),res.getInt(6));
            boolean debut = com.getDate_act().compareTo(fi)<=0;
            boolean fin = com.getDate_act().compareTo(deb)>=0;
            if (debut==true && fin==true){
                listReservation.add(com);
              }
        }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
                 return listReservation ;
 
    }
 
    //comparator  du nombre de places 
    public static Comparator<Reservation> NbreplaceComparator = new Comparator<Reservation>() {

        @Override
	public int compare(Reservation r1, Reservation r2) {
            
            
            int nb_place1 = r1.getNb_place();
            int nb_place2 = r2.getNb_place();


           return nb_place1-nb_place2;

	  
    }
    };
    //comparator de dates
    public static Comparator<Reservation> dateComparator = new Comparator<Reservation>() {

        @Override
	public int compare(Reservation r1, Reservation r2) {
               
            Date d1 = r1.getDate_act();
            Date d2 = r2.getDate_act();
           return d1.compareTo(d2);
    }
    };
    //trier selon nombre de place decroissant
    public List<Reservation> trierreservationnbreplace() {
         ArrayList<Reservation> listReservaion = new ArrayList<>();
         try {
            String req = "Select * from Reservation";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(req);
            Reservation re=null;
            while(res.next()){
                re = new Reservation(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getDate(5),res.getInt(6));
                listReservaion.add(re);
            }
             Collections.sort(listReservaion, NbreplaceComparator);
             Collections.reverse(listReservaion);
             
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
         return listReservaion;
    }
    //trier selon date 
    public List<Reservation> trierreservationdate() {
         ArrayList<Reservation> listReservaion = new ArrayList<>();
         try {
            String req = "Select * from Reservation";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(req);
            Reservation re=null;
            while(res.next()){
                re = new Reservation(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getDate(5),res.getInt(6));
                listReservaion.add(re);
            }
             Collections.sort(listReservaion, dateComparator);
             Collections.reverse(listReservaion);
             
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
         return listReservaion;
    }
     public List<Reservation> chercherReservation(String type, String valeur) {
        ArrayList<Reservation> listReservation = new ArrayList<>();
        try {
            String requete = null;
            if (type.equals("Nom")) {
                requete = "SELECT * from Reservation where nom like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Prenom")) {
                requete = "SELECT * from Reservation where prenom like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Cin")) {
                requete = "SELECT * from Reservation where cin_membre like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Tout")) {
                requete = "SELECT * from Reservation"; //MAJUSCULE NON OBLIGATOIRE 
            }
            PreparedStatement st = DataSource.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Reservation r = new Reservation();
                r.setId_reservation(rs.getInt(1));
                r.setNom(rs.getString(2));
                r.setPrenom(rs.getString(3));
                r.setCin_membre(rs.getInt(4));
                r.setDate_act(rs.getDate(5));
                r.setNb_place(rs.getInt(6));
                    listReservation.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (listReservation.isEmpty()) {
            System.out.println("Il y a aucune réservation dans cette date");
        }
        return listReservation;
    }
    public List<Reservation> trier(String type) {
        ArrayList<Reservation> listReservation = new ArrayList<>();
        try {
            String requete = null;
             if (type.equals("Trie Nom")) {
                requete = "SELECT * from Reservation ORDER by nom "; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Trie Nombre places")) {
                requete = "SELECT * from Reservation ORDER by nb_place"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Trie Prenom")) {
                requete = "SELECT * from Reservation ORDER by prenom"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Trie Date")) {
                requete = "SELECT * from Reservation";
            } else if (type.equals("Tout")) {
                requete = "SELECT * from Reservation";
            }
            PreparedStatement st = DataSource.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Reservation r = new Reservation();
                r.setId_reservation(rs.getInt(1));
                r.setNom(rs.getString(2));
                r.setPrenom(rs.getString(3));
                r.setCin_membre(rs.getInt(4));
                r.setDate_act(rs.getDate(5));
                r.setNb_place(rs.getInt(6));
                    listReservation.add(r);
            }
            if (type.equals("Trie Date")) {
                Collections.sort(listReservation, dateComparator);
                Collections.reverse(listReservation);
        }} catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (listReservation.isEmpty()) {
            System.out.println("Il y a aucune réservation dans cette date");
        }
        return listReservation;
    }
        public List<Reservation> chercherReservationPeriode(Date D, Date F) {
        ArrayList<Reservation> listReservation = new ArrayList<>();
        try {
            String req = "Select * from Reservation";
            PreparedStatement st = DataSource.getInstance().getCnx().prepareStatement(req);
            //st.setDate(1, d);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Reservation r = new Reservation();
                r.setNom(rs.getString(2));
                r.setPrenom(rs.getString(3));
                r.setCin_membre(rs.getInt(4));
                r.setNb_place(rs.getInt(6));
                r.setDate_act(rs.getDate(5));
                Boolean a = r.getDate_act().compareTo(F) <= 0;
                Boolean b = r.getDate_act().compareTo(D) >= 0;
                if (a == true && b == true) {
                    listReservation.add(r);
                }
                //      x>p>y 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (listReservation.isEmpty()) {
            System.out.println("Il y a aucune reservation dans cette date");
        }
        return listReservation;
    }
        public  List<String> affichernomactivite() throws SQLException{
            ArrayList<String> listReservation = new ArrayList<>();
            PreparedStatement st = DataSource.getInstance().getCnx().prepareStatement("SELECT a.nom_act FROM activite a, reservation r WHERE r.id_act = a.id_act");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                
                    listReservation.add(rs.getString(1));
            }
        return listReservation;
            
        }



    

    

    //a faire
    public void addreservation(Object r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deletee(Object u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void update(Object r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Reservation> TrierParnombreplaces() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Reservation r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addreservation(Reservation r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}

    