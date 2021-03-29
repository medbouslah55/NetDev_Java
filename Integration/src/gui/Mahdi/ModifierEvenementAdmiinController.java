/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Mahdi;

import models.Evenement;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import models.ControleSaisie;
import services.ServicePublication;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author mehdi
 */
public class ModifierEvenementAdmiinController implements Initializable {

    @FXML
    private DatePicker tfDate_Pub;
    @FXML
    private DatePicker tfDate_even;
    @FXML
    private TextField tfimage;
    @FXML
    private TextField tfsujet;
    @FXML
    private Button btnmodif;

    public int evt;
    @FXML
    private Label date_pub;
    @FXML
    private Label date_even;
    @FXML
    private Label image;
    @FXML
    private Label sujet;

    public int getEvt() {
        return evt;
    }

    public void setEvt(int evt) {
        this.evt = evt;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public DatePicker getTfDate_Pub() {
        return tfDate_Pub;
    }

    public void setTfDate_Pub(String tfDate_Pub) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.tfDate_Pub.setValue(LocalDate.parse(tfDate_Pub, dateTimeFormatter));
    }

    public DatePicker getTfDate_even() {
        return tfDate_even;
    }

    public void setTfDate_even(String tfDate_even) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.tfDate_even.setValue(LocalDate.parse(tfDate_even, dateTimeFormatter));
    }

    public TextField getTfimage() {
        return tfimage;
    }

    public void setTfimage(String tfimage) {
        this.tfimage.setText(tfimage);
    }

    public TextField getTfsujet() {
        return tfsujet;
    }

    public void setTfsujet(String tfsujet) {
        this.tfsujet.setText(tfsujet);
    }

    private Boolean verifchamps() {

        ControleSaisie css = new ControleSaisie();

        if (!css.isAlpha(tfimage.getText())) {
            TrayNotification tray = null;
            tray = new TrayNotification("Erreur", "Verifier votre IMAGE ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }
        if (!css.isAlpha(tfsujet.getText())) {
            TrayNotification tray = null;
            tray = new TrayNotification("Erreur", "Verifier votre SUJET ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }

        if (!css.isDate(tfDate_Pub.getValue().toString())) {
            TrayNotification tray = null;
            tray = new TrayNotification("Erreur", "Verifier votre date_pub ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }

        if (!css.isDate(tfDate_even.getValue().toString())) {
            TrayNotification tray = null;
            tray = new TrayNotification("Erreur", "Verifier votre date_even ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }

        if ((tfimage.getText().isEmpty()) || (tfsujet.getText().isEmpty())) {
            return false;
        }

        return true;
    }

    @FXML
    private void btnModifierEvenement(ActionEvent event) {
        TrayNotification tray = null;

        if (verifchamps() == true) {

            String rsujet = tfsujet.getText();
            String rimage = tfimage.getText();
            java.sql.Date rdapub = java.sql.Date.valueOf(tfDate_Pub.getValue());
            java.sql.Date rdaeven = java.sql.Date.valueOf(tfDate_even.getValue());

            Evenement e = new Evenement(rdaeven, rimage, rsujet, rdapub);
            e.setId_pub(this.getEvt());

            ServicePublication sp = new ServicePublication();
            sp.modifier(e);
            JOptionPane.showMessageDialog(null, "even modifié avec succès !");

        }
    }
}
