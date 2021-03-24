/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.front;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import utils.DataSource;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection cnx = DataSource.getInstance().getCnx();
        try {
            String requete = "SELECT * FROM menu";
            Statement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            
            while (rs.next()) {
                Description.setText(rs.getString(2));
                Menu_Jour_Num.setText("Menu Jour " + rs.getInt(3));
                matin.setText(rs.getString(4));
                img_matin.setImage(new Image("file:/C:/Users/trabe/Desktop/DesktopFx/src/images/" + rs.getString(5)));
                dejeuner.setText(rs.getString(6));
                img_dejeuner.setImage(new Image("file:/C:/Users/trabe/Desktop/DesktopFx/src/images/" + rs.getString(7)));
                dinner.setText(rs.getString(8));
                img_dinner.setImage(new Image("file:/C:/Users/trabe/Desktop/DesktopFx/src/images/" + rs.getString(9)));
                total_calories.setText("Total Calorique: " + rs.getString(10));
//                Image image = new Image(getClass().getResourceAsStream(fruit.getImgSrc()));
//                img_matin.setImage(image);
//                Description.setStyle("-fx-background-color: #" + fruit.getColor() + ";\n" +
//                        "    -fx-background-radius: 30;");            
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }    
    
}
