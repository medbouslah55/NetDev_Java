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

/**
 * FXML Controller class
 *
 * @author mehdi
 */
public class AfficherStatusController implements Initializable {

    @FXML
    private Button AfficherLocaux;
    @FXML
    private Button ModifierLocal;
    @FXML
    private Button btnajout;
    @FXML
    private TextField tftext;
    @FXML
    private DatePicker tfDate_Pub;
    @FXML
    private TableView<Status> tvstatus;
    @FXML
    private TableColumn<?, ?> tvId;
    @FXML
    private TableColumn<?, ?> tvDate_Pub;
    @FXML
    private TableColumn<?, ?> tvtext;
    @FXML
    private Button btnsupprimer;

     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherStatus();
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
    
    public void afficherStatus() {
        
        ServiceStatus ss =new ServiceStatus();
        ObservableList<Status> list = ss.afficher(); 
           
        tvId.setCellValueFactory(new PropertyValueFactory<>("id_pub"));
        tvDate_Pub.setCellValueFactory(new PropertyValueFactory< >("date_pub"));
        tvtext.setCellValueFactory(new PropertyValueFactory<>("text"));
       
        tvstatus.setItems(list);
    }

    @FXML
    private void Supprimer(ActionEvent event) {
         Status s = tvstatus.getSelectionModel().getSelectedItem();
        tvstatus.getItems().removeAll(tvstatus.getSelectionModel().getSelectedItem());
        ServiceStatus ss =new ServiceStatus();
        ss.supprimer(s);

    }
    
}
