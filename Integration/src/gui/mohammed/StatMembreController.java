package gui.mohammed;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mohamedbouslah
 */
public class StatMembreController implements Initializable {

    @FXML
    private AnchorPane AP_ajouter_coach;
    @FXML
    private Button menu_bt_ajouter_coach;
    @FXML
    private Button menu_bt_afficher_coach;
    @FXML
    private Button menu_bt_afficher_membre;
    @FXML
    private Button menu_bt_Stat_membre;
    @FXML
    private AnchorPane ap_tb_coach;
    @FXML
    private PieChart statMembre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            loadDataPie();
        } catch (SQLException ex) {
            Logger.getLogger(StatMembreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        statMembre.setData(piechartdata);
    }

    @FXML
    private void menu_ajouter_coach(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/mohammed/AjouterCoachAdmin.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void menu_afficher_coach(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/mohammed/AfficherCoachAdmin.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void menu_afficher_membre(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/mohammed/AfficherMemebreAdmin.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void menu_stat_membre(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/mohammed/StatMembre.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    ObservableList<PieChart.Data> piechartdata;
    Connection cnx;
    ResultSet rs;

    public void loadDataPie() throws SQLException {
        piechartdata = FXCollections.observableArrayList();
        String dbUsername = "root";
        String dbPassword = "";
        String dbURL = "jdbc:mysql://127.0.0.1:3306/bdpidev3";

        cnx = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        PreparedStatement pst = cnx.prepareStatement("SELECT sexe as 'sexe', COUNT(sexe) as 'total' FROM membre GROUP BY sexe  ");
        rs = pst.executeQuery();

        while (rs.next()) {
            piechartdata.add(new PieChart.Data(rs.getString("sexe"), rs.getInt("total")));

        }
    }

}
