/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.maindspace.gui;

import edu.maindspace.entities.Membre;
import edu.maindspace.services.MembreServices;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author mohamedbouslah
 */
public class InscriptionMembreController implements Initializable {

    @FXML
    private TextField tf_cin_isc;
    @FXML
    private TextField tf_taille_isc;
    @FXML
    private TextField tf_email_isc;
    @FXML
    private TextField tf_pwd_isc;
    @FXML
    private TextField tf_poids_isc;
    @FXML
    private TextField tf_telephone_isc;
    @FXML
    private TextField tf_prenom_isc;
    @FXML
    private TextField tf_nom_isc;
    @FXML
    private DatePicker dp_date_isc;
    @FXML
    private Button bt_inscri;
    @FXML
    private ComboBox cb_sexe_inscri;
    @FXML
    private Button bt_retour_inscri;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //combo sexe
        cb_sexe_inscri.getItems().addAll("Homme", "Femme");
        
    }    
    MembreServices ms =new MembreServices();
    @FXML
    private void Inscription(ActionEvent event) {
        Membre m = new Membre();
        String cin = tf_cin_isc.getText();
        m.setCin(Integer.parseInt(cin));
        m.setNom(tf_nom_isc.getText());
        m.setPrenom(tf_prenom_isc.getText());
        m.setSexe((String) cb_sexe_inscri.getSelectionModel().getSelectedItem());
        Date datee = Date.valueOf(dp_date_isc.getValue());
        m.setDatee(datee);
        m.setEmail(tf_email_isc.getText());
        m.setPassword(tf_pwd_isc.getText());
        String tf_taille = tf_taille_isc.getText();
        m.setTaille(Integer.parseInt(tf_taille));
        String tf_poids = tf_poids_isc.getText();
        m.setPoids(Integer.parseInt(tf_poids));
        String telephone = tf_telephone_isc.getText();
        m.setTelephone(Integer.parseInt(telephone));
  
        ms.ajouter(m);
        
        TrayNotification tray = null;
        tray = new TrayNotification("Compte cree", "Votre compte a ete avec succes ,Merci ", NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(5));
        
        
        
    }

    @FXML
    private void retour_inscri(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
}
