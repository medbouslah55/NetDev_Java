package gui.mohammed;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.teknikindustries.bulksms.SMS;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import org.apache.commons.codec.digest.DigestUtils;
import services.MembreServices;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.DataSource;
import utils.UserSession;

/**
 * FXML Controller class
 *
 * @author mohamedbouslah
 */
public class RecuperePwdMembreController implements Initializable {

    @FXML
    private TextField tf_cin_recu_pwd;
    @FXML
    private TextField tf_nv_pwd;
    @FXML
    private Button bt_verif_cin_pwd;
    @FXML
    private TextField tf_code_conf;
    @FXML
    private Button bt_conf_new_pass;
    @FXML
    private Button bt_retour_Login;

    Connection cnx = DataSource.getInstance().getCnx();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    MembreServices ms = new MembreServices();

    public static int generateRandomIntIntRange() {
        Random r = new Random();
        return r.nextInt((1000 - 50) + 1) + 50;
    }
    int x = generateRandomIntIntRange();

    @FXML
    private void retour_Login(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/mohammed/Login.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void verifier_cin(ActionEvent event) throws SQLException {
        if (verifchampsCin() == true) {
            String cin = tf_cin_recu_pwd.getText();
            int cinn = Integer.parseInt(cin);
            if (ms.RecupPwd(cinn) == 1) {
                UserSession.setInstance(cinn);

                //pour afficher le code generateur dans notif
                String str = Integer.toString(x);

//                //pour SMS 
//                SMS sms = new SMS();
//                String num_tel = "+216" + UserSession.getInstance().getTelephone();
////                System.out.println(num_tel);
//                sms.SendSMS("mohamedd55", "aRTBEN55991044", str, num_tel, "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
//                System.out.println("test sms");
                try {
                    String host = "smtp.gmail.com";
                    String user = "mehdi.dagdagui@esprit.tn";
                    String pass = "Mehdi@2009";
                    String to = UserSession.getInstance().getEmail();
                    String from = "mehdi.dagdagui@esprit.tn";
                    String subject = "Code de verification ";
                    String messageText = "votre code de verification est : " + str;
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

            } else {
                TrayNotification tray = null;
                tray = new TrayNotification("Error", "Cin introuvable  ", NotificationType.ERROR);
                tray.showAndDismiss(Duration.seconds(5));
            }
        }

    }

    @FXML
    private void conf_pwd(ActionEvent event) throws SQLException {
        if (verifchamps() == true) {
            int code = x;
            String codeF = tf_code_conf.getText();
            int cf = (Integer.parseInt(codeF));
            System.out.println(code);

            if (cf == code) {
                System.out.println("dkhal");
                String nvmdp = DigestUtils.shaHex(tf_nv_pwd.getText());
                String requete = "UPDATE membre SET password='" + nvmdp + "' WHERE cin = '" + tf_cin_recu_pwd.getText() + "'";
                PreparedStatement pst = cnx.prepareStatement(requete);
                pst.executeUpdate();
            }
        }
    }

    ControleSaisie cs = new ControleSaisie();

    private Boolean verifchampsCin() {
        if (tf_cin_recu_pwd.getText().isEmpty()) {
            TrayNotification tray = null;
            tray = new TrayNotification("Error", "verifier votre Cin  ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }
        if (!cs.isInte(tf_cin_recu_pwd.getText())) {
            TrayNotification tray = null;
            tray = new TrayNotification("Error", "verifier votre Cin , Cin est un numero  ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }
        return true;
    }

    private Boolean verifchamps() {
        if (tf_code_conf.getText().isEmpty() || tf_nv_pwd.getText().isEmpty()) {
            TrayNotification tray = null;
            tray = new TrayNotification("Error", "il faut remplire les champs   ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }
        if (!cs.isInte(tf_code_conf.getText())) {
            TrayNotification tray = null;
            tray = new TrayNotification("Error", "verifier votre code , c'est un numero  ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }
        if (!cs.isAlpha(tf_nv_pwd.getText())) {
            TrayNotification tray = null;
            tray = new TrayNotification("Error", "verifier votre password  ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }

        return true;
    }

}
