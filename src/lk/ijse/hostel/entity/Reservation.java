package lk.ijse.hostel.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @Column(name = "resId",length = 25)
    private String resId;

    public Reservation(String resId, Date date, Student student, Room room, String status) {
        this.resId = resId;
        this.setDate (date);
        this.student = student;
        this.room = room;
        this.status = status;
    }

    public Reservation() {
    }
    @Column(name = "date")
    private Date date;


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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
