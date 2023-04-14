package lk.ijse.hostel.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.hostel.dao.custom.ReservationDAO;
import lk.ijse.hostel.entity.Reservation;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Observable;

public class RevervationDAOImpl implements ReservationDAO {

    private Session session;
    @Override
    public void setSession(Session session) {
        this.session=session;
    }

    @Override
    public List<Reservation> loadAll() {
        String sqlQuery="FROM Reservation";
        Query query = session.createQuery(sqlQuery);
        List list =query.list ();
        session.close();
        return list;
    }

    @Override
    public String save(Reservation reservation) {
        return (String) session.save(reservation);
    }

    @Override
    public void update(Reservation reservation) {
        session.update(reservation);
    }

    @Override
    public void delete(Reservation reservation) {
        session.delete(reservation);
    }

    @Override
    public Reservation getObject(String id) throws Exception {
        return null;
    }
}
