/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.entities.Evenement;
import com.esprit.services.ServicePublication;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author mehdi
 */
public class AjoutEvenementController implements Initializable {

    @FXML
    private Button AfficherLocaux;
    @FXML
    private Button ModifierLocal;
    @FXML
    private Button btnAjout;
    @FXML
    private DatePicker tfDate_Pub;
    @FXML
    private DatePicker tfDate_even;
    @FXML
    private TextField tfSujet;
    @FXML
    private TextField tfimage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void ajouter(ActionEvent event) {
        ServicePublication sp = new ServicePublication();
       java.sql.Date pub = java.sql.Date.valueOf(tfDate_Pub.getValue());
       java.sql.Date even = java.sql.Date.valueOf(tfDate_even.getValue());
        String Sujet=tfSujet.getText();
        String image =tfimage.getText();
        
        sp.ajouter(new Evenement(pub, even, Sujet, image));
        
        JOptionPane.showMessageDialog(null, "Evenement ajoutée!");
        
        
    }
    
    
}
