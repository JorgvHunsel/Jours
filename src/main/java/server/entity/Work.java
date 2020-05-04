package server.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name ="`work`")
@Data
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "beginDate")
    private Date beginDate;

    @Column(name = "endDate")
    private Date endDate;

    @ManyToOne
    private DAOUser user;

    @ManyToOne
    private Task task;

    public Work(int id, Date beginDate, Date endDate, DAOUser user, Task task) {
        this.id = id;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.user = user;
        this.task = task;
    }

    public Work(Date beginDate, Date endDate, DAOUser user, Task task) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.user = user;
        this.task = task;
    }

    public Work(Date beginDate, DAOUser user, Task task) {
        this.beginDate = beginDate;
        this.user = user;
        this.task = task;
    }
    public Work(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public DAOUser getUser() {
        return user;
    }

    public void setUser(DAOUser user) {
        this.user = user;
    }
}
