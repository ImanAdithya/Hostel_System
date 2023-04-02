package lk.ijse.hostel.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.hostel.bo.custom.StudentBO;
import lk.ijse.hostel.dao.DAOFactory;
import lk.ijse.hostel.dao.custom.StudentDAO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StudentBOImpl implements StudentBO {

    private Session session;
    StudentDAO studentDAO=(StudentDAO) DAOFactory.getDaoFactory ().getDAO (DAOFactory.DAOTypes.STUDENT);
    @Override
    public List<StudentDTO> loadAll() {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();
        studentDAO.setSession (session);

        List<Student> stList=studentDAO.loadAll ();
        List<StudentDTO> list=new ArrayList<> ();
        for (Student student:stList) {
            list.add(
                    new StudentDTO(
                            student.getStId (),
                            student.getStName (),
                            student.getAddress(),
                            student.getContact (),
                            student.getDob(),
                            student.getGender()
                    )
            );
        }

        return list;
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
    public boolean updateStudent(StudentDTO dto) {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try {
            studentDAO.setSession (session);
            studentDAO.update (new Student (
                    dto.getStId (),
                    dto.getStName (),
                    dto.getAddress (),
                    dto.getContact (),
                    dto.getDob (),
                    dto.getGender ()));

            transaction.commit ();
            session.close ();
            return true;
        }catch (Exception e){
            transaction.rollback ();;
        }
        return false;
    }

    @Override
    public boolean deleteStudent(StudentDTO dto) {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try{
            studentDAO.setSession (session);
            studentDAO.delete (new Student (
                    dto.getStId (),
                    dto.getStName (),
                    dto.getAddress (),
                    dto.getContact (),
                    dto.getDob (),
                    dto.getGender ()
            ));
            transaction.commit ();
            session.close ();
            return true;
        }catch (Exception e){
            transaction.rollback ();
        }
        return false;
    }

    @Override
    public StudentDTO getStudent(String id) throws Exception {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        studentDAO.setSession (session);
        Student st=studentDAO.getObject (id);
        session.close ();
        return new StudentDTO (
                st.getStId (),
                st.getStName (),
                st.getAddress (),
                st.getContact (),
                st.getDob (),
                st.getGender ()
                );
    }
}
