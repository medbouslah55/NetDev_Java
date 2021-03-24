/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.diet;

import gui.categorie.AjouterCategorieDietController;
import java.util.regex.Pattern;
import static gui.categorie.AjouterCategorieDietController.saveToFileImageNormal;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import models.Diet;
import services.ServiceCategorieDiet;
import services.ServiceDiet;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class ModifierDietController implements Initializable {

    @FXML
    private ComboBox<String> combo_cat;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfCalories;
    @FXML
    private ComboBox<Integer> comboQuantite;
    @FXML
    private ImageView imgButton;
    @FXML
    private Button img;
    @FXML
    private Button btnModifer;
    @FXML
    private ComboBox<Integer> combo_id;
    
    ObservableList<Integer> listId = FXCollections.observableArrayList();
    ObservableList<String> listNom = FXCollections.observableArrayList();
    ObservableList<Integer> listKg = FXCollections.observableArrayList(50, 100, 150, 200, 250, 300);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load_Id();
        load_nom();
        load_quantite();
//        combo_cat.getSelectionModel().select("MindSpace");

    }
    
    public void load_Id(){
        Connection cnx = DataSource.getInstance().getCnx();
        try {
            String requete = "SELECT id FROM diet";
            Statement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next())
            {
                listId.add(rs.getInt("id"));
            }
            
            combo_id.setItems(listId);
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void load_nom(){
        Connection cnx = DataSource.getInstance().getCnx();
        try {
            String requete = "SELECT nom FROM category_diet";
            Statement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next())
            {
                listNom.add(rs.getString("nom"));
            }
            
            combo_cat.setItems(listNom);
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void load_quantite(){  
        comboQuantite.getItems().clear();
        comboQuantite.setItems(listKg);
//        comboQuantite.getSelectionModel().select(1);
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
    private void addImage(MouseEvent event) {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgButton.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
        if (controle_saisie()) {
            if (tfNom.getText().isEmpty()) {
                tfNom.setText("");
            }
            if (tfCalories.getText().isEmpty()) {
                tfCalories.setText("0");
            }
            try{
                Image image1 = null;
                image1 = imgButton.getImage();
                String photo = null;
                photo = saveToFileImageNormal(image1);
                //System.out.println(photo);

                int id = combo_id.getValue();
                String nom = tfNom.getText();
                int calories = Integer.parseInt(tfCalories.getText());
                int quantite = comboQuantite.getValue();
                String cat = (String) combo_cat.getValue();

                Connection cnx = DataSource.getInstance().getCnx();
                String requete = "SELECT id FROM category_diet WHERE nom="+(char)34+cat+(char)34;
                Statement pst = cnx.prepareStatement(requete);
                ResultSet rs = pst.executeQuery(requete);

                if(rs.next()){
                    ServiceDiet sp = new ServiceDiet();
                    sp.modifier(new Diet(id, rs.getInt(1), nom, calories, quantite, photo));
                    
                    ServiceCategorieDiet scd = new ServiceCategorieDiet();
                    scd.update_total_calorie(getAllCalories(rs.getInt(1)), rs.getInt(1));
                    JOptionPane.showMessageDialog(null, "Diet modifiée!");
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
    
    private boolean controle_saisie(){
        if (tfNom.getText().isEmpty() || tfCalories.getText().isEmpty() || combo_id.getSelectionModel().isEmpty()  || combo_cat.getSelectionModel().isEmpty() || comboQuantite.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Données erronés");
            alert.setHeaderText("Verifier les données");
            alert.setContentText("Veuillez bien remplir tous les champs !");
            alert.showAndWait();
            
            return false;
        } else {

            if (!Pattern.matches("^[a-zA-Z]*$", tfNom.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Données erronés");
                alert.setHeaderText("Verifier les données");
                alert.setContentText("Vérifiez les Nom !");
                alert.showAndWait();
                tfNom.requestFocus();
                tfNom.selectEnd();
                return false;
            }

            if (!Pattern.matches("^[0-9]+$", tfCalories.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Données erronés");
                alert.setHeaderText("Verifier les données");
                alert.setContentText("Vérifiez les calories !");
                alert.showAndWait();
                tfCalories.requestFocus();
                tfCalories.selectEnd();
                return false;
            }
        }
        return true;
    }
}