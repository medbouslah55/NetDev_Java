/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.hamza;

import com.jfoenix.controls.JFXDatePicker;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import static gui.hamza.PaiementController.showAlert;
import models.Reservation;
import models.User;
import services.ReservationCRUD;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.UserSession;

/**
 * FXML Controller class
 *
 * @author HAMZA
 */
public class AjouterreservationuserController implements Initializable {
    // private TextField tfcin;

    @FXML
    private JFXDatePicker tfdate;
    @FXML
    private TextField tfnbrplace;
    @FXML
    private Button btnajout;
    @FXML
    private MaterialDesignIconView btnback;
    private Stage primarystage;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        tfnom.setText(member.getNom());
//        tfprenom.setText(member.getPrenom());
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Stage window = primarystage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("interfacereservationuser.fxml"));;
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }

    @FXML

    private void ajouterreservation(ActionEvent event) throws IOException {
        try {
            switch (controleDeSaisi()) {
                case 0:
                    showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Remplir les champs vides! ");
                    break;
                case 1:
                    showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Verifiez le nom  !");
                    break;
                case 2:
                    showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez le prenom! ");
                    break;
                //case 3 : showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez le cin! ");break;
                case 4:
                    showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez le nombre de place! ");
                    break;
                default:
                    ReservationCRUD r = new ReservationCRUD();
                    //int j=Integer.parseInt(tfcin.getText());
                    int k = Integer.parseInt(tfnbrplace.getText());

                    r.addreservationn(new Reservation(tfnom.getText(), tfprenom.getText(), Date.valueOf(tfdate.getValue()), k));
                    notifsuccess("Reservation ajouter avec succes");
                    //JOptionPane.showMessageDialog(null,"Reservation ajoute");
                    //Stage window = primarystage;
//                    Parent rootRec2 = FXMLLoader.load(getClass().getResource("interfacereservationuser.fxml"));
//                    ;
//                    Scene rec2 = new Scene(rootRec2);
//                    Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                    app.setScene(rec2);
//                    app.show();
                    sendMail();
            }
        } catch (Exception e) {
            notiferror("Veuiller verifier les champs");
            System.out.println(e.getMessage());
        }
    }

    void sendMail() {
        String to = "firasgacha8@gmail.com";
        String from = "minds8737@gmail.com";
        String host = "smtp.gmail.com";
        final String username = "minds8737@gmail.com";
        final String password = "mindspace123456";
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
            m.setSubject("Reservation Ajouter");
            String nom = tfnom.getText();
            String prenom = tfprenom.getText();
            //String cin = tfcin.getText();
            Date d = Date.valueOf(tfdate.getValue());
            int nbrplaces = Integer.parseInt(tfnbrplace.getText());
            m.setText("Votre Reservation est ajoutee avec succes "
                    + "\n Nom = " + nom
                    + "\n Prenom = " + prenom
                    //+ "\n Cin = "+cin
                    + "\n Date = " + d
                    + "\n Nombre Places = " + nbrplaces
            );
            //send mail
            Transport.send(m);
            //sentBoolValue.setVisible(true);
            System.out.println("Message sent!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void notifsuccess(String message) {
        String title = "Congratulations";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(javafx.util.Duration.seconds(3));
    }

    private void notiferror(String message) {
        String title = "Failed";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.ERROR);
        tray.showAndDismiss(javafx.util.Duration.seconds(3));
    }
//        private int controlesaisie() {
//        String masque = "^[0-9]+$";
//        Pattern pattern = Pattern.compile(masque);
//        Matcher controler = pattern.matcher(tfcin.getText());
//        Matcher controlerr = pattern.matcher(tfnbrplace.getText());
//        if (tfcin.getText().isEmpty()) {
//            return 1;
//        
//        } else {
//            return 0;
//        }
//    }

    private int controleDeSaisi() {

        if (tfnom.getText().isEmpty() || tfprenom.getText().isEmpty()
                || tfnbrplace.getText().isEmpty()) {

            return 0;
        } else if (!Pattern.matches("[a-zA-Z]*", tfnom.getText())) {

            tfnom.requestFocus();
            tfnom.selectEnd();
            return 1;
        } else if (!Pattern.matches("[a-zA-Z]*", tfprenom.getText())) {
            showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le prenom! ");
            tfprenom.requestFocus();
            tfprenom.selectEnd();
            return 2;
//            } else if (!Pattern.matches("[0-9]*", tfcin.getText())) {
//                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez la cin! ");
//                tfcin.requestFocus();
//                tfcin.selectEnd();
//                return 3;
        } else if (!Pattern.matches("[0-9]*", tfnbrplace.getText())) {
            showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le nombre de places ! ");
            tfnbrplace.requestFocus();
            tfnbrplace.selectEnd();
            return 4;
        } else {
            return 5;
        }

    }

    @FXML
    private void ajouterreservation(MouseEvent event) {
    }

}
