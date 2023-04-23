package lk.ijse.hostel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hostel.bo.BOFactory;
import lk.ijse.hostel.bo.custom.UserBO;
import lk.ijse.hostel.dto.UserDTO;
import lk.ijse.hostel.util.Notification;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

    public AnchorPane LoginPane;
    public TextField txtUName;
    public PasswordField txtPass;


    public ImageView imgClosed;
    public ImageView imgOpen;
    public TextField txtPassShow;
    public ImageView imgEyeClosed;
    private UserBO userBO = (UserBO) BOFactory.getBO (BOFactory.BOTypes.USER);


    public void btnSign(ActionEvent actionEvent) throws IOException {

        if (checkUserDetail ()){

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

    public boolean checkUserDetail(){
        String userName = txtUName.getText ();
        String pass=txtPass.getText ();

        List<UserDTO> userList = userBO.loadAll ();

        for (UserDTO dto : userList) {
            if(dto.getUserName ().equals (userName) && dto.getPassword ().equals (pass)){
                return true;
            }
        }
        return false;
    }

    public void CreatteACCMouseClick(MouseEvent mouseEvent) throws IOException {

      /*  Stage stage= (Stage) LoginPane.getScene ().getWindow ();
        stage.setScene(new Scene (FXMLLoader.load(getClass().getResource("/lk/ijse/hostel/view/MainAdminForm.fxml"))));


*/
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/lk/ijse/hostel/view/MainAdminForm.fxml"));
        Parent parent=fxmlLoader.load();
        Stage stage=new Stage();
        stage.setScene(new Scene(parent));
        stage.show();


    }
}
