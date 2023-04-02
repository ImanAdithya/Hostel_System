package lk.ijse.hostel.bo.custom.impl;

import lk.ijse.hostel.bo.custom.StudentBO;
import lk.ijse.hostel.dao.DAOFactory;
import lk.ijse.hostel.dao.custom.StudentDAO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentBOImpl implements StudentBO {

    private Session session;
    StudentDAO studentDAO=(StudentDAO) DAOFactory.getDaoFactory ().getDAO (DAOFactory.DAOTypes.STUDENT);
    @Override
    public List<StudentDTO> loadAll() {
        return null;
    }

    @Override
    public boolean saveStudent(StudentDTO dto) {
       session= SessionFactoryConfig.getInstance ().getSession ();
       Transaction transaction=session.beginTransaction ();

       try{
           studentDAO.setSession (session);
           String id=studentDAO.save (new Student (
                   dto.getStId (),
                   dto.getStName (),
                   dto.getAddress (),
                   dto.getContact (),
                   dto.getDob (),
                   dto.getGender ()));
           transaction.commit ();
           session.close ();
           if (id!=null){
               return true;
           }
       }catch (Exception e){
           transaction.rollback ();
       }
        return false;
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) {
        return false;
    }

    @Override
    public boolean deleteStudent(StudentDTO studentDTO) {
        return false;
    }
}
