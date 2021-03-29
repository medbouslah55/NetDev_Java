package gui.mohammed;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.ControleSaisie;
import org.apache.commons.codec.digest.DigestUtils;
import services.AdminServices;
import services.MembreServices;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.UserSession;

/**
 * FXML Controller class
 *
 * @author mohamedbouslah
 */
public class LoginController implements Initializable {

    @FXML
    private TextField tf_login_email_membre;
    @FXML
    private TextField tf_login_pass_membre;
    @FXML
    private Button bt_login_membre;
    @FXML
    private TextField tf_login_email_admin;
    @FXML
    private TextField tf_login_pass_admin;
    @FXML
    private Button bt_login_admin;
    @FXML
    private Button bt_inscri_membre_login;
    @FXML
    private Button bt_pass_oublier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    MembreServices ms = new MembreServices();
    AdminServices as = new AdminServices();

    @FXML
    private void login_membre(ActionEvent event) throws SQLException, IOException {
        if (verifchampsMembre() == true) {
            String email = tf_login_email_membre.getText();
            String pwd = DigestUtils.shaHex(tf_login_pass_membre.getText());

                UserSession.setInstance(email);
            if (ms.loginMembre(email, pwd) == 1) {
                TrayNotification tray = null;
                tray = new TrayNotification("Login", "Welcome back " + UserSession.getInstance().getNom(), NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.seconds(5));
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
//                Parent root = FXMLLoader.load(getClass().getResource("/gui/firas/Consulter-Mes-Réclamations.fxml"));/* Exception */
                Parent root = FXMLLoader.load(getClass().getResource("/gui/mohammed/MenuFront.fxml"));/* Exception */
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } else {
                TrayNotification tray = null;
                tray = new TrayNotification("Error", "Email ou password incorrect", NotificationType.ERROR);
                tray.showAndDismiss(Duration.seconds(5));
            }
        }
    }

    @FXML
    private void login_admin(ActionEvent event) throws IOException {
        if (verifchampsAdmin() == true) {
            String email = tf_login_email_admin.getText();
            String pwd = tf_login_pass_admin.getText();
            if (as.loginAdmin(email, pwd) == 1) {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/gui/mohammed/MenuBackend.fxml"));/* Exception */
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                TrayNotification tray = null;
                tray = new TrayNotification("Error", "Email ou password incorrect", NotificationType.ERROR);
                tray.showAndDismiss(Duration.seconds(5));
            }
        }
    }

    @FXML
    private void inscri_membre_login(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/mohammed/InscriptionMembre.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void pwd_oublier(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/mohammed/RecuperePwdMembre.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    ControleSaisie css = new ControleSaisie();

    private Boolean verifchampsMembre() {
        if (tf_login_email_membre.getText().isEmpty() || tf_login_pass_membre.getText().isEmpty()) {
            TrayNotification tray = null;
            tray = new TrayNotification("Error", "Email ou password incorrect  ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }

        if (!css.isValidEmailAddress(tf_login_email_membre.getText())) {
            TrayNotification tray = null;
            tray = new TrayNotification("Error", "verifier votre email  ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;

        }

        return true;

    }

    private Boolean verifchampsAdmin() {
        if (tf_login_email_admin.getText().isEmpty() || tf_login_pass_admin.getText().isEmpty()) {
            TrayNotification tray = null;
            tray = new TrayNotification("Error", "Email ou password incorrect  ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }

        if (!css.isValidEmailAddress(tf_login_email_admin.getText())) {
            TrayNotification tray = null;
            tray = new TrayNotification("Error", "verifier votre email  ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }

        return true;

    }

}
