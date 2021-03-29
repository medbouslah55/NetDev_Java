package models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Date;
import org.apache.commons.codec.digest.DigestUtils;

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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    private float taille;
    private float poids;
    private String email;
    private String password;
    private int telephone;

    public float getTaille() {
        return taille;
    }

    public void setTaille(float taille) {
        this.taille = taille;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = DigestUtils.shaHex(password);
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

}
