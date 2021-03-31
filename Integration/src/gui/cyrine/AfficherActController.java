/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.cyrine;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import javax.swing.JOptionPane;
import models.Activite;
import models.Centre;
import models.ControleSaisie;
import models.Panier;
import services.ActiviteService;
import services.CentreService;
import services.PanierCRUD;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.DataSource;
import utils.Document_ACT_PDF_Creation;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AfficherActController implements Initializable {

    @FXML
    private TableView<Activite> liste;
    @FXML
    private TableColumn<Activite, String> colNom;
    @FXML
    private TableColumn<Activite, String> colCat;
    @FXML
    private TableColumn<Activite, String> colPrix;
    @FXML
    private TableColumn<Activite, String> colCap;
    @FXML
    private TableColumn<Activite, String> colDesc;
    @FXML
    private TableColumn<Activite, Date> colDate;
    @FXML
    private TableColumn<Activite, String> colCentre;
    @FXML
    private TableColumn<Activite, String> colCoach;
    @FXML
    private Button refrech;
    @FXML
    private Button btnModif;
    @FXML
    private Button btnSupp;
    @FXML
    private TextField ftRechCat;
    @FXML
    private Button triAct;
    @FXML
    private Button pdf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.showActivite();

        //PDF
        pdf.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Document_ACT_PDF_Creation dc = new Document_ACT_PDF_Creation();
                    dc.generatePDF();
                    File file = new File("my_docs.pdf");
                    if (file.exists()) {
                        long startTime = System.currentTimeMillis();
                        Desktop.getDesktop().open(file);
                        long endTime = System.currentTimeMillis();
                        System.out.println("Total time taken to open file -> " + file.getName() + " in " + (endTime - startTime) + " ms");
                    } else {
                        System.out.println("File not exits -> " + file.getAbsolutePath());
                    }
                    TrayNotification tray = null;
                    tray = new TrayNotification("Génération pdf", "PDF généré avec succès!!", NotificationType.SUCCESS);
                    tray.showAndDismiss(Duration.seconds(5));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    TrayNotification tray = null;
                    tray = new TrayNotification("Génération pdf", "PDF non généré!!", NotificationType.ERROR);
                    tray.showAndDismiss(Duration.seconds(5));
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                    TrayNotification tray = null;
                    tray = new TrayNotification("Génération pdf", "PDF non généré!!", NotificationType.ERROR);
                    tray.showAndDismiss(Duration.seconds(5));
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    TrayNotification tray = null;
                    tray = new TrayNotification("Génération pdf", "PDF non généré!!", NotificationType.ERROR);
                    tray.showAndDismiss(Duration.seconds(5));
                }

            }
        });

        ActiviteService as = new ActiviteService();
        ControleSaisie cs = new ControleSaisie();
        ftRechCat.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!ftRechCat.getText().isEmpty()) {
                if (cs.isAlpha(ftRechCat.getText())) {
                    List k = as.ChercherListActParCategorie(ftRechCat.getText());
                    ObservableList<Activite> l = FXCollections.observableArrayList(k);
                    liste.setItems(l);
                } else {
                    TrayNotification tray = null;
                    tray = new TrayNotification("Recherche par catégorie", "vérifier vos champs", NotificationType.WARNING);
                    tray.showAndDismiss(Duration.seconds(5));
                }

            } else {
                this.showActivite();

            }
        });

    }

    public ObservableList<Activite> getActList() {
        ObservableList<Activite> l = FXCollections.observableArrayList();
        String req = "Select * from activite";

        ActiviteService as = new ActiviteService();
        List<Activite> l2 = as.ActiviteListe();
        try {
            int i = 0;
            while (l2.size() > i) {
                Activite a = new Activite();

                a.setCategorie_act(l2.get(i).getCategorie_act());
                a.setNom_act(l2.get(i).getNom_act());
                a.setId_act(l2.get(i).getId_act());
                a.setPrix_reservation(l2.get(i).getPrix_reservation());
                a.setDate_act(l2.get(i).getDate_act());
                a.setCapacite(l2.get(i).getCapacite());
                a.setDescription(l2.get(i).getDescription());
                a.setId_centre(l2.get(i).getId_centre());
                a.setId_coach(l2.get(i).getId_coach());
                a.setNom_centre(l2.get(i).getNom_centre());
                a.setNom_coach(l2.get(i).getNom_coach());
                i++;
                l.add(a);
            }

        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }

        /* CentreService cs = new CentreService() ;
    l =(ObservableList<Centre>) cs.CentreListe() ;*/
        return l;
    }

    public void showActivite() {
        ObservableList<Activite> l = getActList();

        colNom.setCellValueFactory(new PropertyValueFactory<Activite, String>("nom_act"));

        colCat.setCellValueFactory(new PropertyValueFactory<Activite, String>("categorie_act"));
        colPrix.setCellValueFactory(new PropertyValueFactory<Activite, String>("prix_reservation"));
        colCap.setCellValueFactory(new PropertyValueFactory<Activite, String>("capacite"));
        colDesc.setCellValueFactory(new PropertyValueFactory<Activite, String>("description"));
        colDate.setCellValueFactory(new PropertyValueFactory<Activite, Date>("date_act"));

        //lezem nafichi esem centre moch nom
        colCentre.setCellValueFactory(new PropertyValueFactory<Activite, String>("nom_centre"));
        colCoach.setCellValueFactory(new PropertyValueFactory<Activite, String>("nom_coach"));
        liste.setItems(l);

    }

