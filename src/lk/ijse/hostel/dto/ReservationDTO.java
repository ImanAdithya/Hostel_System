package lk.ijse.hostel.dto;

public class ReservationDTO {

    private String resID;

    private String date;

    public ReservationDTO(String resID, String date, String stId, String roomId, String status) {
        this.resID = resID;
        this.date = date;
        this.stId = stId;
        this.roomId = roomId;
        this.status = status;
    }

    private String stId;

    private String roomId;

    private String status;

    public String getResID() {
        return resID;
    }

    public void setResID(String resID) {
        this.resID = resID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStId() {
        return stId;
    }

    public void setStId(String stId) {
        this.stId = stId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
