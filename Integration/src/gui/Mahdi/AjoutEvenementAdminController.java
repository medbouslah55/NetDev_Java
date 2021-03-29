/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Mahdi;

import models.Evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import models.ControleSaisie;
import services.ServicePublication;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author mehdi
 */
public class AjoutEvenementAdminController implements Initializable {

    @FXML
    private AnchorPane AP_ajouter_coach;
    @FXML
    private Button menu_bt_ajouterdate_pub;
    @FXML
    private Button menu_bt_afficher_evenement;
    @FXML
    private Button menu_bt_afficher_membre;
    @FXML
    private TextField tfimage;
    @FXML
    private Button bt_ajouter_Evenement;
    @FXML
    private DatePicker tfDate_Pub;
    @FXML
    private DatePicker tfDate_Even;
    @FXML
    private TextField tfSujet;
    @FXML
    private ComboBox<String> combo_id_centre;

    ObservableList<String> list = FXCollections.observableArrayList();

    Connection cnx = DataSource.getInstance().getCnx();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            String requete = "SELECT nom_centre FROM centre";
            Statement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(rs.getString("nom_centre"));
            }

            combo_id_centre.setItems(list);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void menu_ajouter_Evenement(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AjoutEvenementAdmin.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void menu_afficher_Evenement(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AfficherEvenementAdmin.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void menu_afficher_Status(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AfficherStatusAdmin.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private Boolean verifchamps() {

        ControleSaisie cs = new ControleSaisie();

        if (tfDate_Pub.getValue() == null) {

            TrayNotification tray = null;
            tray = new TrayNotification("Erreur", "Veuillez remplir le la date de pub+ ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }

        if (tfDate_Even.getValue() == null) {

            TrayNotification tray = null;
            tray = new TrayNotification("Erreur", "Veuillez remplir le la date even+ ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }

        if (tfimage.getText().isEmpty() || tfSujet.getText().isEmpty()) {

            TrayNotification tray = null;
            tray = new TrayNotification("Erreur", "Veuillez remplire tt les champs ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }

        if (!cs.isAlpha(tfimage.getText())) {
            TrayNotification tray = null;
            tray = new TrayNotification("Erreur", "Verifier votre IMAGE ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }
        if (!cs.isAlpha(tfSujet.getText())) {
            TrayNotification tray = null;
            tray = new TrayNotification("Erreur", "Verifier votre SUJET ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }

        return true;
    }

    @FXML
    private void AjouterEvenement(ActionEvent event) throws SQLException {

        TrayNotification tray = null;

        ServicePublication sp = new ServicePublication();

        if (verifchamps() == true) {
            String image = tfimage.getText();
            String Sujet = tfSujet.getText();
            String reg = (String) combo_id_centre.getValue();
            java.sql.Date pub = java.sql.Date.valueOf(tfDate_Pub.getValue());
            java.sql.Date even = java.sql.Date.valueOf(tfDate_Even.getValue());
            String requete = "SELECT id_centre FROM centre WHERE nom_centre=" + (char) 34 + reg + (char) 34;
            Statement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            if (rs.next()) {
                sp.ajouter(new Evenement(pub, even, image, Sujet, rs.getInt(1)));
                JOptionPane.showMessageDialog(null, "Evenement ajoutée!");
                tray = new TrayNotification("MINDSPACE", "Evenement ajouté avec succces ", NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.seconds(7));
            }

        }

    }

}
