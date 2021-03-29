/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.firas;

import models.Reclamation;
import services.ReclamationCRUD;
import com.jfoenix.controls.JFXDatePicker;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import static tests.MainProg.primaryStage;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class ReclamationAdminController implements Initializable {

    @FXML
    private TableView<Reclamation> recTable;
    @FXML
    private TableColumn<Reclamation, String> colnom;
    @FXML
    private TableColumn<Reclamation, String> colprenom;
    @FXML
    private TableColumn<Reclamation, String> colmail;
    @FXML
    private TableColumn<Reclamation, Date> coldate;
    @FXML
    private TableColumn<Reclamation, String> coldesc;
    @FXML
    private TableColumn<Reclamation, String> coltype;
    @FXML
    private TableColumn<Reclamation, String> coletat;
    @FXML
    private Button btnetat;
    @FXML
    private Button btnactualiser;
    @FXML
    private Button btnimprimer;
    @FXML
    private JFXDatePicker tfdeb;
    @FXML
    private JFXDatePicker tffin;
    @FXML
    private FontAwesomeIconView btnrech;
    @FXML
    private ComboBox<String> typeRecherche;
    @FXML
    private TextField RechercheTextField;

    ObservableList<String> listeTypeRecherche = FXCollections.observableArrayList("Tout", "Nom", "Prenom", "Email", "Type", "Status");

    ObservableList<String> listeTrie = FXCollections.observableArrayList("Tout", "Nom", "Prenom", "Status", "Date");
    @FXML
    private MaterialDesignIconView closeButton;
    @FXML
    private Button btnback;
    @FXML
    private ComboBox<String> combotrie;
    
    
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        typeRecherche.setItems(listeTypeRecherche);
        typeRecherche.setValue("Tout");
        combotrie.setItems(listeTrie);
        combotrie.setValue("Tout");
        loadDate();
        list();
    }

    @FXML
    private void printRec(ActionEvent event) {
        System.out.println("To Printer!");
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            Window primaryStage = null;
            job.showPrintDialog(primaryStage);

            Node root = this.recTable;
            job.printPage(root);
            job.endJob();
        }
    }

    @FXML
    private void loadDate() {
        ObservableList<Reclamation> recList = FXCollections.observableArrayList();
        colnom.setCellValueFactory(new PropertyValueFactory<>("Nom_rec"));
        colprenom.setCellValueFactory(new PropertyValueFactory<>("Prenom_rec"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("Date_rec"));
        coletat.setCellValueFactory(new PropertyValueFactory<>("Etat_rec"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("Description_rec"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("Type_rec"));
        colmail.setCellValueFactory(new PropertyValueFactory<>("Email_rec"));
        ReclamationCRUD rt = new ReclamationCRUD();
        List old = rt.listeReclamation();
        recList.addAll(old);
        recTable.setItems(recList);
        recTable.refresh();
    }

    public void trierDate(ActionEvent event) {
        ObservableList<Reclamation> recList = FXCollections.observableArrayList();
        colnom.setCellValueFactory(new PropertyValueFactory<>("Nom_rec"));
        colprenom.setCellValueFactory(new PropertyValueFactory<>("Prenom_rec"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("Date_rec"));
        coletat.setCellValueFactory(new PropertyValueFactory<>("Etat_rec"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("Description_rec"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("Type_rec"));
        ReclamationCRUD rt = new ReclamationCRUD();
        List old = rt.trierreclamationDate();
        recList.addAll(old);
        recTable.setItems(recList);
        recTable.refresh();
    }

    @FXML
    public void channgeEtat() {
        Reclamation test = (Reclamation) recTable.getSelectionModel().getSelectedItem();
        ReclamationCRUD rt = new ReclamationCRUD();
        rt.changerEtatReclamation(test.getId_rec());
        loadDate();
    }

    @FXML
    public void dateXY(ActionEvent e) {
        ObservableList<Reclamation> recList = FXCollections.observableArrayList();
        colnom.setCellValueFactory(new PropertyValueFactory<>("Nom_rec"));
        colprenom.setCellValueFactory(new PropertyValueFactory<>("Prenom_rec"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("Date_rec"));
        coletat.setCellValueFactory(new PropertyValueFactory<>("Etat_rec"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("Description_rec"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("Type_rec"));
        ReclamationCRUD rt = new ReclamationCRUD();
        List old = rt.chercherReclamationUserPeriode(Date.valueOf(tfdeb.getValue()), Date.valueOf(tffin.getValue()));
        recList.addAll(old);
        recTable.setItems(recList);
        recTable.refresh();
        tfdeb.getEditor().clear();
        tffin.getEditor().clear();
        notifSUCCESS("Les Réclamations de "+tfdeb.getValue()+" à "+tffin.getValue());
    }

    @FXML
    private void list() {
        ReclamationCRUD rt = new ReclamationCRUD();
        List arr = rt.chercherReclamationNom(typeRecherche.getValue(), RechercheTextField.getText());
        ObservableList obb = FXCollections.observableArrayList(arr);
        recTable.setItems(obb);
    }
    
    @FXML
    private void trierall() {
        ReclamationCRUD rt = new ReclamationCRUD();
        List arr = rt.trierReclamationall(combotrie.getValue());
        ObservableList obb = FXCollections.observableArrayList(arr);
        recTable.setItems(obb);
        notifSUCCESS("Les Réclamations sont triées selon "+combotrie.getValue());
    }

    @FXML
    private void close(MouseEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    private void notifSUCCESS(String message) {
        String title = "Congratulations";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(3));
    }

    private void notifERROR(String message) {
        String title = "Erreur";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.ERROR);
        tray.showAndDismiss(Duration.seconds(3));
    }

    @FXML
    private void gomenu(ActionEvent event) throws IOException {
       Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/firas/MenuGeneralFiras.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
