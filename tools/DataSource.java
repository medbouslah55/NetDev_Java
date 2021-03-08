/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mehdi
 */
public class DataSource {
    private static DataSource instance;
    private Connection cnx;
    
    private final String URL = "jdbc:mysql://127.0.0.1:3306/mindspace";
    private final String LOGIN = "root";
    private final String PASSWORD = "";
    
    
    private DataSource() {
        try {
            cnx=DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Connecting !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static DataSource getInstance() {
        if (instance == null)
            instance = new DataSource();
        return instance;
    }
    
    public Connection getCnx() {
        return cnx;
    }
}