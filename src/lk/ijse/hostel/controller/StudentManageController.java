package lk.ijse.hostel.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentManageController implements Initializable {
    public ComboBox cmbGender;
    public DatePicker txtDate;
    public TextField txtContact;
    public TextField txtAdress;
    public TextField txtstName;
    public TextField txtstId;

    private Session session;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setGender ();
    }

    public void onActionDelete(ActionEvent actionEvent) {
    }

    public void onActionUpdate(ActionEvent actionEvent) {
    }

    public void onActionSave(ActionEvent actionEvent) {
        String dob= String.valueOf (txtDate.getValue ());
        String gender=cmbGender.getValue ().toString ();
        System.out.println (dob);
        System.out.println (gender);
        StudentDTO studentDTO = new StudentDTO (txtstId.getText (), txtstName.getText (), txtAdress.getText (), txtContact.getText (), dob, gender);


    }

    public void setGender(){
        ObservableList<String> data = FXCollections.observableArrayList("Male", "Female", "Other");
        cmbGender.setItems(data);
    }
}
