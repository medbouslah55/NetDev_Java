/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.hamza;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author HAMZA
 */
public class PaiementController implements Initializable {

    @FXML
    private Button valider;
    @FXML
    private Button Annuler;
    @FXML
    private TextField carte;
    @FXML
    private TextField year;
    @FXML
    private TextField month;
    @FXML
    private TextField prix;
    @FXML
    private TextField cvc;
    private Stage primarystage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        valider.setOnAction((ActionEvent event) -> {

            try {

                if (controleDeSaisi()) {

                    if (carte.getText().isEmpty()) {
                        carte.setText("");
                    }
                    if (month.getText().isEmpty()) {
                        month.setText("");
                    }
                    if (year.getText().isEmpty()) {
                        year.setText("");
                    }
                    if (cvc.getText().isEmpty()) {
                        cvc.setText("");
                    }

                }

                Stripe.apiKey = "sk_test_flb9vUYyiwC85Wx2ONpANeYf";

                Customer a = Customer.retrieve("cus_EabQCFUrV5J5qW");
                Map<String, Object> cardParams = new HashMap<String, Object>();
                cardParams.put("number", Long.parseLong(carte.getText()));
                cardParams.put("exp_month", Integer.parseInt(month.getText()));
                cardParams.put("exp_year", Integer.parseInt(year.getText()));
                cardParams.put("cvc", Integer.parseInt(cvc.getText()));

                Map<String, Object> tokenParams = new HashMap<String, Object>();
                tokenParams.put("card", cardParams);

                Token token = Token.create(tokenParams);

                Map<String, Object> source = new HashMap<String, Object>();
                source.put("source", token.getId());

                // a.getSources().create(source);
                Map<String, Object> chargeParams = new HashMap<String, Object>();
                chargeParams.put("amount", Integer.parseInt(prix.getText()));
                chargeParams.put("currency", "usd");
                chargeParams.put("customer", a.getId());

                Charge.create(chargeParams);

            } catch (StripeException ex) {
                Logger.getLogger(PaiementController.class.getName()).log(Level.SEVERE, null, ex);
            }

            notifsuccess("Felicitations votre paiement est effectuee ");

        });

        Annuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
                s.close();

            }

        });

    }

    private boolean controleDeSaisi() {

        if (carte.getText().isEmpty() || month.getText().isEmpty() || year.getText().isEmpty()
                || cvc.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

            if (!Pattern.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]", carte.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez la reference ! ");
                carte.requestFocus();
                carte.selectEnd();
                return false;
            }

            if (!Pattern.matches("[0-9]*", month.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le Mois ! ");
                month.requestFocus();
                month.selectEnd();
                return false;
            }
            if (!Pattern.matches("[0-9]*", year.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez l'année ! ");
                year.requestFocus();
                year.selectEnd();
                return false;
            }
            if (!Pattern.matches("[0-9]*", cvc.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le cvc ! ");
                cvc.requestFocus();
                cvc.selectEnd();
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

    private void notifsuccess(String message) {
        String title = "Congratulations";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(javafx.util.Duration.seconds(3));
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Stage window = primarystage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("interfacepanier.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }
}
