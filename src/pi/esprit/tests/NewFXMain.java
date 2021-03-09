/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.tests;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author HAMZA
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       try {
        //FXMLLoader home = new FXMLLoader(getClass().getResource("../Gui/interfacereservation.fxml"));
        FXMLLoader home = new FXMLLoader(getClass().getResource("../Gui/interfacepanier.fxml"));
        Parent root = home.load();
        Scene rec = new Scene(root);
        primaryStage.setTitle("Reservation");
        //primaryStage.setTitle("Panier"); 
        primaryStage.setScene(rec);
        primaryStage.show();
        } catch (IOException e) {
              System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
