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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
public class ModifierCentreController implements Initializable {

    @FXML
    private Label lbCentre;
    @FXML
    private Label lbTel;
    @FXML
    private Label lbMail;
    @FXML
    private Label lbAdr;
    @FXML
    private Label lbType;
    @FXML
    private TextField ftModifNom;
    @FXML
    private TextField ftModifTel;
    @FXML
    private TextField ftModifMail;
    @FXML
    private TextField ftModifAdr;
    @FXML
    private TextField ftModifType;
    @FXML
    private Button btnModif2;
    @FXML
    private Label lbTitre1;

    private int id_centre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setFtModifNom(String ftModifNom) {
        this.ftModifNom.setText(ftModifNom);
    }

    public void setFtModifTel(String ftModifTel) {
        this.ftModifTel.setText(ftModifTel);
    }

    public void setFtModifMail(String ftModifMail) {
        this.ftModifMail.setText(ftModifMail);
    }

    public void setFtModifAdr(String ftModifAdr) {
        this.ftModifAdr.setText(ftModifAdr);
    }

    public void setFtModifType(String ftModifType) {
        this.ftModifType.setText(ftModifType);
    }

    public void setId_centre(int id_centre) {
        this.id_centre = id_centre;
    }

    public int getId_centre() {
        return id_centre;
    }

    @FXML
    private void updateCentre2(ActionEvent event) {

        if (this.controle() == false) {
            System.out.println("vérifier vos champs");
            // JOptionPane.showMessageDialog(null, "vérifier vos champs !");
        } else {
            String rNom = ftModifNom.getText();
            String rType = ftModifType.getText();
            String rTel = ftModifTel.getText();
            String rMail = ftModifMail.getText();
            String rAdr = ftModifAdr.getText();
            Centre c = new Centre(rNom, rTel, rMail, rAdr, rType);
            c.setId_centre(this.getId_centre());
            CentreService cs = new CentreService();
            cs.modifierCentre(c);
            
            TrayNotification tray = null;
            tray = new TrayNotification("Modification d'un centre", "Centre modifié avec succès !", NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(10));
//            JOptionPane.showMessageDialog(null, "Centre modifié avec succès !");

            /*   FXMLLoader loader =
            new FXMLLoader(getClass().getResource("GererCentre.fxml"));
            Parent root = loader.load() ;
            GererCentreController gc = loader.getController() ;
            gc.showCentres();*/
        }

    }

    private boolean controle() {

        ControleSaisie cs = new ControleSaisie();
        TrayNotification tray = null;

        if (ftModifNom.getText().isEmpty() && ftModifType.getText().isEmpty() && ftModifTel.getText().isEmpty()
                && ftModifMail.getText().isEmpty() && ftModifAdr.getText().isEmpty()) {
            tray = new TrayNotification("Modification d'un centre", "Les champs sont vides", NotificationType.WARNING);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }

        if (ftModifNom.getText().isEmpty()) {
            tray = new TrayNotification("Modification d'un centre", "Le champs nom est vide", NotificationType.WARNING);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }

        if (ftModifType.getText().isEmpty()) {
            tray = new TrayNotification("Modification d'un centre", "Le champs type est vide", NotificationType.WARNING);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }

        if (ftModifTel.getText().isEmpty()) {
            tray = new TrayNotification("Modification d'un centre", "Le champs numéro téléphone est vide", NotificationType.WARNING);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }

        if (ftModifMail.getText().isEmpty()) {
            tray = new TrayNotification("Modification d'un centre", "Le champs mail est vide", NotificationType.WARNING);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }

        if (ftModifAdr.getText().isEmpty()) {
            tray = new TrayNotification("Modification d'un centre", "Le champs address est vide", NotificationType.WARNING);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }

        if (!cs.isValidEmailAddress(ftModifMail.getText())) {
            tray = new TrayNotification("Modification d'un centre", "Le champs mail est érroné", NotificationType.WARNING);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }

        /* int mail =0 ;
     if (ftMail.getText().matches("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) 
         mail=1 ;
     
     if(mail==0)
         return false ;*/
        if (!cs.isAlpha(ftModifType.getText())) {
            tray = new TrayNotification("Modification d'un centre", "Le champs type est érroné", NotificationType.WARNING);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }
        if (!cs.isAlpha(ftModifNom.getText())) {
            tray = new TrayNotification("Ajout d'un centre", "Le champs nom est érroné", NotificationType.WARNING);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }

        if (ftModifTel.getText().length() < 8 || ftModifTel.getText().length() > 8 || !cs.isInte(ftModifTel.getText())) {
            tray = new TrayNotification("Ajout d'un centre", "Le champs numéro téléphone est érroné", NotificationType.WARNING);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }

        CentreService tst = new CentreService();
        if (tst.ChercherCentreParNom(ftModifNom.getText(), this.getId_centre()) != null) {
            tray = new TrayNotification("Ajout d'un centre", "Ce nom exite déja pour un autre centre", NotificationType.WARNING);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }

        if (tst.ChercherCentreParMail(ftModifMail.getText(), this.getId_centre()) != null) {
            tray = new TrayNotification("Ajout d'un centre", "Ce mail exite déja pour un autre centre", NotificationType.WARNING);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }

        return true;

    }

}
