/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.cyrine;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.CentreService;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class MailingCentreMembreController implements Initializable {

    @FXML
    private ComboBox<String> ComboMail;
    @FXML
    private Label lbCentre;
    @FXML
    private Label lbTel;
    @FXML
    private Label lbMail;
    @FXML
    private Label lbAdr;
    @FXML
    private Label lbType1;
    @FXML
    private TextArea textMail;
    @FXML
    private TextField tfSujet;
    @FXML
    private Button btnEnvoyer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CentreService cs = new CentreService();
        ComboMail.setItems(FXCollections.observableArrayList(cs.getMails()));

    }

    public void setComboMail(ObservableList<String> mails) {
        this.ComboMail.setItems(mails);
    }

    @FXML
    private void EnvoyerMail(ActionEvent event) {
        TrayNotification tray = null;
        if (textMail.getText().isEmpty() || tfSujet.getText().isEmpty() || ComboMail.getSelectionModel().getSelectedItem() == null) {
            tray = new TrayNotification("Ajout d'un centre", "Le champs sont érronés", NotificationType.WARNING);
            tray.showAndDismiss(Duration.seconds(10));

        } else {

            try {
                String host = "smtp.gmail.com";
                String user = "mehdi.dagdagui@esprit.tn";
                String pass = "Mehdi@2009";
                Statement st = DataSource.getInstance().getCnx().createStatement();
                String req2 = "SELECT * from membre";

                ResultSet rs2 = st.executeQuery(req2);

                while (rs2.next()) {
                    String to = rs2.getString("email");
                    String from = "mehdi.dagdagui@esprit.tn";
                    String subject = this.tfSujet.getText();
                    String messageText = this.textMail.getText();
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
                    tray = new TrayNotification("Envoie du mail", "Mail envoyé avec succès", NotificationType.WARNING);
                    tray.showAndDismiss(Duration.seconds(10));

                } //resultset tsaker
            } catch (Exception ex) {
                System.out.println(ex.getMessage());

            }
        }

    }

}
