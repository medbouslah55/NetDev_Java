/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.hssan.front;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Menu;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class MenuFrontController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private Label matin;
    @FXML
    private ImageView img_matin;
    @FXML
    private Label Description;
    @FXML
    private Label dejeuner;
    @FXML
    private ImageView img_dejeuner;
    @FXML
    private Label dinner;
    @FXML
    private ImageView img_dinner;
    @FXML
    private Label Menu_Jour_Num;
    @FXML
    private Label total_calories;
    
    private Menu Menu = new Menu();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }


    public void setData(Menu menu) {
        this.Menu = menu;

        Description.setText(menu.getDescirption());
        Menu_Jour_Num.setText("Menu Jour " + menu.getNum_jour());
        matin.setText(menu.getMatin());
        img_matin.setImage(new Image("file:/C:/Users/trabe/Desktop/Integration/src/images/" + menu.getMatin_img()));
        dejeuner.setText(menu.getDejeuner());
        img_dejeuner.setImage(new Image("file:/C:/Users/trabe/Desktop/Integration/src/images/" + menu.getDejeuner_img()));
        dinner.setText(menu.getDinner());
        img_dinner.setImage(new Image("file:/C:/Users/trabe/Desktop/Integration/src/images/" + menu.getDinner_img()));
        total_calories.setText("Total Calorique: " + menu.getTotal_calories());
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/hssan/front/AfficherRegime.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
