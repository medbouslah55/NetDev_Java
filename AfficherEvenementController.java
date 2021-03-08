/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.entities.Evenement;
import com.esprit.services.ServicePublication;
import com.sun.istack.internal.logging.Logger;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static jdk.nashorn.internal.objects.NativeString.search;

/**
 * FXML Controller class
 *
 * @author mehdi
 */
public class AfficherEvenementController implements Initializable {

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
    @FXML
    private TableColumn<?, ?> tvDate_Pub;
    @FXML
    private TableColumn<?, ?> tvDate_Even;
    @FXML
    private TableColumn<?, ?> tvImage;
    @FXML
    private TableColumn<?, ?> tvSujet;
    @FXML
    private TableView<Evenement> tvevenement;
    @FXML
    private Button btnsupprimer;
    @FXML
    private TableColumn<?, ?> tvId;
    @FXML
    private TextField txtrechercher;
    @FXML
    private Button btnrechercher;
   /* 
    String requete = null;
    Connection cnx =null;
     PreparedStatement pre=null;
     ResultSet rs=null;
     Evenement evenement = null;
      ObservableList<Evenement> list = FXCollections.observableArrayList();
     
*/
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherEven();
    }    

    @FXML
    private void AfficherLocauxAction(ActionEvent event) {
        
    
    }

    @FXML
    private void ModifierLocalAction(ActionEvent event) {
    }

    @FXML
    private void ajouter(ActionEvent event) {
    }
    
    
    
    
    @FXML
       public void afficherEven() {
           
           
           
           ServicePublication sp = new ServicePublication();
           ObservableList<Evenement> list =  sp.afficher(); 
           
        tvId.setCellValueFactory(new PropertyValueFactory<>("id_pub"));
        tvDate_Pub.setCellValueFactory(new PropertyValueFactory< >("date_pub"));
        tvDate_Even.setCellValueFactory(new PropertyValueFactory< >("date_even"));
        tvImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        tvSujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
       
        tvevenement.setItems(list);
           
           
           
           
           
           
           
        /*   ServicePublication sp = new ServicePublication();
        ObservableList<Evenement> list =  sp.afficher(); 
      
        tvDate_Pub.setCellValueFactory(new PropertyValueFactory<Evenement, java.sql.Date>("date_pub"));
        tvDate_Even.setCellValueFactory(new PropertyValueFactory<Evenement, java.sql.Date>("date_even"));
        tvImage.setCellValueFactory(new PropertyValueFactory<Evenement, String>("image"));
        tvSujet.setCellValueFactory(new PropertyValueFactory<Evenement, String>("sujet"));
       
        tvevenement.setItems(list); 
        
 */
     
      
       }

    @FXML
    private void Supprimer(ActionEvent event) {
         Evenement e = tvevenement.getSelectionModel().getSelectedItem();
        tvevenement.getItems().removeAll(tvevenement.getSelectionModel().getSelectedItem());
        ServicePublication sp = new ServicePublication();
        sp.supprimer(e);

    }

    @FXML
    private void Rechercher(ActionEvent event) {
        
        
 }
    
    /*public void Rechercher( java.sql.Date Date_pub){
        ObservableList<Evenement> list =  sp.afficher();
        for(Evenement evenement : tvevenement.getItems()){
            java.sql.Date rechercherDate_Pub = evenement.getDate_pub();
            if(list.u
        }
        
    }

    
        
     */          
    
   }

