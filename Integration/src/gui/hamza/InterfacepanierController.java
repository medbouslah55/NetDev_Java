/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.hamza;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import models.Abonnement;
import models.Panier;
import services.PanierCRUD;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.DataSource;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.AbonnementCRUD;
import services.ReservationCRUD;

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
    private TableColumn<Panier, Float> collprix;
    @FXML
    private Button btnback;
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
    @FXML
    private MaterialDesignIconView closeButton;
    @FXML
    private Button btnprint;
    @FXML
    private Label total;
    @FXML
    private TableColumn<Panier, Integer> collcapacite;
    @FXML
    private TableColumn<Panier, String> collpayerousupprimer;
    @FXML
    private Button btntri;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            list();
        } catch (SQLException ex) {
            Logger.getLogger(InterfacepanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        afficherpanier();
        total.setText(total().toString());
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
        
        collnomact.setCellValueFactory(new PropertyValueFactory<>("nom_act"));
        collprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        collcapacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        PanierCRUD pan = new PanierCRUD();
        List old = pan.listPanierbyid();
        panlist.addAll(old);
        
        Callback<TableColumn<Panier, String>, TableCell<Panier, String>> cellFoctory = (TableColumn<Panier, String> param) -> {
                // make cell containing buttons
                final TableCell<Panier, String> cell = new TableCell<Panier, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        //that cell created only on non-empty rows
                        if (empty) {
                            setGraphic(null);
                            setText(null);

                        } else {
                            FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                            MaterialDesignIconView Acheter = new MaterialDesignIconView(MaterialDesignIcon.CART);
                            deleteIcon.setStyle(
                                    " -fx-cursor: hand ;"
                                    + "-glyph-size:28px;"
                                    + "-fx-fill:#ff1744;"
                            );
                            deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                                Panier test = (Panier) tvpanier.getSelectionModel().getSelectedItem();
                                PanierCRUD rt = new PanierCRUD();
                                rt.supprimerpanier(test.getId_panier());
                                
                                notifsuccess("une reservation à éte supprimer du panier avec suceés");
                            });

                            Acheter.setStyle(
                                    " -fx-cursor: hand ;"
                                    + "-glyph-size:28px;"
                                    + "-fx-fill:#00E676;"
                            );
                            Acheter.setOnMouseClicked((MouseEvent event) -> {
                                try {
                                    Parent rootRec2 = FXMLLoader.load(getClass().getResource("paiement.fxml"));
                                    Scene rec2 = new Scene(rootRec2);
                                    Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                    app.setScene(rec2);
                                    app.show();
                                } catch (IOException e) {
                                    System.out.println(e.getMessage());
                                }
                            });
                            HBox managebtn = new HBox(Acheter, deleteIcon);
                            managebtn.setStyle("-fx-alignment:center");
                            HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                            //HBox managebtn = new HBox(Acheter);
                            managebtn.setStyle("-fx-alignment:center");
                            //HBox.setMargin(f1, new Insets(2, 2, 0, 3));
                            HBox.setMargin(Acheter, new Insets(2, 3, 0, 2));
                            setGraphic(managebtn);
                            setText(null);
                        }
                    }
                };
                return cell;
                };
            
            collpayerousupprimer.setCellFactory(cellFoctory);
            tvpanier.setItems(panlist);
         
        }
        private void supprimerpanier() throws SQLException{
        Panier test = (Panier) tvpanier.getSelectionModel().getSelectedItem();
        PanierCRUD pa = new PanierCRUD();
        pa.supprimerpanier(test.getId_panier());
        //JOptionPane.showMessageDialog(null,"Panier supprimeé");
        //afficherpanier();
        //tvpanier.refresh();
        actualiser();
        
    }
