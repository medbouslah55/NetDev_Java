package models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Date;

/**
 *
 * @author mohamedbouslah
 */
public class Admin extends User {

    private String email;
    private String password;
    private int telephone;

    public Admin(String email, String password, int telephone) {
        this.email = email;
        this.password = password;
        this.telephone = telephone;
    }

    public Admin() {

    }

    public Admin(int cin, String nom, String prenom, String sexe, Date datee, String email, String password, int telephone) {
        super(cin, nom, prenom, sexe, datee);
        this.email = email;
        this.password = password;
        this.telephone = telephone;
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
        this.password = password;
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
        return super.toString() + "Admin{" + "email=" + email + ", password=" + password + ", telephone=" + telephone + '}';
    }

}
