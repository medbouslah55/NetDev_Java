/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import pi.esprit.entities.Panier;
import pi.esprit.entities.Reservation;
import pi.esprit.services.PanierCRUD;
import pi.esprit.services.ReservationCRUD;

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
    private void ajouterunpanier(ActionEvent event) throws IOException { 
        PanierCRUD p = new PanierCRUD();
        int i=Integer.parseInt(tfpri.getText());
        int j=Integer.parseInt(tftota.getText());
        p.ajouterpanier(new Panier(tfnomact.getText(),i,j));
        JOptionPane.showMessageDialog(null,"Panier ajoute");
        Stage window = primarystage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("interfacepanier.fxml"));;
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();  
    }
    
    
    }
    

