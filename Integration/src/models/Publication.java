/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;



/**
 *
 * @author mehdi
 */
public class Publication  {
    
    private int id_pub;
    private java.sql.Date date_pub ;

    public Publication() {
    }

    
    public Publication( int id_pub,java.sql.Date date_pub) {
       this.id_pub= id_pub;
        this.date_pub = date_pub;
        
    }

    public Publication(java.sql.Date date_pub) {
        this.date_pub = date_pub;
    }

    
    public int getId_pub() {
        return id_pub;
    }

    public void setId_pub(int id_pub) {
        this.id_pub = id_pub;
    }

    public java.sql.Date getDate_pub() {
        return date_pub;
    }

    public void setDate_pub(java.sql.Date date_pub) {
        this.date_pub = date_pub;
    }

    @Override
    public String toString() {
        return "Publication{" + "id_pub=" + id_pub + ", date_pub=" + date_pub + '}';
    }
    

    
}
