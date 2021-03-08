/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.categorie;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
import org.apache.commons.lang3.RandomStringUtils;
import services.ServiceCategorieDiet;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class AjouterCategorieDietController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextArea taDesc;
    @FXML
    private ImageView imgButton;
    @FXML
    private Button img;
    @FXML
    private Button btnAjouter;
    @FXML
    private DatePicker date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
    public static String saveToFileImageNormal(Image image)throws SQLException  {

        String ext = "jpg";
        File dir = new File("C:/Users/trabe/Desktop/MindSpace/src/images/");
        String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        File outputFile = new File(dir, name);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return name;
    }


    @FXML
    private void Ajouter(ActionEvent event) {
        if (controle_saisie()) {
            try{
                Image image1 = null;
                image1 = imgButton.getImage();
                String photo = null;
                photo = saveToFileImageNormal(image1);
                //System.out.println(photo);
                
                java.sql.Date datepick = java.sql.Date.valueOf(date.getValue());
                ServiceCategorieDiet sp = new ServiceCategorieDiet();
                sp.ajouter(new CategorieDiet(tfNom.getText(), taDesc.getText(), photo, datepick));
                JOptionPane.showMessageDialog(null, "Categorie ajoutée!");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    private boolean controle_saisie(){
        if (tfNom.getText().isEmpty() || taDesc.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Données erronés");
            alert.setHeaderText("Verifier les données");
            alert.setContentText("Veuillez bien remplir tous les champs !");
            alert.showAndWait();
            
            return false;
        } else {

            if (!Pattern.matches("^[a-z A-Z]*$", tfNom.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Données erronés");
                alert.setHeaderText("Verifier les données");
                alert.setContentText("Vérifiez les Nom !");
                alert.showAndWait();
                tfNom.requestFocus();
                tfNom.selectEnd();
                return false;
            }

            if (!Pattern.matches("^[a-z A-Z]*$", taDesc.getText())) {
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
