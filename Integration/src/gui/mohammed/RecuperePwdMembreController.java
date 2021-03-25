package gui.mohammed;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.MembreServices;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author mohamedbouslah
 */
public class RecuperePwdMembreController implements Initializable {

    @FXML
    private TextField tf_cin_recu_pwd;
    @FXML
    private TextField tf_nv_pwd;
    @FXML
    private Button bt_verif_cin_pwd;
    @FXML
    private TextField tf_code_conf;
    @FXML
    private Button bt_conf_new_pass;
    @FXML
    private Button bt_retour_Login;

    Connection cnx = DataSource.getInstance().getCnx();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    MembreServices ms = new MembreServices();

    public static int generateRandomIntIntRange() {
        Random r = new Random();
        return r.nextInt((1000 - 50) + 1) + 50;
    }
    int x = generateRandomIntIntRange();

    @FXML
    private void retour_Login(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/mohammed/Login.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void verifier_cin(ActionEvent event) {
        String cin = tf_cin_recu_pwd.getText();
        int cinn = Integer.parseInt(cin);
        if (ms.RecupPwd(cinn) == 1) {

            //pour afficher le code generateur dans notif
            String str = Integer.toString(x);

            TrayNotification tray = null;
            tray = new TrayNotification("bien", str, NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(5));

        } else {
            TrayNotification tray = null;
            tray = new TrayNotification("Error", "Cin introuvable  ", NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
        }
    }

    @FXML
    private void conf_pwd(ActionEvent event) throws SQLException {
        int code = x;
        String codeF = tf_code_conf.getText();
        int cf = (Integer.parseInt(codeF));
        System.out.println(code);

        if (cf == code) {
            System.out.println("dkhal");
            String nvmdp = tf_nv_pwd.getText();
            String requete = "UPDATE membre SET password='" + nvmdp + "' WHERE cin = '" + tf_cin_recu_pwd.getText() + "'";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.executeUpdate();

        }
    }

}
