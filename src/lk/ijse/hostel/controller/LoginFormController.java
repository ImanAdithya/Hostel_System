package lk.ijse.hostel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hostel.util.Notification;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

    public AnchorPane LoginPane;
    public TextField txtUName;
    public PasswordField txtPass;
    public static String userName="admin";
    public static String password="1234";
    public ImageView imgClosed;
    public ImageView imgOpen;
    public TextField txtPassShow;
    public ImageView imgEyeClosed;


    public void btnSign(ActionEvent actionEvent) throws IOException {
        if (txtUName.getText ().equals (userName) && txtPass.getText ().equals (password)){
            Stage stage= (Stage) LoginPane.getScene ().getWindow ();
            stage.setScene(new Scene (FXMLLoader.load(getClass().getResource("/lk/ijse/hostel/view/MainForm.fxml"))));

            Notification.notification ("Login Succussfully");
        }
    }

    public void OnClickEyeOpen(MouseEvent mouseEvent) {
        imgEyeClosed.setVisible (true);
        txtPassShow.setVisible (true);
        txtPassShow.setText (txtPass.getText ());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imgEyeClosed.setVisible (false);
        imgOpen.setVisible (true);
        txtPass.setVisible (true);
        txtPassShow.setVisible (false);
    }

    public void eyeClosedOnAction(MouseEvent mouseEvent) {
        imgEyeClosed.setVisible (false);
        imgOpen.setVisible (true);
        txtPassShow.setVisible (false);
        txtPass.setVisible (true);

    }
}
