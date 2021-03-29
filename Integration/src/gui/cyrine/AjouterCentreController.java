/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.cyrine;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class AjouterCentreController implements Initializable {

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
    private TextField ftNom;
    @FXML
    private TextField ftTel;
    @FXML
    private TextField ftMail;
    @FXML
    private TextField ftAdr;
    @FXML
    private TextField ftType;
    @FXML
    private Button btnAjout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AddCentre(ActionEvent event) {

        if (this.controle() == false) {
            //  JOptionPane.showMessageDialog(null, "vérifier vos données!");
            System.out.println("vérifier vos données");
        } else {
            String rNom = ftNom.getText();
            String rType = ftType.getText();
            String rTel = ftTel.getText();
            String rMail = ftMail.getText();
            String rAdr = ftAdr.getText();
            Centre c = new Centre(rNom, rTel, rMail, rAdr, rType);
            CentreService cs = new CentreService();
            cs.ajouterCentre(c);
            TrayNotification tray = null;
            tray = new TrayNotification("L'ajout d'un centre", "Centre ajouté avec succès!!", NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(5));
        }

    }

    private boolean controle() {
        ControleSaisie cs = new ControleSaisie();
        TrayNotification tray = null;

        if (ftNom.getText().isEmpty() && ftType.getText().isEmpty() && ftTel.getText().isEmpty()
                && ftMail.getText().isEmpty() && ftAdr.getText().isEmpty()) {
            tray = new TrayNotification("Ajout d'un centre", "Les champs sont vides", NotificationType.WARNING);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }

        if (ftNom.getText().isEmpty()) {
            tray = new TrayNotification("Ajout d'un centre", "Le champs nom est vide", NotificationType.WARNING);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }

        if (ftType.getText().isEmpty()) {
            tray = new TrayNotification("Ajout d'un centre", "Le champs type est vide", NotificationType.WARNING);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }

        if (ftTel.getText().isEmpty()) {
            tray = new TrayNotification("Ajout d'un centre", "Le champs numéro téléphone est vide", NotificationType.WARNING);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }

        if (ftMail.getText().isEmpty()) {
            tray = new TrayNotification("Ajout d'un centre", "Le champs mail est vide", NotificationType.WARNING);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }

        if (ftAdr.getText().isEmpty()) {
            tray = new TrayNotification("Ajout d'un centre", "Le champs address est vide", NotificationType.WARNING);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }

        if (!cs.isValidEmailAddress(ftMail.getText())) {
            tray = new TrayNotification("Ajout d'un centre", "Le champs mail est érroné", NotificationType.WARNING);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }

        /* int mail =0 ;
     if (ftMail.getText().matches("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) 
         mail=1 ;
     
     if(mail==0)
         return false ;*/
        if (!cs.isAlpha(ftType.getText())) {
            tray = new TrayNotification("Ajout d'un centre", "Le champs type est érroné", NotificationType.WARNING);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }
        if (!cs.isAlpha(ftNom.getText())) {
            tray = new TrayNotification("Ajout d'un centre", "Le champs nom est érroné", NotificationType.WARNING);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }

        if (ftTel.getText().length() < 8 || ftTel.getText().length() > 8 || !cs.isInte(ftTel.getText())) {
            tray = new TrayNotification("Ajout d'un centre", "Le champs numéro téléphone est érroné", NotificationType.WARNING);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }

        CentreService tst = new CentreService();
        if (tst.ChercherCentreParNom(ftNom.getText()) != null) {
            tray = new TrayNotification("Ajout d'un centre", "Ce nom exite déja", NotificationType.WARNING);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }

        if (tst.ChercherCentreParMail(ftMail.getText()) != null) {
            tray = new TrayNotification("Ajout d'un centre", "Ce mail exite déja", NotificationType.WARNING);
            tray.showAndDismiss(Duration.seconds(10));
            return false;
        }

        return true;

    }

}
