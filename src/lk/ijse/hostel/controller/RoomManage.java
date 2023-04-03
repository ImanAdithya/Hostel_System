package lk.ijse.hostel.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.hostel.bo.BOFactory;
import lk.ijse.hostel.bo.custom.RoomBO;
import lk.ijse.hostel.dto.RoomDTO;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RoomManage implements Initializable {
    public TableColumn colQty;
    public TableColumn colKeyMoney;
    public TableColumn colRoomType;
    public TableColumn colRoomId;
    public TableView tblRoom;
    public TextField txtQty;
    public TextField txtKeymoney;
    public TextField txtRoomId;
    public TextField txtRoomType;
    private RoomBO roomBO = (RoomBO) BOFactory.getBO (BOFactory.BOTypes.ROOM);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableRoom ();
        loadAllRoom ();
    }
    public void onActionSave(ActionEvent actionEvent) {
        int qty= Integer.parseInt (txtQty.getText ());
        RoomDTO roomDTO = new RoomDTO (
                txtRoomId.getText (),
                txtRoomType.getText (),
                txtKeymoney.getText (),
                qty
        );

        try {

            List<RoomDTO> allRooms = roomBO.loadAll ();
            for (RoomDTO r : allRooms) {
                if (r.getRoomID ().equals (txtRoomId.getText ())) {
                    new Alert (Alert.AlertType.ERROR, "This ID Already Have").show ();
                    break;
                } else {
                    boolean isSaved = roomBO.saveRoom (roomDTO);
                    new Alert (Alert.AlertType.CONFIRMATION, "ROOM ADDED SUCCUS").show ();
                    tblRoom.getItems ().clear ();
                    clearData ();
                    loadAllRoom ();
                }
            }
        }catch (Exception e){
            e.printStackTrace ();
        }

    }

    public void onActionDelete(ActionEvent actionEvent) {
        int qty= Integer.parseInt (txtQty.getText ());
        RoomDTO roomDTO = new RoomDTO (
                txtRoomId.getText (),
                txtRoomType.getText (),
                txtKeymoney.getText (),
                qty
                );

        boolean isDeleted=roomBO.deleteRoom (roomDTO);

        if (isDeleted){
            new Alert (Alert.AlertType.INFORMATION, "Room Delete Succuss").show ();
            tblRoom.getItems ().clear ();
            clearData ();
            loadAllRoom ();
        }else{
            new Alert (Alert.AlertType.ERROR, "Something went Wrong").show ();
        }

    }

    public void onActionUpdate(ActionEvent actionEvent) {
        int qty= Integer.parseInt (txtQty.getText ());
        RoomDTO roomDTO = new RoomDTO (
                txtRoomId.getText (),
                txtRoomType.getText (),
                txtKeymoney.getText (),
                qty
        );

        boolean isUpdate=roomBO.updateRoom (roomDTO);

        if (isUpdate){
            new Alert (Alert.AlertType.INFORMATION, "Student Update Succuss").show ();
            tblRoom.getItems ().clear ();
            clearData ();
            loadAllRoom ();
        }else {
            new Alert (Alert.AlertType.ERROR, "Something went Wrong").show ();
        }
    }
    public void clearData() {
        txtRoomId.clear ();
        txtRoomType.clear ();
        txtKeymoney.clear ();
        txtQty.clear ();
    }
    public void setTable() {
        colRoomId.setCellValueFactory (new PropertyValueFactory<> ("roomId"));
        colRoomType.setCellValueFactory (new PropertyValueFactory<> ("type"));
        colKeyMoney.setCellValueFactory (new PropertyValueFactory<> ("keyMoney"));
        colQty.setCellValueFactory (new PropertyValueFactory<> ("qty"));

    }
    public void loadAllRoom() {

        try {
            List allRoom = roomBO.loadAll ();
            ObservableList observableList = FXCollections.observableArrayList (allRoom);
            tblRoom.setItems (observableList);

        } catch (Exception e) {
            System.out.println (e);
        }
    }

    public void setTableRoom() {
        colRoomId.setCellValueFactory (new PropertyValueFactory<> ("roomID"));
        colRoomType.setCellValueFactory (new PropertyValueFactory<> ("type"));
        colKeyMoney.setCellValueFactory (new PropertyValueFactory<> ("keyMoney"));
        colQty.setCellValueFactory (new PropertyValueFactory<> ("qty"));
    }

    public void OnActionMiuseClickedRoom(MouseEvent mouseEvent) {
        int index = tblRoom.getSelectionModel ().getSelectedIndex ();
        String roomId = colRoomId.getCellData (index).toString ();//select Column value

        try {
            RoomDTO dto = roomBO.getRoom (roomId);
            txtRoomId.setText (dto.getRoomID ());
            txtRoomType.setText (dto.getType ());
            txtKeymoney.setText (dto.getKeyMoney ());
            txtQty.setText (String.valueOf (dto.getQty ()));
        } catch (Exception e) {
            System.out.println (e);
        }
    }
}
