package lk.ijse.hostel.dao.custom.impl;

import lk.ijse.hostel.dao.custom.RoomDAO;
import lk.ijse.hostel.entity.Room;
import org.hibernate.Session;

import java.util.List;

public class RoomDAOImpl implements RoomDAO {

    private Session session;
    @Override
    public void setSession(Session session) {
        this.session=session;
    }

    @Override
    public List<Room> loadAll() {
        return null;
    }

    @Override
    public String save(Room room) {
        return null;
    }

    @Override
    public String update(Room room) {
        return null;
    }

    @Override
    public String delete(Room room) {
        return null;
    }
}
