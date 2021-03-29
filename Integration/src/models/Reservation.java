/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author HAMZA
 */
public class Reservation {
    private int id_reservation;
    private String nom;
    private String prenom;
    private int cin_membre;
    private Date date_act;
    private int nb_place;

    public Reservation() {
    }

    public Reservation(String nom, String prenom, int cin_membre, Date date_act, int nb_place) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin_membre = cin_membre;
        this.date_act = date_act;
        this.nb_place = nb_place;
    }

    public Reservation(int id_reservation, String nom, String prenom, int cin_membre, Date date_act, int nb_place) {
        this.id_reservation = id_reservation;
        this.nom = nom;
        this.prenom = prenom;
        this.cin_membre = cin_membre;
        this.date_act = date_act;
        this.nb_place = nb_place;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getCin_membre() {
        return cin_membre;
    }

    public Date getDate_act() {
        return date_act;
    }

    public int getNb_place() {
        return nb_place;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setCin_membre(int cin_membre) {
        this.cin_membre = cin_membre;
    }

    public void setDate_act(Date date_act) {
        this.date_act = date_act;
    }

    public void setNb_place(int nb_place) {
        this.nb_place = nb_place;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_reservation=" + id_reservation + ", nom=" + nom + ", prenom=" + prenom + ", cin_membre=" + cin_membre + ", date_act=" + date_act + ", nb_place=" + nb_place + '}';
    }

    public Reservation(int id_reservation, String nom, String prenom, Date date_act, int nb_place) {
        this.id_reservation = id_reservation;
        this.nom = nom;
        this.prenom = prenom;
        this.date_act = date_act;
        this.nb_place = nb_place;
    }

    public Reservation(String nom, String prenom, Date date_act, int nb_place) {
        this.nom = nom;
        this.prenom = prenom;
        this.date_act = date_act;
        this.nb_place = nb_place;
    }


    


    
    
    
}

