package lk.ijse.hostel.bo.custom.impl;

import lk.ijse.hostel.bo.custom.RoomBO;
import lk.ijse.hostel.dao.DAOFactory;
import lk.ijse.hostel.dao.custom.RoomDAO;
import lk.ijse.hostel.dao.custom.StudentDAO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {

    private Session session;
    RoomDAO roomDAO=(RoomDAO) DAOFactory.getDaoFactory ().getDAO (DAOFactory.DAOTypes.ROOM);
    @Override
    public List<RoomDTO> loadAll() {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        roomDAO.setSession (session);
        List<Room>list= roomDAO.loadAll ();
        List<RoomDTO>roomList= new ArrayList<> ();

        for (Room room:list) {
            roomList.add(
                    new RoomDTO (
                            room.getRoomId (),
                            room.getType (),
                            room.getKeyMoney (),
                            room.getQty ()
                    )
            );
        }

        return roomList;

    }

    @Override
    public boolean saveRoom(RoomDTO dto) {
        session= SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try{
            roomDAO.setSession (session);
            roomDAO.save (new Room (
                    dto.getRoomID (),
                    dto.getType (),
                    dto.getKeyMoney (),
                    dto.getQty ()
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
    public boolean deleteRoom(RoomDTO dto) {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        try{
            roomDAO.setSession (session);
            roomDAO.delete (new Room (
                    dto.getRoomID (),
                    dto.getType (),
                    dto.getKeyMoney (),
                    dto.getQty ()
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
    public RoomDTO getRoom(String id) throws Exception {
        session=SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction=session.beginTransaction ();

        roomDAO.setSession (session);
        Room r=roomDAO.getObject (id);
        session.close ();
        return new RoomDTO (
                r.getRoomId (),
                r.getType (),
                r.getKeyMoney (),
                r.getQty ()
        );
    }

}
