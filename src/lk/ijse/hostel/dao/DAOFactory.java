package lk.ijse.hostel.dao;

import lk.ijse.hostel.bo.BOFactory;
import lk.ijse.hostel.bo.SuperBO;
import lk.ijse.hostel.bo.custom.impl.RevervationBOImpl;
import lk.ijse.hostel.bo.custom.impl.RoomBOImpl;
import lk.ijse.hostel.bo.custom.impl.StudentBOImpl;
import lk.ijse.hostel.bo.custom.impl.UserBOImpl;
import lk.ijse.hostel.dao.custom.impl.RevervationDAOImpl;
import lk.ijse.hostel.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hostel.dao.custom.impl.StudentDAOImpl;
import lk.ijse.hostel.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    public static DAOFactory daoFactory;

    public DAOFactory() {
    }

    public static DAOFactory getDaoFactory(){
        if (daoFactory==null){
            daoFactory=new DAOFactory ();
        }
        return daoFactory;
    }

    public enum DAOTypes{
        STUDENT,ROOM,USER,RESERVATION
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case STUDENT:
                return new StudentDAOImpl ();
            case ROOM:
                return new RoomDAOImpl ();
            case RESERVATION:
                return new RevervationDAOImpl ();
            case USER:
                return new UserDAOImpl ();
            default:
                return null;
        }
    }

}
