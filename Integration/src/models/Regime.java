/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Comparator;
import javafx.scene.image.ImageView;

/**
 *
 * @author trabe
 */
public class Regime implements Comparator<Regime> {

    private int id;
    private String type;
    private String description;
    private String image;
    private ImageView img;

    public Regime() {
    }

    public Regime(int id, String type, String description, String image) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.image = image;
    }

    public Regime(int id, String type, String description, String image, ImageView img) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.image = image;
        this.img = img;
    }

    public Regime(String type, String description, String image) {
        this.type = type;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
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

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Regime{" + "id=" + id + ", nom=" + type + ", description=" + description + ", image=" + image + '}';
    }

    @Override
    public int compare(Regime o1, Regime o2) {
        return o1.getType().compareTo(o2.getType());
    }
}
