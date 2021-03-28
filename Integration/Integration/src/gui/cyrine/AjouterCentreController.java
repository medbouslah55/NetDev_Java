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
            JOptionPane.showMessageDialog(null, "vérifier vos données!");
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

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public boolean isInte(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public boolean isAlpha(String ch) {
        if (ch == null) {
            return false;
        } else {
            int i = 0;
            while (i < ch.length()) {
                if (((int) ch.charAt(i) >= 65 && (int) ch.charAt(i) <= 90) || ((int) ch.charAt(i) >= 97 && (int) ch.charAt(i) <= 122)) {
                    i++;
                } else {
                    return false;
                }
            }

        }

        return true;

    }

    private boolean controle() {

        if (ftNom.getText().isEmpty() || ftType.getText().isEmpty() || ftTel.getText().isEmpty()
                || ftMail.getText().isEmpty() || ftAdr.getText().isEmpty()) {
            return false;
        }

        int mail = 0;
        if (ftMail.getText().matches("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            mail = 1;
        }

        if (mail == 0) {
            return false;
        }

        if (this.isAlpha(ftNom.getText()) == false || this.isAlpha(ftType.getText()) == false) {
            return false;
        }

        if (ftTel.getText().length() < 8 || ftTel.getText().length() > 8 || this.isInte(ftTel.getText()) == false) {
            return false;
        }

        return true;

    }

}
