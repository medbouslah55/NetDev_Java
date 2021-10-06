/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.List;

/**
 *
 * @author HAMZA
 */
public class Panier {
    private int id_panier;
    private String nom_activite;
    private float prix;
    private int quantite;
    private float total ;

    public Panier() {
    }

    public Panier(int id_panier, String nom_activite, float prix, int quantite, float total) {
        this.id_panier = id_panier;
        this.nom_activite = nom_activite;
        this.prix = prix;
        this.quantite = quantite;
        this.total = total;
    }

    public Panier(String nom_activite, float prix, int quantite, float total) {
        this.nom_activite = nom_activite;
        this.prix = prix;
        this.quantite = quantite;
        this.total = total;
    }

    public Panier(String nom_activite, float prix, float total) {
        this.nom_activite = nom_activite;
        this.prix = prix;
        this.quantite = 1;
        this.total = total;
    }
    

    public int getId_panier() {
        return id_panier;
    }

    public String getNom_activite() {
        return nom_activite;
    }

    public float getPrix() {
        return prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public float getTotal() {
        return total;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public void setNom_activite(String nom_activite) {
        this.nom_activite = nom_activite;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Panier{" + "id_panier=" + id_panier + ", nom_activite=" + nom_activite + ", prix=" + prix + ", quantite=" + quantite + ", total=" + total + '}';
    }

    
    

    


    

 
    
}
