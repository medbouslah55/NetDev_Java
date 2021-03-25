/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.front;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.util.Duration;
import models.Menu;
import models.Regime;
import services.ServiceMenu;
import services.ServiceRegime;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class MainUiController implements Initializable {
    
    ObservableList<String> list = FXCollections.observableArrayList();
    
    Connection cnx = DataSource.getInstance().getCnx();
    
    ObservableList<Menu> listMenu = FXCollections.observableArrayList();
    
    @FXML
    private ComboBox<String> combo;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load_nom();
    }
    
    public void load_nom(){
        try {
            String requete = "SELECT type FROM regime";
            Statement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next())
            {
                list.add(rs.getString("type"));
            }
            
            combo.setItems(list);
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    @FXML
    private void load(){
        try {
            grid.getChildren().clear();
            String reg = (String)combo.getValue();
            String requete = "SELECT id_regime FROM regime WHERE type="+(char)34+reg+(char)34;
            Statement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            
            if(rs.next()){
                ServiceMenu SP = new ServiceMenu();
                listMenu = SP.afficherFront(rs.getInt(1));
                int column = 1;
                int row = 0;
                
                try {
                    for (int i = 0; i < listMenu.size(); i++) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/gui/front/MenuFront.fxml"));
                        Pane Pane = fxmlLoader.load();
                        
                        MenuFrontController itemController = fxmlLoader.getController();
                        itemController.setData(listMenu.get(i));
                        
                        if (column > 1) {
                            column = 1;
                            row++;
                        }
                        
                        grid.add(Pane, column, row); //(child,column,row)
                        //set grid width
                        grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                        grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                        grid.setMaxWidth(Region.USE_PREF_SIZE);
                        
                        //set grid height
                        grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                        grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                        grid.setMaxHeight(Region.USE_PREF_SIZE);
                        
                        GridPane.setMargin(Pane, new Insets(10));
                        column++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainUiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
