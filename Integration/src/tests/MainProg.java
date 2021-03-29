/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author mohamedbouslah
 */
public class MainProg extends Application {
    
    public static Stage primaryStage = null;
    
    @Override
    public void start(Stage s) throws IOException {
        primaryStage=s;
        primaryStage.setTitle("MindSpace");
        Parent root = FXMLLoader.load(getClass().getResource("/gui/mohammed/Login.fxml"));
        Scene scene = new Scene(root);
//        scene.getStylesheets().add(getClass().getResource("/gui/Design.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
