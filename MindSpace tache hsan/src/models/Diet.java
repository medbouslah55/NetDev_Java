/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author trabe
 */
public class Diet {
    private int id;
    private int id_categorie;
    private String nom;
    private int calories;
    private int quantite;
    private String image;
    private String Date;

    public Diet() {
    }
    
    public Diet(int id, int id_categorie, String nom, int calories, int quantite) {
        this.id = id;
        this.id_categorie = id_categorie;
        this.nom = nom;
        this.calories = calories;
        this.quantite = quantite;
    }
    
    public Diet(int id_categorie, String nom, int calories, int quantite, String image) {
        this.id_categorie = id_categorie;
        this.nom = nom;
        this.calories = calories;
        this.quantite = quantite;
        this.image = image;
    }

    public Diet(int id, int id_categorie, String nom, int calories, int quantite, String image) {
        this.id = id;
        this.id_categorie = id_categorie;
        this.nom = nom;
        this.calories = calories;
        this.quantite = quantite;
        this.image = image;
    }

    public Diet(int id, int id_categorie, String nom, int calories, int quantite, String image, String Date) {
        this.id = id;
        this.id_categorie = id_categorie;
        this.nom = nom;
        this.calories = calories;
        this.quantite = quantite;
        this.image = image;
        this.Date = Date;
    }

    public Diet(int calories) {
        this.calories = calories;
    }
    
    
    
    public int getId() {
        return id;
    }
    
    public int getId_categorie() {
        return id_categorie;
    }
    
    public String getNom() {
        return nom;
    }

    public int getCalories() {
        return calories;
    }

    public int getQuantite() {
        return quantite;
    }
    
    public String getImage() {
        return image;
    }

    public String getDate() {
        return Date;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    @Override
    public String toString() {
        return "Diet{" + "id=" + id + ", id_categorie=" + id_categorie + ", nom=" + nom + ", calories=" + calories + ", quantite=" + quantite + ", image=" + image + ", Date=" + Date + '}';
    }
}