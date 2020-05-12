package server.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="`task`")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @ManyToOne
    private Project project;

    @OneToMany(mappedBy = "task")
    private List<Work> work;


    @ManyToMany
    @JoinTable(
            name = "user_task",
            joinColumns = @JoinColumn(name = "taskId"),
            inverseJoinColumns = @JoinColumn(name = "userId"))
    List<DAOUser> userTasks;

    public Task(int id, String name, String description, String status, Project project, List<DAOUser> userTasks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.project = project;
        this.userTasks = userTasks;
    }

    public Task(String name, String description, String status, Project project, List<DAOUser> userTasks) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.project = project;
        this.userTasks = userTasks;
    }

    public Task(int id){
        this.id = id;
    }

    public Task(int id, String status){
        this.id = id;
        this.status = status;
    }

    public Task(){}


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<DAOUser> getUserTasks() {
        return userTasks;
    }

    public void setUserTasks(List<DAOUser> userTasks) {
        this.userTasks = userTasks;
    }

    public List<Work> getWork() {
        return work;
    }

    public void setWork(List<Work> work) {
        this.work = work;
    }
}
