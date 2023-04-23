package lk.ijse.hostel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lk.ijse.hostel.bo.BOFactory;
import lk.ijse.hostel.bo.custom.StudentBO;
import lk.ijse.hostel.bo.custom.UserBO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.UserDTO;
import lk.ijse.hostel.util.SendMail;
import lk.ijse.hostel.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.mail.MessagingException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserAccController implements Initializable {

    public TextField txtUserName;
    public TextField txtEmail;
    public TextField txtUserId;
    public PasswordField txtPass;
    public PasswordField txtRePass;
    private UserBO userBO = (UserBO) BOFactory.getBO (BOFactory.BOTypes.USER);

    public void onActionCreateAccount(ActionEvent actionEvent) throws MessagingException {

        String pass=txtPass.getText ();
        String rePass=txtRePass.getText ();
        String userId = txtUserId.getText ();
        String userName = txtUserName.getText ();
        String email=txtEmail.getText ();

        /*List <UserDTO>allRoom = userBO.loadAll ();

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
        */

       if (checkDuplidate ()){
          if (checkValidation ()){
              if(pass.equals (rePass)){
                  userBO.saveUser (new UserDTO (
                          userId,
                          userName,
                          pass
                  ));
                  new Alert (Alert.AlertType.CONFIRMATION, "USER ACCOUNT CREATED SUCCUSS").show ();
                  SendMail.outMail ("YOU ARE USER IN D24HOSTEL SYSTEM",email,"D24HOSTEL");
                  clearFeilds ();
                  setUserId ();
              }else {
                  new Alert (Alert.AlertType.ERROR, "Check your Password and Try Again").show ();
              }
          }
       }else{
           new Alert (Alert.AlertType.ERROR, "THIS USER ID ALREADY GET").show ();
           clearFeilds ();
       }




    }

    public boolean checkDuplidate(){
        String userId=txtUserId.getText ();
        List <UserDTO>allRoom = userBO.loadAll ();
        for (UserDTO u : allRoom) {
            if (userId.equals (u.getUserId ())){
                return false;
            }
        }
        return  true;
    }

    public void clearFeilds(){
        txtRePass.clear ();
        txtEmail.clear ();
        txtPass.clear ();
        txtUserId.clear ();
        txtUserName.clear ();
    }
    public String nextUserID() {
        Session session = SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction = session.beginTransaction ();

        Query query = session.createQuery ("select userId from User order by userId desc");

        String nextId = "U001";

        if (query.list ().size () == 0) {
            return nextId;
        } else {
            String id = (String) query.list ().get (0);

            String[] SUs = id.split ("U00");

            for (String a : SUs) {
                id = a;
            }

            int idNum = Integer.valueOf (id);

            id = "U00" + (idNum + 1);

            transaction.commit ();
            session.close ();

            return id;
        }
    }

    public void setUserId(){
        String userID=nextUserID ();
        txtUserId.setText (userID);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUserId ();
    }
    private boolean checkValidation() {
        String email=txtEmail.getText ();

        if (!email.matches("^([a-z0-9]{2,})([@])([a-z]{2,9})([.])([a-z]{2,})$")) {
            new Alert (Alert.AlertType.ERROR, "INVALID EMAIL ADDRESS").show ();
            txtEmail.requestFocus ();
            return false;
        } else {
            return true;
        }



    }

}
