package lk.ijse.hostel.dao.custom.impl;

import lk.ijse.hostel.dao.custom.ReservationDAO;
import lk.ijse.hostel.entity.Reservation;
import org.hibernate.Session;

import java.util.List;

public class RevervationDAOImpl implements ReservationDAO {

    private Session session;
    @Override
    public void setSession(Session session) {
        this.session=session;
    }

    @Override
    public List<Reservation> loadAll() {
        return null;
    }

    @Override
    public String save(Reservation reservation) {
        return null;
    }

    @Override
    public String update(Reservation reservation) {
        return null;
    }

    @Override
    public String delete(Reservation reservation) {
        return null;
    }
}
