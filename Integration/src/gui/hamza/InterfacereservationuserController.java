/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.hamza;

import com.jfoenix.controls.JFXDatePicker;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import static gui.hamza.PaiementController.showAlert;
import models.Reservation;
import models.User;
import services.ReservationCRUD;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.DataSource;
import utils.UserSession;

/**
 * FXML Controller class
 *
 * @author HAMZA
 */
public class InterfacereservationuserController implements Initializable {

    //private TextField tfcin;
    @FXML
    private JFXDatePicker tfdate;
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
    //private Button tftridate;
    @FXML
    private Button btnactualiser;
    @FXML
    private TextField tfrecherche;
    ObservableList<Reservation> reslist = FXCollections.observableArrayList();
    @FXML
    private Button btnpdf;
    @FXML
    private TableColumn<Reservation, String> collnom;
    @FXML
    private TableColumn<Reservation, String> collprenom;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private MaterialDesignIconView closeButton;
    @FXML
    private ComboBox<String> typeRecherche;
    ObservableList<String> listeTypeRecherche = FXCollections.observableArrayList("Tout", "Nom", "Prenom");
    @FXML
    private ComboBox<String> typetri;
    ObservableList<String> listeTypetri = FXCollections.observableArrayList(" Tout", "Trie Date", "Trie Nom","Trie Prenom", "Trie Nombre places");
    @FXML
    private JFXDatePicker tfdeb;
    @FXML
    private JFXDatePicker tffin;
    @FXML
    private Button btnrecherchedate;
    @FXML
    private Button back;
     User member = UserSession.getInstance().getLoggedUser();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                        typeRecherche.setItems(listeTypeRecherche);
                        typeRecherche.setValue("Tout");
                        afficherreservations(); 
                        list();
                        typetri.setItems(listeTypetri);
                        typetri.setValue("Tout");
                        listtri();
                        
                //recherchee();
}    
//    public Connection getConnection(){
//        Connection conn;
//        try{
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pi", "root","");
//            return conn;
//        }catch(Exception ex){
//            System.out.println("Error: " + ex.getMessage());
//            return null;
//        }
//    }
//    
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
        collnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        collprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
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
            switch(controleDeSaisi()) {
                case 0 : showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Remplir les champs vides! ");break;
                case 1 : showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Verifiez le nom  !");break;
                case 2 : showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez le prenom! ");break;
                //case 3 : showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez le cin! ");break;
                case 4 : showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez le nombre de place! ");break;
                default : 
        Reservation test = (Reservation) tvreservations.getSelectionModel().getSelectedItem();
        ReservationCRUD r = new ReservationCRUD();
        //int j=Integer.parseInt(tfcin.getText());
        int k=Integer.parseInt(tfnbrplace.getText());
        r.modifierreservation(new Reservation(test.getId_reservation(),tfnom.getText(), tfprenom.getText(),Date.valueOf(tfdate.getValue()),k));
        //JOptionPane.showMessageDialog(null,"Reservation modifieé");
        tfnom.clear();
        tfprenom.clear();
        //tfcin.clear();
        tfdate.getEditor().clear();
        tfnbrplace.clear();
        afficherreservations();
        tvreservations.refresh();
        notifsuccess("Reservation modifier avec succes");
    }}
    private void supprimerreservation() throws SQLException{
        Reservation test = (Reservation) tvreservations.getSelectionModel().getSelectedItem();
        ReservationCRUD res = new ReservationCRUD();
        res.delete(test.getId_reservation());
        //JOptionPane.showMessageDialog(null,"Reservation supprimeé");
        tvreservations.refresh();
        afficherreservations();
        notifsuccess("Reservation supprimer avec succes");
        
    }
        // TODO
        
