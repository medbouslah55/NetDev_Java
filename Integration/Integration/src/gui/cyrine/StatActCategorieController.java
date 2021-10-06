/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.cyrine;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import services.ActiviteService;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class StatActCategorieController implements Initializable {

    @FXML
    private PieChart charpie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            loadDataPie();
            charpie.setData(piechartdata);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    ObservableList<PieChart.Data> piechartdata;

    /*    Connection cnx;
    ResultSet rs;*/

    public void loadDataPie() throws SQLException {
        piechartdata = FXCollections.observableArrayList();
        /* String dbUsername = "root";
        String dbPassword = "root";
        String dbURL = "jdbc:mysql://localhost:8889/mindspace1";*/

        // cnx = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        /*PreparedStatement pst = cnx.prepareStatement("SELECT sexe as 'sexe', COUNT(sexe) as 'total' FROM membre GROUP BY sexe  ");
        rs=pst.executeQuery();*/
        ResultSet rs;
        ActiviteService ac = new ActiviteService();
        rs = ac.StatCategorie();

        while (rs.next()) {
            piechartdata.add(new PieChart.Data(rs.getString("categorie_act"), rs.getInt("total")));

        }
    }

}
