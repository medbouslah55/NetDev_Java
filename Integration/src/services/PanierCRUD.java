/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.Panierinterface;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Panier;

import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.DataSource;

/**
 *
 * @author HAMZA
 */
public class PanierCRUD implements Panierinterface<Panier>{
    public void ajouterpanier (Panier p){
        String requete = "INSERT INTO Panier (nom_act,prix,capacite)" + "VALUES(?,?,?)";
        try {
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(requete);
            
            pst.setString(1,p.getNom_act());
            pst.setFloat(2,p.getPrix());
            
            pst.setFloat(3,p.getCapacite());
            pst.executeUpdate();
            //System.out.println("Panier ajoutee");
            notifsuccess("Activite ajouter au panier");
          
        } catch (SQLException ex) {
            Logger.getLogger(PanierCRUD.class.getName()).log(Level.SEVERE, null, ex);
            notiferror("Error");
        }
        
}
     public List<Panier> listPanierbyid() {
        List<Panier> PanierList = new ArrayList<>();
        try {
            String requete = "select * from Panier";
            Statement ab = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = ab.executeQuery(requete);
            while(rs.next()){
                Panier p = new Panier();
                p.setId_panier(rs.getInt(1));
                p.setNom_act(rs.getString(2));
                p.setPrix(rs.getFloat(3)); 
                p.setCapacite(rs.getInt(4));
                PanierList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return PanierList;
    }
    public void supprimerpanier(int id) {
     try {
            String requete = "DELETE FROM Panier where id_panier =?";
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,id );
            pst.executeUpdate();
            //System.out.println("Panier supprimer");
            notifsuccess("Panier supprimer");

        } catch (SQLException ex) {
            Logger.getLogger(PanierCRUD.class.getName()).log(Level.SEVERE, null, ex);
            notiferror("Error");
        }   
    }
//     public void supprimertoutpanier() {
//     try {
//            String requete = "DELETE TABLE panier ";
//            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(requete);
//            
//            pst.executeUpdate();
//            System.out.println("Panier vider");
//
//        } catch (SQLException ex) {
//            Logger.getLogger(PanierCRUD.class.getName()).log(Level.SEVERE, null, ex);
//        }   
//    }
//         List<Panier> panierList = new ArrayList<>();
//        try {
//            String requete= "GETALL FROM `Panier` WHERE `Panier`.`id_panier` = ? ";
//           PreparedStatement pst = Myconnection.getInstance().getCnx().prepareStatement(requete);
//            ResultSet rs =  pst.executeQuery("Select * from Panier");
//            while(rs.next()){
//                panierList.add(new Panier(
//                        rs.getInt(1),
//                        rs.getDouble(2)
//                        )); 
//            }
//            pst.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(PanierCRUD.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return panierList;
//    }
   
//      public void modifierpanier (Panier p) {
//        try {
//            String requete = "UPDATE Panier SET id_panier='"+p.getId_panier()
//                    +"',nom_activite='"+p.getNom_activite()
//                    +"',prix='"+p.getPrix()
//                    
//                    + "',total='"+p.getTotal()
//                    + "' WHERE id_panier=" + p.getId_panier();
//            PreparedStatement pst = Myconnection.getInstance().getCnx().prepareStatement(requete);              
//            int rowsUpdated = pst.executeUpdate(requete);
//            if (rowsUpdated > 0) {
//            }
//            notifsuccess("Panier Modifier");
//            //System.out.println("Panier modifier");
//        } catch (SQLException ex) {
//            //System.err.println(ex.getMessage());
//            notiferror("Error");
//            
//        }
//    
//}
                private void notifsuccess(String message){
        String title = "Congratulations";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(javafx.util.Duration.seconds(3));
    }
           private void notiferror(String message){
        String title = "Failed";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.ERROR);
        tray.showAndDismiss(javafx.util.Duration.seconds(3));
    }
            public ArrayList<Panier> chercherPanier(String valeur) throws SQLException {
            ArrayList<Panier> listpanier = new ArrayList<>();
         String requete = null;
         requete = "SELECT * from Panier where nom_act like '%" + valeur + "%'";
         PreparedStatement st = DataSource.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Panier p = new Panier();
                p.setId_panier(rs.getInt(1));
                p.setNom_act(rs.getString(2));
                p.setPrix(rs.getFloat(3));      
                p.setCapacite(rs.getInt(4));
               
                    listpanier.add(p);
            }
        if (listpanier.isEmpty()) {
            //System.out.println("Aucune reservation trouvee");
            notiferror("Aucune reservation trouvee");
        }
        return listpanier;}
       public List<Panier> trierprix() throws SQLException {
        ArrayList<Panier> listPanier = new ArrayList<>();
       
            String requete = null;
             
                requete = "SELECT * from Panier ORDER by prix "; //MAJUSCULE NON OBLIGATOIRE 
            
            
            PreparedStatement st = DataSource.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Panier p = new Panier();
                p.setId_panier(rs.getInt(1));
                p.setNom_act(rs.getString(2));
                p.setPrix(rs.getFloat(3));
                p.setCapacite(rs.getInt(4));
                    listPanier.add(p);
            }
            
        if (listPanier.isEmpty()) {
            System.out.println("Panier vide");
        }
        return listPanier;
    }
            public  List<String> afficherprixreservation() throws SQLException{
            ArrayList<String> listpanier = new ArrayList<>();
            PreparedStatement st = DataSource.getInstance().getCnx().prepareStatement("SELECT prix_reservation FROM activite a,panier p WHERE a.id_act = p.id_act");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                
                    listpanier.add(rs.getString(1));
            }
        return listpanier;
            
        }

    @Override
    public void addpanier(Panier p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletepanier(int x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Panier> displaypanier() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updatepanier(Panier p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
        
      
            

 