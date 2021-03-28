package models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Date;
import java.util.Comparator;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author mohamedbouslah
 */
public class Membre extends User implements Comparator<Membre>{
    private float taille;
    private float poids;
    private String email;
    private String password;
    private int telephone;
    private int cin;

    public Membre() {
    }

    public Membre(int cin, String nom, String prenom, String sexe, Date datee ,float taille, float poids, String email, String password, int telephone) {
        super(cin, nom, prenom, sexe, datee);
        this.taille = taille;
        this.poids = poids;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
    }
    
    public Membre(int cin) {
        super(cin);
    }
    
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

    @Override
    public int getCin() {
        return super.getCin(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getDatee() {
        return super.getDatee(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNom() {
        return super.getNom(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPrenom() {
        return super.getPrenom(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSexe() {
        return super.getSexe(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCin(int cin) {
        super.setCin(cin); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDatee(Date datee) {
        super.setDatee(datee); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNom(String nom) {
        super.setNom(nom); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPrenom(String prenom) {
        super.setPrenom(prenom); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSexe(String sexe) {
        super.setSexe(sexe); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return  super.toString()+"Membre{" + "taille=" + taille + ", poids=" + poids + ", email=" + email + ", password=" + password + ", telephone=" + telephone + '}';
    }

    @Override
    public int compare(Membre o1, Membre o2) {
        return o1.getNom().compareTo(o2.getNom());
    }
    
   
    
    
}
