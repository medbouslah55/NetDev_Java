/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.maindspace.gui;

import edu.maindspace.entities.Admin;
import edu.maindspace.entities.Membre;
import edu.maindspace.services.AdminServices;
import edu.maindspace.services.MembreServices;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
public class LoginController implements Initializable {

    @FXML
    private TextField tf_login_email_membre;
    @FXML
    private TextField tf_login_pass_membre;
    @FXML
    private Button bt_login_membre;
    @FXML
    private TextField tf_login_email_admin;
    @FXML
    private TextField tf_login_pass_admin;
    @FXML
    private Button bt_login_admin;
    @FXML
    private Button bt_inscri_membre_login;
    @FXML
    private Button bt_pass_oublier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    MembreServices ms =new MembreServices();
    AdminServices as =new AdminServices();
    @FXML
    private void login_membre(ActionEvent event) {
        String email = tf_login_email_membre.getText();
        String pwd = tf_login_pass_membre.getText();
        ms.loginMembre(email, pwd);
    }

    @FXML
    private void login_admin(ActionEvent event) throws IOException {
        String email = tf_login_email_admin.getText();
        String pwd = tf_login_pass_admin.getText();
        if(as.loginAdmin(email, pwd)==1){
            Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("MenuBackend.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
            
        }
        else
        {
            TrayNotification tray = null;
        tray = new TrayNotification("Error", "Email ou password incorrect  ", NotificationType.ERROR);
        tray.showAndDismiss(Duration.seconds(5));
        }
        
        
    }

    @FXML
    private void inscri_membre_login(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("InscriptionMembre.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void pwd_oublier(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("RecuperePwdMembre.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
