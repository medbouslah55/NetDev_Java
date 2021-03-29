/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Mahdi;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import models.Status;
import services.ServiceStatus;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author mehdi
 */
public class AfficherStatusAdminController implements Initializable {

    @FXML
    private AnchorPane AP_ajouter_coach;
    @FXML
    private Button menu_bt_ajouterdate_pub;
    @FXML
    private Button menu_bt_afficher_evenement;
    @FXML
    private Button bt_modifier_Evenement;
    @FXML
    private Button bt_supp_Evenement;
    @FXML
    private Button bt_trie_Evenement;
    @FXML
    private TableView<?> tvevenement;
    @FXML
    private TableColumn<?, ?> tvDate_Pub;
    @FXML
    private TableColumn<?, ?> tvDate_Even;
    @FXML
    private TableColumn<?, ?> tvImage;
    @FXML
    private TableColumn<?, ?> tvSujet;
    @FXML
    private Button menu_bt_afficher_membre;
    @FXML
    private TableView<Status> tvstatus;
    @FXML
    private TableColumn<?, ?> tvtext;
    @FXML
    private Button menu_bt_ajouterstatus;
    @FXML
    private Button bt_supp_Status;
    @FXML
    private Button bt_trie_Statu;
    private Stage primaryStage;

    ObservableList<Status> list = FXCollections.observableArrayList();
    @FXML
    private TextField mehdii;
    @FXML
    private Button btn_modifier_Status;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM status");
            while (rs.next()) {
                list.add(new Status(rs.getInt(1), rs.getDate(2), rs.getString(3)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherStatusAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

        AfficherStatus();
    }

    @FXML
    private void menu_ajouter_Evenement(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AjoutEvenementAdmin.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void menu_afficher_Evenement(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AfficherEvenementAdmin.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void menu_afficher_Status(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AfficherStatusAdmin.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void menu_ajouter_Status(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AjoutStatusAdmin.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void bt_modifier_Evenement(ActionEvent event) {
    }

    @FXML
    private void bt_supp_Evenement(ActionEvent event) {
    }

    @FXML
    private void bt_trie_Evenement(ActionEvent event) throws SQLException {
    }

    public void AfficherStatus() {

        ServiceStatus ss = new ServiceStatus();
        ObservableList<Status> list = ss.afficher();

        // tvId.setCellValueFactory(new PropertyValueFactory<>("id_pub"));
        tvDate_Pub.setCellValueFactory(new PropertyValueFactory<>("date_pub"));
        tvtext.setCellValueFactory(new PropertyValueFactory<>("text"));

        tvstatus.setItems(list);

    }

    @FXML
    private void bt_supp_Status(ActionEvent event) {
        Status s = tvstatus.getSelectionModel().getSelectedItem();
        tvstatus.getItems().removeAll(tvstatus.getSelectionModel().getSelectedItem());
        ServiceStatus ss = new ServiceStatus();
        ss.supprimer(s);
    }

    @FXML
    private void bt_trie_Statu(ActionEvent event) throws SQLException {
        trierDate();

    }

    public void trierDate() throws SQLException {

        tvDate_Pub.setCellValueFactory(new PropertyValueFactory<>("date_pub"));
        tvtext.setCellValueFactory(new PropertyValueFactory<>("text"));

        ServiceStatus ss = new ServiceStatus();

        List old = ss.getTrierParDATE_PUB();
        list.addAll(old);
        tvstatus.setItems(list);
        tvstatus.refresh();

    }

    @FXML
    private void recherche(KeyEvent event) {

        ServiceStatus ss = new ServiceStatus();
        List<Status> results = new ArrayList<>();
        results = ss.afficher();
        FilteredList<Status> filteredData = new FilteredList<>(list, b -> true);

        Status s = new Status();
        // 2. Set the filter Predicate whenever the filter changes.
        mehdii.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(status -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (status.getText().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } //else if (String.valueOf(s.gettext()).indexOf(lowerCaseFilter)!=-1)
                //   return true;
                else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Status> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tvstatus.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tvstatus.setItems(sortedData);

    }

    @FXML
    private void MAIL(ActionEvent event) throws MessagingException {

        //private void MAIL() throws SQLException, MessagingException {
        System.out.println("Preparing to send:");
        Properties properties = new Properties();

        String myAccountEmail = "mehdi.dagdagui@esprit.tn";
        String password = "Mehdi@2009";

        properties.put("com.hof.email.starttime", "20170519094544");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.connectiontimeout", "60000");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "*");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.timeout", "60000");
        properties.put("mail.transport.protocol", "smtp");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        String recepient = "mohamed.bouslah@esprit.tn";

        Message message = prepareMessage(session, myAccountEmail, recepient);

        Transport.send(message);
        System.out.println("Message send successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        try {

            ServiceStatus ss = new ServiceStatus();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("status ajoute ");
            message.setText("bonjour, votre status "
                    + "a ete ajoute avec succes."
                    + ".");
            return message;
        } catch (MessagingException ex) {
            Logger.getLogger(AfficherStatusAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @FXML
    private void PDF(ActionEvent event) throws SQLException {
        System.out.println("To Printer!");
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            Window primaryStage = null;
            job.showPrintDialog(this.primaryStage);

            Node root = this.tvstatus;
            job.printPage(root);
            job.endJob();
        }
    }

    @FXML
    private void openupdate(ActionEvent event) {

        Status s = tvstatus.getSelectionModel().getSelectedItem();

        try {
            FXMLLoader loader
                    = new FXMLLoader(getClass().getResource("ModifierStatustAdmiin.fxml"));
            Parent root = loader.load();
            ModifierStatusAdmiinController md = loader.getController();

            md.setTtext(s.getText());
            md.setTfDate_Pub(s.getDate_pub().toString());
            md.setSts(s.getId_pub());

            Stage stage = new Stage();
            stage.setTitle("Modifier status");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
