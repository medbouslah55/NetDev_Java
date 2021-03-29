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
/**
 * FXML Controller class
 *
 * @author Firas
 */
public class MenuAbonnementAdminController implements Initializable {

    @FXML
    private Button btnplus;
    @FXML
    private Button btnliste;
    private Stage primaryStage;
    @FXML
    private MaterialDesignIconView closeButton;

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
    public void goajouter(ActionEvent event) throws IOException {
        Stage window = primaryStage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("ajouterAbonnementAdmin.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }

    @FXML
    public void golist(ActionEvent event) throws IOException {
        try {
            Stage window = primaryStage;
            Parent rootRec2 = FXMLLoader.load(getClass().getResource("listeAbonnement.fxml"));
            Scene rec2 = new Scene(rootRec2);
            Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app.setScene(rec2);
            app.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void close(MouseEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    
}
