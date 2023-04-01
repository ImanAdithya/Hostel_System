package lk.ijse.hostel.dto;

public class RoomDTO {
    private String roomID;
    private String type;

    public RoomDTO() {
    }

    private String keyMoney;

    public RoomDTO(String roomID, String type, String keyMoney, int qty) {
        this.roomID = roomID;
        this.type = type;
        this.keyMoney = keyMoney;
        this.qty = qty;
    }

    private int qty;


    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeyMoney() {
        return keyMoney;
    }

    public void setKeyMoney(String keyMoney) {
        this.keyMoney = keyMoney;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
