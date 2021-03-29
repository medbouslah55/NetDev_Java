/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.cyrine;

import gui.cyrine.ModifierCentreController;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javax.swing.JOptionPane;
import models.Centre;
import models.ControleSaisie;
import services.CentreService;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AfficherCentresController implements Initializable {

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
    private Button refrech;
    @FXML
    private Button btnModif;
    @FXML
    private Button btnSupp;
    @FXML
    private TextField ftRechNom;
    @FXML
    private Button triCentre;

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
    private void DeleteCentre(ActionEvent event) {
        Centre c = liste.getSelectionModel().getSelectedItem();
        if (c != null) {
            liste.getItems().removeAll(liste.getSelectionModel().getSelectedItem());
            CentreService cs = new CentreService();
            cs.supprimerCentre(c);
            TrayNotification tray = null;
            tray = new TrayNotification("La suppression d'un centre", "Centre supprimé avec succès!!", NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(5));
        } else {// pas de selection de ligne mel table view 
            TrayNotification tray = null;
            tray = new TrayNotification("Suppression d'un centre", "Séléctionner une ligne tout d'abord", NotificationType.WARNING);
            tray.showAndDismiss(Duration.seconds(5));

        }
    }

    @FXML
    private void UpdateCentre(ActionEvent event) {
        Centre c = liste.getSelectionModel().getSelectedItem();
        if (c != null) {
            try {
                FXMLLoader loader
                        = new FXMLLoader(getClass().getResource("ModifierCentre.fxml"));
                Parent root = loader.load();
                ModifierCentreController md = loader.getController();
                md.setFtModifNom(c.getNom_centre());
                md.setFtModifAdr(c.getAdr_centre());
                md.setFtModifMail(c.getMail_centre());
                md.setFtModifTel(c.getTel_centre());
                md.setFtModifType(c.getType_centre());
                md.setId_centre(c.getId_centre());

                Stage stage = new Stage();
                stage.setTitle("Modifier centre");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        } else {// pas de selection de ligne mel table view 
            TrayNotification tray = null;
            tray = new TrayNotification("Modification d'un centre", "Séléctionner une ligne tout d'abord", NotificationType.WARNING);
            tray.showAndDismiss(Duration.seconds(5));

        }

    }

    /*     public ObservableList<Centre> getCentreListfiltrer(String nom){
    ObservableList<Centre> l = FXCollections.observableArrayList() ;
    String req ="Select * from centre" ;
    
    try {
     Statement st = MyConnection.getInstance().getConx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if(rs.getString("nom_centre").toUpperCase().contains(nom.toUpperCase())){
                Centre c = new Centre() ;
                c.setId_centre(rs.getInt("id_centre"));
                c.setAdr_centre(rs.getString("adr_centre"));
                c.setMail_centre(rs.getString("mail_centre"));
                c.setTel_centre(rs.getString("tel_centre"));
                c.setType_centre(rs.getString("type_centre"));
                c.setNom_centre(rs.getString("nom_centre"));
               
                l.add(c);}
            }
    
    }catch(Exception ex){
    
        System.out.println(ex.getMessage());
    }
    
 
   /* CentreService cs = new CentreService() ;
  /*  l =(ObservableList<Centre>) cs.CentreListe() ;*/
 /*  return l ;
    }*/
 /*  public void showCentresfiltrer(String nom){
     ObservableList<Centre> l = getCentreListfiltrer(nom) ;
    colNom.setCellValueFactory(new PropertyValueFactory<Centre,String>("nom_centre"));
    colType.setCellValueFactory(new PropertyValueFactory<Centre,String>("type_centre"));
    colMail.setCellValueFactory(new PropertyValueFactory<Centre,String>("mail_centre"));
    colTel.setCellValueFactory(new PropertyValueFactory<Centre,String>("tel_centre"));
    colAdr.setCellValueFactory(new PropertyValueFactory<Centre,String>("adr_centre"));
    liste.setItems(l);
    
    }*/
    @FXML
    private void RechrechListe(ActionEvent event) {
        this.showCentres();
    }

    private void detectChange(ActionEvent event) {
        String res = ftRechNom.getText();
        if (res.isEmpty()) {
            this.showCentres();
        }
    }


    /* @FXML
    private void rechCentre(ActionEvent event) {
        if(ftRechNom.getText().isEmpty()){}
        else {
        showCentresfiltrer(ftRechNom.getText()) ;
        }
        
        
    }*/
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

    @FXML
    private void rechParNom(ActionEvent event) {
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
