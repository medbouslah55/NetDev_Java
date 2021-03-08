/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.categorie;

import java.net.URL;
import java.util.ResourceBundle;
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
import services.ServiceCategorieDiet;
/**
 * FXML Controller class
 *
 * @author trabe
 */
public class AfficherCategorieController implements Initializable {

    @FXML
    private TableView<CategorieDiet> tableview;
    @FXML
    private TableColumn<?, ?> Image;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> desc;
    @FXML
    private TableColumn<?, ?> totalCalorie;
    @FXML
    private TableColumn<?, ?> date;
    @FXML
    private Button btnSupp;
    @FXML
    private TextField filterField;
    
    ObservableList<CategorieDiet> list = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceCategorieDiet sp = new ServiceCategorieDiet();
        list = sp.afficher();
        
        
        Image.setPrefWidth(80);
        Image.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        id.setCellValueFactory(
            new PropertyValueFactory<>("id"));

        nom.setCellValueFactory(
            new PropertyValueFactory<>("nom"));
        
        desc.setCellValueFactory(
            new PropertyValueFactory<>("description"));
        
        totalCalorie.setCellValueFactory(
            new PropertyValueFactory<>("totalCalories"));
        
        date.setCellValueFactory(
            new PropertyValueFactory<>("Date"));
        
        tableview.setItems(list);
//        System.out.println(list);        
    }    

    @FXML
    private void supprimer(ActionEvent event) {
        CategorieDiet c = tableview.getSelectionModel().getSelectedItem();
        tableview.getItems().removeAll(tableview.getSelectionModel().getSelectedItem());
        ServiceCategorieDiet cs = new ServiceCategorieDiet();
        cs.supprimer(c);
    }
    
    @FXML
    private void recherche(){
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<CategorieDiet> filteredData = new FilteredList<>(list, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(cat_diet -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (cat_diet.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                                    return true; // Filter matches first name.
				} else if (cat_diet.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                                    return true; // Filter matches last name.
				}
				else if (String.valueOf(cat_diet.getId()).indexOf(lowerCaseFilter)!=-1)
				    return true;
                                else  
                                    return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<CategorieDiet> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableview.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableview.setItems(sortedData);
    }
}
