package server.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="`company`")
@Data
public class Company {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany
            @JoinTable(
                    name = "company_user",
                    joinColumns = @JoinColumn(name = "companyId"),
                    inverseJoinColumns = @JoinColumn(name = "userId")
            )
    List<User> users;

    public Company(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Company(){}

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
