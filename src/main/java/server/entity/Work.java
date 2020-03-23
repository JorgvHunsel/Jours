package server.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name ="`work`")
@Data
public class Work {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "beginTime")
    private Time beginTime;

    @Column(name = "endTime")
    private Time endTime;

    @ManyToOne
    private DAOUser user;

    public Work(int id, Date date, Time beginTime, Time endTime, DAOUser user) {
        this.id = id;
        this.date = date;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.user = user;
    }

    public Work(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Time beginTime) {
        this.beginTime = beginTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public DAOUser getUser() {
        return user;
    }

    public void setUser(DAOUser user) {
        this.user = user;
    }
}
