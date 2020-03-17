package server.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name ="`project`")
@Data
public class Project {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "endDate")
    private Date endDate;

    @ManyToOne
    private Company company;

    public Project(int id, String name, Date endDate, Company company) {
        this.id = id;
        this.name = name;
        this.endDate = endDate;
        this.company = company;
    }

    public Project(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
