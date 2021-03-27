/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Firas
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Date;

/**
 *
 * @author Firas
 */
public class Reclamation {

    private int Id_rec;
    private String Nom_rec;
    private String Prenom_rec;
    private String Email_rec;
    private String Type_rec;
    private Date Date_rec;
    private String Description_rec;
    private String Etat_rec;

    public Reclamation() {
    }

    public Reclamation(String Nom_rec, String Prenom_rec, String Email_rec, String Type_rec, Date Date_rec, String Description_rec, String Etat_rec) {
        this.Nom_rec = Nom_rec;
        this.Prenom_rec = Prenom_rec;
        this.Email_rec = Email_rec;
        this.Type_rec = Type_rec;
        this.Date_rec = Date_rec;
        this.Description_rec = Description_rec;
        this.Etat_rec = Etat_rec;
    }

    public Reclamation(int Id_rec, String Nom_rec, String Prenom_rec, String Email_rec, String Type_rec, Date Date_rec, String Description_rec, String Etat_rec) {
        this.Id_rec = Id_rec;
        this.Nom_rec = Nom_rec;
        this.Prenom_rec = Prenom_rec;
        this.Email_rec = Email_rec;
        this.Type_rec = Type_rec;
        this.Date_rec = Date_rec;
        this.Description_rec = Description_rec;
        this.Etat_rec = Etat_rec;
    }


    public Reclamation(int Id_rec, String Nom_rec, String Prenom_rec, String Email_rec, String Type_rec, Date Date_rec, String Description_rec) {
        this.Id_rec = Id_rec;
        this.Nom_rec = Nom_rec;
        this.Prenom_rec = Prenom_rec;
        this.Email_rec = Email_rec;
        this.Type_rec = Type_rec;
        this.Date_rec = Date_rec;
        this.Description_rec = Description_rec;
    }

    public Reclamation(String Nom_rec, String Prenom_rec, String Email_rec, String Type_rec, Date Date_rec, String Description_rec) {
        this.Nom_rec = Nom_rec;
        this.Prenom_rec = Prenom_rec;
        this.Email_rec = Email_rec;
        this.Type_rec = Type_rec;
        this.Date_rec = Date_rec;
        this.Description_rec = Description_rec;
    }

    public String getEtat_rec() {
        return Etat_rec;
    }

    public void setEtat_rec(String Etat_rec) {
        this.Etat_rec = Etat_rec;
    }

    public int getId_rec() {
        return Id_rec;
    }

    public String getNom_rec() {
        return Nom_rec;
    }

    public String getPrenom_rec() {
        return Prenom_rec;
    }

    public String getEmail_rec() {
        return Email_rec;
    }

    public String getType_rec() {
        return Type_rec;
    }

    public Date getDate_rec() {
        return Date_rec;
    }

    public String getDescription_rec() {
        return Description_rec;
    }

    public void setId_rec(int Id_rec) {
        this.Id_rec = Id_rec;
    }

    public void setNom_rec(String Nom_rec) {
        this.Nom_rec = Nom_rec;
    }

    public void setPrenom_rec(String Prenom_rec) {
        this.Prenom_rec = Prenom_rec;
    }

    public void setEmail_rec(String Email_rec) {
        this.Email_rec = Email_rec;
    }

    public void setType_rec(String Type_rec) {
        this.Type_rec = Type_rec;
    }

    public void setDate_rec(Date Date_rec) {
        this.Date_rec = Date_rec;
    }

    public void setDescription_rec(String Description_rec) {
        this.Description_rec = Description_rec;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "Nom_rec=" + Nom_rec + ", Prenom_rec=" + Prenom_rec + ", Email_rec=" + Email_rec + ", Type_rec=" + Type_rec + ", Date_rec=" + Date_rec + ", Description_rec=" + Description_rec + '}';
    }

}
