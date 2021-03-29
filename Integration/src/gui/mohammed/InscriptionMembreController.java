package gui.mohammed;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Properties;
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
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import models.ControleSaisie;
import models.Membre;
import services.MembreServices;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author mohamedbouslah
 */
public class InscriptionMembreController implements Initializable {

    @FXML
    private TextField tf_cin_isc;
    @FXML
    private TextField tf_taille_isc;
    @FXML
    private TextField tf_email_isc;
    @FXML
    private TextField tf_pwd_isc;
    @FXML
    private TextField tf_poids_isc;
    @FXML
    private TextField tf_telephone_isc;
    @FXML
    private TextField tf_prenom_isc;
    @FXML
    private TextField tf_nom_isc;
    @FXML
    private DatePicker dp_date_isc;
    @FXML
    private Button bt_inscri;
    @FXML
    private ComboBox cb_sexe_inscri;
    @FXML
    private Button bt_retour_inscri;

    MembreServices ms = new MembreServices();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //combo sexe
        cb_sexe_inscri.getItems().addAll("Homme", "Femme");

    }

    @FXML
    private void Inscription(ActionEvent event) {
        if (verifchamps() == true) {

            Membre m = new Membre();
            String cin = tf_cin_isc.getText();
            m.setCin(Integer.parseInt(cin));
            m.setNom(tf_nom_isc.getText());
            m.setPrenom(tf_prenom_isc.getText());
            m.setSexe((String) cb_sexe_inscri.getSelectionModel().getSelectedItem());
            Date datee = Date.valueOf(dp_date_isc.getValue());
            m.setDatee(datee);
            m.setEmail(tf_email_isc.getText());
            m.setPassword(tf_pwd_isc.getText());
            String tf_taille = tf_taille_isc.getText();
            m.setTaille(Integer.parseInt(tf_taille));
            String tf_poids = tf_poids_isc.getText();
            m.setPoids(Integer.parseInt(tf_poids));
            String telephone = tf_telephone_isc.getText();
            m.setTelephone(Integer.parseInt(telephone));

            ms.ajouter(m);

            TrayNotification tray = null;
            tray = new TrayNotification("Compte créer", "Votre compte a ete avec succes ,Merci ", NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(5));

            try {
                String host = "smtp.gmail.com";
                String user = "mehdi.dagdagui@esprit.tn";
                String pass = "Mehdi@2009";
                String to = tf_email_isc.getText();
                String from = "mehdi.dagdagui@esprit.tn";
                String subject = "Compte crée";
                String messageText = "Bonjour cher nouveau membre , votre compte a été bien crée. Cordialemment";
                boolean sessionDebug = false;

                Properties props = System.getProperties();

                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", host);
                props.put("mail.smtp.port", "587");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.required", "true");

                java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
                Session mailSession = Session.getDefaultInstance(props, null);
                mailSession.setDebug(sessionDebug);
                Message msg = new MimeMessage(mailSession);
                msg.setFrom(new InternetAddress(from));
                InternetAddress[] address = {new InternetAddress(to)};
                msg.setRecipients(Message.RecipientType.TO, address);
                msg.setSubject(subject);
                msg.setSentDate(new java.util.Date());
                msg.setText(messageText);

                Transport transport = mailSession.getTransport("smtp");
                transport.connect(host, user, pass);
                transport.sendMessage(msg, msg.getAllRecipients());
                transport.close();
                System.out.println("message send successfully");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void retour_inscri(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/mohammed/Login.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    TrayNotification tray = null;
    ControleSaisie cs = new ControleSaisie();

    private Boolean verifchamps() {

        if ((tf_cin_isc.getText().isEmpty()) || (tf_nom_isc.getText().isEmpty()) || (tf_prenom_isc.getText().isEmpty())
                || (tf_email_isc.getText().isEmpty()) || (tf_pwd_isc.getText().isEmpty()) || (tf_taille_isc.getText().isEmpty())
                || (tf_poids_isc.getText().isEmpty()) || (tf_telephone_isc.getText().isEmpty())) {

            tray = new TrayNotification("Erreur", "Il faut remplire tous les champs ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }

        if (!cs.isInte(tf_cin_isc.getText())) {

            tray = new TrayNotification("Erreur", "Verifier votre cin ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }
        if (!cs.isAlpha(tf_nom_isc.getText())) {

            tray = new TrayNotification("Erreur", "Verifier votre Nom ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }

        if (!cs.isAlpha(tf_prenom_isc.getText())) {

            tray = new TrayNotification("Erreur", "Verifier votre Prenom ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }

        if (!cs.isDate(dp_date_isc.getValue().toString())) {

            tray = new TrayNotification("Erreur", "Verifier votre Date de naissence", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }

        if (!cs.isValidEmailAddress(tf_email_isc.getText())) {

            tray = new TrayNotification("Erreur", "Verifier votre Email", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }

        if (!cs.isAlpha(tf_pwd_isc.getText())) {

            tray = new TrayNotification("Erreur", "Verifier votre Password", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }

        if (!cs.isInte(tf_taille_isc.getText())) {

            tray = new TrayNotification("Erreur", "Verifier votre Taille", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }

        if (!cs.isInte(tf_poids_isc.getText())) {

            tray = new TrayNotification("Erreur", "Verifier votre Poids", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }

        if (tf_telephone_isc.getText().length() < 8 || tf_telephone_isc.getText().length() > 8 || !cs.isInte(tf_telephone_isc.getText())) {

            tray = new TrayNotification("Erreur", "Verifier votre Telephone", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }
        return true;
    }

}
