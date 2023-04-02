package lk.ijse.hostel.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Room")
public class Room {
    @Id
    @Column(name = "roomID",length = 25)
    private String roomId;
    @Column(name = "type")
    private String type;

    public Room(String roomId, String type, String keyMoney, int qty) {
        this.roomId = roomId;
        this.type = type;
        this.keyMoney = keyMoney;
        this.qty = qty;
    }

    public Room() {
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId='" + roomId + '\'' +
                ", type='" + type + '\'' +
                ", keyMoney='" + keyMoney + '\'' +
                ", qty=" + qty +
                '}';
    }

    @Column(name = "keyMoney")
    private String keyMoney;
    @Column(name = "qty")
    private int qty;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "room")
    List<Reservation> reservationList;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
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
