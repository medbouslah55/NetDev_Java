package gui.hssan.menu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import models.Menu;
import org.apache.commons.lang3.RandomStringUtils;
import services.ServiceMenu;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class AjouterMenuController implements Initializable {

    @FXML
    private Button btnAjouter;
    @FXML
    private TextArea taDesc;
    @FXML
    private ImageView imgButton;
    @FXML
    private Button img;
    @FXML
    private TextField tf_nb_jours;
    @FXML
    private TextField tfDinner;
    @FXML
    private TextField tfMatin;
    @FXML
    private TextField tfDejeuner;
    @FXML
    private ImageView imgButton1;
    @FXML
    private Button img1;
    @FXML
    private ImageView imgButton11;
    @FXML
    private Button img11;
    @FXML
    private TextField tfTotalCalories;
    @FXML
    private ComboBox<String> comboRegime;

    ObservableList<String> list = FXCollections.observableArrayList();

    Connection cnx = DataSource.getInstance().getCnx();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load_nom();
    }

    public void load_nom() {
        try {
            String requete = "SELECT type FROM regime";
            Statement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(rs.getString("type"));
            }

            comboRegime.setItems(list);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void Ajouter(ActionEvent event) {
        if (controle_saisie()) {
            try {
                Image image1 = null;
                image1 = imgButton.getImage();
                String photo1 = null;
                photo1 = saveToFileImageNormal(image1);

                Image image2 = null;
                image2 = imgButton1.getImage();
                String photo2 = null;
                photo2 = saveToFileImageNormal(image2);

                Image image3 = null;
                image3 = imgButton11.getImage();
                String photo3 = null;
                photo3 = saveToFileImageNormal(image3);

                ServiceMenu sp = new ServiceMenu();

                String desc = taDesc.getText();
                int nbJours = Integer.parseInt(tf_nb_jours.getText());
                String matin = tfMatin.getText();
                String dejeuner = tfDejeuner.getText();
                String dinner = tfDinner.getText();
                int total_calories = Integer.parseInt(tfTotalCalories.getText());
                String reg = (String) comboRegime.getValue();

                String requete = "SELECT id_regime FROM regime WHERE type=" + (char) 34 + reg + (char) 34;
                Statement pst = cnx.prepareStatement(requete);
                ResultSet rs = pst.executeQuery(requete);
                if (rs.next()) {
                    sp.ajouter(new Menu(desc, nbJours, matin, photo1, dejeuner, photo2, dinner, photo3, total_calories, rs.getInt(1)));
//                    JOptionPane.showMessageDialog(null, "Menu ajoutée!");
                    TrayNotification tray = null;
                    tray = new TrayNotification("Menu ajoutée", "Votre menu a éte ajouté avec succes ,Merci ", NotificationType.SUCCESS);
                    tray.showAndDismiss(Duration.seconds(7));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private boolean controle_saisie() {
        if (taDesc.getText().isEmpty() || tf_nb_jours.getText().isEmpty() || tfMatin.getText().isEmpty() || tfDejeuner.getText().isEmpty() || tfDinner.getText().isEmpty()
                || tfTotalCalories.getText().isEmpty()) {

            TrayNotification tray = null;
            tray = new TrayNotification("Données erronés", "Veuillez bien remplir tous les champs !", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(7));

            return false;
        } else {

            if (!Pattern.matches("^[A-Z a-z 0-9]+$", taDesc.getText())) {
                TrayNotification tray = null;
                tray = new TrayNotification("Données erronés", "Vérifiez la Description !", NotificationType.ERROR);
                tray.showAndDismiss(Duration.seconds(7));
                taDesc.requestFocus();
                taDesc.selectEnd();
                return false;
            }
            
            if (!Pattern.matches("^[0-9]+$", tfTotalCalories.getText())) {
                TrayNotification tray = null;
                tray = new TrayNotification("Données erronés", "Vérifiez la Calories !", NotificationType.ERROR);
                tray.showAndDismiss(Duration.seconds(7));
                taDesc.requestFocus();
                taDesc.selectEnd();
                return false;
            }
            
            if (!Pattern.matches("^[0-9]+$", tfTotalCalories.getText())) {
                TrayNotification tray = null;
                tray = new TrayNotification("Données erronés", "Vérifiez la Calories !", NotificationType.ERROR);
                tray.showAndDismiss(Duration.seconds(7));
                taDesc.requestFocus();
                taDesc.selectEnd();
                return false;
            }
        }
        return true;
    }

    @FXML
    private void addImage1(MouseEvent event) {
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
    private void addImage2(MouseEvent event) {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgButton1.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void addImage3(MouseEvent event) {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgButton11.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static String saveToFileImageNormal(Image image) throws SQLException {
        String ext = "jpg";
        File dir = new File("C:/Users/trabe/Desktop/Integration/src/images/");
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
    private void load_caloires(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/gui/hssan/menu/Calories.fxml"));
            Parent tableViewParent = loader.load();

            Scene tableViewScene = new Scene(tableViewParent);
            //This line gets the Stage information
            Stage window = new Stage();
            window.setTitle("Calculateur calorique");
            window.setScene(tableViewScene);
            window.show();
    }
}
