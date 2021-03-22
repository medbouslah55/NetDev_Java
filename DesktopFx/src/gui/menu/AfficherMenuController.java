/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menu;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import utils.Document_Creation;

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
                new PropertyValueFactory<>("matin_img"));

        dej.setCellValueFactory(
                new PropertyValueFactory<>("dejeuner"));

        image_dej.setPrefWidth(80);
        image_dej.setCellValueFactory(
                new PropertyValueFactory<>("dejeuner_img"));

        dinner.setCellValueFactory(
                new PropertyValueFactory<>("dinner"));

        image_dinner.setPrefWidth(80);
        image_dinner.setCellValueFactory(
                new PropertyValueFactory<>("dinner_img"));

        total_cal.setCellValueFactory(
                new PropertyValueFactory<>("total_calories"));

        tableview.setItems(list);
//        System.out.println(list);

        pdf.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Document_Creation dc = new Document_Creation();
                    dc.generatePDF();
                    File file = new File("my_docs.pdf");
                    if (file.exists()) {
                        long startTime = System.currentTimeMillis();
                        Desktop.getDesktop().open(file);
                        long endTime = System.currentTimeMillis();
                        System.out.println("Total time taken to open file -> " + file.getName() + " in " + (endTime - startTime) + " ms");
                    } else {
                        System.out.println("File not exits -> " + file.getAbsolutePath());
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
            loader.setLocation(getClass().getResource("/gui/menu/ModifierMenu.fxml"));
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

    @FXML
    private void recherche() {
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Menu> filteredData = new FilteredList<>(list, b -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(cat_diet -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (cat_diet.getDescirption().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (String.valueOf(cat_diet.getTotal_calories()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Menu> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableview.setItems(sortedData);
    }
}
