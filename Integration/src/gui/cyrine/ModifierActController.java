/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.cyrine;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import models.Activite;
import models.Centre;
import services.ActiviteService;
import services.CentreService;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ModifierActController implements Initializable {
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
    private Button btnModif2;
    @FXML
    private TextArea ftDesc;
    @FXML
    private Label lbType;

    private int id_act;
    private int id_centre;

    @FXML
    private Label lbIDcentre;
    @FXML
    private Label lbType12;
    @FXML
    private ComboBox<String> ListeCoach1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        init();
        System.out.println(this.getFtNomAct());
    }

    public void init() {
        lbIDcentre.setVisible(false);
        // String ch = this.getLbIDcentre() ;
        //  System.out.println("meow "+this.getLbIDcentre());
        /*     ObservableList<String> l = FXCollections.observableArrayList() ;
        CentreService cs = new CentreService() ;
        List<Centre> listcentre = cs.CentreListe() ;
     
        int getOldSelected  = 0 ;
        
        for(int i=0 ; i<listcentre.size() ; i++){
        l.add(listcentre.get(i).getNom_centre()) ;
        if(this.id_centre==listcentre.get(i).getId_centre())
            getOldSelected=i ;
        }
       ListeCentre.setItems(l);
         */
        //  ListeCentre.setValue(listcentre.get(getOldSelected).getNom_centre());

    }

    public void changerValCentre(String ch) {
        ListeCentre.setValue(ch);
    }

    public void changerValCoach(String ch) {
        ListeCoach1.setValue(ch);
    }

    public String getLbIDcentre() {
        return lbIDcentre.getText();
    }

    public void setLbIDcentre(String lbIDcentre) {
        this.lbIDcentre.setText(lbIDcentre);
    }

    public void setFtNomAct(String ftNomAct) {
        this.ftNomAct.setText(ftNomAct);
    }

    public void setFtCat(String ftCat) {
        this.ftCat.setText(ftCat);
    }

    public void setFtPrix(Double ftPrix) {
        this.ftPrix.setText(ftPrix.toString());
    }

    public void setFtcap(Integer ftcap) {
        this.ftcap.setText(ftcap.toString());
    }

    public void setListeCentre(ObservableList<String> ListeCentre) {
        this.ListeCentre.setItems(ListeCentre);
    }

    public void setListeCoach1(ObservableList<String> Liste) {
        this.ListeCoach1.setItems(Liste);
    }

    public void setDate_act(String date_act) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        this.date_act.setValue(LocalDate.parse(date_act, dateTimeFormatter));
    }

    public void setFtDesc(String ftDesc) {
        this.ftDesc.setText(ftDesc);
    }

    public void setId_act(int id_act) {
        this.id_act = id_act;
    }

    public int getId_act() {
        return id_act;
    }

    public int getId_centre() {
        return id_centre;
    }

    public void setId_centre(int id_centre) {
        this.id_centre = id_centre;
    }

    public String getFtNomAct() {
        return ftNomAct.getText();
    }

    @FXML
    private void UpdateAct(ActionEvent event) {
        if (this.controle() == false) {
            System.out.println("vérifier vos champs");
            JOptionPane.showMessageDialog(null, "vérifier vos champs!");
        } else {

            try {
                String rNom = ftNomAct.getText();
                String rPrix = ftPrix.getText();
                String rCat = ftCat.getText();
                String rCap = ftcap.getText();
                String rDesc = ftDesc.getText();
                String rDate = date_act.getValue().toString();
                Date d1 = Date.valueOf(rDate);
                Double dte = Double.parseDouble(rPrix);
                CentreService cs = new CentreService();
                Centre centre = cs.ChercherCentreParNom(ListeCentre.getSelectionModel().getSelectedItem());

                String coach = ListeCoach1.getSelectionModel().getSelectedItem();
                Statement st = DataSource.getInstance().getCnx().createStatement();
                String req2 = "SELECT * from coach where nom like '"
                        + coach.substring(0, coach.lastIndexOf(" ")) + "' and prenom like '" + coach.substring(coach.lastIndexOf(" ") + 1, coach.length()) + "'";
                int id = 0;
                ResultSet rs2 = st.executeQuery(req2);
                while (rs2.next()) {
                    id = rs2.getInt("cin");

                }

                Activite act = new Activite(rCat, rNom, dte, d1, rDesc, centre.getId_centre(), Integer.parseInt(rCap), id);
                act.setId_act(id_act);
                ActiviteService ac = new ActiviteService();
                ac.modifierActivite(act);
                JOptionPane.showMessageDialog(null, "Activité modifiée avec succès!");

                /* Centre c = new Centre(rNom, rTel, rMail, rAdr, rType);
                     c.setId_centre(this.getId_centre());
                     CentreService cs = new CentreService() ;
                     cs.modifierCentre(c);*/
 /*  FXMLLoader loader =
                     new FXMLLoader(getClass().getResource("GererCentre.fxml"));
                     Parent root = loader.load() ;
                     GererCentreController gc = loader.getController() ;
                     gc.showCentres();*/
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
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

}
