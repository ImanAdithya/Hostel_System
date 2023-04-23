package lk.ijse.hostel.dto;

import java.util.Date;

public class ReservationDTO {

    private String resID;

    public ReservationDTO(String resID, Date date, StudentDTO studentDTO, RoomDTO roomDTO, String status) {
        this.resID = resID;
        this.date = date;
        this.setStudentDTO (studentDTO);
        this.setRoomDTO (roomDTO);
        this.status = status;
    }

    private Date date;
    private StudentDTO studentDTO;
    private RoomDTO roomDTO;
    private String status;

    public String getResID() {
        return resID;
    }

    public void setResID(String resID) {
        this.resID = resID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public StudentDTO getStudentDTO() {
        return studentDTO;
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
    }

    public RoomDTO getRoomDTO() {
        return roomDTO;
    }

    public void setRoomDTO(RoomDTO roomDTO) {
        this.roomDTO = roomDTO;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "resID='" + resID + '\'' +
                ", date=" + date +
                ", studentDTO=" + studentDTO +
                ", roomDTO=" + roomDTO +
                ", status='" + status + '\'' +
                '}';
    }
}
