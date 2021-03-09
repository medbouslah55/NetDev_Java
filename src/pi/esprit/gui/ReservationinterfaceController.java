/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;
import pi.esprit.entities.Reservation;
import pi.esprit.services.ReservationCRUD;
import static pi.esprit.tools.Myconnection.ConnectDb;

/**
 * FXML Controller class
 *
 * @author HAMZA
 */
public class ReservationinterfaceController implements Initializable {

   
    @FXML
    private TextField tfcin;
    @FXML
    private DatePicker tfdate;
    @FXML
    private TextField tfnbrplace;
    @FXML
    private TableView<Reservation> tvreservations;
   
    @FXML
    private TableColumn<Reservation, Integer> collcin;
    @FXML
    private TableColumn<Reservation, Date> colldate;
    @FXML
    private TableColumn<Reservation, Integer> collnbrplace;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    private PreparedStatement pst = null;
     private ObservableList<Reservation> listr;
    ObservableList<Reservation> list;
    ObservableList<Reservation> listS = FXCollections.observableArrayList();
    private Stage primarystage; 
    @FXML
    private Button tftridate;
    @FXML
    private Button btnactualiser;
    @FXML
    private TextField tfrecherche;
    ObservableList<Reservation> reslist = FXCollections.observableArrayList();
    @FXML
    private Button btnpdf;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                afficherreservations();
}    
    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pi", "root","");
            return conn;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
//    public ObservableList<Reservation> getReservationList(){
//        ObservableList<Reservation> ListReservation = FXCollections.observableArrayList();
//        Connection conn = getConnection();
//        String query = "SELECT * FROM Reservation";
//        Statement st;
//        ResultSet rs;
//        try{
//            st = conn.createStatement(); 
//            rs = st.executeQuery(query);
//            Reservation Reservation;
//            while(rs.next()){
//                Reservation = new Reservation(rs.getInt("id_reservation"), rs.getInt("cin_membre"), rs.getDate("date_act"), rs.getInt("nb_place"));
//                ListReservation.add(Reservation);
//            }        
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return ListReservation;
//    }
    public void afficherreservations (){
        ObservableList<Reservation> listre = FXCollections.observableArrayList();
        collcin.setCellValueFactory(new PropertyValueFactory<>("cin_membre"));
        colldate.setCellValueFactory(new PropertyValueFactory<>("date_act"));
        collnbrplace.setCellValueFactory(new PropertyValueFactory<>("nb_place"));
        ReservationCRUD r = new ReservationCRUD();
        List old = r.listReservationbyid();
        listre.addAll(old);
        tvreservations.setItems(listre);    
    }

