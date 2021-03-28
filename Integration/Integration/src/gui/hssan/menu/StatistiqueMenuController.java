/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.hssan.menu;

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
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class StatistiqueMenuController implements Initializable {

    @FXML
    private BarChart<String, Integer> barCart;
    
    Connection cnx = DataSource.getInstance().getCnx();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String requete = "SELECT id_menu, total_calories FROM menu ORDER BY total_calories ASC";
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        
        try {
            Statement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>("Menu " + rs.getInt(1), rs.getInt(2)));
            }
            barCart.getData().add(series);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }    
    
}
