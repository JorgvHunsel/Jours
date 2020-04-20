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
    private Project project;

    public Work(int id, Date beginDate, Date endDate, DAOUser user, Project project) {
        this.id = id;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.user = user;
        this.project = project;
    }

    public Work(Date beginDate, Date endDate, DAOUser user, Project project) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.user = user;
        this.project = project;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public DAOUser getUser() {
        return user;
    }

    public void setUser(DAOUser user) {
        this.user = user;
    }
}
