/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.hssan.menu;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class MenuController implements Initializable {

    @FXML
    private Label GestionNom;
    @FXML
    private AnchorPane MainPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setLabelGestionNom(String nom){
        this.GestionNom.setText(nom);
    }

    @FXML
    private void afficher_menu(MouseEvent event) throws IOException {
        setLabelGestionNom("Gestion des Menus");
        Parent fxml = FXMLLoader.load(getClass().getResource("/gui/hssan/menu/AfficherMenu.fxml"));
        MainPane.getChildren().removeAll();
        MainPane.getChildren().addAll(fxml);
    }

    @FXML
    private void ajouter_menu(MouseEvent event) throws IOException {
        setLabelGestionNom("Gestion des Menus");
        Parent fxml = FXMLLoader.load(getClass().getResource("/gui/hssan/menu/AjouterMenu.fxml"));
        MainPane.getChildren().removeAll();
        MainPane.getChildren().addAll(fxml);
    }

    @FXML
    private void statistique_menu(MouseEvent event) throws IOException {
        setLabelGestionNom("Statistique calorique");
        Parent fxml = FXMLLoader.load(getClass().getResource("/gui/hssan/menu/StatistiqueMenu.fxml"));
        MainPane.getChildren().removeAll();
        MainPane.getChildren().addAll(fxml);
    }

    @FXML
    private void ajouter_regime(MouseEvent event) throws IOException {
        setLabelGestionNom("Gestion des Régimes");
        Parent fxml = FXMLLoader.load(getClass().getResource("/gui/hssan/regime/AjouterRegime.fxml"));
        MainPane.getChildren().removeAll();
        MainPane.getChildren().addAll(fxml);
    }

    @FXML
    private void afficher_regime(MouseEvent event) throws IOException {
        setLabelGestionNom("Gestion des Régimes");
        Parent fxml = FXMLLoader.load(getClass().getResource("/gui/hssan/regime/AfficherRegime.fxml"));
        MainPane.getChildren().removeAll();
        MainPane.getChildren().addAll(fxml);
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
