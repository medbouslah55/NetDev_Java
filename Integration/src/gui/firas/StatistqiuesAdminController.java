/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.firas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class StatistqiuesAdminController implements Initializable {

    @FXML
    private BarChart<?, ?> chartbar;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        XYChart.Series set1 = new XYChart.Series<>();
        set1.getData().add(new XYChart.Data("Delice",5000));
        set1.getData().add(new XYChart.Data("Adidas",2000));
        set1.getData().add(new XYChart.Data("Nike",4000));
        chartbar.getData().addAll(set1);
    }    
    
}
