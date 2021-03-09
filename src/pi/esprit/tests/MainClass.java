/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.tests;
import java.sql.Date;
import pi.esprit.entities.Panier;
import pi.esprit.entities.Reservation;
import pi.esprit.services.PanierCRUD;
import pi.esprit.services.ReservationCRUD;
import pi.esprit.tools.Myconnection;

/**
 *
 * @author HAMZA
 */
public class MainClass {
    public static void main(String[] args) {
        String str="2030-10-30";  
        Date date1 = Date.valueOf(str);
        String str2="2020-12-10";  
        Date date2 = Date.valueOf(str2);
        String str3="2050-05-20";  
        Date date3 = Date.valueOf(str3);
        ReservationCRUD rc = new ReservationCRUD();
        PanierCRUD pc = new PanierCRUD();
       //Reservation R1 = new Reservation(111, date1,98);
       //rc.addreservation(R1);
       //rc.delete(3);
       //Reservation R1= new Reservation(99, date1,55);
       //Reservation R2= new Reservation(77, date1,999);
       //rc.addreservation(R1);
       //rc.addreservation(R2);
       //rc.update(R2);
       //rc.update(R8);    //Update reservation
       //rc.getAll().forEach(e->{System.out.println(e);});    //Affichage reservation
       //Panier P1 = new Panier(1,22);
       //rc.deletee(R2);        //supprimer reservation
       //pc.addpanier(P1);
       //pc.deletepanier(1);
       //pc.displaypanier().forEach(e->{System.out.println(e);});
       //rc.FindReservationBycin(66).forEach(e->System.out.println(e));
       //Reservation R2= new Reservation(2,33, date1,0);
       //Reservation R3= new Reservation(3,44, date2,30);
       //Reservation R4= new Reservation(4,66, date3,20);
       //rc.addreservation(R2);
       //rc.addreservation(R3);
       //rc.addreservation(R4);
       //rc.deletee(R2);
       //rc.TrierParnombreplaces().forEach(e->{System.out.println(e);});
       Panier P1 = new Panier(1,"ccc",1,11);
       //Panier P3 = new Panier(5,99);
       //pc.addpanier(P2);
       //pc.addpanier(P3);
       //pc.updatepanier(P3);
       //pc.deletepanier(4);
       //rc.deletee(R3);
       //rc.update(R2);
       //rc.getAll().forEach(e->{System.out.println(e);});;
       rc.trierreservationdate().forEach(e->System.out.println(e));
       //rc.trierreservationnbreplace().forEach(e->System.out.println(e));
       //rc.FindReservationfromdatetodate(date1,date3).forEach(e->System.out.println(e));
       //pc.ajouterpanier(P1);
       //pc.modifierpanier(P1);
       //pc.supprimerpanier(1);
       //pc.listPanierbyid().forEach(e->{System.out.println(e);});
       
    }
}
