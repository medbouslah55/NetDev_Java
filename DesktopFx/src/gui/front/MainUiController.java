/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.front;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import models.Menu;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class MainUiController implements Initializable {

    @FXML
    private Pane contentArea;
    private List<Menu> fruits = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            show();
        } catch (IOException ex) {
            Logger.getLogger(MainUiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public void show() throws IOException{
        
        Parent fxml = FXMLLoader.load(getClass().getResource("/gui/front/MenuFront.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
}
