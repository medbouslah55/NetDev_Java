/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.firas;

import models.Reclamation;
import services.ReclamationCRUD;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import models.User;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.UserSession;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class ReclamerController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfDes;
    @FXML
    private Button tfEnv;
    @FXML
    private Button btnback;
    @FXML
    private MaterialDesignIconView closeButton;
    @FXML
    private ComboBox<String> combotype;

    ObservableList<String> listeTypeProbleme = FXCollections.observableArrayList("Probléme de connexion", "Probléme d'application", "Autre");
    @FXML
    private TextField tfcaptcha;
    @FXML
    private Label tfcode;
    User member = UserSession.getInstance().getLoggedUser();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        combotype.setItems(listeTypeProbleme);
        combotype.setValue("Type");
        randomcaptchacode();
        tfNom.setText(member.getNom());
        tfPrenom.setText(member.getPrenom());
        tfEmail.setText(member.getEmail());
    }

    @FXML
    private void AjouterReclamation(ActionEvent event) throws IOException, InterruptedException {

        switch (controlesaisie()) {
            case 1:
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setHeaderText(null);
                al.setContentText("Remplir les champs vides");
                al.showAndWait();
                break;
            case 2:
                Alert a2 = new Alert(Alert.AlertType.ERROR);
                a2.setHeaderText(null);
                a2.setContentText("Captcha Invalide");
                a2.showAndWait();
                randomcaptchacode();
                break;
            default:
                try {
                ReclamationCRUD rt = new ReclamationCRUD();
                rt.creeReclamation(new Reclamation(tfNom.getText(), tfPrenom.getText(), tfEmail.getText(), combotype.getValue(), Date.valueOf(LocalDate.now()), tfDes.getText()));
                notifSUCCESS("Votre Réclamation à eté envoyeé avec Suceés");
                sendMail();
                tfNom.clear();
                tfPrenom.clear();
                tfEmail.clear();
                tfDes.clear();
                ///////////////////////////////////////////////////////////////

                Parent rootRec2 = FXMLLoader.load(getClass().getResource("/gui/mohammed/MenuFront.fxml"));
                Scene rec2 = new Scene(rootRec2);
                Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app.setScene(rec2);
                app.show();
                break;
            } catch (HeadlessException | IOException e) {
                notifERROR("Erreur D'Ajouter Un Abonnement");
            }

        }

    }

    @FXML
    private void backmenu(ActionEvent event) throws IOException {
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("MenuReclamationUser.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }

    private int controlesaisie() {
        String masque = "^[a-z]+$";
        Pattern pattern = Pattern.compile(masque);
        Matcher controler = pattern.matcher(tfDes.getText());
        if (tfDes.getText().isEmpty()) {
            return 1;
        } else if (tfcode.getText().matches(tfcaptcha.getText()) == false) {
            return 2;
        } else {
            return 0;
        }
    }

    @FXML
    private void close(MouseEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    private void sendMail() {
        String to = "firasgacha8@gmail.com";
        String from = "fg7@students.kiron.ngo";
        String host = "smtp.gmail.com";
        final String username = "fg7@students.kiron.ngo";
        final String password = "f*i*r*a*s*1*3*0*2*9*8";
        ////SETUP SERVER

        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        try {
            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            //create mail
            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress(from));
            m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            m.setSubject("MindSpace : Confirmation Réclamation");
            m.setText("Mr/Mme " + tfNom.getText() + "\t" + tfPrenom.getText() + "\n"
                    + "Votre Réclamation a été envoyé avec succès! \n"
                    + "Date d'envoie : " + Date.valueOf(LocalDate.now()) + "\n"
                    + "cordialement MindSpace"
            );

            //send mail
            Transport.send(m);
            System.out.println("Message sent!");

        } catch (MessagingException e) {
            notifERROR("Erreur D'Envoyé Email");
        }
        notifSUCCESS("Email Envoyé Avec Succès");
    }

    void getfromMember() {
    }

    //////////////////////////////////////////////////////////////////////////////////
    @FXML
    public void randomcaptchacode() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 8;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        tfcode.setText(generatedString);
    }

    private void notifSUCCESS(String message) {
        String title = "Congratulations";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(3));
    }

    private void notifERROR(String message) {
        String title = "Erreur";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.ERROR);
        tray.showAndDismiss(Duration.seconds(3));
    }
}
