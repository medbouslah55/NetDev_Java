/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.cyrine;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import models.Activite;
import models.Centre;
import services.ActiviteService;
import services.CentreService;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AjouterActController implements Initializable {

    @FXML
    private Label lbCentre;
    @FXML
    private Label lbTel;
    @FXML
    private Label lbMail;
    @FXML
    private Label lbAdr;
    @FXML
    private Label lbType1;
    @FXML
    private Label lbType11;
    @FXML
    private TextField ftNomAct;
    @FXML
    private TextField ftCat;
    @FXML
    private TextField ftPrix;
    @FXML
    private TextField ftcap;
    @FXML
    private ComboBox<String> ListeCentre;
    @FXML
    private DatePicker date_act;
    @FXML
    private Button btnAjout;
    @FXML
    private TextArea ftDesc;
    @FXML
    private Label lbType;
    @FXML
    private Label lbcoach;
    @FXML
    private ComboBox<String> ListeCoach1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> l = FXCollections.observableArrayList();
        CentreService cs = new CentreService();
        List<Centre> listcentre = cs.CentreListe();

        for (int i = 0; i < listcentre.size(); i++) {
            l.add(listcentre.get(i).getNom_centre());
        }
        ListeCentre.setItems(l);

        //get coachs 
        ObservableList<String> coach = FXCollections.observableArrayList();
        try {

            String req = "SELECT * from coach";
            // Statement st = new MyConnection().getConx().createStatement();

            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                coach.add(rs.getString("nom") + " " + rs.getString("prenom"));
            }

            ListeCoach1.setItems(coach);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

    }

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public boolean isInte(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public boolean isAlpha(String ch) {
        if (ch == null) {
            return false;
        } else {
            int i = 0;
            while (i < ch.length()) {
                if (((int) ch.charAt(i) >= 65 && (int) ch.charAt(i) <= 90) || ((int) ch.charAt(i) >= 97 && (int) ch.charAt(i) <= 122)) {
                    i++;
                } else {
                    return false;
                }
            }

        }

        return true;

    }

    private boolean controle() {

        if (ftNomAct.getText().isEmpty() || ftCat.getText().isEmpty() || ftPrix.getText().isEmpty()
                || ListeCentre.getSelectionModel().getSelectedItem() == null
                || ftcap.getText().isEmpty() || ftDesc.getText().isEmpty() || date_act.getValue() == null) {
            return false;
        }

        if (this.isAlpha(ftNomAct.getText()) == false || this.isAlpha(ftCat.getText()) == false) {
            return false;
        }

        if (this.isNumeric(ftPrix.getText()) == false || this.isInte(ftcap.getText()) == false) {
            return false;
        }

        return true;

    }

    @FXML
    private void AddActivite(ActionEvent event) {
        /// il faut controler les champs 

        TrayNotification tray = null;

        //System.out.println(ListeCentre.getSelectionModel().getSelectedItem());
        if (this.controle() == false) {
            System.out.println("vérifier vos champs ");
            // JOptionPane.showMessageDialog(null, "vérifier vos champs !");

        } else {
            String rNom = ftNomAct.getText();
            String rCat = ftCat.getText();
            String rPrix = ftPrix.getText();
            String rCap = ftcap.getText();
            String rDesc = ftDesc.getText();
            String rDate = date_act.getValue().toString();
            Date d1 = Date.valueOf(rDate);
            Double dte = Double.parseDouble(rPrix);

            //vu l'unicité des nom de centre
            CentreService cs = new CentreService();
            Centre centre = cs.ChercherCentreParNom(ListeCentre.getSelectionModel().getSelectedItem());

            //recherche du coach
            int id = 0;
            try {
                String coach = ListeCoach1.getSelectionModel().getSelectedItem();
                String prnom = coach.substring(coach.lastIndexOf(" ") + 1, coach.length());
                System.out.println(prnom);
                String nom = coach.substring(0, coach.lastIndexOf(" "));
                System.out.println(nom);
                String req = "SELECT * from coach where nom like '" + nom + "' and prenom like '" + prnom + "'";
                // Statement st = new MyConnection().getConx().createStatement();

                Statement st = DataSource.getInstance().getCnx().createStatement();
                ResultSet rs = st.executeQuery(req);

                while (rs.next()) {
                    id = rs.getInt("cin");
                }
                System.out.println(id);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());

            }

            // l insertion 
            Activite act = new Activite(rCat, rNom, dte, d1, rDesc, centre.getId_centre(), Integer.parseInt(rCap), id);
            ActiviteService ac = new ActiviteService();
            ac.ajouterActivite(act);
            // JOptionPane.showMessageDialog(null, "Activité ajoutée avec succès!");
            tray = new TrayNotification("L'ajout d'une activité", "Activité ajoutée avec succès!!", NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(5));

            //   System.out.println(act);
            /*
          String str="2025-03-31";  
        Date d1 = Date.valueOf(str);
             */
        }
    }

}
