package gui.mohammed;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Coach;
import models.ControleSaisie;
import services.CoachServices;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author mohamedbouslah
 */
public class AjouterCoachAdminController implements Initializable {

    @FXML
    private AnchorPane AP_ajouter_coach;
    @FXML
    private Button menu_bt_ajouter_coach;
    @FXML
    private Button menu_bt_afficher_coach;
    @FXML
    private Button menu_bt_afficher_membre;
    @FXML
    private TextField tf_cin_coach;
    @FXML
    private TextField tf_nom_coach;
    @FXML
    private TextField tf_prenom_coach;
    @FXML
    private Button bt_ajouter_coach;
    @FXML
    private DatePicker dp_date_coach;
    @FXML
    private ComboBox cb_sexe_coach;
    @FXML
    private Button menu_bt_Stat_membre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cb_sexe_coach.getItems().addAll("Homme", "Femme");
    }

    @FXML
    private void menu_ajouter_coach(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/mohammed/AjouterCoachAdmin.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void menu_afficher_coach(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/mohammed/AfficherCoachAdmin.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void menu_afficher_membre(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/mohammed/AfficherMemebreAdmin.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void menu_stat_membre(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/mohammed/StatMembre.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    CoachServices cs = new CoachServices();

    @FXML
    private void AjouterCoach(ActionEvent event) {
        if (verifchamps() == true) {
            Coach c = new Coach();
            String cin = tf_cin_coach.getText();
            c.setCin(Integer.parseInt(cin));
            c.setNom(tf_nom_coach.getText());
            c.setPrenom(tf_prenom_coach.getText());
            c.setSexe((String) cb_sexe_coach.getSelectionModel().getSelectedItem());
            Date datee = Date.valueOf(dp_date_coach.getValue());
            c.setDatee(datee);

            cs.ajouter(c);
        }
    }

    ControleSaisie css = new ControleSaisie();
    TrayNotification tray = null;

    private Boolean verifchamps() {
        if (tf_cin_coach.getText().isEmpty() || tf_nom_coach.getText().isEmpty()
                || tf_prenom_coach.getText().isEmpty() || dp_date_coach.getValue().toString().isEmpty()) {

            tray = new TrayNotification("Error", "Verifier votre champs  ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }
        if (!css.isInte(tf_cin_coach.getText())) {
            tray = new TrayNotification("Error", "Verifier votre Cin  ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }
        if (!css.isAlpha(tf_nom_coach.getText())) {
            tray = new TrayNotification("Error", "Verifier votre Nom ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }
        if (!css.isAlpha(tf_prenom_coach.getText())) {
            tray = new TrayNotification("Error", "Verifier votre Prenom ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }
        if (!css.isDate(dp_date_coach.getValue().toString())) {
            tray = new TrayNotification("Error", "Verifier votre Nom ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }

        return true;
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/mohammed/MenuBackend.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
