package lk.ijse.hostel.dao.custom;

import lk.ijse.hostel.dao.CrudDAO;
import lk.ijse.hostel.entity.Reservation;
import lk.ijse.hostel.entity.Room;

import java.util.List;

public interface RoomDAO extends CrudDAO<Room> {
    List<String> roomIds();
}
