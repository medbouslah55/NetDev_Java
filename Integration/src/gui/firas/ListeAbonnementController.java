/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.firas;

import models.Abonnement;
import services.AbonnementCRUD;
import utils.DataSource;
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
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import javafx.util.Duration;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import org.apache.poi.ss.usermodel.Row;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class ListeAbonnementController implements Initializable {

    @FXML
    private TextField tftitre;
    @FXML
    private TextField tftype;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tfdescription;
    @FXML
    private FontAwesomeIconView btnactualiser;
    @FXML
    private Button btnback;
    @FXML
    private TableView<Abonnement> coltabab;
    @FXML
    private TableColumn<?, ?> colti;
    @FXML
    private TableColumn<?, ?> colty;
    @FXML
    private TableColumn<?, ?> colp;
    @FXML
    private TableColumn<Abonnement, String> cold;
    @FXML
    private TableColumn<Abonnement, String> colediter;
    @FXML
    private MaterialDesignIconView closeButton;
    @FXML
    private Button btnimprimer;
    @FXML
    private ComboBox<String> combotrie;
    private JFXTextField tfrechre;
    ObservableList<Abonnement> list = FXCollections.observableArrayList();
    ObservableList<String> listeTrie = FXCollections.observableArrayList("Tout", "Titre", "Prix", "Type");
    ObservableList<String> listeexport = FXCollections.observableArrayList("Type De Fichier", "PDF", "EXCEL");
    @FXML
    private ComboBox<String> comboexporter;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combotrie.setItems(listeTrie);
        combotrie.setValue("Tout");
        comboexporter.setItems(listeexport);
        comboexporter.setValue("Type De Fichier");
        loadDate22();
        //dynamqierecherche();

    }

    @FXML
    private void loadDate() {
        ObservableList<Abonnement> abList = FXCollections.observableArrayList();
        colti.setCellValueFactory(new PropertyValueFactory<>("Titre_ab"));
        colty.setCellValueFactory(new PropertyValueFactory<>("Type_ab"));
        colp.setCellValueFactory(new PropertyValueFactory<>("prix_ab"));
        cold.setCellValueFactory(new PropertyValueFactory<>("desc_ab"));
        AbonnementCRUD rt = new AbonnementCRUD();
        List old = rt.listAbonnement();
        abList.addAll(old);
        coltabab.setItems(abList);
        //coltabab.refresh();
    }

    @FXML
    private void selection(MouseEvent event) {
        Abonnement test = (Abonnement) coltabab.getSelectionModel().getSelectedItem();
        tftitre.setText(test.getTitre_ab());
        tftype.setText(test.getType_ab());
        tfprix.setText(Float.toString(test.getPrix_ab()));
        tfdescription.setText(test.getDesc_ab());

    }

    private void ModifierAB(ActionEvent event) throws IOException {
        try {
            //selection(event);
            Abonnement test = (Abonnement) coltabab.getSelectionModel().getSelectedItem();
            AbonnementCRUD rt = new AbonnementCRUD();
            rt.modifierAbonnement(new Abonnement(tftitre.getText(), tftype.getText(), Float.parseFloat(tfprix.getText()), tfdescription.getText()), test.getId_ab());
            loadDate();
            tftitre.clear();
            tftype.clear();
            tfprix.clear();
            tfdescription.clear();
            notifSUCCESS("Votre Abonnement à éte modifier avec suceés");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void SupprimerAB(ActionEvent event) throws SQLException {
        Abonnement test = (Abonnement) coltabab.getSelectionModel().getSelectedItem();
        AbonnementCRUD rt = new AbonnementCRUD();
        rt.supprimerAbonnementById(test.getId_ab());
        loadDate();
        notifSUCCESS("Votre Abonnement à éte supprimer avec suceés");
    }

    @FXML
    private void backtomenu(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/firas/MenuGeneralFiras.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void printRec(ActionEvent event) throws FileNotFoundException {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            Window primaryStage = null;
            job.showPrintDialog(primaryStage);
            Node root = this.coltabab;
            job.printPage(root);
            job.endJob();
        }
        notifSUCCESS("Document Imprimé");
    }

    private void notifSUCCESS(String message) {
        String title = "Congratulations";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(3));
    }

    @FXML
    private void cherchertous() {
        AbonnementCRUD rt = new AbonnementCRUD();
        List arr = rt.TrierAbonnementTous(combotrie.getValue());
        ObservableList obb = FXCollections.observableArrayList(arr);
        coltabab.setItems(obb);
    }

    @FXML
    private void extarcter() throws SQLException, DocumentException, IOException {
        if (comboexporter.getValue().contentEquals("PDF") == true) {
            CreatePDF();
        }
        if (comboexporter.getValue().contentEquals("EXCEL") == true) {
            createEXCEL();
        }
    }

    private void CreatePDF() throws SQLException, DocumentException {
        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\trabe\\Desktop\\Integration\\ListeTypeAbonnement.pdf"));

            doc.open();
            doc.add(new Paragraph(" "));
            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 28, Font.UNDERLINE, BaseColor.RED);
            Paragraph p = new Paragraph("Les Type Abonnements En PDF", font);
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            PdfPTable tabpdf = new PdfPTable(4);
            tabpdf.setWidthPercentage(100);
            PdfPCell cell;
            cell = new PdfPCell(new Phrase("Titre", FontFactory.getFont("Times New Roman", 15)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setBorderColor(BaseColor.RED);
            cell.setBorderWidth(2);
            tabpdf.addCell(cell);
            cell = new PdfPCell(new Phrase("Type", FontFactory.getFont("Times New Roman", 15)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setBorderColor(BaseColor.RED);
            cell.setBorderWidth(2);
            tabpdf.addCell(cell);

            cell = new PdfPCell(new Phrase("Prix", FontFactory.getFont("Times New Roman", 15)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setBorderColor(BaseColor.RED);
            cell.setBorderWidth(2);
            tabpdf.addCell(cell);

            cell = new PdfPCell(new Phrase("Description", FontFactory.getFont("Times New Roman", 15)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setBorderColor(BaseColor.RED);
            cell.setBorderWidth(2);
            tabpdf.addCell(cell);

            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery("Select * from abonnement");
            while (rs.next()) {
                cell = new PdfPCell(new Phrase(rs.getString("titre"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);
                cell = new PdfPCell(new Phrase(rs.getString("type"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);
                cell = new PdfPCell(new Phrase(rs.getString("prix"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);
                cell = new PdfPCell(new Phrase(rs.getString("descr_ab"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);
            }
            doc.add(tabpdf);
            notifSUCCESS("Donneés Extraire Sous Format PDF");
            doc.close();
            Desktop.getDesktop().open(new File("C:\\Users\\trabe\\Desktop\\Integration\\ListeTypeAbonnement.pdf"));

        } catch (DocumentException | HeadlessException | IOException e) {
            System.out.println("ERROR PDF");
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());
        }
    }

    private void Excel(File file) throws FileNotFoundException, IOException {
        try {
            FileOutputStream fileOut;
            fileOut = new FileOutputStream(file);
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet workSheet = workbook.createSheet("sheet 0");

            workSheet.setColumnWidth(1, 25);

            HSSFFont fontBold = workbook.createFont();
            fontBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            HSSFCellStyle styleBold = workbook.createCellStyle();
            styleBold.setFont(fontBold);

            Row row1 = workSheet.createRow((short) 0);
            workSheet.autoSizeColumn(7);
            row1.createCell(0).setCellValue("Titre");
            row1.createCell(1).setCellValue("Type");
            row1.createCell(2).setCellValue("Prix");
            row1.createCell(3).setCellValue("Description");
            Row row2;

            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery("Select * from abonnement");
            while (rs.next()) {
                int a = rs.getRow();
                row2 = workSheet.createRow((short) a);

                row2.createCell(0).setCellValue(rs.getString(2));
                row2.createCell(1).setCellValue(rs.getString(3));
                row2.createCell(2).setCellValue(rs.getString(4));
                row2.createCell(3).setCellValue(rs.getString(5));
            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("controllers.CommandeBack.ExcelAction()");
        }
    }

    private void createEXCEL() throws IOException {
        FileChooser chooser = new FileChooser();
        // Set extension filter
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Excel Files(.xls)", ".xls");
        chooser.getExtensionFilters().add(filter);
        // Show save dialog
        File file = chooser.showSaveDialog(comboexporter.getScene().getWindow());
        if (file != null) {
            Excel(file);
            notifSUCCESS("Donneés Extraire Sous Format EXCEL");
        }
    }

    private void loadDate22() {
        try {
            colti.setCellValueFactory(new PropertyValueFactory<>("Titre_ab"));
            colty.setCellValueFactory(new PropertyValueFactory<>("Type_ab"));
            colp.setCellValueFactory(new PropertyValueFactory<>("prix_ab"));
            cold.setCellValueFactory(new PropertyValueFactory<>("desc_ab"));
            AbonnementCRUD rt = new AbonnementCRUD();
            List old = rt.listAbonnement();
            list.addAll(old);

            //add cell of button edit 
            Callback<TableColumn<Abonnement, String>, TableCell<Abonnement, String>> cellFoctory = (TableColumn<Abonnement, String> param) -> {
                // make cell containing buttons
                final TableCell<Abonnement, String> cell = new TableCell<Abonnement, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        //that cell created only on non-empty rows
                        if (empty) {
                            setGraphic(null);
                            setText(null);

                        } else {

                            FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                            FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                            deleteIcon.setStyle(
                                    " -fx-cursor: hand ;"
                                    + "-glyph-size:28px;"
                                    + "-fx-fill:#ff1744;"
                            );
                            editIcon.setStyle(
                                    " -fx-cursor: hand ;"
                                    + "-glyph-size:28px;"
                                    + "-fx-fill:#00E676;"
                            );
                            deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                                Abonnement test = (Abonnement) coltabab.getSelectionModel().getSelectedItem();
                                AbonnementCRUD rt = new AbonnementCRUD();
                                rt.supprimerAbonnementById(test.getId_ab());
                                loadDate();
                                notifSUCCESS("Votre Abonnement à éte supprimer avec suceés");
                            });
                            editIcon.setOnMouseClicked((MouseEvent event) -> {
                                Abonnement test = (Abonnement) coltabab.getSelectionModel().getSelectedItem();
                                AbonnementCRUD rt = new AbonnementCRUD();
                                rt.modifierAbonnement(new Abonnement(tftitre.getText(), tftype.getText(), Float.parseFloat(tfprix.getText()), tfdescription.getText()), test.getId_ab());
                                loadDate();
                                tftitre.clear();
                                tftype.clear();
                                tfprix.clear();
                                tfdescription.clear();
                                notifSUCCESS("Votre Abonnement à éte modifier avec suceés");

                            });

                            HBox managebtn = new HBox(editIcon, deleteIcon);
                            managebtn.setStyle("-fx-alignment:center");
                            HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                            HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                            setGraphic(managebtn);
                            setText(null);
                        }
                    }
                };
                return cell;
            };
            colediter.setCellFactory(cellFoctory);
            coltabab.setItems(list);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
