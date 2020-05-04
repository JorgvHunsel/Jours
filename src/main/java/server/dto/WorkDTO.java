package server.dto;

import server.entity.Work;

import javax.persistence.Column;
import java.util.Date;

public class WorkDTO {
    private int id;
    private Date beginDate;
    private Date endDate;
    private String username;

    public WorkDTO(Work work){
        this.id = work.getId();
        this.beginDate = work.getBeginDate();
        this.endDate = work.getEndDate();
        this.username = work.getUser().getUsername();
    }

    public WorkDTO(int id, Date beginDate, Date endDate) {
        this.id = id;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

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
}
