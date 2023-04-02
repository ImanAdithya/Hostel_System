package lk.ijse.hostel.bo;

import lk.ijse.hostel.bo.custom.impl.RevervationBOImpl;
import lk.ijse.hostel.bo.custom.impl.RoomBOImpl;
import lk.ijse.hostel.bo.custom.impl.StudentBOImpl;
import lk.ijse.hostel.bo.custom.impl.UserBOImpl;

public class BOFactory {

    public static BOFactory boFactory;
    public BOFactory() {
    }

    public BOFactory  getBoFactory(){
        if (boFactory==null){
            boFactory=new BOFactory ();
        }
        return boFactory;
    }

    public enum BOTypes{
        STUDENT,ROOM,USER,RESERVATION
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case STUDENT:
                return new StudentBOImpl ();
            case ROOM:
                return new RoomBOImpl ();
            case RESERVATION:
                return new RevervationBOImpl ();
            case USER:
                return new UserBOImpl ();
            default:
                return null;
        }
    }

}
