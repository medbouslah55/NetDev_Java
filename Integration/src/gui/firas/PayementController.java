/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.firas;

import com.jfoenix.controls.JFXTextField;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class PayementController implements Initializable {
    
    @FXML
    private TextField tfclient;
    @FXML
    private TextField tfmail;
    @FXML
    private TextField tfcarte;
    @FXML
    private TextField tfcvv;
    @FXML
    private Button valider;
    @FXML
    private ComboBox<String> combomois;
    @FXML
    private ComboBox<String> comboyear;
    @FXML
    private MaterialDesignIconView closeButton;
    
    ObservableList<String> listemois = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
    ObservableList<String> listannee = FXCollections.observableArrayList("2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040");
    @FXML
    private Label dateaffiche;
    @FXML
    private Button annulerbtn;
    @FXML
    private JFXTextField tfprix;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        combomois.setItems(listemois);
        comboyear.setItems(listannee);
        combomois.setValue("Mois");
        comboyear.setValue("Année");
        dateaffiche.setText(Date.valueOf(LocalDate.now()).toString() + " / " + LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute()).toString());
        
        valider.setOnAction((ActionEvent event) -> {
            
            try {
                
                if (controleDeSaisi()) {
                    
                    if (tfcarte.getText().isEmpty()) {
                        tfcarte.setText("");
                    }
                    if (combomois.getValue().isEmpty()) {
                        combomois.setValue("Mois");
                    }
                    if (comboyear.getValue().isEmpty()) {
                        comboyear.setValue("Année");
                    }
                    if (tfcvv.getText().isEmpty()) {
                        tfcvv.setText("");
                    }                    
                    
                }
                
                Stripe.apiKey = "sk_test_flb9vUYyiwC85Wx2ONpANeYf";
                
                Customer a = Customer.retrieve("cus_EabQCFUrV5J5qW");
                Map<String, Object> cardParams = new HashMap<String, Object>();
                cardParams.put("number", Long.parseLong(tfcarte.getText()));
                cardParams.put("exp_month", Integer.parseInt(combomois.getValue()));
                cardParams.put("exp_year", Integer.parseInt(comboyear.getValue()));
                cardParams.put("cvc", Integer.parseInt(tfcvv.getText()));
                
                Map<String, Object> tokenParams = new HashMap<String, Object>();
                tokenParams.put("card", cardParams);
                
                Token token = Token.create(tokenParams);
                
                Map<String, Object> source = new HashMap<String, Object>();
                source.put("source", token.getId());
                
                a.getSources().create(source);
                
                Map<String, Object> chargeParams = new HashMap<String, Object>();
                chargeParams.put("amount", Integer.parseInt(tfprix.getText()));
                chargeParams.put("currency", "usd");
                chargeParams.put("customer", a.getId());
                
                Charge.create(chargeParams);
                
                notifSUCCESS("Payement Effectuer avec Succées");
                
            } catch (StripeException ex) {
                Logger.getLogger(PayementController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
    }
    
    private boolean controleDeSaisi() {        
        
        if (tfcarte.getText().isEmpty() || combomois.getValue().isEmpty() || comboyear.getValue().isEmpty()
                || tfcvv.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
        } else {
            
            if (!Pattern.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]", tfcarte.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez la reference ! ");
                tfcarte.requestFocus();
                tfcarte.selectEnd();
                return false;
            }
            
            if (!Pattern.matches("[0-9]*", combomois.getValue())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le Mois ! ");
                combomois.requestFocus();
                return false;
            }
            if (!Pattern.matches("[0-9]*", comboyear.getValue())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez l'année ! ");
                comboyear.requestFocus();
                //year.selectEnd();
                return false;
            }
            if (!Pattern.matches("[0-9]*", tfcvv.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le cvc ! ");
                tfcvv.requestFocus();
                tfcvv.selectEnd();
                return false;
            }
            
        }
        return true;
    }
    
    public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();
        
    }
    
    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void goacheterabonnement(ActionEvent event) throws IOException {
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("AcheterAbonnement.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
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
