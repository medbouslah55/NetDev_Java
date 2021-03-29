/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author Firas
 */
public class AbonnementUser {
    private int id;
    private String nom;
    private String prenom;
    private String type;
    private int nb_mois;
    private Date dateD;

    public AbonnementUser(String nom, String prenom, String type, int nb_mois, Date dateD) {
        this.nom = nom;
        this.prenom = prenom;
        this.type = type;
        this.nb_mois = nb_mois;
        this.dateD = dateD;
    }
    
    public AbonnementUser() {
    }

    public String getType() {
        return type;
    }
    
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getNb_mois() {
        return nb_mois;
    }

    public Date getDateD() {
        return dateD;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNb_mois(int nb_mois) {
        this.nb_mois = nb_mois;
    }

    public void setDateD(Date dateD) {
        this.dateD = dateD;
    }

    @Override
    public String toString() {
        return "AbonnementUser{" + "nom=" + nom + ", prenom=" + prenom + ", type=" + type + ", nb_mois=" + nb_mois + ", dateD=" + dateD + '}';
    }
    
    
    
    
    
}
