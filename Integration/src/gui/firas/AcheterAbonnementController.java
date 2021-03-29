/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.firas;

import models.Abonnement;
import services.AbonnementCRUD;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class AcheterAbonnementController implements Initializable {

    @FXML
    private FontAwesomeIconView btnactualiser;
    @FXML
    private TableView<Abonnement> coltabab;
    @FXML
    private TableColumn<Abonnement, String> colti;
    @FXML
    private TableColumn<Abonnement, String> colty;
    @FXML
    private TableColumn<Abonnement, String> colp;
    @FXML
    private TableColumn<Abonnement, String> cold;
    @FXML
    private TableColumn<Abonnement, String> colediter;
    @FXML
    private MaterialDesignIconView closeButton;
    @FXML
    private Button btnback;
    @FXML
    private ComboBox<?> combotrie;
    ObservableList<Abonnement> list = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDate22();
    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void backtomenu(ActionEvent event) throws IOException {
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("/gui/mohammed/MenuFront.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }

    @FXML
    private void trierab(ActionEvent event) {
    }

    private void loadDate22() {
        try {
            colti.setCellValueFactory(new PropertyValueFactory<>("Titre_ab"));
            colty.setCellValueFactory(new PropertyValueFactory<>("Type_ab"));
            colp.setCellValueFactory(new PropertyValueFactory<>("prix_ab"));
            cold.setCellValueFactory(new PropertyValueFactory<>("desc_ab"));
            AbonnementCRUD rt = new AbonnementCRUD();
            List old = rt.listAbonnement();
            list.addAll(old);
            //add cell of button edit 
            Callback<TableColumn<Abonnement, String>, TableCell<Abonnement, String>> cellFoctory = (TableColumn<Abonnement, String> param) -> {
                // make cell containing buttons
                final TableCell<Abonnement, String> cell = new TableCell<Abonnement, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        //that cell created only on non-empty rows
                        if (empty) {
                            setGraphic(null);
                            setText(null);

                        } else {

                            MaterialDesignIconView Acheter = new MaterialDesignIconView(MaterialDesignIcon.CART);

                            Acheter.setStyle(
                                    " -fx-cursor: hand ;"
                                    + "-glyph-size:28px;"
                                    + "-fx-fill:#00E676;"
                            );
                            Acheter.setOnMouseClicked((MouseEvent event) -> {
                                try {
                                    Parent rootRec2 = FXMLLoader.load(getClass().getResource("Payement.fxml"));
                                    Scene rec2 = new Scene(rootRec2);
                                    Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                    app.setScene(rec2);
                                    app.show();
                                } catch (IOException e) {
                                    System.out.println(e.getMessage());
                                }
                            });
                            HBox managebtn = new HBox(Acheter);
                            managebtn.setStyle("-fx-alignment:center");
                            //HBox.setMargin(f1, new Insets(2, 2, 0, 3));
                            HBox.setMargin(Acheter, new Insets(2, 3, 0, 2));
                            setGraphic(managebtn);
                            setText(null);
                        }
                    }
                };
                return cell;
            };
            colediter.setCellFactory(cellFoctory);
            coltabab.setItems(list);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
