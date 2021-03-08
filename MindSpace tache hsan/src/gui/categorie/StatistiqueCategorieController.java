/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.categorie;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import models.Diet;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class StatistiqueCategorieController implements Initializable {

    @FXML
    private BarChart<String, Integer> barCart;
    
    Connection cnx = DataSource.getInstance().getCnx();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String requete = "SELECT nom, total_calories FROM category_diet ORDER BY total_calories ASC";
        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        try {
            Statement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));
            }
            barCart.getData().add(series);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
//        XYChart.Series seriesP = new XYChart.Series();
//        XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
//        
//        series.getData().add(new XYChart.Data<String, Integer>("Fruit", 200));
//        series.getData().add(new XYChart.Data<String, Integer>("Légume", 300));
//        series.getData().add(new XYChart.Data<String, Integer>("Hhhh", 10));
//        series.getData().add(new XYChart.Data<String, Integer>("azdazdda", 20));
//        barCart.getData().add(series);
    }    
    
}
