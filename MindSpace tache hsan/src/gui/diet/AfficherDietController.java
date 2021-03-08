/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.diet;

import gui.categorie.AjouterCategorieDietController;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.CategorieDiet;
import models.Diet;
import services.ServiceCategorieDiet;
import services.ServiceDiet;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class AfficherDietController implements Initializable {

    @FXML
    private TableView<Diet> tableview;
    @FXML
    private TableColumn<?, ?> Image;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> cat;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> calorie;
    @FXML
    private TableColumn<?, ?> quantite;
    @FXML
    private TableColumn<?, ?> date;
    @FXML
    private Button btnSupp;
    
    ObservableList<Diet> list = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceDiet sp = new ServiceDiet();
        ObservableList<Diet> list = sp.afficher();
        
        
        Image.setPrefWidth(80);
        Image.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        id.setCellValueFactory(
            new PropertyValueFactory<>("id"));
        
        cat.setCellValueFactory(
            new PropertyValueFactory<>("id_categorie"));
        
        nom.setCellValueFactory(
            new PropertyValueFactory<>("nom"));
        
        calorie.setCellValueFactory(
            new PropertyValueFactory<>("calories"));
        
        quantite.setCellValueFactory(
            new PropertyValueFactory<>("quantite"));
        
        date.setCellValueFactory(
            new PropertyValueFactory<>("Date"));
        
        tableview.setItems(list);
//        System.out.println(list);
    }
    
    public Integer getAllCalories(int id){
        Connection cnx = DataSource.getInstance().getCnx();
        Integer list = 0;
        try {
            String req = "SELECT calories FROM diet WHERE id_category="+(char)34+id+(char)34;
            PreparedStatement pre = cnx.prepareStatement(req);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                list += rs.getInt("calories");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AjouterCategorieDietController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @FXML
    private void supprimer(ActionEvent event) {
        Diet c = tableview.getSelectionModel().getSelectedItem();
        tableview.getItems().removeAll(tableview.getSelectionModel().getSelectedItem());
        ServiceDiet cs = new ServiceDiet();
        cs.supprimer(c);
        
        ServiceCategorieDiet scd = new ServiceCategorieDiet();
        scd.update_total_calorie(getAllCalories(c.getId_categorie()), c.getId_categorie());
    }
}
