/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.firas;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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

    ObservableList<String> listemois = FXCollections.observableArrayList("1-Janvier", "2-Février", "3-Mars", "4-Avril", "5-Mai", "6-Juin", "7-Juillet", "8-Aout", "9-Septembre", "10-Octobre", "11-Novombre", "12-Décembre");
    ObservableList<String> listannee = FXCollections.observableArrayList("2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040");
    @FXML
    private Label dateaffiche;
    @FXML
    private Button annulerbtn;

    /**
     * Initializes the controller class.
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
        dateaffiche.setText(Date.valueOf(LocalDate.now()).toString()+" / "+LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute()).toString());
    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    @FXML 
    private void goacheterabonnement(ActionEvent event){
        try {
            Parent rootRec2 = FXMLLoader.load(getClass().getResource("AcheterAbonnement.fxml"));
            Scene rec2 = new Scene(rootRec2);
            Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app.setScene(rec2);
            app.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
