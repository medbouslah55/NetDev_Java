/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.hssan.regime;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import models.Regime;
import services.ServiceRegime;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class AfficherRegimeController implements Initializable {

    @FXML
    private TableView<Regime> tableview;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> Desc;
    @FXML
    private TableColumn<?, ?> img;
    @FXML
    private TableColumn<?, ?> type;
    @FXML
    private Button btnSupp;
    @FXML
    private Button btnModif;

    ObservableList<Regime> list = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
    }

    public void afficher() {
        ServiceRegime sp = new ServiceRegime();
        list = sp.afficher();

        id.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        type.setCellValueFactory(
                new PropertyValueFactory<>("type"));

        Desc.setCellValueFactory(
                new PropertyValueFactory<>("description"));

        img.setCellValueFactory(
                new PropertyValueFactory<>("img"));

        tableview.setItems(list);
    }

    @FXML
    private void supprimer(ActionEvent event) {
        if (tableview.getSelectionModel().getSelectedItem() != null) {
            Regime c = tableview.getSelectionModel().getSelectedItem();
            tableview.getItems().removeAll(tableview.getSelectionModel().getSelectedItem());
            ServiceRegime cs = new ServiceRegime();
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
            loader.setLocation(getClass().getResource("/gui/hssan/regime/ModifierRegime.fxml"));
            Parent tableViewParent = loader.load();

            Scene tableViewScene = new Scene(tableViewParent);

            //access the controller and call a method
            ModifierRegimeController controller = loader.getController();
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
    private void TrieParType(ActionEvent event) {
        ServiceRegime cs = new ServiceRegime();
        ObservableList<Regime> list = cs.TrieParNom();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        type.setCellValueFactory(new PropertyValueFactory<>("type"));

        Desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        img.setCellValueFactory(new PropertyValueFactory<>("img"));

        tableview.setItems(list);
        TrayNotification tray = new TrayNotification("Tri", "Trie avec succces !", NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(5));
    }

    @FXML
    private void PrintTable(ActionEvent event) {
        System.out.println("To Printer!");
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            Window primaryStage = null;
            job.showPrintDialog(primaryStage);

            Node root = this.tableview;
            job.printPage(root);
            job.endJob();
        }
    }
}
