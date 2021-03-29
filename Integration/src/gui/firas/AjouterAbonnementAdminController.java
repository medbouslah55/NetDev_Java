/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.firas;

import models.Abonnement;
import services.AbonnementCRUD;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class AjouterAbonnementAdminController implements Initializable {

    @FXML
    private TextField tftitre;
    @FXML
    private TextField tftype;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tfdescr;
    private Stage primaryStage;
    @FXML
    private Button btnback;
    @FXML
    private MaterialDesignIconView closeButton;
    @FXML
    private Button btnajou;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void ajouterAB(ActionEvent event) throws IOException {
        if (tftitre.getText().isEmpty() | tfdescr.getText().isEmpty() | tftype.getText().isEmpty() | tfprix.getText().isEmpty()) {
            //JOptionPane.showMessageDialog(null, "Remplir les champs vides");
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Remplir les champs vides");
            al.showAndWait();
        } else {
            AbonnementCRUD rt = new AbonnementCRUD();
            rt.ajouterAbonnement(new Abonnement(tftitre.getText(), tftype.getText(), Float.parseFloat(tfprix.getText()), tfdescr.getText()));
            notifSUCCESS("Abonnement Ajouteé Avec Succès");
            tftitre.clear();
            tftype.clear();
            tfprix.clear();
            tfdescr.clear();
            Parent rootRec2 = FXMLLoader.load(getClass().getResource("ajouterAbonnementAdmin.fxml"));
            Scene rec2 = new Scene(rootRec2);
            Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app.setScene(rec2);
            app.show();
        }
    }

    @FXML
    private void backmenu(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/firas/MenuGeneralFiras.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void controlesaisie() {
        if (tftitre.getText().isEmpty() | tfdescr.getText().isEmpty() | tftype.getText().isEmpty() | tfprix.getText().isEmpty()) {
            //JOptionPane.showMessageDialog(null, "Remplir les champs vides");
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Remplir les champs vides");
            al.showAndWait();

        }
    }

    @FXML
    private void close(MouseEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    private void notifSUCCESS(String message) {
        String title = "Congratulations";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(3));
    }
}