//        @FXML
//        private void supprimertoutpanier() throws SQLException{
//        Panier test = (Panier) tvpanier.getSelectionModel().getSelectedItem();
//        PanierCRUD pa = new PanierCRUD();
//        pa.supprimertoutpanier();
//        //JOptionPane.showMessageDialog(null,"Panier supprimeé");
//       //afficherpanier();
//        tvpanier.refresh();
//        //afficherpanier(); 
//    }
            private void CLOSE(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

        public void ajouterpanier (){
        
        }

@FXML
        private void back(ActionEvent event) throws IOException{
              Stage window = primarystage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("/gui/mohammed/MenuFront.fxml"));;
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }    
        
               
            

    @FXML
    private void ButtonAction(ActionEvent event) throws SQLException, IOException, DocumentException {
        if(event.getSource() == btncaisse){
        Stage window = primarystage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("paiement.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
        
        
        }else if(event.getSource() == btnsupprimer){
            supprimerpanier();
            notifsuccess("Suppression effectuez");
            
        }
        else if(event.getSource() == btnprint){
            BPDF();
            notifsuccess("Impression En cours");
        }
//        else if(event.getSource() == tfrecherche){
//            rechercher();
//        }
        else if(event.getSource() == btnactualiser){
            actualiser();
            notifsuccess("Panier Actualiser");
        }
        else if(event.getSource() == btntri){
            triprix();
            notifsuccess("Panier Actualiser");
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
         notifsuccess("Votre liste est actualiser");
          total();  
         
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
//         private void rechercher() throws SQLException {
//             try {
//                 
//            String value9 = tfrecherche.getText();
//            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement("select * from Panier where nom_activite Like'"+value9+"'");
//            ResultSet rs5 = ps.executeQuery();  
//            while (rs5.next()){   
//               panlist.add(new Panier(rs5.getString(2),rs5.getFloat(3),rs5.getInt(4),rs5.getFloat(5)) );               
//            }
//            collnomact.setCellValueFactory(new PropertyValueFactory<>("nom_activite"));
//            collprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
//            collquantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
//            colltotal.setCellValueFactory(new PropertyValueFactory<>("total"));
//            tvpanier.setItems( panlist);
//            tfrecherche.clear();
//             } catch (Exception e) {
//                 System.out.println(e.getMessage());
//             }
//    }
         @FXML
            private void list() throws SQLException {
        
            PanierCRUD pt = new PanierCRUD();
            //loadDate();
            List arr = pt.chercherPanier(tfrecherche.getText());
            ObservableList obb = FXCollections.observableArrayList(arr);
            tvpanier.setItems(obb);
        
    }

    @FXML
    private void close(MouseEvent event) {
         // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    private Float total() {
        float nb = 0;
        try {
            String req = "Select prix from panier";
            PreparedStatement st = DataSource.getInstance().getCnx().prepareStatement(req);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                nb = nb + rs.getFloat("prix");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (nb == 0) {
            System.out.println("Total est 0");
        }
        return nb;
    }
              private void notifsuccess(String message){
        String title = "Congratulations";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(javafx.util.Duration.seconds(3));
    }
           private void notiferror(String message){
        String title = "Failed";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.ERROR);
        tray.showAndDismiss(javafx.util.Duration.seconds(3));
    }
           @FXML
           private void PDF() throws SQLException, DocumentException {
            try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\trabe\\Desktop\\Integration\\Panier.pdf"));
            
            doc.open();
            doc.add(new Paragraph(" "));
            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 28, Font.UNDERLINE, BaseColor.BLACK);
            Paragraph p = new Paragraph("Les Reservation en PDF", font);
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            PdfPTable tabpdf = new PdfPTable(3);
            tabpdf.setWidthPercentage(100);
            PdfPCell cell;
            cell = new PdfPCell(new Phrase("Nom Activite", FontFactory.getFont("Times New Roman", 15)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setBorderColor(BaseColor.WHITE);
            cell.setBorderWidth(2);
            tabpdf.addCell(cell);
            cell = new PdfPCell(new Phrase("Prix", FontFactory.getFont("Times New Roman", 15)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setBorderColor(BaseColor.WHITE);
            cell.setBorderWidth(2);
            tabpdf.addCell(cell);

//            cell = new PdfPCell(new Phrase("Quantite", FontFactory.getFont("Times New Roman", 15)));
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell.setBackgroundColor(BaseColor.WHITE);
//            cell.setBorderColor(BaseColor.WHITE);
//            cell.setBorderWidth(2);
//            tabpdf.addCell(cell);

            cell = new PdfPCell(new Phrase("Capacite", FontFactory.getFont("Times New Roman", 15)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setBorderColor(BaseColor.WHITE);
            cell.setBorderWidth(2);
            tabpdf.addCell(cell);         

            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery("Select * from panier");
            while (rs.next()) {
                cell = new PdfPCell(new Phrase(rs.getString("nom_act"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);
                cell = new PdfPCell(new Phrase(rs.getString("prix"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);
//                cell = new PdfPCell(new Phrase(rs.getString("quantite"), FontFactory.getFont("Times New Roman", 11)));
//                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell.setBackgroundColor(BaseColor.WHITE);
//                tabpdf.addCell(cell);
                cell = new PdfPCell(new Phrase(rs.getString("capacite"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);
            }
            doc.add(tabpdf);
            notifsuccess("Donneés Extraire Sous Format PDF");
            doc.close();
            Desktop.getDesktop().open(new File("C:\\Users\\trabe\\Desktop\\Integration\\Panier.pdf"));

        } catch (HeadlessException | IOException e) {
            System.out.println("ERROR PDF");
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());
        }
    }
                private void triprix() throws SQLException {
        
        PanierCRUD rt = new PanierCRUD();
            //loadDate();
            List arr = rt.trierprix();
            ObservableList obb = FXCollections.observableArrayList(arr);
            tvpanier.setItems(obb);
        notifsuccess("Les messages sont triees selon le prix ");
    }
//    private void loadtable() {
//        ObservableList<Panier> list = FXCollections.observableArrayList();
//        try {
//            collnomact.setCellValueFactory(new PropertyValueFactory<>("nom_activite"));
//            collprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
//            collquantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
//            colltotal.setCellValueFactory(new PropertyValueFactory<>("total"));
//            PanierCRUD rt = new PanierCRUD();
//            List old = rt.listPanierbyid();
//            list.addAll(old);
//            tvpanier.setItems(list);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
}
        
    

  
    

