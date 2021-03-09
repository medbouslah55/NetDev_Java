/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;
import pi.esprit.entities.Panier;
import pi.esprit.entities.Reservation;
import pi.esprit.services.PanierCRUD;
import pi.esprit.services.ReservationCRUD;
import static pi.esprit.tools.Myconnection.ConnectDb;

/**
 * FXML Controller class
 *
 * @author HAMZA
 */
public class InterfacepanierController implements Initializable {

    @FXML
    private TableView<Panier> tvpanier;
    @FXML
    private TableColumn<Panier, String> collnomact;
    @FXML
    private TableColumn<Panier, Integer> collprix;
    @FXML
    private TableColumn<Panier, Integer> colltotal;
    @FXML
    private Button btnback;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btncaisse;
    @FXML
    private Button btnsupprimer;
     private Stage primarystage; 
     ObservableList<Panier> panlist = FXCollections.observableArrayList();
    @FXML
    private Button btnpdf;
    @FXML
    private Button btnactualiser;
    @FXML
    private TextField tfrecherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherpanier();
        // TODO
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
        public void afficherpanier (){
        ObservableList<Panier> listp = FXCollections.observableArrayList();
        collnomact.setCellValueFactory(new PropertyValueFactory<>("nom_activite"));
        collprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colltotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        PanierCRUD pan = new PanierCRUD();
        List old = pan.listPanierbyid();
        listp.addAll(old);
        tvpanier.setItems(listp);    
    }
        private void supprimerpanier() throws SQLException{
        Panier test = (Panier) tvpanier.getSelectionModel().getSelectedItem();
        PanierCRUD pa = new PanierCRUD();
        pa.supprimerpanier(test.getId_panier());
        JOptionPane.showMessageDialog(null,"Panier supprimeé");
        afficherpanier();
        tvpanier.refresh();
        afficherpanier(); 
    }
            private void CLOSE(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

        public void ajouterpanier (){
        
        }

        private void caisse(){}
        private void back(){}
               
            

    @FXML
    private void ButtonAction(ActionEvent event) throws SQLException, IOException {
          if(event.getSource() == btncaisse){
            caisse();
        }else if (event.getSource() == btnback){
            back();
        }else if(event.getSource() == btnsupprimer){
            supprimerpanier();
        }
        else if(event.getSource() == btnpdf){
            BPDF();
        }
        else if(event.getSource() == tfrecherche){
            rechercher();
        }
        else if(event.getSource() == btnactualiser){
            actualiser();
        }
        else if(event.getSource() == btnajouter){
             Stage window = primarystage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("ajouterpanierinterface.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show(); 
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ajouterpanierinterface.fxml"));
//        AnchorPane page = (AnchorPane) fxmlLoader.load();
//        Stage dialogStage = new Stage();
//        dialogStage.setTitle("Ajouter Panier");
//        Scene scene = new Scene(page);
//        dialogStage.setScene(scene);
//        dialogStage.showAndWait();
     }}
      private void BPDF() {
    System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
    Window primaryStage = null;
           job.showPrintDialog(primaryStage); 
    Node root = this.tvpanier;
           job.printPage(root);
           job.endJob();
    }
    }
        
    public void actualiser(){
         panlist.clear();
       afficherpanier();
       tvpanier.refresh();
       
    }
//         private void recherche() throws SQLException {
//            Connection conn = ConnectDb();
//            String value9 = tfrecherche.getText();
//            PreparedStatement ps = conn.prepareStatement("select * from Panier where nom_activite Like'"+value9+"'");
//            ResultSet rs5 = ps.executeQuery();  
//            while (rs5.next()){   
//               panlist.add(new Panier(rs5.getString(1),rs5.getFloat(2),rs5.getFloat(3)));               
//            }
//            collnomact.setCellValueFactory(new PropertyValueFactory<>("nom_activite"));
//            collprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
//            colltotal.setCellValueFactory(new PropertyValueFactory<>("total"));
//            tvpanier.setItems(panlist);
//         
//            tfrecherche.clear();
//    }
         @FXML
         private void rechercher() throws SQLException {
             try {
                 Connection conn = ConnectDb();
            String value9 = tfrecherche.getText();
            PreparedStatement ps = conn.prepareStatement("select * from Panier where nom_activite Like'"+value9+"'");
            ResultSet rs5 = ps.executeQuery();  
            while (rs5.next()){   
               panlist.add(new Panier(rs5.getString(2),rs5.getFloat(3),rs5.getFloat(4)) );               
            }
            collnomact.setCellValueFactory(new PropertyValueFactory<>("nom_activite"));
            collprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            colltotal.setCellValueFactory(new PropertyValueFactory<>("total"));
            tvpanier.setItems( panlist);
            tfrecherche.clear();
             } catch (Exception e) {System.out.println(e.getMessage());
             }
    }

        
    }

  
    

