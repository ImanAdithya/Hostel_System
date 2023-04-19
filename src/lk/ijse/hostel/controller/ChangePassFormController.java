package lk.ijse.hostel.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.hostel.bo.BOFactory;
import lk.ijse.hostel.bo.custom.UserBO;
import lk.ijse.hostel.dto.UserDTO;
import lk.ijse.hostel.util.GenarateCode;
import lk.ijse.hostel.util.SendMail;

public class ChangePassFormController {

    public TextField txtVerfyCode;
    public TextField txtNewPass;
    public TextField txtPass;
    public TextField txtEmail;
    public TextField txtUserName;
    public TextField txtUserId;
    public  String verifyCode;
    private UserBO userBO = (UserBO) BOFactory.getBO (BOFactory.BOTypes.USER);

    public void onActionChangePass(ActionEvent actionEvent) {
        String textCode=txtVerfyCode.getText ();
        String userId=txtUserId.getText ();
        String userName=txtUserName.getText ();
        String pass=txtPass.getText ();
        String newPass=txtNewPass.getText ();


        if (textCode.equals (verifyCode)){
            userBO.updateUser (new UserDTO (userId,userName,newPass));
            new Alert (Alert.AlertType.INFORMATION, "YOUR PASSWORD CHANGED SUCCUSS").show ();
            clearFeild ();
        }else {
            new Alert (Alert.AlertType.WARNING, "CHECK YOUR VERIFY CODE").show ();
        }
    }

    public void onActionSubmit(ActionEvent actionEvent) {
        String userId=txtUserId.getText ();
        String userName=txtUserName.getText ();
        String pass=txtPass.getText ();
        String email=txtEmail.getText ();
        String verfiytext="Your Password Verification Code";
        try {
            UserDTO u = userBO.getUser (userId);
            if (userName.equals (u.getUserName ()) && pass.equals (u.getPassword ())){
                new Alert (Alert.AlertType.INFORMATION, "CHECK YOUR EMAIL INBOX").show ();
                verifyCode = String.valueOf (GenarateCode.verifyCode ());
                SendMail.outMail (verifyCode,email,verfiytext);
                clearFeild ();
            }else{
                new Alert (Alert.AlertType.ERROR, "INVALID USER DETAILS..TRY AGAIN").show ();
                clearFeild ();
            }
        } catch (Exception e) {
            new Alert (Alert.AlertType.ERROR, "INVALID USER DETAILS..TRY AGAIN").show ();
            clearFeild ();
            System.out.println (e);
        }
    }

    public void clearFeild(){
        txtUserId.clear ();
        txtUserName.clear ();
        txtEmail.clear ();
        txtPass.clear ();
        txtVerfyCode.clear ();
        txtNewPass.clear ();
    }
}
