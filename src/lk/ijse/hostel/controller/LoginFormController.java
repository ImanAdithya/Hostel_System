package lk.ijse.hostel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hostel.util.Notification;

import java.io.IOException;

public class LoginFormController {

    public AnchorPane LoginPane;

    public TextField txtUName;
    public PasswordField txtPass;
    public static String userName="admin";
    public static String password="1234";



    public void btnSign(ActionEvent actionEvent) throws IOException {
        if (txtUName.getText ().equals (userName) && txtPass.getText ().equals (password)){
            Stage stage= (Stage) LoginPane.getScene ().getWindow ();
            stage.setScene(new Scene (FXMLLoader.load(getClass().getResource("/lk/ijse/hostel/view/MainForm.fxml"))));

            Notification.notification ("Login Succussfully");
        }
    }
}
