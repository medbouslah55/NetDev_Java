/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.cyrine;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Activite;
import services.ActiviteService;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ActMembreController implements Initializable {

    @FXML
    private TableView<Activite> liste;
    @FXML
    private TableColumn<Activite, String> colNom;
    @FXML
    private TableColumn<Activite, String> colCat;
    @FXML
    private TableColumn<Activite, String> colPrix;
    @FXML
    private TableColumn<Activite, String> colCap;
    @FXML
    private TableColumn<Activite, String> colDesc;
    @FXML
    private TableColumn<Activite, Date> colDate;
    @FXML
    private TableColumn<Activite, String>colCentre;
    @FXML
    private TableColumn<Activite, String> colCoach;
    @FXML
    private TextField ftRechCat;
    @FXML
    private Button triAct;

    /**
     * Initializes the controller class.
     */
    
    
    
   public  List<Activite> deb ;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ActiviteService as = new ActiviteService();
       
               System.out.println(deb);
               
               //lezem nbadel el recherche selon liste mtaa tableview
     ftRechCat.textProperty().addListener((observable, oldValue, newValue) -> {
           if(!ftRechCat.getText().isEmpty())   {
        List k = as.ChercherListActParCategorie(ftRechCat.getText(),deb) ;
        ObservableList<Activite> l = FXCollections.observableArrayList(k) ;  
         liste.setItems(l);}
        else{
      // this.showActivite();
         liste.setItems(FXCollections.observableArrayList(deb));
       }
     });
    }    

    @FXML
    private void RechercheParCat(ActionEvent event) {
          ActiviteService as = new ActiviteService();
       
               System.out.println(deb);
               
               //lezem nbadel el recherche selon liste mtaa tableview
     ftRechCat.textProperty().addListener((observable, oldValue, newValue) -> {
           if(!ftRechCat.getText().isEmpty())   {
        List k = as.ChercherListActParCategorie(ftRechCat.getText(),deb) ;
        ObservableList<Activite> l = FXCollections.observableArrayList(k) ;  
         liste.setItems(l);}
        else{
      // this.showActivite();
         liste.setItems(FXCollections.observableArrayList(deb));
       }
     });
    }

    @FXML
    private void TriListe(ActionEvent event) {
           ObservableList<Activite> l = FXCollections.observableArrayList() ;
           l= liste.getItems() ;
           List<Activite> tri = new ArrayList<Activite>(l) ;
           Collections.sort(tri,new Activite());
           liste.setItems(FXCollections.observableArrayList(tri));
    }

    public void setListe(ObservableList<Activite> l) {
        colNom.setCellValueFactory(new PropertyValueFactory<Activite,String>("nom_act"));

    colCat.setCellValueFactory(new PropertyValueFactory<Activite,String>("categorie_act"));
    colPrix.setCellValueFactory(new PropertyValueFactory<Activite,String>("prix_reservation"));
    colCap.setCellValueFactory(new PropertyValueFactory<Activite,String>("capacite"));
    colDesc.setCellValueFactory(new PropertyValueFactory<Activite,String>("description"));
    colDate.setCellValueFactory(new PropertyValueFactory<Activite,Date>("date_act"));
    
    
    //lezem nafichi esem centre moch nom
    colCentre.setCellValueFactory(new PropertyValueFactory<Activite,String>("nom_centre"));
    colCoach.setCellValueFactory(new PropertyValueFactory<Activite,String>("nom_coach"));
    liste.setItems(l);
    }

    public void setDeb(List<Activite> deb) {
        this.deb = deb;
    }

    public List<Activite> getDeb() {
        return deb;
    }
    
    
    
    
}
