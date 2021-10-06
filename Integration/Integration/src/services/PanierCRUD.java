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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Panier;
import utils.DataSource;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author HAMZA
 */
public class PanierCRUD implements Panierinterface<Panier>{
    public void ajouterpanier (Panier p){
        String requete = "INSERT INTO Panier (id_panier,nom_activite,prix,quantite,total)" + "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,p.getId_panier());
            pst.setString(2,p.getNom_activite());
            pst.setFloat(3,p.getPrix());
            pst.setInt(4,p.getQuantite());
            pst.setFloat(5,p.getTotal());
            pst.executeUpdate();
            //System.out.println("Panier ajoutee");
            notifsuccess("Panier ajouter");
          
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
                p.setNom_activite(rs.getString(2));
                p.setPrix(rs.getFloat(3));
                p.setQuantite(rs.getInt(4));
                p.setTotal(rs.getInt(5));
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
     public void supprimertoutpanier() {
     try {
            String requete = "TRUNCATE TABLE panier ";
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(requete);
            
            pst.executeUpdate();
            System.out.println("Panier vider");

        } catch (SQLException ex) {
            Logger.getLogger(ReservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
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
   
      public void modifierpanier (Panier p) {
        try {
            String requete = "UPDATE Panier SET id_panier='"+p.getId_panier()
                    +"',nom_activite='"+p.getNom_activite()
                    +"',prix='"+p.getPrix()
                    +"',quantite='"+p.getQuantite()
                    + "',total='"+p.getTotal()
                    + "' WHERE id_panier=" + p.getId_panier();
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(requete);              
            int rowsUpdated = pst.executeUpdate(requete);
            if (rowsUpdated > 0) {
            }
            notifsuccess("Panier Modifier");
            //System.out.println("Panier modifier");
        } catch (SQLException ex) {
            //System.err.println(ex.getMessage());
            notiferror("Error");
            
        }
    
}
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
         requete = "SELECT * from Panier where nom_activite like '%" + valeur + "%'";
         PreparedStatement st = DataSource.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Panier p = new Panier();
                p.setId_panier(rs.getInt(1));
                p.setNom_activite(rs.getString(2));
                p.setPrix(rs.getFloat(3));
                p.setQuantite(rs.getInt(4));
                p.setTotal(rs.getFloat(5));
               
                    listpanier.add(p);
            }
         
        
        if (listpanier.isEmpty()) {
            //System.out.println("Aucune reservation trouvee");
            notiferror("Aucune reservation trouvee");
        }
        return listpanier;
    
        
        
      
            }
    @Override
    public void addpanier(Panier p) {
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

    @Override
    public void deletepanier(int x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      
}