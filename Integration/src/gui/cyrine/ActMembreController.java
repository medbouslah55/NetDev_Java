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
import javafx.util.Duration;
import models.Activite;
import models.ControleSaisie;
import models.Panier;
import services.ActiviteService;
import services.PanierCRUD;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

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
    private TableColumn<Activite, String> colCentre;
    @FXML
    private TableColumn<Activite, String> colCoach;
    @FXML
    private TextField ftRechCat;
    @FXML
    private Button triAct;

    /**
     * Initializes the controller class.
     */
    public List<Activite> deb;
    @FXML
    private Button ajouterpanier;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ActiviteService as = new ActiviteService();
        ControleSaisie cs = new ControleSaisie();
        System.out.println(deb);

        //lezem nbadel el recherche selon liste mtaa tableview
        ftRechCat.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!ftRechCat.getText().isEmpty()) {
                if (cs.isAlpha(ftRechCat.getText())) {
                    List k = as.ChercherListActParCategorie(ftRechCat.getText(), deb);
                    ObservableList<Activite> l = FXCollections.observableArrayList(k);
                    liste.setItems(l);
                } else {
                    TrayNotification tray = null;
                    tray = new TrayNotification("Recherche par catégorie", "vérifier vos champs", NotificationType.WARNING);
                    tray.showAndDismiss(Duration.seconds(5));
                }

            } else {
                // this.showActivite();
                liste.setItems(FXCollections.observableArrayList(deb));
            }
        });
    }

    @FXML
    private void RechercheParCat(ActionEvent event) {
        ActiviteService as = new ActiviteService();
        ControleSaisie cs = new ControleSaisie();
        System.out.println(deb);

        //lezem nbadel el recherche selon liste mtaa tableview
        ftRechCat.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!ftRechCat.getText().isEmpty()) {
                if (cs.isAlpha(ftRechCat.getText())) {
                    List k = as.ChercherListActParCategorie(ftRechCat.getText(), deb);
                    ObservableList<Activite> l = FXCollections.observableArrayList(k);
                    liste.setItems(l);
                } else {
                    TrayNotification tray = null;
                    tray = new TrayNotification("Recherche par catégorie", "vérifier vos champs", NotificationType.WARNING);
                    tray.showAndDismiss(Duration.seconds(5));
                }

            } else {
                // this.showActivite();
                liste.setItems(FXCollections.observableArrayList(deb));
            }
        });
    }

    @FXML
    private void TriListe(ActionEvent event) {
        ObservableList<Activite> l = FXCollections.observableArrayList();
        l = liste.getItems();
        List<Activite> tri = new ArrayList<Activite>(l);
        Collections.sort(tri, new Activite());
        liste.setItems(FXCollections.observableArrayList(tri));
    }

    public void setListe(ObservableList<Activite> l) {
        colNom.setCellValueFactory(new PropertyValueFactory<Activite, String>("nom_act"));

        colCat.setCellValueFactory(new PropertyValueFactory<Activite, String>("categorie_act"));
        colPrix.setCellValueFactory(new PropertyValueFactory<Activite, String>("prix_reservation"));
        colCap.setCellValueFactory(new PropertyValueFactory<Activite, String>("capacite"));
        colDesc.setCellValueFactory(new PropertyValueFactory<Activite, String>("description"));
        colDate.setCellValueFactory(new PropertyValueFactory<Activite, Date>("date_act"));

        //lezem nafichi esem centre moch nom
        colCentre.setCellValueFactory(new PropertyValueFactory<Activite, String>("nom_centre"));
        colCoach.setCellValueFactory(new PropertyValueFactory<Activite, String>("nom_coach"));
        liste.setItems(l);
    }

    public void setDeb(List<Activite> deb) {
        this.deb = deb;
    }

    public List<Activite> getDeb() {
        return deb;
    }

    @FXML
    private void ajouterpanier(ActionEvent event) {
        Activite a = liste.getSelectionModel().getSelectedItem();
        PanierCRUD p= new PanierCRUD();
        Panier b = new Panier();
        b.setNom_act(a.getNom_act());
        b.setPrix((float) a.getPrix_reservation());
        b.setCapacite(a.getCapacite());
        p.ajouterpanier(b);
        System.out.println("Panier ajoutee");
    }

}
