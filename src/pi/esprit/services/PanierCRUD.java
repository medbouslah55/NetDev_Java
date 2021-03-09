/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.services;

import Iservices.Panierinterface;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pi.esprit.entities.Panier;
import pi.esprit.entities.Reservation;
import pi.esprit.tools.Myconnection;

/**
 *
 * @author HAMZA
 */
public class PanierCRUD implements Panierinterface<Panier>{
    public void ajouterpanier (Panier p){
        String requete = "INSERT INTO Panier (id_panier,nom_activite,prix,total)" + "VALUES(?,?,?,?)";
        try {
            PreparedStatement pst = Myconnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,p.getId_panier());
            pst.setString(2,p.getNom_activite());
            pst.setFloat(3,p.getPrix());
            pst.setFloat(4,p.getTotal());
            pst.executeUpdate();
            System.out.println("Panier ajoutee");
          
        } catch (SQLException ex) {
            Logger.getLogger(PanierCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
     public List<Panier> listPanierbyid() {
        List<Panier> PanierList = new ArrayList<>();
        try {
            String requete = "select * from Panier";
            Statement ab = Myconnection.getInstance().getCnx().createStatement();
            ResultSet rs = ab.executeQuery(requete);
            while(rs.next()){
                Panier p = new Panier();
                p.setId_panier(rs.getInt(1));
                p.setNom_activite(rs.getString(2));
                p.setPrix(rs.getInt(3));
                p.setTotal(rs.getInt(4));
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
            PreparedStatement pst = Myconnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,id );
            pst.executeUpdate();
            System.out.println("Panier supprimer");

        } catch (SQLException ex) {
            Logger.getLogger(PanierCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
     public void deletepanier(Panier pp) {
     try {
            String requete = "DELETE FROM panier where id_panier =?";
            PreparedStatement pst = Myconnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,pp.getId_panier());
            pst.executeUpdate();
            System.out.println("Panier supprimer");

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
                    + "',total='"+p.getTotal()
                    + "' WHERE id_panier=" + p.getId_panier();
            PreparedStatement pst = Myconnection.getInstance().getCnx().prepareStatement(requete);              
            int rowsUpdated = pst.executeUpdate(requete);
            if (rowsUpdated > 0) {
            }
            System.out.println("Panier modifier");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
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