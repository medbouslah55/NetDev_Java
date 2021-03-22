/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class MenuController implements Initializable {

    @FXML
    private Button AffichageCat;
    @FXML
    private Button AjouterCat;
    @FXML
    private Button stat;
    @FXML
    private Label GestionNom;
    @FXML
    private AnchorPane MainPane;
    @FXML
    private Button AjouterRegime;
    @FXML
    private Button AfficherRegime;

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
        Parent fxml = FXMLLoader.load(getClass().getResource("/gui/menu/AfficherMenu.fxml"));
        MainPane.getChildren().removeAll();
        MainPane.getChildren().addAll(fxml);
    }

    @FXML
    private void ajouter_menu(MouseEvent event) throws IOException {
        setLabelGestionNom("Gestion des Menus");
        Parent fxml = FXMLLoader.load(getClass().getResource("/gui/menu/AjouterMenu.fxml"));
        MainPane.getChildren().removeAll();
        MainPane.getChildren().addAll(fxml);
    }

    @FXML
    private void statistique_menu(MouseEvent event) throws IOException {
        setLabelGestionNom("Statistique calorique");
        Parent fxml = FXMLLoader.load(getClass().getResource("/gui/menu/StatistiqueMenu.fxml"));
        MainPane.getChildren().removeAll();
        MainPane.getChildren().addAll(fxml);
    }

    @FXML
    private void ajouter_regime(MouseEvent event) throws IOException {
        setLabelGestionNom("Gestion des Régimes");
        Parent fxml = FXMLLoader.load(getClass().getResource("/gui/regime/AjouterRegime.fxml"));
        MainPane.getChildren().removeAll();
        MainPane.getChildren().addAll(fxml);
    }

    @FXML
    private void afficher_regime(MouseEvent event) throws IOException {
        setLabelGestionNom("Gestion des Régimes");
        Parent fxml = FXMLLoader.load(getClass().getResource("/gui/regime/AfficherRegime.fxml"));
        MainPane.getChildren().removeAll();
        MainPane.getChildren().addAll(fxml);
    }
}
