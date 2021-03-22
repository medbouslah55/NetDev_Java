/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.regime;

import static gui.menu.AjouterMenuController.saveToFileImageNormal;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import models.Menu;
import models.Regime;
import services.ServiceMenu;
import services.ServiceRegime;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class ModifierRegimeController implements Initializable {

    private Regime regime;

    private String id;
    @FXML
    private Button btnModifier;
    @FXML
    private TextArea taDesc;
    @FXML
    private ImageView imgButton;
    @FXML
    private Button img;
    @FXML
    private TextField tfType;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public void initData(Regime regimmme) {
        regime = regimmme;

        id = (Integer.toString(regime.getId()));
        tfType.setText(regime.getType());
        taDesc.setText(regime.getDescription());
        imgButton.setImage(new Image("file:/C:/Users/trabe/Desktop/DesktopFx/src/images/" + regime.getImage()));
    }

    @FXML
    private void Modifier(ActionEvent event) {
        if (controle_saisie()) {
            try{
                Image image1 = null;
                image1 = imgButton.getImage();
                String photo1 = null;
                photo1 = saveToFileImageNormal(image1);
                
                ServiceRegime sp = new ServiceRegime();
                
                int idd = Integer.parseInt(id);
                String type = tfType.getText();
                String desc = taDesc.getText();
                
                sp.modifier(new Regime(idd, type, desc, photo1));
                JOptionPane.showMessageDialog(null, "Régime Modifié!");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
    }
    
    private boolean controle_saisie(){
        if (taDesc.getText().isEmpty() || tfType.getText().isEmpty()){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Données erronés");
            alert.setHeaderText("Verifier les données");
            alert.setContentText("Veuillez bien remplir tous les champs !");
            alert.showAndWait();
            
            return false;
//        } else {
//
//            if (!Pattern.matches("^[a-z A-Z]*$", taDesc.getText())) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Données erronés");
//                alert.setHeaderText("Verifier les données");
//                alert.setContentText("Vérifiez la Description !");
//                alert.showAndWait();
//                taDesc.requestFocus();
//                taDesc.selectEnd();
//                return false;
//            }
        }
        return true;
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

}
