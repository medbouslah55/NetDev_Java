/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.hssan.menu;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Menu;
import services.ServiceMenu;
import utils.Document_Creation_Menu;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class AfficherMenuController implements Initializable {

    @FXML
    private TableView<Menu> tableview;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> desc;
    @FXML
    private TableColumn<?, ?> num_jour;
    @FXML
    private TableColumn<?, ?> matin;
    @FXML
    private TableColumn<?, ?> image_mat;
    @FXML
    private TableColumn<?, ?> dej;
    @FXML
    private TableColumn<?, ?> image_dej;
    @FXML
    private TableColumn<?, ?> dinner;
    @FXML
    private TableColumn<?, ?> image_dinner;
    @FXML
    private TableColumn<?, ?> total_cal;
    @FXML
    private TableColumn<?, ?> regime;
    @FXML
    private Button btnSupp;
    @FXML
    private Button btnModif;
    @FXML
    private TextField filterField;

    ObservableList<Menu> list = FXCollections.observableArrayList();
    @FXML
    private Button pdf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
        recherche();

        pdf.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Document_Creation_Menu dc = new Document_Creation_Menu();
                    dc.generatePDF();
                    File file = new File("my_doc_menu.pdf");
                    if (file.exists()) {
                        long startTime = System.currentTimeMillis();
                        Desktop.getDesktop().open(file);
                        long endTime = System.currentTimeMillis();
                        System.out.println("Total time taken to open file -> " + file.getName() + " in " + (endTime - startTime) + " ms");
                    } else {
                        System.out.println("File exits -> " + file.getAbsolutePath());
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SQLException ex) {
                    System.out.println("erreur pdf");
                }

            }
        });

    }

    public void afficher() {
        ServiceMenu sp = new ServiceMenu();
        list = sp.afficher();

        id.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        regime.setCellValueFactory(
                new PropertyValueFactory<>("id_regime"));

        desc.setCellValueFactory(
                new PropertyValueFactory<>("descirption"));

        num_jour.setCellValueFactory(
                new PropertyValueFactory<>("num_jour"));

        matin.setCellValueFactory(
                new PropertyValueFactory<>("matin"));

        image_mat.setPrefWidth(80);
        image_mat.setCellValueFactory(
                new PropertyValueFactory<>("img_matin"));

        dej.setCellValueFactory(
                new PropertyValueFactory<>("dejeuner"));

        image_dej.setPrefWidth(80);
        image_dej.setCellValueFactory(
                new PropertyValueFactory<>("img_dejeuner"));

        dinner.setCellValueFactory(
                new PropertyValueFactory<>("dinner"));

        image_dinner.setPrefWidth(80);
        image_dinner.setCellValueFactory(
                new PropertyValueFactory<>("img_dinner"));

        total_cal.setCellValueFactory(
                new PropertyValueFactory<>("total_calories"));

        tableview.setItems(list);
//        System.out.println(list);
    }

    @FXML
    private void supprimer(ActionEvent event) {
        if (tableview.getSelectionModel().getSelectedItem() != null) {
            Menu c = tableview.getSelectionModel().getSelectedItem();
            tableview.getItems().removeAll(tableview.getSelectionModel().getSelectedItem());
            ServiceMenu cs = new ServiceMenu();
            cs.supprimer(c);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Selection Error");
            alert.setHeaderText("Selectionner une Ligne à supprimer !");
            alert.setContentText("Veuillez Sélectionner une ligne a supprimer pour que ça marche");
            alert.showAndWait();
        }
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        if (tableview.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/gui/hssan/menu/ModifierMenu.fxml"));
            Parent tableViewParent = loader.load();

            Scene tableViewScene = new Scene(tableViewParent);

            //access the controller and call a method
            ModifierMenuController controller = loader.getController();
            controller.initData(tableview.getSelectionModel().getSelectedItem());

            //This line gets the Stage information
            Stage window = new Stage();
            window.setTitle("Modifier");
            window.setScene(tableViewScene);
            window.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Selection Error");
            alert.setHeaderText("Selectionner une Ligne à modifier !");
            alert.setContentText("Veuillez Sélectionner une ligne a modifier pour que ça marche");
            alert.showAndWait();
        }
    }

    private void recherche() {
        ServiceMenu as = new ServiceMenu();

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!filterField.getText().isEmpty()) {
                List k = as.ChercherListActParCategorie(filterField.getText());
                ObservableList<Menu> l = FXCollections.observableArrayList(k);
                tableview.setItems(l);
            } else {
                this.afficher();
            }
        });
    }
}
