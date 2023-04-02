package lk.ijse.hostel.dao.custom.impl;

import lk.ijse.hostel.dao.custom.UserDAO;
import lk.ijse.hostel.entity.User;
import org.hibernate.Session;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    private Session session;
    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public List<User> loadAll() {
        return null;
    }

    @Override
    public String save(User user) {
        return null;
    }

    @Override
    public String update(User user) {
        return null;
    }

    @Override
    public String delete(User user) {
        return null;
    }
}
