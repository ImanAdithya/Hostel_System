package lk.ijse.hostel.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import lk.ijse.hostel.bo.BOFactory;
import lk.ijse.hostel.bo.custom.ReservationBO;
import lk.ijse.hostel.bo.custom.RoomBO;
import lk.ijse.hostel.dto.ReservationDTO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;

import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

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

        @FXML
        void onActionDeleteRes(ActionEvent event) {
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
                            roomDTO.setQty (roomDTO.getQty ()-1);
                            resBO.updateRoom (roomDTO);
                    }

            }catch (Exception e){
                    e.printStackTrace ();
            }
        }

        @FXML
        void onActionUpdateRes(ActionEvent event) {

        }

        @FXML
        void onMouseClickReservation(MouseEvent event) {

        }

        @Override
        public void initialize(URL location, ResourceBundle resources) {
                setIds ();
                setData();
                setStatus ();
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


}



