/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Mahdi;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.util.Duration;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.Evenement;
import services.ServicePublication;
import utils.DataSource;
import utils.Document_Creation_Mahdi;

/**
 * FXML Controller class
 *
 * @author mehdi
 */
public class AfficherEvenementAdminController implements Initializable {

    @FXML
    private AnchorPane AP_ajouter_coach;
    @FXML
    private Button menu_bt_ajouterdate_pub;
    @FXML
    private Button menu_bt_afficher_evenement;
    @FXML
    private TableView<Evenement> tvevenement;
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
    private Button bt_supp_Evenement;
    @FXML
    private Button bt_trie_Evenement;
    @FXML
    private AnchorPane tfDate_Pub;
    private Stage primaryStage;
    @FXML
    private Button pdf;
    @FXML
    private Button btn_modifier_Evenement;
    @FXML
    private TextField mehdi;

    ObservableList<Evenement> list = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    private Connection cnx = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        afficherEven();

        try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM evenement");
            while (rs.next()) {
                list.add(new Evenement(rs.getDate(2), rs.getString(4), rs.getString(5), rs.getInt(1), rs.getDate(3)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEvenementAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    private void menu_ajouter_Status(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AjoutStatusAdmin.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void afficherEven() {

        ServicePublication sp = new ServicePublication();
        ObservableList<Evenement> list = sp.afficher();

        // tvId.setCellValueFactory(new PropertyValueFactory<>("id_pub"));
        tvDate_Pub.setCellValueFactory(new PropertyValueFactory<>("date_pub"));
        tvDate_Even.setCellValueFactory(new PropertyValueFactory<>("date_even"));
        tvImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        tvSujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));

        tvevenement.setItems(list);

    }

    @FXML
    private void bt_supp_Evenement(ActionEvent event) {

        Evenement e = tvevenement.getSelectionModel().getSelectedItem();
        tvevenement.getItems().removeAll(tvevenement.getSelectionModel().getSelectedItem());
        ServicePublication sp = new ServicePublication();
        System.out.println(e);
        sp.supprimer(e);
    }

    @FXML
    public void recherche() {

        ServicePublication sp = new ServicePublication();
        List<Evenement> results = new ArrayList<>();
        results = sp.afficher();
        FilteredList<Evenement> filteredData = new FilteredList<>(list, b -> true);

        Evenement e = new Evenement();
        // 2. Set the filter Predicate whenever the filter changes.
        mehdi.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(evenement -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (evenement.getImage().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (evenement.getSujet().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(e.getSujet()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Evenement> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tvevenement.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tvevenement.setItems(sortedData);

    }

    @FXML
    private void bt_trie_Evenement(ActionEvent event) throws SQLException {
        trierDate();
    }

    public void trierDate() throws SQLException {
        ServicePublication cs = new ServicePublication();
        ObservableList<Evenement> list = cs.TrieParNom();
        //           tvId.setCellValueFactory(new PropertyValueFactory<>("id_pub"));
        tvDate_Pub.setCellValueFactory(new PropertyValueFactory<>("date_pub"));
        tvDate_Even.setCellValueFactory(new PropertyValueFactory<>("date_even"));
        tvImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        tvSujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));


        tvevenement.setItems(list);
        TrayNotification tray = new TrayNotification("Tri", "Trie avec succces !", NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(5));
    }

    @FXML
    private void MAIL() throws SQLException, MessagingException {

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
        String recepient = "mehdi.dagdagui@esprit.tn";

        Message message = prepareMessage(session, myAccountEmail, recepient);

        Transport.send(message);
        System.out.println("Message send successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        try {
            ServicePublication sp = new ServicePublication();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Evenement ajoute ");
            message.setText("bonjour, votre publication "
                    + "a ete ajoute avec succes."
                    + ".");
            return message;
        } catch (MessagingException ex) {
            Logger.getLogger(AfficherEvenementAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @FXML
    private void PDF(ActionEvent event) {
        pdf.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Document_Creation_Mahdi dc = new Document_Creation_Mahdi();
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
    private void openupdate(ActionEvent event) {

        Evenement e = tvevenement.getSelectionModel().getSelectedItem();

        try {
            FXMLLoader loader
                    = new FXMLLoader(getClass().getResource("ModifierEvenementAdmiin.fxml"));
            Parent root = loader.load();
            ModifierEvenementAdmiinController md = loader.getController();
            /*  md.setFtModifNom(c.getNom_centre());
            md.setFtModifAdr(c.getAdr_centre());
            md.setFtModifMail(c.getMail_centre());
            md.setFtModifTel(c.getTel_centre());
            md.setFtModifType(c.getType_centre());
            md.setId_centre(c.getId_centre());*/

            md.setTfimage(e.getImage());
            md.setTfsujet(e.getSujet());
            md.setTfDate_even(e.getDate_even().toString());
            md.setTfDate_Pub(e.getDate_pub().toString());
            // md.setEvt(e);
            md.setEvt(e.getId_pub());

            Stage stage = new Stage();
            stage.setTitle("Modifier évenement");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/mohammed/MenuBackend.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
