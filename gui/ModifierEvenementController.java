/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package com.esprit.gui;

import com.esprit.entities.Evenement;
import com.esprit.services.ServicePublication;
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
public class ModifierEvenementController implements Initializable {

    @FXML
    private Button AfficherLocaux;
    @FXML
    private Button ModifierLocal;
    @FXML
    private Button btnAjout;
    @FXML
    private DatePicker tfDate_Pub;
    @FXML
    private DatePicker tfDate_even;
    @FXML
    private TextField tfSujet;
    @FXML
    private TextField tfimage;
    @FXML
    private Button btnModifier;
    private TextField id_even;
    @FXML
    private ComboBox<Integer> comboId;
    
       ObservableList<Integer> list = FXCollections.observableArrayList();
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Loader();
        // TODO
    }    

   
    @FXML
    private void Modifier(ActionEvent event) {
          ServicePublication sp = new ServicePublication();
        
        java.sql.Date pub = java.sql.Date.valueOf(tfDate_Pub.getValue());
       java.sql.Date even = java.sql.Date.valueOf(tfDate_even.getValue());
        String Sujet=tfSujet.getText();
        String image =tfimage.getText();
        
        sp.modifier(new Evenement(even, image, Sujet,comboId.getValue(), pub));
        
        
        
        JOptionPane.showMessageDialog(null, "Evenement modifiee!");
      
    }
    
  
    public void Loader(){
        
        
        Connection cnx = DataSource.getInstance().getCnx();
        try {
            String requete = "SELECT id_pub FROM evenement";
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

    @FXML
    private void AfficherLocauxAction(ActionEvent event) {
    }

    @FXML
    private void ModifierLocalAction(ActionEvent event) {
    }

    @FXML
    private void ajouter(ActionEvent event) {
    }

   

    
}
