/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Comparator;

/**
 *
 * @author DELL
 */
public class Centre implements Comparator<Centre> {

    private int id_centre;
    private String nom_centre;
    private String tel_centre;
    private String mail_centre;
    private String adr_centre;
    private String type_centre;

    public Centre(String nom_centre, String tel_centre, String mail_centre, String adr_centre, String type_centre) {
        this.nom_centre = nom_centre;
        this.tel_centre = tel_centre;
        this.mail_centre = mail_centre;
        this.adr_centre = adr_centre;
        this.type_centre = type_centre;
    }

    public Centre() {
    }

    public String toString() {
        return "nom_centre : " + this.nom_centre
                + " , tel_centre :" + this.tel_centre
                + " , mail_centre : " + this.mail_centre
                + " , adr_centre : " + this.adr_centre
                + " , type_centre : " + this.type_centre + "\n";
    }

    public int getId_centre() {
        return id_centre;
    }

    public String getNom_centre() {
        return nom_centre;
    }

    public String getTel_centre() {
        return tel_centre;
    }

    public String getMail_centre() {
        return mail_centre;
    }

    public String getAdr_centre() {
        return adr_centre;
    }

    public String getType_centre() {
        return type_centre;
    }

    public void setId_centre(int id_centre) {
        this.id_centre = id_centre;
    }

    public void setNom_centre(String nom_centre) {
        this.nom_centre = nom_centre;
    }

    public void setTel_centre(String tel_centre) {
        this.tel_centre = tel_centre;
    }

    public void setMail_centre(String mail_centre) {
        this.mail_centre = mail_centre;
    }

    public void setAdr_centre(String adr_centre) {
        this.adr_centre = adr_centre;
    }

    public void setType_centre(String type_centre) {
        this.type_centre = type_centre;
    }

    @Override
    public int compare(Centre o1, Centre o2) {
        return o1.getNom_centre().compareTo(o2.getNom_centre());
    }

}
