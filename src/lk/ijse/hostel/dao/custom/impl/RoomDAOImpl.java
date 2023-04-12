package lk.ijse.hostel.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.hostel.dao.custom.RoomDAO;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class RoomDAOImpl implements RoomDAO {

    private Session session;
    @Override
    public void setSession(Session session) {
        this.session=session;
    }

    @Override
    public List<Room> loadAll() {
        String sqlQuery="FROM Room ";
        Query query = session.createQuery(sqlQuery);
        List list =query.list ();
        session.close();
        return list;
    }

    @Override
    public String save(Room room) {
       return (String) session.save (room);
    }

    @Override
    public void update(Room room) {
        session.update (room);
    }
    @Override
    public void delete(Room room) {
        session.delete (room);
    }

    @Override
    public Room getObject(String id) throws Exception {
        return session.get(Room.class,id);
    }

    @Override
    public List<String> roomIds() {
        String hql = "SELECT id from Room ";
        Query<String> query=session.createQuery (hql);
        List<String> results = query.list();
        session.close();
        return results;
    }
}
