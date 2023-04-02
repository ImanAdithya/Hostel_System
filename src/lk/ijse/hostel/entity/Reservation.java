package lk.ijse.hostel.entity;

import javax.persistence.*;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @Column(name = "resId",length = 25)
    private String resId;

    public Reservation() {
    }
    @Column(name = "date")
    private String Date;

    public Reservation(String resId, String date, Student student, Room room, String status) {
        this.resId = resId;
        Date = date;
        this.student = student;
        this.room = room;
        this.status = status;
    }
    @ManyToOne
    @JoinColumn(name = "stID")
    private Student student;

    @ManyToOne
    @JoinColumn(name ="roomID")
    private Room room;

    @Column(name = "status")
    private String status;

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
