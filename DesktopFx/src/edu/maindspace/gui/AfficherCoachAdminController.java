/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.maindspace.gui;

import edu.maindspace.entities.Coach;
import edu.maindspace.entities.ControleSaisie;
import edu.maindspace.services.CoachServices;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.Document_Creation_Coach;

/**
 * FXML Controller class
 *
 * @author mohamedbouslah
 */
public class AfficherCoachAdminController implements Initializable {

    @FXML
    private AnchorPane AP_ajouter_coach;
    @FXML
    private Button menu_bt_ajouter_coach;
    @FXML
    private Button menu_bt_afficher_coach;
    @FXML
    private Button menu_bt_afficher_membre;
    @FXML
    private AnchorPane ap_tb_coach;
    @FXML
    private TableColumn<Coach, Integer> tc_cin_coach;
    @FXML
    private TableColumn<Coach, String> tc_nom_coach;
    @FXML
    private TableColumn<Coach, String> tc_prenom_coach;
    @FXML
    private TableColumn<Coach, String> tc_date_coach;
    @FXML
    private TableColumn<Coach, String> tc_email_coach;//sexe
    @FXML
    private ComboBox<Integer> cb_modifier_coach;
    @FXML
    private TextField tf_nom_modifier_coach;
    @FXML
    private TextField tf_prenom_modifier_coach;
    @FXML
    private Button bt_modifier_coach;
    @FXML
    private ComboBox<Integer> cb_supprimer_coach;
    @FXML
    private Button bt_supprimer_coach;
    @FXML
    private Button bt_tri_coach;
    @FXML
    private TableView<Coach> tv_afficher_coach;
    @FXML
    private Button menu_bt_Stat_membre;
    
     private Stage primaryStage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficher_coach();
        cin_cb_modifier_supprimer();
    }    

    @FXML
    private void menu_ajouter_coach(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AjouterCoachAdmin.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void menu_afficher_coach(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AfficherCoachAdmin.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void menu_afficher_membre(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AfficherMemebreAdmin.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void menu_stat_membre(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("StatMembre.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    ObservableList options;
     String dbUsername = "root";
        String dbPassword = "root";
        String dbURL = "jdbc:mysql://localhost:8889/bdpidev";
        Connection cnx ;
        
    public void cin_cb_modifier_supprimer(){
       
        try {
            cnx = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            options = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = cnx.createStatement().executeQuery("SELECT cin FROM coach");
            while (rs.next()) {
                //get string from db,whichever way 
                options.add(new Coach(rs.getInt("cin")).getCin());
            }

        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }

        cb_modifier_coach.setItems(null);
        cb_modifier_coach.setItems(options);
        
        cb_supprimer_coach.setItems(null);
        cb_supprimer_coach.setItems(options);
        
    }
    
    @FXML
    private void cb_modifier_coach(ActionEvent event) throws SQLException {
        cnx = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        Statement st = cnx.createStatement();
        
        String req = "SELECT nom,prenom FROM coach WHERE cin='"+cb_modifier_coach.getSelectionModel().getSelectedItem()+"'";
        ResultSet rs = st.executeQuery(req);
        
        while (rs.next()) {
                String m = rs.getString("nom");
                String p = rs.getString("prenom");
                
               
                tf_nom_modifier_coach.setText(m);
                tf_prenom_modifier_coach.setText(p);
                
               
            }
    }
    
    CoachServices cs =new CoachServices();
    
    @FXML
    private void bt_modifier_coach(ActionEvent event) {
        Coach t = new Coach();
        if(verifchamps()==true)
        {
            t.setCin(cb_modifier_coach.getSelectionModel().getSelectedItem());
            t.setNom(tf_nom_modifier_coach.getText());
            t.setPrenom(tf_prenom_modifier_coach.getText());

            cs.modifier(t);
            afficher_coach();
        }
    }

    @FXML
    private void cb_supprimer_coach(ActionEvent event) {
    }

    @FXML
    private void bt_supprimer_coach(ActionEvent event) {
        Coach t = new Coach();
        t.setCin(cb_supprimer_coach.getSelectionModel().getSelectedItem());
        
        cs.supprimer(t);
        cin_cb_modifier_supprimer();
        afficher_coach();
    }

    @FXML
    private void bt_tri_coach(ActionEvent event) {
        CoachServices cs =new CoachServices();
        ObservableList<Coach> list =  cs.TrieParNom();
        tc_cin_coach.setCellValueFactory(new PropertyValueFactory<Coach, Integer>("cin"));
        tc_nom_coach.setCellValueFactory(new PropertyValueFactory<Coach, String>("nom"));
        tc_prenom_coach.setCellValueFactory(new PropertyValueFactory<Coach, String>("prenom"));
        tc_date_coach.setCellValueFactory(new PropertyValueFactory<Coach, String>("datee"));
        tc_email_coach.setCellValueFactory(new PropertyValueFactory<Coach, String>("sexe"));
        
        tv_afficher_coach.setItems(list);
    }
    
    public void afficher_coach(){
        CoachServices cs =new CoachServices();
        ObservableList<Coach> list =  cs.afficher();
        tc_cin_coach.setCellValueFactory(new PropertyValueFactory<Coach, Integer>("cin"));
        tc_nom_coach.setCellValueFactory(new PropertyValueFactory<Coach, String>("nom"));
        tc_prenom_coach.setCellValueFactory(new PropertyValueFactory<Coach, String>("prenom"));
        tc_date_coach.setCellValueFactory(new PropertyValueFactory<Coach, String>("datee"));
        tc_email_coach.setCellValueFactory(new PropertyValueFactory<Coach, String>("sexe"));
        
        
        tv_afficher_coach.setItems(list);
    }

    @FXML
    private void pdf_coach(ActionEvent event) {
        //imprim
         System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
    Window primaryStage = null;
           job.showPrintDialog(this.primaryStage); 
            
    Node root = this.tv_afficher_coach;
           job.printPage(root);
           job.endJob();
     
    }
    }
    ControleSaisie css =new ControleSaisie();
    private Boolean verifchamps()
    {
        if(tf_nom_modifier_coach.getText().isEmpty() || tf_prenom_modifier_coach.getText().isEmpty())
        {
            return false;
        }
        
        if(!css.isAlpha(tf_nom_modifier_coach.getText()))
        {
            TrayNotification tray = null;
            tray = new TrayNotification("Erreur", "Verifier votre Nom ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }
        if(!css.isAlpha(tf_prenom_modifier_coach.getText()))
        {
            TrayNotification tray = null;
            tray = new TrayNotification("Erreur", "Verifier votre Prenom ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            return false;
        }
        return true;
    }

    
    
}