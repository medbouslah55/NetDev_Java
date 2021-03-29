/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Date;
import java.sql.SQLException;
import models.Membre;
import models.User;
import services.MembreServices;

/**
 *
 * @author AmineMissaoui
 */
public final class UserSession {

    private static UserSession instance;
    private User loggedUser;
    private int cin;
    private String nom;
    private String prenom;
    private String sexe;
    private Date datee;

    private float taille;
    private float poids;
    private String email;
    private String password;
    private int telephone;

    private UserSession(String email) {
        MembreServices se = new MembreServices();
        Membre u = se.getUserById(email);
        this.cin = u.getCin();
        this.nom = u.getNom();
        this.prenom = u.getPrenom();
        this.sexe = u.getSexe();
        this.datee = u.getDatee();
        this.taille = u.getTaille();
        this.poids = u.getPoids();
        this.email = u.getEmail();
        this.password = u.getEmail();
        this.telephone = u.getTelephone();
    }

    private UserSession(int cin) {
        MembreServices se = new MembreServices();
        Membre u = se.getUserBycin(cin);
        this.cin = u.getCin();
        this.nom = u.getNom();
        this.prenom = u.getPrenom();
        this.sexe = u.getSexe();
        this.datee = u.getDatee();
        this.taille = u.getTaille();
        this.poids = u.getPoids();
        this.email = u.getEmail();
        this.password = u.getEmail();
        this.telephone = u.getTelephone();
    }

    public static UserSession setInstance(String email) throws SQLException {
        if (instance == null) {
            instance = new UserSession(email);
        }
        return instance;
    }

    public static UserSession setInstance(int cin) throws SQLException {
        if (instance == null) {
            instance = new UserSession(cin);
        }
        return instance;
    }

    public static UserSession getInstance() {
        return instance;
    }

    public int getCin() {
        return cin;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public Date getDatee() {
        return datee;
    }

    public float getTaille() {
        return taille;
    }

    public float getPoids() {
        return poids;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getTelephone() {
        return telephone;
    }
    
    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public void cleanUserSession() {
        email = "";// or null
    }

    @Override
    public String toString() {
        return "UserSession{" + "cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + ", datee=" + datee + ", taille=" + taille + ", poids=" + poids + ", email=" + email + ", password=" + password + ", telephone=" + telephone + '}';
    }
}
