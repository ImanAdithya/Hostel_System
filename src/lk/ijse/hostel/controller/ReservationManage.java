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
import lk.ijse.hostel.bo.custom.RoomBO;
import lk.ijse.hostel.dto.ReservationDTO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.util.SendMail;

import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ReservationManage implements Initializable {
        public TextField txtStName;
        public ComboBox cmbStatus;
        @FXML
        private TextField txtResId;

        @FXML
        private ComboBox<?> cmbRoomId;

        @FXML
        private ComboBox<?> cmbStId;

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
        private TableView<?> tblReservation;

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

        @Override
        public void initialize(URL location, ResourceBundle resources) {
                setIds ();
                setData();
                setStatus ();
                setCellValueFactory ();
               // loadTable ();

        }

        @FXML
        void onActionDeleteRes(ActionEvent event) {
                String stId=cmbStId.getValue ().toString ();
                String roomID=cmbRoomId.getValue ().toString ();
                String status=cmbStatus.getValue ().toString ();
                String resId=txtResId.getText ();
                StudentDTO studentDTO=getStudnetDetail ();
                RoomDTO roomDTO=getRoomDetail ();
                java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

                try{
                        boolean isDelete=resBO.deleteReservation (
                                new ReservationDTO (
                                        resId,
                                        sqlDate,
                                        studentDTO,
                                        roomDTO,
                                        status
                                ));
                        if (isDelete){
                                RoomDTO room=getRoomDetail ();
                                room.setQty (room.getQty ()+1);
                                resBO.updateRoom (room);

                        }
                }catch (Exception e){
                        e.printStackTrace ();
                }
        }

        @FXML
        void onActionSaveRes(ActionEvent event) {
             StudentDTO studentDTO=getStudnetDetail ();
             RoomDTO roomDTO=getRoomDetail ();
             String resId=txtResId.getText ();
             String status=cmbStatus.getValue ().toString ();
             java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

            try {
                    boolean isSaveReservation = resBO.saveReservation (
                            new ReservationDTO (
                                    resId,
                                    sqlDate,
                                    studentDTO,
                                    roomDTO,
                                    status
                            ));
                    if (isSaveReservation){
                            RoomDTO room=getRoomDetail ();

                            System.out.println (room.getQty ()-1);
                            room.setQty (room.getQty ()-1);
                            resBO.updateRoom (room);
                    }


            }catch (Exception e){
                    e.printStackTrace ();
            }

               /* try {
                        List<ReservationDTO> allRes = resBO.loadAll ();
                        for (ReservationDTO s : allRes) {
                                if (s.getResID ().equals (txtResId.getText ())) {
                                        new Alert (Alert.AlertType.ERROR, "This ID Already Have").show ();
                                        break;
                                } else {
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
                                        }


                                        new Alert (Alert.AlertType.CONFIRMATION, "Reservation saved").show ();

                                }
                        }
                }catch (Exception e){
                        e.printStackTrace ();
                }*/

        }

        @FXML
        void onActionUpdateRes(ActionEvent event) {
                String stId=cmbStId.getValue ().toString ();
                String roomID=cmbRoomId.getValue ().toString ();
                String status=cmbStatus.getValue ().toString ();
                String resId=txtResId.getText ();
                StudentDTO studentDTO=getStudnetDetail ();
                RoomDTO roomDTO=getRoomDetail ();
                java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

                try{
                    boolean isUpdate=resBO.updateReservation (
                            new ReservationDTO (
                                    resId,
                                    sqlDate,
                                    studentDTO,
                                    roomDTO,
                                    status
                    ));
                }catch (Exception e){
                        e.printStackTrace ();
                }

        }

        private void setData() {
                cmbStId.setOnAction(event -> {
                        String stId=cmbStId.getValue ().toString ();
                        StudentDTO dto = resBO.getStudent (stId);
                        txtStName.setText (dto.getStName ());
                });


                cmbRoomId.setOnAction(event -> {
                        String roomId=cmbRoomId.getValue ().toString ();
                        RoomDTO dto = resBO.getRoom (roomId);
                        txtRoomType.setText (dto.getType ());
                        txtQty.setText (String.valueOf (dto.getQty ()));
                });
        }

        public void setIds(){
                List<String> stIds=resBO.getStudentIds ();
                ObservableList student = FXCollections.observableArrayList (stIds);
                cmbStId.setItems (student);

                List<String> roomIds=resBO.getRoomIds ();
                ObservableList room = FXCollections.observableArrayList (roomIds);
                cmbRoomId.setItems (room);

        }

        public void setStatus(){
                ObservableList<String> data = FXCollections.observableArrayList ("PAID", "UNPAID");
                cmbStatus.setItems (data);
        }

        public StudentDTO getStudnetDetail(){
            String stId=cmbStId.getValue ().toString ();
             return resBO.getStudent (stId);
        }
        public RoomDTO getRoomDetail(){
            String roomId=cmbRoomId.getValue ().toString ();
             return resBO.getRoom (roomId);
        }

        public void setCellValueFactory(){
                colResId.setCellValueFactory (new PropertyValueFactory<> (""));
                colStId.setCellValueFactory (new PropertyValueFactory<> (""));
                colStName.setCellValueFactory (new PropertyValueFactory<> (""));
                colRoomId.setCellValueFactory (new PropertyValueFactory<> (""));
                colRoomType.setCellValueFactory (new PropertyValueFactory<> (""));
                colStatus.setCellValueFactory (new PropertyValueFactory<> (""));

        }
        @FXML
        void onMouseClickReservation(MouseEvent event) {

        }

        public void loadTable(){
                List allRoom = resBO.loadAll ();

                for (Object o : allRoom) {
                        System.out.println (o);
                }
        }
}



