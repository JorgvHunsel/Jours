package server.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="`task`")
@Data
public class Task {
    @Id
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

    @ManyToMany(mappedBy = "tasks")
    List<User> userTasks;

    public Task(int id, String name, String description, String status, Project project, List<User> userTasks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.project = project;
        this.userTasks = userTasks;
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

    public List<User> getUserTasks() {
        return userTasks;
    }

    public void setUserTasks(List<User> userTasks) {
        this.userTasks = userTasks;
    }
}
