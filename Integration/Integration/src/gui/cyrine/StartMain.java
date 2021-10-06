/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.cyrine;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author DELL
 */
public class StartMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        /*  Parent root =
                    FXMLLoader.load(getClass().getResource("Home.fxml")) ;
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();*/

        try {
            Parent root
                    = // FXMLLoader.load(getClass().getResource("Home.fxml")) ;
                    FXMLLoader.load(getClass().getResource("CentresMembre.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setTitle("Home window!");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
