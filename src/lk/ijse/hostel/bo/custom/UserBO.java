package lk.ijse.hostel.bo.custom;

import lk.ijse.hostel.bo.SuperBO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.dto.UserDTO;

import java.util.List;

public interface UserBO  extends SuperBO {
    boolean saveUser(UserDTO dto);
    UserDTO getUser(String id) throws Exception;
    boolean updateUser(UserDTO dto);
    List<UserDTO> loadAll();
}
