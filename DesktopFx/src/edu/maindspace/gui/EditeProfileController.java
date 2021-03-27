/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.maindspace.gui;

import edu.maindspace.entities.Membre;
import edu.maindspace.entities.User;
import edu.maindspace.services.MembreServices;
import edu.maindspace.tools.UserSession;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author mohamedbouslah
 */
public class EditeProfileController implements Initializable {

    @FXML
    private TextField tf_taille;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_pwd;
    @FXML
    private TextField tf_poids;
    @FXML
    private TextField tf_telephone;
    @FXML
    private TextField tf_prenom;
    @FXML
    private TextField tf_nom;
    @FXML
    private Button bt_Modif;
    @FXML
    private Button bt_retour_inscri;

    /**
     * Initializes the controller class.
     */
    User member= UserSession.getInstance().getLoggedUser();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tf_nom.setText(UserSession.getInstance().getLoggedUser().getNom());
        tf_prenom.setText(UserSession.getInstance().getLoggedUser().getPrenom());
        tf_email.setText(UserSession.getInstance().getLoggedUser().getEmail());
        tf_pwd.setText(UserSession.getInstance().getLoggedUser().getPassword());
        
        int tell =UserSession.getInstance().getLoggedUser().getTelephone();
        String tel =Integer.toString(tell);
        tf_telephone.setText(tel);
        
        float taill =UserSession.getInstance().getLoggedUser().getTaille();
        String taille=Float.toString(taill);
        tf_taille.setText(taille);
        
        float poid =UserSession.getInstance().getLoggedUser().getPoids();
        String poids=Float.toString(poid);
        tf_poids.setText(poids);
        
        
    }    
    MembreServices ms=new MembreServices();
    @FXML
    private void Modifier_profile(ActionEvent event) {
        Membre t = new Membre();
        t.setCin(UserSession.getInstance().getLoggedUser().getCin());
        t.setNom(tf_nom.getText());
        t.setPrenom(tf_prenom.getText());
        t.setPassword(tf_pwd.getText());
        t.setEmail(tf_email.getText());
        String tf_taillee = tf_taille.getText();
        t.setTaille(Integer.parseInt(tf_taillee));
        String tf_poidss = tf_poids.getText();
        t.setPoids(Integer.parseInt(tf_poidss));
        String telephone = tf_telephone.getText();
        t.setTelephone(Integer.parseInt(telephone));
        ms.modifier(t);
        
    }

    @FXML
    private void retour_menu(ActionEvent event) {
    }
    
}
