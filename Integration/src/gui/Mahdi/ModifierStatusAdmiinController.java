/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Mahdi;

import models.Status;
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
import javax.swing.JOptionPane;
import services.ServiceStatus;

/**
 * FXML Controller class
 *
 * @author mehdi
 */
public class ModifierStatusAdmiinController implements Initializable {

    @FXML
    private DatePicker tfDate_Pub;
    @FXML
    private Button btnmodif;
    @FXML
    private Label date_pub;
    @FXML
    private Label date_even;
    @FXML
    private TextField tftext;
    public int sts;
    @FXML
    private Label text;

    public int getSts() {
        return sts;
    }

    public void setSts(int sts) {
        this.sts = sts;
    }

    public DatePicker getTfDate_Pub() {
        return tfDate_Pub;
    }

    public void setTfDate_Pub(String tfDate_Pub) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.tfDate_Pub.setValue(LocalDate.parse(tfDate_Pub, dateTimeFormatter));
    }

    public TextField getTftext() {
        return tftext;
    }

    public void setTfimage(String tftext) {
        this.tftext.setText(tftext);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnModifierEvenement(ActionEvent event) {
    }

    @FXML
    private void btnModifierStatus(ActionEvent event) {
        String rtext = tftext.getText();

        java.sql.Date rdapub = java.sql.Date.valueOf(tfDate_Pub.getValue());

        Status s = new Status(rdapub, rtext);
        s.setId_pub(this.getSts());

        ServiceStatus ss = new ServiceStatus();
        ss.modifier(s);
        JOptionPane.showMessageDialog(null, "status modifié avec succès !");

    }

    void setTtext(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
