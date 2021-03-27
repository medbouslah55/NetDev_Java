/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.hssan.menu;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class CaloriesController implements Initializable {

    @FXML
    private TextField tfCarbs;
    @FXML
    private Button calculate;
    @FXML
    private TextField tfProtein;
    @FXML
    private TextField totalCalories;
    @FXML
    private TextField tfGraisse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void calculateCal(ActionEvent event) {
        double protein = Double.parseDouble(tfProtein.getText());
        double carbs = Double.parseDouble(tfCarbs.getText());
        double fats = Double.parseDouble(tfGraisse.getText());
        
        double total = (protein * 4) + (carbs*4) + (fats*8);
        
        totalCalories.setText(String.valueOf(total + " Calories"));
    }
    
}
