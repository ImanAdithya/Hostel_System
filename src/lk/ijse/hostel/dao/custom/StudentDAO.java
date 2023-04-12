package lk.ijse.hostel.dao.custom;

import lk.ijse.hostel.dao.CrudDAO;
import lk.ijse.hostel.entity.Reservation;
import lk.ijse.hostel.entity.Student;

import java.util.List;

public interface StudentDAO extends CrudDAO<Student> {

    List<String> getStIds();
}
