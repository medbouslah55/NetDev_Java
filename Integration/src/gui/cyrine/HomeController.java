/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.cyrine;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class HomeController implements Initializable {

    @FXML
    private Button BtnAjouterCentre;
    @FXML
    private Button BtnAfficherCentre;
    @FXML
    private Button BtnAjouterAct;
    @FXML
    private Button BtnAfficherAct;
    @FXML
    private AnchorPane AffichagePane;
    @FXML
    private ImageView logo;
    @FXML
    private Label nomGestion;
    @FXML
    private Button BtnAfficherStat;
    @FXML
    private Button BtnEnvoieMail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.setNomGestion("");
    }

    public void setNomGestion(String ch) {
        this.nomGestion.setText(ch);
    }

    @FXML
    private void OpenAjouterCentre(ActionEvent event) {
        try {
            this.BtnAjouterCentre.setStyle("-fx-background-color: #fabe58");
            this.BtnAfficherAct.setStyle("-fx-background-color:  transparent");
            this.BtnAfficherCentre.setStyle("-fx-background-color:  transparent");
            this.BtnAjouterAct.setStyle("-fx-background-color:  transparent");
            this.BtnAfficherStat.setStyle("-fx-background-color: transparent");
            this.BtnEnvoieMail.setStyle("-fx-background-color: transparent") ;
            
            this.setNomGestion("Gestion des centres");
            AnchorPane pane = FXMLLoader.load(getClass().getResource("AjouterCentre.fxml"));
            AffichagePane.getChildren().setAll(pane);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void OpenAfficherCentres(ActionEvent event) {
        try {
            this.BtnAjouterCentre.setStyle("-fx-background-color: transparent");
            this.BtnAfficherAct.setStyle("-fx-background-color:  transparent");
            this.BtnAfficherCentre.setStyle("-fx-background-color:  #fabe58");
            this.BtnAjouterAct.setStyle("-fx-background-color:  transparent");
            this.BtnAfficherStat.setStyle("-fx-background-color: transparent");
            this.BtnEnvoieMail.setStyle("-fx-background-color: transparent") ;
            
            this.setNomGestion("Gestion des centres");
            AnchorPane pane = FXMLLoader.load(getClass().getResource("AfficherCentres.fxml"));
            AffichagePane.getChildren().setAll(pane);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void OpenAjouterAct(ActionEvent event) {

        try {
            this.BtnAjouterCentre.setStyle("-fx-background-color: transparent");
            this.BtnAfficherAct.setStyle("-fx-background-color:  transparent");
            this.BtnAfficherCentre.setStyle("-fx-background-color: transparent");
            this.BtnAjouterAct.setStyle("-fx-background-color:  #fabe58");
            this.BtnAfficherStat.setStyle("-fx-background-color: transparent");
            this.BtnEnvoieMail.setStyle("-fx-background-color: transparent") ;
            
            this.setNomGestion("Gestion des activités");
            AnchorPane pane = FXMLLoader.load(getClass().getResource("AjouterAct.fxml"));
            AffichagePane.getChildren().setAll(pane);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void OpenAfficherAct(ActionEvent event) {
        try {
            this.BtnAjouterCentre.setStyle("-fx-background-color: transparent");
            this.BtnAfficherAct.setStyle("-fx-background-color:  #fabe58");
            this.BtnAfficherCentre.setStyle("-fx-background-color: transparent");
            this.BtnAjouterAct.setStyle("-fx-background-color:  transparent");
            this.BtnAfficherStat.setStyle("-fx-background-color: transparent");
            this.BtnEnvoieMail.setStyle("-fx-background-color: transparent") ;
            
            this.setNomGestion("Gestion des activités");
            AnchorPane pane = FXMLLoader.load(getClass().getResource("AfficherAct.fxml"));
            AffichagePane.getChildren().setAll(pane);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void OpenAfficherStat(ActionEvent event) {
        try {
            this.BtnAjouterCentre.setStyle("-fx-background-color: transparent");
            this.BtnAfficherAct.setStyle("-fx-background-color:  transparent");
            this.BtnAfficherCentre.setStyle("-fx-background-color: transparent");
            this.BtnAjouterAct.setStyle("-fx-background-color:  transparent");
            this.BtnAfficherStat.setStyle("-fx-background-color: #fabe58");
            this.BtnEnvoieMail.setStyle("-fx-background-color: transparent") ;
            
            this.setNomGestion("Gestion des activités");
            AnchorPane pane = FXMLLoader.load(getClass().getResource("StatActCategorie.fxml"));
            AffichagePane.getChildren().setAll(pane);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void OpenEnvoieMail(ActionEvent event) {
        try {
            this.BtnAjouterCentre.setStyle("-fx-background-color: transparent");
            this.BtnAfficherAct.setStyle("-fx-background-color:  transparent");
            this.BtnAfficherCentre.setStyle("-fx-background-color: transparent");
            this.BtnAjouterAct.setStyle("-fx-background-color:  transparent");
            this.BtnAfficherStat.setStyle("-fx-background-color :transparent");
            this.BtnEnvoieMail.setStyle("-fx-background-color: #fabe58");
            
            this.setNomGestion("Gestion des activités");
            AnchorPane pane = FXMLLoader.load(getClass().getResource("MailingCentreMembre.fxml"));
            AffichagePane.getChildren().setAll(pane);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/mohammed/MenuBackend.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
