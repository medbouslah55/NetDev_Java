/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.cyrine;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Activite;
import models.Centre;
import models.ControleSaisie;
import services.ActiviteService;
import services.CentreService;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class CentresMembreController implements Initializable {

    @FXML
    private TableView<Centre> liste;
    @FXML
    private TableColumn<Centre, String> colNom;
    @FXML
    private TableColumn<Centre, String> colTel;
    @FXML
    private TableColumn<Centre, String> colMail;
    @FXML
    private TableColumn<Centre, String> colAdr;
    @FXML
    private TableColumn<Centre, String> colType;
    @FXML
    private TextField ftRechNom;
    @FXML
    private Button triCentre;
    @FXML
    private Button btnAct;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.showCentres();
        CentreService as = new CentreService();
        ControleSaisie cs = new ControleSaisie();
        ftRechNom.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!ftRechNom.getText().isEmpty()) {
                if (cs.isAlpha(ftRechNom.getText())) {
                    List k = as.ChercherListCentreParNom(ftRechNom.getText());
                    ObservableList<Centre> l = FXCollections.observableArrayList(k);
                    liste.setItems(l);
                } else {
                    TrayNotification tray = null;
                    tray = new TrayNotification("Recherche par nom", "vérifier vos champs", NotificationType.WARNING);
                    tray.showAndDismiss(Duration.seconds(5));
                }

            } else {
                this.showCentres();

            }
        });
    }

    @FXML
    private void TriListe(ActionEvent event) {
        CentreService ac = new CentreService();
        List<Centre> trier = ac.CentreListeTrierParNom();

        ObservableList<Centre> l = FXCollections.observableArrayList();
        try {
            int i = 0;
            while (trier.size() > i) {
                Centre c = new Centre();
                c.setId_centre(trier.get(i).getId_centre());
                c.setAdr_centre(trier.get(i).getAdr_centre());
                c.setMail_centre(trier.get(i).getMail_centre());
                c.setNom_centre(trier.get(i).getNom_centre());
                c.setTel_centre(trier.get(i).getTel_centre());
                c.setType_centre(trier.get(i).getType_centre());
                i++;

                l.add(c);
            }

            colNom.setCellValueFactory(new PropertyValueFactory<Centre, String>("nom_centre"));
            colType.setCellValueFactory(new PropertyValueFactory<Centre, String>("type_centre"));
            colMail.setCellValueFactory(new PropertyValueFactory<Centre, String>("mail_centre"));
            colTel.setCellValueFactory(new PropertyValueFactory<Centre, String>("tel_centre"));
            colAdr.setCellValueFactory(new PropertyValueFactory<Centre, String>("adr_centre"));
            liste.setItems(l);

        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }
    }

    public ObservableList<Centre> getCentreList() {
        ObservableList<Centre> l = FXCollections.observableArrayList();
        CentreService cs = new CentreService();
        List<Centre> l2 = cs.CentreListe();

        try {
            int i = 0;
            while (l2.size() > i) {
                Centre c = new Centre();
                c.setId_centre(l2.get(i).getId_centre());
                c.setAdr_centre(l2.get(i).getAdr_centre());
                c.setMail_centre(l2.get(i).getMail_centre());
                c.setTel_centre(l2.get(i).getTel_centre());
                c.setType_centre(l2.get(i).getType_centre());
                c.setNom_centre(l2.get(i).getNom_centre());
                i++;
                l.add(c);
            }

        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }

        /* CentreService cs = new CentreService() ;
    l =(ObservableList<Centre>) cs.CentreListe() ;*/
        return l;
    }

    public void showCentres() {
        ObservableList<Centre> l = getCentreList();
        colNom.setCellValueFactory(new PropertyValueFactory<Centre, String>("nom_centre"));
        colType.setCellValueFactory(new PropertyValueFactory<Centre, String>("type_centre"));
        colMail.setCellValueFactory(new PropertyValueFactory<Centre, String>("mail_centre"));
        colTel.setCellValueFactory(new PropertyValueFactory<Centre, String>("tel_centre"));
        colAdr.setCellValueFactory(new PropertyValueFactory<Centre, String>("adr_centre"));
        liste.setItems(l);

    }

    @FXML
    private void listerAct(ActionEvent event) {
        try {
            FXMLLoader loader
                    = new FXMLLoader(getClass().getResource("ActMembre.fxml"));
            Parent root = loader.load();
            ActMembreController ac = loader.getController();
            ActiviteService act = new ActiviteService();
            //linjection 
            ObservableList<Activite> l = FXCollections.observableArrayList(act.listeAct_Par_Centre(liste.getSelectionModel().getSelectedItem().getId_centre()));
            if (!l.isEmpty()) {
                ac.setListe(l);
                ac.setDeb(act.listeAct_Par_Centre(liste.getSelectionModel().getSelectedItem().getId_centre()));
                System.out.println(ac.getDeb());

                Stage stage = new Stage();
                stage.setTitle("Liste des activité du centre " + liste.getSelectionModel().getSelectedItem().getNom_centre());
                stage.setScene(new Scene(root));
                stage.show();
            } else {
                TrayNotification tray = null;
                tray = new TrayNotification("Affichage des activités par centre", "Pas d'activités pour ce centre pour le moment", NotificationType.WARNING);
                tray.showAndDismiss(Duration.seconds(5));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void RechParNom(ActionEvent event) {
        CentreService as = new CentreService();
        ControleSaisie cs = new ControleSaisie();
        ftRechNom.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!ftRechNom.getText().isEmpty()) {
                if (cs.isAlpha(ftRechNom.getText())) {
                    List k = as.ChercherListCentreParNom(ftRechNom.getText());
                    ObservableList<Centre> l = FXCollections.observableArrayList(k);
                    liste.setItems(l);
                } else {
                    TrayNotification tray = null;
                    tray = new TrayNotification("Recherche par nom", "vérifier vos champs", NotificationType.WARNING);
                    tray.showAndDismiss(Duration.seconds(5));
                }

            } else {
                this.showCentres();

            }
        });
    }

}
