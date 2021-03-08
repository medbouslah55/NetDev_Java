/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.categorie;

import static gui.categorie.AjouterCategorieDietController.saveToFileImageNormal;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import models.CategorieDiet;
import models.Diet;
import services.ServiceCategorieDiet;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class ModifierCategorieDietController implements Initializable {

    @FXML
    private Button btnModifier;
    @FXML
    private TextField tfNom;
    @FXML
    private TextArea taDesc;
    @FXML
    private ImageView imgButton;
    @FXML
    private Button img;
    @FXML
    private ComboBox<Integer> comboId = null;
    
    ObservableList<Integer> list = FXCollections.observableArrayList();
    String Nom;
    @FXML
    private DatePicker date;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load_Id();
    }
    
    public void load_Id(){
        Connection cnx = DataSource.getInstance().getCnx();
        try {
            String requete = "SELECT id FROM category_diet";
            Statement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next())
            {
                list.add(rs.getInt("id"));
            }
            
            comboId.setItems(list);
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void Modifier(ActionEvent event) {
        if (controle_saisie()) {
            try{
                Image image1 = null;
                image1 = imgButton.getImage();
                String photo = null;
                photo = saveToFileImageNormal(image1);
                //System.out.println(photo);
                java.sql.Date datepick = java.sql.Date.valueOf(date.getValue());
                ServiceCategorieDiet sp = new ServiceCategorieDiet();
                sp.modifier(new CategorieDiet(comboId.getValue(), tfNom.getText(), taDesc.getText(), photo, datepick));
                JOptionPane.showMessageDialog(null, "Categorie Modifié!");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
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
    
    private boolean controle_saisie(){
        if (tfNom.getText().isEmpty() || taDesc.getText().isEmpty() || comboId.getSelectionModel().isEmpty()){
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

            if (!Pattern.matches("^[a-zA-Z]*$", taDesc.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Données erronés");
                alert.setHeaderText("Verifier les données");
                alert.setContentText("Vérifiez la Description !");
                alert.showAndWait();
                taDesc.requestFocus();
                taDesc.selectEnd();
                return false;
            }
        }
        return true;
    }
}
