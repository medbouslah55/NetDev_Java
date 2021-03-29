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
    private String nom_act;
    private float prix;
    private int capacite;
    


    public Panier() {
    }

    public Panier(String nom_act, float prix, int capacite) {
        this.nom_act = nom_act;
        this.prix = prix;
        this.capacite = capacite;
    }

    public Panier(int id_panier, String nom_act, float prix, int capacite) {
        this.id_panier = id_panier;
        this.nom_act = nom_act;
        this.prix = prix;
        this.capacite = capacite;
    }

    public int getId_panier() {
        return id_panier;
    }

    public String getNom_act() {
        return nom_act;
    }

    public float getPrix() {
        return prix;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public void setNom_act(String nom_act) {
        this.nom_act = nom_act;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    @Override
    public String toString() {
        return "Panier{" + "id_panier=" + id_panier + ", nom_act=" + nom_act + ", prix=" + prix + ", capacite=" + capacite + '}';
    }

    

}