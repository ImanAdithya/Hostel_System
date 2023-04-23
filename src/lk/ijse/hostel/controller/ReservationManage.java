package lk.ijse.hostel.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.hostel.bo.BOFactory;
import lk.ijse.hostel.bo.custom.ReservationBO;

import lk.ijse.hostel.dto.ReservationDTO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.dto.tm.RevervationTm;
import lk.ijse.hostel.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;


public class ReservationManage implements Initializable {
    public TextField txtStName;
    public ComboBox cmbStatus;
    public ComboBox cmbROOMId;
    public ComboBox cmbSTId;
    @FXML
    private TextField txtResId;
    @FXML
    private TextField txtRoomType;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField cmbStname;

    @FXML
    private RadioButton rbnPayNow;

    @FXML
    private ToggleGroup pay;

    @FXML
    private RadioButton rbnPayLater;

    @FXML
    private TableView<RevervationTm> tblReservation;

    @FXML
    private TableColumn<?, ?> colResId;

    @FXML
    private TableColumn<?, ?> colStId;

    @FXML
    private TableColumn<?, ?> colStName;

    @FXML
    private TableColumn<?, ?> colRoomId;

    @FXML
    private TableColumn<?, ?> colRoomType;

    @FXML
    private TableColumn<?, ?> colStatus;

