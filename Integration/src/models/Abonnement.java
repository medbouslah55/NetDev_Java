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
public class Abonnement {
    private int Id_ab;
    private String Titre_ab;
    private String Type_ab;
    private float prix_ab;
    private String desc_ab;

    public Abonnement() {
    }

    public Abonnement(String Titre_ab, String Type_ab, float prix_ab, String desc_ab) {
        this.Titre_ab = Titre_ab;
        this.Type_ab = Type_ab;
        this.prix_ab = prix_ab;
        this.desc_ab = desc_ab;
    }
    public int getId_ab() {
        return Id_ab;
    }

    public String getTitre_ab() {
        return Titre_ab;
    }

    public String getType_ab() {
        return Type_ab;
    }
    public float getPrix_ab() {
        return prix_ab;
    }

    public String getDesc_ab() {
        return desc_ab;
    }

    public void setId_ab(int Id_ab) {
        this.Id_ab = Id_ab;
    }

    public void setTitre_ab(String Titre_ab) {
        this.Titre_ab = Titre_ab;
    }

    public void setType_ab(String Type_ab) {
        this.Type_ab = Type_ab;
    }
    public void setPrix_ab(float prix_ab) {
        this.prix_ab = prix_ab;
    }

    public void setDesc_ab(String desc_ab) {
        this.desc_ab = desc_ab;
    }

    @Override
    public String toString() {
        return "Abonnement{" + "Titre_ab=" + Titre_ab + ", Type_ab=" + Type_ab + ", prix_ab=" + prix_ab + ", desc_ab=" + desc_ab + '}';
    }
}
