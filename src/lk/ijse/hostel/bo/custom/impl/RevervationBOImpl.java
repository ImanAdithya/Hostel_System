package lk.ijse.hostel.bo.custom.impl;

import lk.ijse.hostel.bo.custom.ReservationBO;
import lk.ijse.hostel.dao.DAOFactory;
import lk.ijse.hostel.dao.custom.ReservationDAO;
import lk.ijse.hostel.dao.custom.RoomDAO;
import lk.ijse.hostel.dao.custom.StudentDAO;
import lk.ijse.hostel.dto.ReservationDTO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.entity.Reservation;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RevervationBOImpl implements ReservationBO {

    private Session session;
    StudentDAO studentDAO=(StudentDAO) DAOFactory.getDaoFactory ().getDAO (DAOFactory.DAOTypes.STUDENT);
    RoomDAO roomDAO=(RoomDAO) DAOFactory.getDaoFactory ().getDAO (DAOFactory.DAOTypes.ROOM);
    ReservationDAO reservationDAO=(ReservationDAO) DAOFactory.getDaoFactory ().getDAO (DAOFactory.DAOTypes.RESERVATION);


    @Override
    public List<String> getStudentIds() {
        try{
            session= SessionFactoryConfig.getInstance ().getSession ();
            studentDAO.setSession (session);
            return studentDAO.getStIds ();

        }catch (Exception e){
            session.close ();
            return null;
        }
    }

    @Override
    public List<String> getRoomIds() {
       try{
           session=SessionFactoryConfig.getInstance ().getSession ();
           roomDAO.setSession (session);
           return roomDAO.roomIds ();
       }catch (Exception e){
           session.close ();
           return null;
       }
    }

    @Override
    public StudentDTO getStudent(String id) {
       session=SessionFactoryConfig.getInstance ().getSession ();
       Transaction transaction=session.beginTransaction ();

       studentDAO.setSession (session);
        try {
            Student st = studentDAO.getObject (id);
            session.close ();
            return new StudentDTO (
                    st.getStId (),
                    st.getStName (),
                    st.getAddress (),
                    st.getContact (),
                    st.getDob (),
                    st.getGender ()
            );

        } catch (Exception e) {
            e.printStackTrace ();
            transaction.rollback ();
            return null;
        }
    }

    @Override
    public RoomDTO getRoom(String id) {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        roomDAO.setSession (session);
        try {
            Room r = roomDAO.getObject (id);
            session.close ();
            return new RoomDTO (
                    r.getRoomId (),
                    r.getType (),
                    r.getKeyMoney (),
                    r.getQty ()
            );

        } catch (Exception e) {
            e.printStackTrace ();
            transaction.rollback ();
            return null;
        }

    }

    @Override
    public boolean updateRoom(RoomDTO dto) {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try {
            roomDAO.setSession (session);
            roomDAO.update (new Room (
                    dto.getRoomID (),
                    dto.getType (),
                    dto.getKeyMoney (),
                    dto.getQty ()
            ));

            transaction.commit ();
            session.close ();
            return true;
        }catch (Exception e){
            transaction.rollback ();;
        }
        return false;
    }

    @Override
    public List<ReservationDTO> loadAllRes() {
        return null;
    }

    @Override
    public boolean saveReservation(ReservationDTO dto) {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try{
            reservationDAO.setSession (session);
            reservationDAO.save (
                    new Reservation (
                            dto.getResID (),
                            dto.getDate (),
                            new Student (
                                    dto.getStudentDTO ().getStId (),
                                    dto.getStudentDTO ().getStName (),
                                    dto.getStudentDTO ().getAddress (),
                                    dto.getStudentDTO ().getContact (),
                                    dto.getStudentDTO ().getDob (),
                                    dto.getStudentDTO ().getGender ()
                            ),
                            new Room (
                                    dto.getRoomDTO ().getRoomID (),
                                    dto.getRoomDTO ().getType (),
                                    dto.getRoomDTO ().getKeyMoney (),
                                    dto.getRoomDTO ().getQty ()
                            ),
                            dto.getStatus ()
                    ));
            transaction.commit();
            session.close();
            return true;

        }catch (Exception e){
            transaction.rollback ();
            e.printStackTrace ();
            return false;
        }
    }

    @Override
    public boolean updateReservation(ReservationDTO dto) {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try{
            reservationDAO.setSession (session);
            reservationDAO.update (
                    new Reservation (
                            dto.getResID (),
                            dto.getDate (),
                            new Student (
                                    dto.getStudentDTO ().getStId (),
                                    dto.getStudentDTO ().getStName (),
                                    dto.getStudentDTO ().getAddress (),
                                    dto.getStudentDTO ().getContact (),
                                    dto.getStudentDTO ().getDob (),
                                    dto.getStudentDTO ().getGender ()
                            ),
                            new Room (
                                    dto.getRoomDTO ().getRoomID (),
                                    dto.getRoomDTO ().getType (),
                                    dto.getRoomDTO ().getKeyMoney (),
                                    dto.getRoomDTO ().getQty ()
                            ),
                            dto.getStatus ()
                    ));
            transaction.commit();
            session.close();
            return true;

        }catch (Exception e){
            transaction.rollback ();
            e.printStackTrace ();
            return false;
        }
    }

    @Override
    public boolean deleteReservation(ReservationDTO dto) {
        return false;
    }
}
