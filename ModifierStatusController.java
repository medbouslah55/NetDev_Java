/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.entities.Status;
import com.esprit.services.ServiceStatus;
import com.esprit.tools.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author mehdi
 */
public class ModifierStatusController implements Initializable {

    @FXML
    private Button AfficherLocaux;
    @FXML
    private Button ModifierLocal;
    @FXML
    private Button btnajout;
    @FXML
    private TextField tftext;
    @FXML
    private DatePicker tfDate_Pub;
    @FXML
   private ComboBox<Integer> comboId;
    
    
    ObservableList<Integer> list = FXCollections.observableArrayList();
   
    @FXML
    private Button btnmodifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Loader();
    }    
    


    @FXML
    private void AfficherLocauxAction(ActionEvent event) {
    }

    @FXML
    private void ModifierLocalAction(ActionEvent event) {
       
    }

    @FXML
    private void ajouter(ActionEvent event) {
    }
    
    private void modifier(ActionEvent event) {
   ServiceStatus ss =new ServiceStatus();
         
        
        
      java.sql.Date pub = java.sql.Date.valueOf(tfDate_Pub.getValue());
        String text=tftext.getText();
        
        
        ss.modifier(new Status(comboId.getValue(),pub,text));
        
      // ss.modifier(new Status(pub,text,comboId.getValue()));
        
       
        JOptionPane.showMessageDialog(null, "Status modifiee!");
    }
    
    
    
     public void Loader(){
        
        
        Connection cnx = DataSource.getInstance().getCnx();
        try {
            String requete = "SELECT id_pub FROM status";
            Statement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next())
            {
                list.add(rs.getInt("id_pub"));
            }
            comboId.setItems(list);
        
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
