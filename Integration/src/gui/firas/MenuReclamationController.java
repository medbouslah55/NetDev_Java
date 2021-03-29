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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class MenuReclamationController implements Initializable {

    @FXML
    private Button idrec;
    @FXML
    private Button conrec;
    private Stage primaryStage;
    @FXML
    private MaterialDesignIconView closeButton;
    @FXML
    private Button btnback;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void allerVersReclamation(ActionEvent event) throws IOException {
        Stage window = primaryStage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("Reclamer.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }
    @FXML
    private void close() {
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();

    }

    @FXML
    private void listeReclamation(ActionEvent event) throws IOException {
        Stage window = primaryStage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("Consulter-Mes-Réclamations.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }

    @FXML
    private void gomenu(ActionEvent event) throws IOException {
        Stage window = primaryStage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("/gui/mohammed/MenuFront.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }
    

}
