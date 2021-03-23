/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.maindspace.entities;

import java.sql.Date;

/**
 *
 * @author mohamedbouslah
 */
public class User {
    private int cin;
    private String nom;
    private String prenom;
    private String sexe;
    private Date datee;

    public User() {
    }
    
    public User(int cin) {
        this.cin = cin;
    }
    
    public User(int cin, String nom, String prenom, String sexe, Date datee) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.datee = datee;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getDatee() {
        return datee;
    }

    public void setDatee(Date datee) {
        this.datee = datee;
    }

    @Override
    public String toString() {
        return "User{" + "cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + ", datee=" + datee + '}';
    }
    
    

    
}
