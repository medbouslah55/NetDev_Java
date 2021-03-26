/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import java.util.Comparator;


/**
 *
 * @author DELL
 */
public class Activite implements Comparator<Activite>  {
    private int id_act ;
    private String categorie_act;
    private String nom_act ;
    private double prix_reservation ;
    private Date date_act ;
    private String description ;
    private int id_centre ;
    private int capacite ;
     private int id_coach ;
     private String nom_centre ;
    private String nom_coach ;

     public String toString(){
     return "nom_act : "+this.nom_act+ " , categorie_act : "+this.categorie_act +" , Description : "+this.description +" , capacite : "+this.capacite+ " , date_act : "+this.date_act+
             " , prix_reservation : "+this.prix_reservation+" , id_centre :"+this.id_centre + " , id_coach :" + this.id_coach  +"\n" ;
     
     }
     
     
     
     
    public Activite(String categorie_act, String nom_act, double prix_reservation, Date date_act, String description, int id_centre,int capacite, int id_coach) {
        this.categorie_act = categorie_act;
        this.nom_act = nom_act;
        this.prix_reservation = prix_reservation;
        this.date_act = date_act;
        this.description = description;
        this.id_centre = id_centre;
         this.capacite = capacite;
         this.id_coach = id_coach ;
    }

    public Activite() {
    }

    
    public int getId_act() {
        return id_act;
    }

    public String getCategorie_act() {
        return categorie_act;
    }

    public String getNom_act() {
        return nom_act;
    }

    public double getPrix_reservation() {
        return prix_reservation;
    }

    public Date getDate_act() {
        return date_act;
    }

    public String getDescription() {
        return description;
    }

    public int getId_centre() {
        return id_centre;
    }

    public String getNom_centre() {
        return nom_centre;
    }

    public String getNom_coach() {
        return nom_coach;
    }
    
    
    

    public void setCategorie_act(String categorie_act) {
        this.categorie_act = categorie_act;
    }

    public void setNom_act(String nom_act) {
        this.nom_act = nom_act;
    }

    public void setPrix_reservation(double prix_reservation) {
        this.prix_reservation = prix_reservation;
    }

    public void setDate_act(Date date_act) {
        this.date_act = date_act;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId_centre(int id_centre) {
        this.id_centre = id_centre;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public int getId_coach() {
        return id_coach;
    }

    public void setId_act(int id_act) {
        this.id_act = id_act;
    }

    public void setId_coach(int id_coach) {
        this.id_coach = id_coach;
    }

    public void setNom_centre(String nom_centre) {
        this.nom_centre = nom_centre;
    }

    public void setNom_coach(String nom_coach) {
        this.nom_coach = nom_coach;
    }
    
    
    

    @Override
    public int compare(Activite o1, Activite o2) {
       if( o1.getPrix_reservation()>o2.getPrix_reservation()) 
           return 1 ;
       else if  ( o1.getPrix_reservation()<o2.getPrix_reservation()) 
           return -1 ;
       else 
           return 0 ;
    }
    
}
