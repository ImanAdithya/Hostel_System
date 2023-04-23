package lk.ijse.hostel.controller;

import javafx.scene.control.Label;
import javafx.scene.text.Text;
import lk.ijse.hostel.bo.BOFactory;
import lk.ijse.hostel.bo.custom.ReservationBO;
import lk.ijse.hostel.bo.custom.RoomBO;
import lk.ijse.hostel.bo.custom.StudentBO;

public class DashboardForm {
    public Label lblStudent;
    public Label lblRooms;
    public Text txtStudents;
    public Text txtRooms;
    public Text txtReservation;

    RoomBO roomBO = (RoomBO) BOFactory.getBO (BOFactory.BOTypes.ROOM);
    StudentBO studentBO = (StudentBO) BOFactory.getBO (BOFactory.BOTypes.STUDENT);
    ReservationBO reservationBO = (ReservationBO) BOFactory.getBO (BOFactory.BOTypes.RESERVATION);

    public void initialize(){
        txtStudents.setText (String.valueOf (studentBO.loadAll ().size ()));
        txtReservation.setText (String.valueOf (reservationBO.loadAll ().size ()));
        txtRooms.setText (String.valueOf (roomBO.loadAll ().size ()));
    }
}
