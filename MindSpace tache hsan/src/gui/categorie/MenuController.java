/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.categorie;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
    private Button ModifierCat;
    @FXML
    private Button AjouterCat;
    @FXML
    private ImageView logo;
    @FXML
    private AnchorPane MainPane;
    @FXML
    private Label GestionNom;
    @FXML
    private FontAwesomeIconView plus;
    @FXML
    private FontAwesomeIconView plus1;
    @FXML
    private FontAwesomeIconView plus11;
    @FXML
    private FontAwesomeIconView plus2;
    @FXML
    private FontAwesomeIconView plus12;
    @FXML
    private FontAwesomeIconView plus111;
    @FXML
    private Button AjouterDiet;
    @FXML
    private Button AfficherDiet;
    @FXML
    private Button ModifierDiet;
    @FXML
    private Button stat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setLabelGestionNom("Choisir une gestion");
    }
    
    public void setLabelGestionNom(String nom){
        this.GestionNom.setText(nom);
    }

    @FXML
    private void ajouter_cat(MouseEvent event) throws IOException {
        setLabelGestionNom("Gestion des Catégorie");
        Parent fxml = FXMLLoader.load(getClass().getResource("/gui/categorie/AjouterCategorieDiet.fxml"));
        MainPane.getChildren().removeAll();
        MainPane.getChildren().addAll(fxml);
    }

    @FXML
    private void modifier_cat(MouseEvent event) throws IOException {
        setLabelGestionNom("Gestion des Catégorie");
        Parent fxml = FXMLLoader.load(getClass().getResource("/gui/categorie/ModifierCategorieDiet.fxml"));
        MainPane.getChildren().removeAll();
        MainPane.getChildren().addAll(fxml);
    }

    @FXML
    private void afficher_cat(MouseEvent event) throws IOException {
        setLabelGestionNom("Gestion des Catégorie");
        Parent fxml = FXMLLoader.load(getClass().getResource("/gui/categorie/AfficherCategorie.fxml"));
        MainPane.getChildren().removeAll();
        MainPane.getChildren().addAll(fxml);
    }
    
    @FXML
    private void statistique_categorie(MouseEvent event) throws IOException {
        setLabelGestionNom("Gestion des Catégorie");
        Parent fxml = FXMLLoader.load(getClass().getResource("/gui/categorie/StatistiqueCategorie.fxml"));
        MainPane.getChildren().removeAll();
        MainPane.getChildren().addAll(fxml);
    }

    @FXML
    private void ajouter_diet(MouseEvent event) throws IOException {
        setLabelGestionNom("Gestion des Aliments");
        Parent fxml = FXMLLoader.load(getClass().getResource("/gui/diet/AjouterDiet.fxml"));
        MainPane.getChildren().removeAll();
        MainPane.getChildren().addAll(fxml);
    }

    @FXML
    private void afficher_diet(MouseEvent event) throws IOException {
        setLabelGestionNom("Gestion des Aliments");
        Parent fxml = FXMLLoader.load(getClass().getResource("/gui/diet/AfficherDiet.fxml"));
        MainPane.getChildren().removeAll();
        MainPane.getChildren().addAll(fxml);
    }

    @FXML
    private void modifier_diet(MouseEvent event) throws IOException {
        setLabelGestionNom("Gestion des Aliments");
        Parent fxml = FXMLLoader.load(getClass().getResource("/gui/diet/ModifierDiet.fxml"));
        MainPane.getChildren().removeAll();
        MainPane.getChildren().addAll(fxml);
    }
    
}
