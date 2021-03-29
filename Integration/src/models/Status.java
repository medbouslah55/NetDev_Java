/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author mehdi
 */
public class Status extends Publication {
    private String text;
    private int id_centre;

    public Status() {
    }

    public Status( int id_pub ,java.sql.Date date_pub,String text) {
        super( date_pub);
        this.text = text;
    }

    public Status(String text, Date date_pub, int id_centre) {
        super(date_pub);
        this.text = text;
        this.id_centre = id_centre;
    }

    public int getId_centre() {
        return id_centre;
    }

    public void setId_centre(int id_centre) {
        this.id_centre = id_centre;
    }
    
    public Status(Date pub, String text, Integer value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Status(Date rdapub, String rtext) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Status(String text, Date date_pub) {
        super(date_pub);
        this.text = text;
    }
    
    
    @Override
    public java.sql.Date getDate_pub() {
        return super.getDate_pub(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getId_pub() {
        return super.getId_pub(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDate_pub(java.sql.Date date_pub) {
        super.setDate_pub(date_pub); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setId_pub(int id_pub) {
        super.setId_pub(id_pub); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return super.toString()+ "Status{" + "text=" + text + '}';
    }

   

    
    
}

