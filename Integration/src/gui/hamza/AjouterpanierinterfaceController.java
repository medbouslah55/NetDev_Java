/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.hamza;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Panier;
import services.PanierCRUD;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author HAMZA
 */
public class AjouterpanierinterfaceController implements Initializable {

    @FXML
    private TextField tfnomact;
    @FXML
    private TextField tfpri;
    @FXML
    private TextField tftota;
    private Stage primarystage;
    @FXML
    private Button back;
    @FXML
    private MaterialDesignIconView btnback;
    @FXML
    private TextField tfquantite;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private void back(ActionEvent event) throws IOException {
        Stage window = primarystage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("interfacepanier.fxml"));;
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }    

    @FXML
//    private void ajouterunpanier(ActionEvent event) throws IOException { 
//        PanierCRUD p = new PanierCRUD();
//        
//        
//        p.ajouterpanier(new Panier(tfnomact.getText(),Float.parseFloat(tfpri.getText()),Integer.parseInt(tfquantite.getText()),Float.parseFloat(tftota.getText())));
//        //JOptionPane.showMessageDialog(null,"Panier ajoute");
//        notifsuccess("Panier ajoute");
//        Stage window = primarystage;
//        Parent rootRec2 = FXMLLoader.load(getClass().getResource("interfacepanier.fxml"));;
//        Scene rec2 = new Scene(rootRec2);
//        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        app.setScene(rec2);
//        app.show();  
//    }
            private void notifsuccess(String message){
        String title = "Congratulations";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(javafx.util.Duration.seconds(3));
    }
           private void notiferror(String message){
        String title = "Failed";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.ERROR);
        tray.showAndDismiss(javafx.util.Duration.seconds(3));
    }
    
    
    }
    

