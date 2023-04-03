package lk.ijse.hostel.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.hostel.bo.BOFactory;
import lk.ijse.hostel.bo.custom.StudentBO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class StudentManageController implements Initializable {
    public ComboBox cmbGender;
    public DatePicker txtDate;
    public TextField txtContact;
    public TextField txtAdress;
    public TextField txtstName;
    public TextField txtstId;
    public TableColumn colGender;
    public TableColumn colDob;
    public TableColumn colContact;
    public TableColumn colAddress;
    public TableColumn colStName;
    public TableColumn colstId;
    public TableView tblStudent;

    private Session session;
    private StudentBO studentBO = (StudentBO) BOFactory.getBO (BOFactory.BOTypes.STUDENT);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setGender ();
        setTableStudent ();
        loadAllStudent ();
    }

    public void onActionDelete(ActionEvent actionEvent) {
        String dob = String.valueOf (txtDate.getValue ());
        String gender = cmbGender.getValue ().toString ();
        StudentDTO studentDTO = new StudentDTO (txtstId.getText (), txtstName.getText (), txtAdress.getText (), txtContact.getText (), dob, gender);

        boolean isDeleted=studentBO.deleteStudent (studentDTO);

        if (isDeleted){
            new Alert (Alert.AlertType.INFORMATION, "Student Delete Succuss").show ();
            tblStudent.getItems ().clear ();
            clearData ();
            loadAllStudent ();
        }else{
            new Alert (Alert.AlertType.ERROR, "Something went Wrong").show ();
        }

    }

    public void onActionUpdate(ActionEvent actionEvent) {
        String dob = String.valueOf (txtDate.getValue ());
        String gender = cmbGender.getValue ().toString ();
        StudentDTO studentDTO = new StudentDTO (txtstId.getText (), txtstName.getText (), txtAdress.getText (), txtContact.getText (), dob, gender);

        boolean isUpdate=studentBO.updateStudent (studentDTO);

        if (isUpdate){
            new Alert (Alert.AlertType.INFORMATION, "Student Update Succuss").show ();
            tblStudent.getItems ().clear ();
            clearData ();
            loadAllStudent ();
        }else {
            new Alert (Alert.AlertType.ERROR, "Something went Wrong").show ();
        }
    }

    public void onActionSave(ActionEvent actionEvent) {
        String dob = String.valueOf (txtDate.getValue ());
        String gender = cmbGender.getValue ().toString ();
        StudentDTO studentDTO = new StudentDTO (txtstId.getText (), txtstName.getText (), txtAdress.getText (), txtContact.getText (), dob, gender);

        List<StudentDTO> allStudents = studentBO.loadAll ();
        for (StudentDTO s : allStudents) {
            if (s.getStId ().equals (txtstId.getText ())) {
                new Alert (Alert.AlertType.ERROR, "This ID Already Have").show ();
                break;
            } else {
                boolean isSaved = studentBO.saveStudent (studentDTO);
                new Alert (Alert.AlertType.CONFIRMATION, "Student saved").show ();
                tblStudent.getItems ().clear ();
                clearData ();
                loadAllStudent ();
            }
        }
    }

    public void setGender() {
        ObservableList<String> data = FXCollections.observableArrayList ("Male", "Female", "Other");
        cmbGender.setItems (data);
    }

    public void OnActionMouseClicked(MouseEvent mouseEvent) {
        int index = tblStudent.getSelectionModel ().getSelectedIndex ();
        String stId = colstId.getCellData (index).toString ();//select Column value

        try {
            StudentDTO dto = studentBO.getStudent (stId);
            txtstId.setText (dto.getStId ());
            txtstName.setText (dto.getStName ());
            txtAdress.setText (dto.getAddress ());
            txtContact.setText (dto.getContact ());
            txtDate.setValue (LocalDate.parse (dto.getDob ()));
            cmbGender.setValue (dto.getGender ());
        } catch (Exception e) {
            System.out.println (e);
        }

    }

    public void setTableStudent() {
        colstId.setCellValueFactory (new PropertyValueFactory<> ("stId"));
        colStName.setCellValueFactory (new PropertyValueFactory<> ("stName"));
        colAddress.setCellValueFactory (new PropertyValueFactory<> ("address"));
        colDob.setCellValueFactory (new PropertyValueFactory<> ("dob"));
        colContact.setCellValueFactory (new PropertyValueFactory<> ("contact"));
        colGender.setCellValueFactory (new PropertyValueFactory<> ("gender"));
    }

    public void clearData() {
        txtstId.clear ();
        txtstName.clear ();
        txtAdress.clear ();
        txtContact.clear ();
        txtDate.setValue (null);
        cmbGender.setValue (null);
    }

    public void loadAllStudent() {

        try {
            List allStudents = studentBO.loadAll ();
            ObservableList observableList = FXCollections.observableArrayList (allStudents);
            tblStudent.setItems (observableList);

        } catch (Exception e) {
            System.out.println (e);
        }
    }

}