//    private void ajouterreservation() throws SQLException {
//        ReservationCRUD r = new ReservationCRUD();
//        int j=Integer.parseInt(tfcin.getText());
//        int k=Integer.parseInt(tfnbrplace.getText());
//        r.addreservation(new Reservation(j,Date.valueOf(tfdate.getValue()),k));
//        collcin.setCellValueFactory(new PropertyValueFactory<>("cin_membre"));
//        colldate.setCellValueFactory(new PropertyValueFactory<>("date_act"));
//        collnbrplace.setCellValueFactory(new PropertyValueFactory<>("nb_place"));
//        afficherreservations();
//        JOptionPane.showMessageDialog(null,"Reservation ajoute");
//        tvreservations.refresh();
//    }
    private void modifierreservation(){
        Reservation test = (Reservation) tvreservations.getSelectionModel().getSelectedItem();
        ReservationCRUD r = new ReservationCRUD();
        int j=Integer.parseInt(tfcin.getText());
        int k=Integer.parseInt(tfnbrplace.getText());
        r.modifierreservation(new Reservation(test.getId_reservation(),j,Date.valueOf(tfdate.getValue()),k));
        JOptionPane.showMessageDialog(null,"Reservation modifieé");
        afficherreservations();
        tvreservations.refresh();
    }
    private void supprimerreservation() throws SQLException{
        Reservation test = (Reservation) tvreservations.getSelectionModel().getSelectedItem();
        ReservationCRUD res = new ReservationCRUD();
        res.delete(test.getId_reservation());
        JOptionPane.showMessageDialog(null,"Reservation supprimeé");
        afficherreservations();
        tvreservations.refresh();
        afficherreservations();
        
    }
        // TODO
        

    @FXML
    private void ButtonAction(ActionEvent event) throws SQLException, IOException {
        if(event.getSource() == btnajouter){
        Stage window = primarystage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("ajouterreservation.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show(); 
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ajouterreservation.fxml"));
//        AnchorPane page = (AnchorPane) fxmlLoader.load();
//        Stage dialogStage = new Stage();
//        dialogStage.setTitle("Ajouter Reservation");
//        Scene scene = new Scene(page);
//        dialogStage.setScene(scene);
//        dialogStage.showAndWait();
          
        }else if (event.getSource() == btnmodifier){
            modifierreservation();
        }else if(event.getSource() == btnsupprimer){
            supprimerreservation();
        }else if(event.getSource() == tftridate){
            trierDate();
        }
        else if(event.getSource() == tfrecherche){
            recherche();
        }
        else if(event.getSource() == btnactualiser){
            actualiser();
        }
        else if(event.getSource() == btnpdf){
            BPDF();
        }
    }
    public void trierDate(){
        ObservableList<Reservation> reslist = FXCollections.observableArrayList();
        collcin.setCellValueFactory(new PropertyValueFactory<>("cin_membre"));
        colldate.setCellValueFactory(new PropertyValueFactory<>("date_act"));
        collnbrplace.setCellValueFactory(new PropertyValueFactory<>("nb_place"));
        ReservationCRUD rt = new ReservationCRUD();
        List old = rt.trierreservationdate();
        reslist.addAll(old);
        tvreservations.setItems(reslist);
        tvreservations.refresh();
    }
//        try {
//        ObservableList<Reservation> ofList = FXCollections.observableArrayList();
//        collcin.setCellValueFactory(new PropertyValueFactory<>("cin_membre"));
//        colldate.setCellValueFactory(new PropertyValueFactory<>("date_act"));
//        collnbrplace.setCellValueFactory(new PropertyValueFactory<>("nb_place"));
//        /////////////////////////////////////////////////////////////////////////////
//        ReservationCRUD rt = new ReservationCRUD();
//        List old = rt.FindReservationBycin(Integer.parseInt(tfcin.getText()));
//        ofList.addAll(old);
//        tvreservations.setItems(ofList);
//        tvreservations.refresh();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }   
//    }
         private void recherche() throws SQLException {
         Connection conn = ConnectDb();
            String value9 = tfrecherche.getText();
            PreparedStatement ps = conn.prepareStatement("select * from Reservation where cin_membre Like'"+value9+"'");
            ResultSet rs5 = ps.executeQuery();  
            while (rs5.next()){   
               reslist.add(new Reservation(rs5.getInt(1),rs5.getInt(2),rs5.getDate(3),rs5.getInt(4)) );               
            }
         collcin.setCellValueFactory(new PropertyValueFactory<>("cin_membre"));
         colldate.setCellValueFactory(new PropertyValueFactory<>("date_act"));
         collnbrplace.setCellValueFactory(new PropertyValueFactory<>("nb_place"));
         tvreservations.setItems( reslist);  
         tfrecherche.clear();
    }
            @FXML
    private void BPDF() {
    System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
    Window primaryStage = null;
           job.showPrintDialog(primaryStage); 
    Node root = this.tvreservations;
           job.printPage(root);
           job.endJob();
    }
    }
    @FXML
    public void actualiser(){
         reslist.clear();
       afficherreservations();
       tvreservations.refresh(); 
    }
    }

    

