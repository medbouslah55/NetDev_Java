package models;

import javafx.scene.image.ImageView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author trabe
 */
public class Menu {

    private int id;
    private String descirption;
    private int num_jour;
    private String matin;
    private String matin_img;
    private String dejeuner;
    private String dejeuner_img;
    private String dinner;
    private String dinner_img;
    private int total_calories;
    private int id_regime;
    private ImageView img_matin;
    private ImageView img_dejeuner;
    private ImageView img_dinner;

    public Menu() {
    }

    public Menu(int id, String descirption, int num_jour, String matin, String dejeuner, String dinner, int total_calories) {
        this.id = id;
        this.descirption = descirption;
        this.num_jour = num_jour;
        this.matin = matin;
        this.dejeuner = dejeuner;
        this.dinner = dinner;
        this.total_calories = total_calories;
    }

    public Menu(int id, String descirption, int num_jour, String matin, String matin_img, String dejeuner, String dejeuner_img, String dinner, String dinner_img, int total_calories, int id_regime) {
        this.id = id;
        this.descirption = descirption;
        this.num_jour = num_jour;
        this.matin = matin;
        this.matin_img = matin_img;
        this.dejeuner = dejeuner;
        this.dejeuner_img = dejeuner_img;
        this.dinner = dinner;
        this.dinner_img = dinner_img;
        this.total_calories = total_calories;
        this.id_regime = id_regime;
    }

    public Menu(String descirption, int num_jour, String matin, String matin_img, String dejeuner, String dejeuner_img, String dinner, String dinner_img, int total_calories, int id_regime) {
        this.descirption = descirption;
        this.num_jour = num_jour;
        this.matin = matin;
        this.matin_img = matin_img;
        this.dejeuner = dejeuner;
        this.dejeuner_img = dejeuner_img;
        this.dinner = dinner;
        this.dinner_img = dinner_img;
        this.total_calories = total_calories;
        this.id_regime = id_regime;
    }

    public Menu(int id, String descirption, int num_jour, String matin, String matin_img, String dejeuner, String dejeuner_img, String dinner, String dinner_img, int total_calories, int id_regime, ImageView img_matin, ImageView img_dejeuner, ImageView img_dinner) {
        this.id = id;
        this.descirption = descirption;
        this.num_jour = num_jour;
        this.matin = matin;
        this.matin_img = matin_img;
        this.dejeuner = dejeuner;
        this.dejeuner_img = dejeuner_img;
        this.dinner = dinner;
        this.dinner_img = dinner_img;
        this.total_calories = total_calories;
        this.id_regime = id_regime;
        this.img_matin = img_matin;
        this.img_dejeuner = img_dejeuner;
        this.img_dinner = img_dinner;
    }
    
    

    public int getId() {
        return id;
    }

    public String getDescirption() {
        return descirption;
    }

    public int getNum_jour() {
        return num_jour;
    }

    public String getMatin() {
        return matin;
    }

    public String getMatin_img() {
        return matin_img;
    }

    public String getDejeuner() {
        return dejeuner;
    }

    public String getDejeuner_img() {
        return dejeuner_img;
    }

    public String getDinner() {
        return dinner;
    }

    public String getDinner_img() {
        return dinner_img;
    }

    public int getTotal_calories() {
        return total_calories;
    }

    public int getId_regime() {
        return id_regime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescirption(String descirption) {
        this.descirption = descirption;
    }

    public void setNum_jour(int num_jour) {
        this.num_jour = num_jour;
    }

    public void setMatin(String matin) {
        this.matin = matin;
    }

    public void setMatin_img(String matin_img) {
        this.matin_img = matin_img;
    }

    public void setDejeuner(String dejeuner) {
        this.dejeuner = dejeuner;
    }

    public void setDejeuner_img(String dejeuner_img) {
        this.dejeuner_img = dejeuner_img;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public void setDinner_img(String dinner_img) {
        this.dinner_img = dinner_img;
    }

    public void setTotal_calories(int total_calories) {
        this.total_calories = total_calories;
    }

    public void setId_regime(int id_regime) {
        this.id_regime = id_regime;
    }

    public ImageView getImg_matin() {
        return img_matin;
    }

    public ImageView getImg_dejeuner() {
        return img_dejeuner;
    }

    public ImageView getImg_dinner() {
        return img_dinner;
    }

    public void setImg_matin(ImageView img_matin) {
        this.img_matin = img_matin;
    }

    public void setImg_dejeuner(ImageView img_dejeuner) {
        this.img_dejeuner = img_dejeuner;
    }

    public void setImg_dinner(ImageView img_dinner) {
        this.img_dinner = img_dinner;
    }
    
    

    @Override
    public String toString() {
        return "Menu{" + "id=" + id + ", descirption=" + descirption + ", num_jour=" + num_jour + ", matin=" + matin + ", matin_img=" + matin_img + ", dejeuner=" + dejeuner + ", dejeuner_img=" + dejeuner_img + ", dinner=" + dinner + ", dinner_img=" + dinner_img + '}';
    }
}
