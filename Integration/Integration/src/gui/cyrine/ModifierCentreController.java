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
import javax.swing.JOptionPane;
import models.Centre;
import services.CentreService;

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
            JOptionPane.showMessageDialog(null, "vérifier vos champs !");
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
            JOptionPane.showMessageDialog(null, "Centre modifié avec succès !");

            /*   FXMLLoader loader =
            new FXMLLoader(getClass().getResource("GererCentre.fxml"));
            Parent root = loader.load() ;
            GererCentreController gc = loader.getController() ;
            gc.showCentres();*/
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

        if (ftModifNom.getText().isEmpty() || ftModifType.getText().isEmpty() || ftModifTel.getText().isEmpty()
                || ftModifMail.getText().isEmpty() || ftModifAdr.getText().isEmpty()) {
            return false;
        }

        int mail = 0;
        if (ftModifMail.getText().matches("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            mail = 1;
        }

        if (mail == 0) {
            return false;
        }

        if (this.isAlpha(ftModifNom.getText()) == false || this.isAlpha(ftModifType.getText()) == false) {
            return false;
        }

        if (ftModifTel.getText().length() < 8 || ftModifTel.getText().length() > 8 || this.isInte(ftModifTel.getText()) == false) {
            return false;
        }

        return true;

    }

}