@FXML
      private void selection (MouseEvent event){
    Reservation test = (Reservation) tvreservations.getSelectionModel().getSelectedItem();
    tfnom.setText(test.getNom());
    tfprenom.setText(test.getPrenom());
   // tfcin.setText(Integer.toString(test.getCin_membre()));
    //tfdate.getValue(test.getDate_act());
    String d1= test.getDate_act().toString();
    LocalDate ss = LocalDate.parse(d1);
    tfdate.setValue(ss);
    //tfdate.setValue(LocalDate.of(test.getDate_act().getYear(), test.getDate_act().getMonth(), test.getDate_act().getDay()));
    tfnbrplace.setText(Integer.toString(test.getNb_place()));

}
    @FXML
        private void ButtonAction(ActionEvent event) throws SQLException, IOException {
        if(event.getSource() == btnajouter){
        Stage window = primarystage;
        //Parent rootRec2 = FXMLLoader.load(getClass().getResource("ajouterreservationuser.fxml"));
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
        }else if(event.getSource() == btnrecherchedate){
            datedebutfin();
        }
        
//        else if(event.getSource() == tfrecherche){
//            recherchee();
//        }
        else if(event.getSource() == btnactualiser){
            actualiser();
        }
        else if(event.getSource() == btnpdf){
            BPDF();
        }
    }
    public void trierDate(){
        ObservableList<Reservation> reslist = FXCollections.observableArrayList();
        collnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        collprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
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
            String value9 = tfrecherche.getText();
            PreparedStatement ps = DataSource.getInstance().getCnx().prepareStatement("select * from Reservation where cin_membre Like'"+value9+"'");
            ResultSet rs5 = ps.executeQuery();  
            while (rs5.next()){   
            reslist.add(new Reservation(rs5.getInt(1),rs5.getString(2),rs5.getString(3),rs5.getInt(4),rs5.getDate(5),rs5.getInt(6)) );               
            }
         collnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         collprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         collcin.setCellValueFactory(new PropertyValueFactory<>("cin_membre"));
         colldate.setCellValueFactory(new PropertyValueFactory<>("date_act"));
         collnbrplace.setCellValueFactory(new PropertyValueFactory<>("nb_place"));
         tvreservations.setItems( reslist);  
         tfrecherche.clear();
    }
         
         
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
    public void actualiser(){
         reslist.clear();
       afficherreservations();
       tvreservations.refresh(); 
        notifsuccess("Liste acualiser");
    }
    private void recherchee(){
               FilteredList<Reservation> filteredData = new FilteredList<>(reslist, b -> true);
		// 2. Set the filter Predicate whenever the filter changes.
                ReservationCRUD rt = new ReservationCRUD();
                List old = rt.trierreservationdate();
                reslist.addAll(old);
                tfrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(rech -> {
				// If filter text is empty, display all persons.			
                if (newValue == null || newValue.isEmpty()) {
                return true;
                }
				// Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
		if (String.valueOf(rech.getCin_membre()).indexOf(lowerCaseFilter)!=-1)
		return true;
		else  
		return false; // Does not match.
			});
		});
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Reservation> sortedData = new SortedList<>(filteredData);
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tvreservations.comparatorProperty());
		// 5. Add sorted (and filtered) data to the table.
		tvreservations.setItems(sortedData);
    }   

    @FXML
    private void close(MouseEvent event) {
         // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    
    @FXML
    private void list() {
        
        ReservationCRUD rt = new ReservationCRUD();
            //loadDate();
            List arr = rt.chercherReservation(typeRecherche.getValue(),tfrecherche.getText());
            ObservableList obb = FXCollections.observableArrayList(arr);
            tvreservations.setItems(obb);
        
    }
    @FXML
     private void listtri() {
        
        ReservationCRUD rt = new ReservationCRUD();
            //loadDate();
            List arr = rt.trier(typetri.getValue());
            ObservableList obb = FXCollections.observableArrayList(arr);
            tvreservations.setItems(obb);
        notifsuccess("Les messages sont triees selon "+typetri.getValue());
    }
//@FXML
//    private void sendMail(){
//        String to = "razor3116@gmail.com";
//        String from = "minds8737@gmail.com";
//        String host = "smtp.gmail.com";
//        final String username = "minds8737@gmail.com";
//        final String password ="mindspace123456";
//        ////SETUP SERVER
//        Properties props = System.getProperties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.port", "587");
//    try{
//            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, password);
//            }
//        });
//            //create mail
//            MimeMessage m = new MimeMessage(session);
//            m.setFrom(new InternetAddress(from));
//            m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
//            m.setSubject("Reservation Ajouter");
//            m.setText("Votre Reservation est ajoutee avec succes");
//            //send mail
//            Transport.send(m);
//            //sentBoolValue.setVisible(true);
//            System.out.println("Message sent!");
//        }   catch (MessagingException e){
//            e.printStackTrace();
//        }
//    }
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
        public void datedebutfin(){
        ObservableList<Reservation> resList = FXCollections.observableArrayList();
        collnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        collprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colldate.setCellValueFactory(new PropertyValueFactory<>("date_act"));
        collcin.setCellValueFactory(new PropertyValueFactory<>("cin_membre"));
        collnbrplace.setCellValueFactory(new PropertyValueFactory<>("nb_place"));
        ReservationCRUD rt = new ReservationCRUD();
        List old = rt.chercherReservationPeriode(Date.valueOf(tfdeb.getValue()), Date.valueOf(tffin.getValue()));
        resList.addAll(old);
        tvreservations.setItems(resList);
        tvreservations.refresh();
        tfdeb.getEditor().clear();
        tffin.getEditor().clear();
        }
                    private int controleDeSaisi() {  
                 
        if (tfnom.getText().isEmpty() || tfprenom.getText().isEmpty() 
                || tfnbrplace.getText().isEmpty()) {
            
            return 0;
        } else 
            
            if (!Pattern.matches("[a-zA-Z]*", tfnom.getText())) {
                
                tfnom.requestFocus();
                tfnom.selectEnd();
                return 1;
            }
            
           else if (!Pattern.matches("[a-zA-Z]*", tfprenom.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le prenom! ");
                tfprenom.requestFocus();
                tfprenom.selectEnd();
                return 2;
//            } else if (!Pattern.matches("[0-9]*", tfcin.getText())) {
//                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez la cin! ");
//                tfcin.requestFocus();
//                tfcin.selectEnd();
//                return 3;
            } else if (!Pattern.matches("[0-9]*", tfnbrplace.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le nombre de places ! ");
                tfnbrplace.requestFocus();
                tfnbrplace.selectEnd();
                 return 4;
            }
            else {return 5;
            }  
        
        
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Stage window = primarystage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("/gui/mohammed/MenuFront.fxml"));;
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }
}

    

    

