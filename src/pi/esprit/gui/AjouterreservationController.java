/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import pi.esprit.entities.Reservation;
import pi.esprit.services.ReservationCRUD;

/**
 * FXML Controller class
 *
 * @author HAMZA
 */
public class AjouterreservationController implements Initializable {

    @FXML
    private TextField tfcin;
    @FXML
    private DatePicker tfdate;
    @FXML
    private TextField tfnbrplace;
    @FXML
    private Button btnajout;
    private Button btnback;
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
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("interfacereservation.fxml"));;
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }

    @FXML
    
    private void ajouterreservation(ActionEvent event) throws IOException {
      
        ReservationCRUD r = new ReservationCRUD();
        int j=Integer.parseInt(tfcin.getText());
        int k=Integer.parseInt(tfnbrplace.getText());
        r.addreservation(new Reservation(j,Date.valueOf(tfdate.getValue()),k));
        JOptionPane.showMessageDialog(null,"Reservation ajoute");
        Stage window = primarystage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("interfacereservation.fxml"));;
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
        
    }

}

