package server.entity;

import lombok.Data;
import server.dto.ProjectDTO;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name ="`project`")
@Data
public class Project {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "endDate")
    private Date endDate;

    @ManyToOne
    private Company company;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;


    public Project(String name, Date endDate, Company company) {
        this.name = name;
        this.endDate = endDate;
        this.company = company;
    }

    public Project(int id, String name, Date endDate, int companyId){
        this.id = id;
        this.name = name;
        this.endDate = endDate;
        this.company = new Company(companyId);
    }

    public Project(ProjectDTO project){
        this.id = project.getId();
        this.name = project.getName();
        this.endDate = project.getEndDate();
        this.company = new Company(project.getCompanyId());
    }

    public Project(int projectId){
        this.id = projectId;
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