    private ReservationBO resBO = (ReservationBO) BOFactory.getBO (BOFactory.BOTypes.RESERVATION);
    // public static  ObservableList<CourseDTO> CourseIds=FXCollections.observableArrayList();
    public static ObservableList<StudentDTO> stIds = FXCollections.observableArrayList ();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setIds ();
        setData ();
        setStatus ();
        setCellValueFactory ();
        loadAllRes ();
        setResID ();


    }

    @FXML
    void onActionDeleteRes(ActionEvent event) {
        String stId = cmbSTId.getValue ().toString ();
        String roomID = cmbROOMId.getValue ().toString ();
        String status = cmbStatus.getValue ().toString ();
        String resId = txtResId.getText ();
        StudentDTO studentDTO = getStudnetDetail ();
        RoomDTO roomDTO = getRoomDetail ();
        java.sql.Date sqlDate = new java.sql.Date (Calendar.getInstance ().getTime ().getTime ());

        try {
            boolean isDelete = resBO.deleteReservation (
                    new ReservationDTO (
                            resId,
                            sqlDate,
                            studentDTO,
                            roomDTO,
                            status
                    ));
            if (isDelete) {
                RoomDTO room = getRoomDetail ();
                room.setQty (room.getQty () + 1);
                resBO.updateRoom (room);
                loadAllRes ();

            }
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

    public boolean checkDuplicate() {
        String resId = txtResId.getText ();
        List<ReservationDTO> list = resBO.loadAll ();

        for (ReservationDTO dto : list) {
            if (resId.equals (dto.getResID ())) {
                new Alert (Alert.AlertType.CONFIRMATION, "RESERVATION ADDED SUCCUSS").show ();
                return false;
            }
        }
        return true;
    }

    @FXML
    void onActionSaveRes(ActionEvent event) {
        StudentDTO studentDTO = getStudnetDetail ();
        RoomDTO roomDTO = getRoomDetail ();
        String resId = txtResId.getText ();
        String status = cmbStatus.getValue ().toString ();
        java.sql.Date sqlDate = new java.sql.Date (Calendar.getInstance ().getTime ().getTime ());

        if (checkDuplicate ()) {
            boolean isSaveReservation = resBO.saveReservation (
                    new ReservationDTO (
                            resId,
                            sqlDate,
                            studentDTO,
                            roomDTO,
                            status
                    ));
            if (isSaveReservation) {
                RoomDTO room = getRoomDetail ();

                System.out.println (room.getQty () - 1);
                room.setQty (room.getQty () - 1);
                resBO.updateRoom (room);
               // tblReservation.getItems ().clear ();
                loadAllRes ();
            }
        }

    }

    @FXML
    void onActionUpdateRes(ActionEvent event) {
        String stId = cmbSTId.getValue ().toString ();
        String roomID = cmbROOMId.getValue ().toString ();
        String status = cmbStatus.getValue ().toString ();
        String resId = txtResId.getText ();
        StudentDTO studentDTO = getStudnetDetail ();
        RoomDTO roomDTO = getRoomDetail ();
        java.sql.Date sqlDate = new java.sql.Date (Calendar.getInstance ().getTime ().getTime ());

        try {
            boolean isUpdate = resBO.updateReservation (
                    new ReservationDTO (
                            resId,
                            sqlDate,
                            studentDTO,
                            roomDTO,
                            status
                    ));
            loadAllRes ();
        } catch (Exception e) {
            e.printStackTrace ();
        }

    }

    private void setData() {
        cmbSTId.setOnAction (event -> {
            String stId = cmbSTId.getValue ().toString ();
            StudentDTO dto = resBO.getStudent (stId);
            txtStName.setText (dto.getStName ());
        });


        cmbROOMId.setOnAction (event -> {
            String roomId = cmbROOMId.getValue ().toString ();
            RoomDTO dto = resBO.getRoom (roomId);
            txtRoomType.setText (dto.getType ());
            txtQty.setText (String.valueOf (dto.getQty ()));
        });
    }

    public void setIds() {
        List<String> stIds = resBO.getStudentIds ();
        ObservableList student = FXCollections.observableArrayList (stIds);
        cmbSTId.setItems (student);

        List<String> roomIds = resBO.getRoomIds ();
        ObservableList room = FXCollections.observableArrayList (roomIds);
        cmbROOMId.setItems (room);

    }

    public void setStatus() {
        ObservableList<String> data = FXCollections.observableArrayList ("PAID", "UNPAID");
        cmbStatus.setItems (data);
    }

    public StudentDTO getStudnetDetail() {
        String stId = cmbSTId.getValue ().toString ();
        return resBO.getStudent (stId);
    }

    public RoomDTO getRoomDetail() {
        try {
            String roomId = cmbROOMId.getValue ().toString ();
            System.out.println ("This IS ROOM ID"+roomId);
            return resBO.getRoom (roomId);
        } catch (Exception e) {
            System.out.println (e);
        }
        return null;
    }

    public void setCellValueFactory() {
        colResId.setCellValueFactory (new PropertyValueFactory<> ("resId"));
        colStId.setCellValueFactory (new PropertyValueFactory<> ("stId"));
        colStName.setCellValueFactory (new PropertyValueFactory<> ("stName"));
        colRoomId.setCellValueFactory (new PropertyValueFactory<> ("roomId"));
        colRoomType.setCellValueFactory (new PropertyValueFactory<> ("roomName"));
        colStatus.setCellValueFactory (new PropertyValueFactory<> ("status"));

    }

    @FXML
    void onMouseClickReservation(MouseEvent event) {
        int index = tblReservation.getSelectionModel ().getSelectedIndex ();
        String resID = colResId.getCellData (index).toString ();//select Column value

        try {
            ReservationDTO dto = resBO.getRes (resID);
            System.out.println (dto.getResID ());
            txtResId.setText (dto.getResID ());
            txtRoomType.setText (dto.getRoomDTO ().getType ());
            txtStName.setText (dto.getStudentDTO ().getStName ());
            String stID = dto.getStudentDTO ().getStId ();
            cmbSTId.setValue (stID);
            cmbROOMId.setValue (dto.getRoomDTO ().getRoomID ());
            cmbStatus.setValue (dto.getStatus ());
            txtQty.setText (String.valueOf (dto.getRoomDTO ().getQty ()));
        } catch (Exception e) {
            System.out.println (e);
        }
    }


    public void loadAllRes() {

        try {
            List<ReservationDTO> allRoom = resBO.loadAll ();

            ObservableList<RevervationTm> resList = FXCollections.observableArrayList ();

            for (ReservationDTO dto : allRoom) {
                resList.add (new RevervationTm (
                        dto.getResID (),
                        dto.getStudentDTO ().getStId (),
                        dto.getStudentDTO ().getStName (),
                        dto.getRoomDTO ().getRoomID (),
                        dto.getRoomDTO ().getType (),
                        dto.getStatus ()
                ));
            }

            tblReservation.setItems (resList);

            System.out.println (resList);


        } catch (Exception e) {
            System.out.println (e);
        }
    }

    public String nextResId() {
        Session session = SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction = session.beginTransaction ();

        Query query = session.createQuery ("select resId from Reservation order by resId desc");

        String nextId = "T001";

        if (query.list ().size () == 0) {
            return nextId;
        } else {
            String id = (String) query.list ().get (0);

            String[] SUs = id.split ("T00");

            for (String a : SUs) {
                id = a;
            }

            int idNum = Integer.parseInt (id);

            id = "T00" + (idNum + 1);

            transaction.commit ();
            session.close ();

            return id;
        }

    }
    public void setResID(){
        String resID=nextResId ();
        txtResId.setText (resID);
    }
}


