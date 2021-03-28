/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.maindspace.gui;

import com.teknikindustries.bulksms.SMS;
import edu.maindspace.entities.Admin;
import edu.maindspace.entities.ControleSaisie;
import edu.maindspace.entities.Membre;
import edu.maindspace.services.AdminServices;
import edu.maindspace.services.MembreServices;
import edu.maindspace.tools.SmsSender;
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
import org.apache.commons.codec.digest.DigestUtils;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import edu.maindspace.tools.UserSession;


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
    MembreServices ms =new MembreServices();
    AdminServices as =new AdminServices();
    @FXML
    private void login_membre(ActionEvent event) throws SQLException, IOException {
        if(verifchampsMembre()==true)
        {
        String email = tf_login_email_membre.getText();
        String pwd = DigestUtils.shaHex(tf_login_pass_membre.getText());//crypt
        UserSession.setInstance(email);
        ms.loginMembre(email, pwd);
        if(ms.loginMembre(email,pwd)==1)
        {
            TrayNotification tray = null;
            tray = new TrayNotification("welcom back", "Nice to see you  "+ UserSession.getInstance().getNom(), NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(5));
            Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("EditeProfile.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
        else
        {
            TrayNotification tray = null;
            tray = new TrayNotification("Error", "Email ou password incorrect  ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
        }
        }
        
    }

    @FXML
    private void login_admin(ActionEvent event) throws IOException {
        if(verifchampsAdmin()==true)
        {
        String email = tf_login_email_admin.getText();
        String pwd = tf_login_pass_admin.getText();
        if(as.loginAdmin(email, pwd)==1){
            Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("MenuBackend.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        //for SMS twillo
//        SmsSender s = new SmsSender();
//            s.send(email, pwd);
//        for sms bulkSMS  
//        SMS sms =new SMS();
//        sms.SendSMS("wing_rickyyy", "Azerty123", "test test", "+21654011100", "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
//            System.out.println("test sms");
        }
        else
        {
            TrayNotification tray = null;
        tray = new TrayNotification("Error", "Email ou password incorrect  ", NotificationType.ERROR);
        tray.showAndDismiss(Duration.seconds(5));
        }
        }
        
        
    }

    @FXML
    private void inscri_membre_login(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("InscriptionMembre.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void pwd_oublier(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("RecuperePwdMembre.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    ControleSaisie css =new ControleSaisie();
    private Boolean verifchampsMembre()
    {
        if(tf_login_email_membre.getText().isEmpty() || tf_login_pass_membre.getText().isEmpty())
        {
            TrayNotification tray = null;
            tray = new TrayNotification("Error", "Email ou password incorrect  ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }
        
        if(!css.isValidEmailAddress(tf_login_email_membre.getText()))
        {
            TrayNotification tray = null;
            tray = new TrayNotification("Error", "verifier votre email  ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
            
        }
        
        
        return true;
        
    }
    private Boolean verifchampsAdmin()
    {
        if(tf_login_email_admin.getText().isEmpty() || tf_login_pass_admin.getText().isEmpty())
        {
            TrayNotification tray = null;
            tray = new TrayNotification("Error", "Email ou password incorrect  ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }
        
        if(!css.isValidEmailAddress(tf_login_email_admin.getText()))
        {
            TrayNotification tray = null;
            tray = new TrayNotification("Error", "verifier votre email  ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
            
        }
        
        
        return true;
        
    }
    
}