//refrech 
    @FXML
    private void RechrechListe(ActionEvent event) {
        this.showActivite();
    }

    @FXML
    private void UpdateAct(ActionEvent event) {
        Activite a = liste.getSelectionModel().getSelectedItem();
        if (a != null) {
            try {
                FXMLLoader loader
                        = new FXMLLoader(getClass().getResource("ModifierAct.fxml"));
                Parent root = loader.load();
                ModifierActController md = loader.getController();

                md.setFtPrix(a.getPrix_reservation());
                //conversion de type date vers string pour qu'il soit accepter par le picker 
                md.setDate_act(a.getDate_act().toString());
                md.setFtcap(a.getCapacite());
                md.setFtCat(a.getCategorie_act());
                md.setFtNomAct(a.getNom_act());
                md.setFtDesc(a.getDescription());
                md.setId_act(a.getId_act());

                //  ListeCentre.setValue(listcentre.get(getOldSelected).getNom_centre());
                md.setId_centre(a.getId_centre());

                //combo box centre 
                ObservableList<String> l = FXCollections.observableArrayList();

                CentreService cs = new CentreService();
                List<Centre> listcentre = cs.CentreListe();

                int getOldSelected = 0;

                for (int i = 0; i < listcentre.size(); i++) {

                    l.add(listcentre.get(i).getNom_centre());
                    if (a.getId_centre() == listcentre.get(i).getId_centre()) {
                        getOldSelected = i;
                    }
                }

                md.setListeCentre(l);
                md.changerValCentre(l.get(getOldSelected));

                // combo box coach  
                ObservableList<String> coa = FXCollections.observableArrayList();
                try {
                    Statement st = DataSource.getInstance().getCnx().createStatement();
                    String req2 = "SELECT * from coach";
                    int i = 0, j = 0;
                    ResultSet rs2 = st.executeQuery(req2);
                    while (rs2.next()) {
                        coa.add(rs2.getString("nom") + " " + rs2.getString("prenom"));
                        // coa.add(rs2.getString("nom")+" "+ rs2.getString("prenom"));
                        if (a.getNom_coach().equals(coa.get(j))) {
                            i = j;
                            //  System.out.println("le pos de i est "+i);
                        }
                        j++;
                    }
                    md.setListeCoach1(coa);
                    md.changerValCoach(coa.get(i));

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                Stage stage = new Stage();
                stage.setTitle("Modifier activité");
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else {// pas de selection de ligne mel table view 
            TrayNotification tray = null;
            tray = new TrayNotification("Modification d'une activité", "Séléctionner une ligne tout d'abord", NotificationType.WARNING);
            tray.showAndDismiss(Duration.seconds(5));

        }
    }

    @FXML
    private void DeletaAct(ActionEvent event) {
        Activite a = liste.getSelectionModel().getSelectedItem();
        if (a != null) {
            liste.getItems().removeAll(liste.getSelectionModel().getSelectedItem());
            ActiviteService ac = new ActiviteService();
            ac.supprimerActivite(a);
            TrayNotification tray = null;
            tray = new TrayNotification("La suppression d'une activité", "Activité supprimée avec succès!!", NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(5));
        } else {// pas de selection de ligne mel table view 
            TrayNotification tray = null;
            tray = new TrayNotification("Suppression d'une activité", "Séléctionner une ligne tout d'abord", NotificationType.WARNING);
            tray.showAndDismiss(Duration.seconds(5));

        }

    }

    /* @FXML
    private void rechAct(ActionEvent event) {
        String res = ftRechCat.getText() ;
        if(res.isEmpty()){
        
        }else{
         this.showActivitefiltrer(); 
        }
        
        
        
       
    }*/
 /*  public ObservableList<Activite> getActListFiltrer(String categorie){
    ObservableList<Activite> l = FXCollections.observableArrayList() ;
    String req ="Select * from activite" ;
    
    try {
     Statement st = MyConnection.getInstance().getConx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Activite a = new Activite() ;
                if(rs.getString("categorie_act").toUpperCase().contains(categorie.toUpperCase()))
                {  a.setCategorie_act(rs.getString("categorie_act"));
                a.setNom_act(rs.getString("nom_act"));
                a.setId_act(rs.getInt("id_act"));
                a.setPrix_reservation(rs.getDouble("prix_reservation"));
                a.setDate_act(rs.getDate("date_act"));
                a.setCapacite(rs.getInt("capacite"));
                a.setDescription(rs.getString("description"));
                a.setId_centre(rs.getInt("id_centre"));
                a.setId_coach(rs.getInt("id_coach"));
            
               
                l.add(a);}
            }
    
    }catch(Exception ex){
    
        System.out.println(ex.getMessage());
    }
    
 
   /* CentreService cs = new CentreService() ;
    l =(ObservableList<Centre>) cs.CentreListe() ;*/
 /* return l ;
    }*/
 /* public void showActivitefiltrer(){
         String res = ftRechCat.getText() ;
         ActiviteService ac = new ActiviteService() ;
     ObservableList<Activite> l =this.getActListFiltrer(res) ;
    colNom.setCellValueFactory(new PropertyValueFactory<Activite,String>("nom_act"));
    colCat.setCellValueFactory(new PropertyValueFactory<Activite,String>("categorie_act"));
    colPrix.setCellValueFactory(new PropertyValueFactory<Activite,String>("prix_reservation"));
    colCap.setCellValueFactory(new PropertyValueFactory<Activite,String>("capacite"));
    colDesc.setCellValueFactory(new PropertyValueFactory<Activite,String>("description"));
    colDate.setCellValueFactory(new PropertyValueFactory<Activite,Date>("date_act"));
    
    
    //lezem nafichi esem centre moch id :(
    colCentre.setCellValueFactory(new PropertyValueFactory<Activite,String>("id_centre"));
    colCoach.setCellValueFactory(new PropertyValueFactory<Activite,String>("id_coach"));
    liste.setItems(l);
    
    }*/

 /* private void detectChange(ActionEvent event) {
         String res = ftRechCat.getText() ;
        if(res.isEmpty())
            this.showActivite();
        
    }*/
    @FXML
    private void TriListe(ActionEvent event) {

        ActiviteService as = new ActiviteService();
        List<Activite> trier = as.CentreActTrierParPrix();

        ObservableList<Activite> l = FXCollections.observableArrayList();

        CentreService cs = new CentreService();
        try {
            int i = 0;
            while (trier.size() > i) {
                Activite a = new Activite();
                a.setPrix_reservation(trier.get(i).getPrix_reservation());
                a.setId_act(trier.get(i).getId_act());
                a.setNom_act(trier.get(i).getNom_act());
                a.setCapacite(trier.get(i).getCapacite());
                a.setDescription(trier.get(i).getDescription());
                a.setDate_act(trier.get(i).getDate_act());
                a.setId_centre(trier.get(i).getId_centre());
                a.setId_coach(trier.get(i).getId_coach());
                a.setCategorie_act(trier.get(i).getCategorie_act());

                a.setNom_centre(cs.getCentre(trier.get(i).getId_centre()).getNom_centre());

                //nom coach
                Statement st = DataSource.getInstance().getCnx().createStatement();

                String req2 = "SELECT * from coach where cin =" + trier.get(i).getId_coach();
                String nom = null;
                ResultSet rs2 = st.executeQuery(req2);
                while (rs2.next()) {
                    nom = rs2.getString("nom") + " " + rs2.getString("prenom");

                }

                a.setNom_coach(nom);
                i++;

                l.add(a);
            }

            colNom.setCellValueFactory(new PropertyValueFactory<Activite, String>("nom_act"));

            colCat.setCellValueFactory(new PropertyValueFactory<Activite, String>("categorie_act"));
            colPrix.setCellValueFactory(new PropertyValueFactory<Activite, String>("prix_reservation"));
            colCap.setCellValueFactory(new PropertyValueFactory<Activite, String>("capacite"));
            colDesc.setCellValueFactory(new PropertyValueFactory<Activite, String>("description"));
            colDate.setCellValueFactory(new PropertyValueFactory<Activite, Date>("date_act"));

            //lezem nafichi esem centre moch nom
            colCentre.setCellValueFactory(new PropertyValueFactory<Activite, String>("nom_centre"));
            colCoach.setCellValueFactory(new PropertyValueFactory<Activite, String>("nom_coach"));
            liste.setItems(l);

        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void RechercheParCat(ActionEvent event) {
        ActiviteService as = new ActiviteService();
        ControleSaisie cs = new ControleSaisie();
        ftRechCat.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!ftRechCat.getText().isEmpty()) {
                if (cs.isAlpha(ftRechCat.getText())) {
                    List k = as.ChercherListActParCategorie(ftRechCat.getText());
                    ObservableList<Activite> l = FXCollections.observableArrayList(k);
                    liste.setItems(l);
                } else {
                    TrayNotification tray = null;
                    tray = new TrayNotification("Recherche par catégorie", "vérifier vos champs", NotificationType.WARNING);
                    tray.showAndDismiss(Duration.seconds(5));
                }

            } else {
                this.showActivite();

            }
        });

    }

}
