package lk.ijse.hostel.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lk.ijse.hostel.bo.BOFactory;
import lk.ijse.hostel.bo.custom.StudentBO;
import lk.ijse.hostel.bo.custom.UserBO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.UserDTO;

import java.util.List;

public class UserAccController {

    public TextField txtUserName;
    public TextField txtEmail;
    public TextField txtUserId;
    public PasswordField txtPass;
    public PasswordField txtRePass;
    private UserBO userBO = (UserBO) BOFactory.getBO (BOFactory.BOTypes.USER);

    public void onActionCreateAccount(ActionEvent actionEvent) {

        String pass=txtPass.getText ();
        String rePass=txtRePass.getText ();
        String userId = txtUserId.getText ();
        String userName = txtUserName.getText ();

        List <UserDTO>allRoom = userBO.loadAll ();

        for (UserDTO u : allRoom) {
            if (!(u.getUserId ().equals (userId))){
                if (pass.equals (rePass)){
                    userBO.saveUser (new UserDTO (
                            userId,
                            userName,
                            pass
                    ));
                    new Alert (Alert.AlertType.CONFIRMATION, "USER ACCOUNT CREATED SUCCUSS").show ();

                }else {
                    new Alert (Alert.AlertType.ERROR, "Check your Password and Try Again").show ();
                }
            }else{
                new Alert (Alert.AlertType.ERROR, "THIS USER ID ALREADY GET").show ();
            }
        }



    }
}
