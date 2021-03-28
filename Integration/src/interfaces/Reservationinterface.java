/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;
import models.Reservation;

/**
 *
 * @author HAMZA
 * @param <o>
 */
public interface Reservationinterface {
    
    public void addreservation (Reservation r);
    public void delete(int x) ;
    public void deletee(Reservation u);
    public void update (Reservation r);
    public java.util.List<Reservation> getAll() ;
    public ArrayList<Reservation> FindReservationBycin(int y) ;
    public ArrayList<Reservation> TrierParnombreplaces();
    
}
