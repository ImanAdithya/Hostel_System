package lk.ijse.hostel.dao.custom.impl;

import lk.ijse.hostel.dao.custom.StudentDAO;
import lk.ijse.hostel.entity.Student;
import org.hibernate.Session;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    private Session session;
    @Override
    public void setSession(Session session) {
        this.session=session;
    }

    @Override
    public List<Student> loadAll() {
        return null;
    }

    @Override
    public String save(Student student) {
        return (String) session.save (student);
    }

    @Override
    public String update(Student student) {
        return null;
    }

    @Override
    public String delete(Student student) {
        return null;
    }
}
