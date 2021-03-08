/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author trabe
 */
public class CategorieDiet {
    private int id;
    private String nom;
    private String description;
    private int totalCalories;
    private String image;
    private Date date;
    
    public CategorieDiet() {
        
    }
    
    public CategorieDiet(String nom, String description, String image, Date date) {
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.date = date;
    }
    
    public CategorieDiet(int id ,String nom, String description, String image) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.image = image;
    }

    public CategorieDiet(int id,String nom ,String description, String image, Date date) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.date = date;
    }

    public CategorieDiet(int id, String nom, String description, int totalCalories, String image, Date date) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.totalCalories = totalCalories;
        this.image = image;
        this.date = date;
    }
    
    
    
    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(int totalCalories) {
        this.totalCalories = totalCalories;
    }

    @Override
    public String toString() {
        return "CategorieDiet{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", image=" + image + ", date=" + date + '}';
    }


    
    
}
