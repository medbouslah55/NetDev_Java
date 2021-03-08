/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.entities.Status;
import com.esprit.services.ServiceStatus;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author mehdi
 */
public class AjoutStatusController implements Initializable {

    @FXML
    private Button AfficherLocaux;
    @FXML
    private Button ModifierLocal;
    @FXML
    private TextField tftext;
    @FXML
    private Button btnajout;
    @FXML
    private DatePicker tfDate_Pub;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AfficherLocauxAction(ActionEvent event) {
    }

    @FXML
    private void ModifierLocalAction(ActionEvent event) {
    }

    @FXML
    private void ajouter(ActionEvent event) {
          ServiceStatus ss =new ServiceStatus();
        
       java.sql.Date pub = java.sql.Date.valueOf(tfDate_Pub.getValue());
        String text=tftext.getText();
     
        ss.ajouter(new Status(text, pub));
        
     //  ss.ajouter(new Status(pub,text));
        
        JOptionPane.showMessageDialog(null, "even ajoutée!");
    
        
    }
      
}
