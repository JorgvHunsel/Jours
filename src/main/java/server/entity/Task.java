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
}
