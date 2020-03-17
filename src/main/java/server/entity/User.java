package server.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="`user`")
@Data
public class User {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(mappedBy = "users")
    List<Company> companies;

    @ManyToMany
            @JoinTable(
                    name = "user_task",
                    joinColumns = @JoinColumn(name = "userId"),
                    inverseJoinColumns = @JoinColumn(name = "taskId"))
    List<Task> tasks;
}