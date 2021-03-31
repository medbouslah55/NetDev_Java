/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;

/**
 *
 * @author trabe
 */
public class DataSource {
    private static DataSource instance;
    private Connection cnx;
    
    private final String URL = "jdbc:mysql://127.0.0.1:3306/bdpidev4";
    private final String LOGIN = "root";
    private final String PASSWORD = "";
    
    
    private DataSource() {
        try {
            cnx=DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Connecting !");
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database");
            alert.setHeaderText("Connection Error");
            alert.setContentText("Cannot Connect To Database!");
            alert.showAndWait();
            throw new IllegalStateException("Cannot connect the database!", ex);
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
