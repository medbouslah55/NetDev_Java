/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.firas;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static tests.MainProg.primaryStage;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class MenuGeneralFirasController implements Initializable {

    @FXML
    private MaterialDesignIconView closeButton;
    @FXML
    private Button btnback;
    @FXML
    private Button idrec;
    @FXML
    private Button conrec;
    @FXML
    private Button btnplus;
    @FXML
    private Button btnliste;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void close(MouseEvent event) {
    }

    @FXML
    private void backtomenu(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/mohammed/MenuBackend.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void allerVersGestionReclamation(ActionEvent event) throws IOException {
        Stage window = primaryStage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("ReclamationAdmin.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }

    @FXML
    private void allerversStatistique(ActionEvent event) throws IOException {
        Stage window = primaryStage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("StatistqiuesAdmin.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }

    @FXML
    private void goajouter(ActionEvent event) throws IOException {
        Stage window = primaryStage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("ajouterAbonnementAdmin.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }

    @FXML
    private void golist(ActionEvent event) throws IOException {
        Stage window = primaryStage;
            Parent rootRec2 = FXMLLoader.load(getClass().getResource("listeAbonnement.fxml"));
            Scene rec2 = new Scene(rootRec2);
            Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app.setScene(rec2);
            app.show();
    }
    
}
