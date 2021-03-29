/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.firas;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFShapeTypes;
import services.ReclamationCRUD;
import static tests.MainProg.primaryStage;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class StatistqiuesAdminController implements Initializable {

    @FXML
    private BarChart<String, Integer> chartbar;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private Button btnback;
    @FXML
    private MaterialDesignIconView closeButton;
    @FXML
    private BarChart<String, Integer> charetat;
    @FXML
    private NumberAxis yetat;
    @FXML
    private CategoryAxis xetat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            stat();
            statetat();
        } catch (SQLException ex) {
            Logger.getLogger(StatistqiuesAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    public void stat() throws SQLException{
        ReclamationCRUD sp = new ReclamationCRUD();
         XYChart.Series set1 = new XYChart.Series<>();
         sp.listeReclamation().forEach(e->{
             set1.getData().add(new XYChart.Data<>(e.getType_rec(),e.getId_rec()));
         });
         chartbar.getData().addAll(set1);
    }
    
    public void statetat() throws SQLException{
        ReclamationCRUD sp = new ReclamationCRUD();
         XYChart.Series set2 = new XYChart.Series<>();
         sp.listeReclamation().forEach(e->{
             set2.getData().add(new XYChart.Data<>(e.getEtat_rec(),e.getId_rec()));
         });
         charetat.getData().addAll(set2);
    }
    @FXML
    private void backmenu(ActionEvent event) throws IOException {
      Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/firas/MenuGeneralFiras.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void close(MouseEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    
}
