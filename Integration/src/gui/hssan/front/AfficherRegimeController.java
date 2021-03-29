/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.hssan.front;

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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Menu;
import models.Regime;
import services.ServiceMenu;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.DataSource;
import utils.UserSession;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class AfficherRegimeController implements Initializable {

    @FXML
    private Button bt_dec_menu_back;
    @FXML
    private Label label_nom;
    @FXML
    private GridPane grid;
    @FXML
    private ComboBox<String> combo;
    
    ObservableList<String> list = FXCollections.observableArrayList();
    ObservableList<Menu> listMenu = FXCollections.observableArrayList();
    Connection cnx = DataSource.getInstance().getCnx();
    @FXML
    private Label labelChoix;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load_nom();
        setlabel();
    }
    
    public void setlabel(){
        label_nom.setText("Bienvenue " + UserSession.getInstance().getLoggedUser().getNom() + ":");
    }
    
    public void load_nom() {
        try {
            String requete = "SELECT type FROM regime";
            Statement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(rs.getString("type"));
            }

            combo.setItems(list);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void deco_menu_front(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/mohammed/Login.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        UserSession.getInstance().cleanUserSession();
        TrayNotification tray = null;
        tray = new TrayNotification("Déconnecté", "Déconnecté avec succès! ", NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(5));
    }

    @FXML
    private void load(ActionEvent event) {
        try {
            grid.getChildren().clear();
            String reg = (String) combo.getValue();
            String requete = "SELECT id_regime FROM regime WHERE type=" + (char) 34 + reg + (char) 34;
            Statement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);

            if (rs.next()) {
                ServiceMenu SP = new ServiceMenu();
                listMenu = SP.afficherFront(rs.getInt(1));
                int column = 1;
                int row = 0;

                try {
                    for (int i = 0; i < listMenu.size(); i++) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/gui/hssan/front/MenuFront.fxml"));
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
//                        grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                        grid.setMaxWidth(Region.USE_PREF_SIZE);

                        //set grid height
                        grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                        grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                        grid.setMaxHeight(Region.USE_PREF_SIZE);

                        GridPane.setMargin(Pane, new Insets(10));
                        column++;
                        labelChoix.setText("");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherRegimeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/mohammed/MenuFront.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
