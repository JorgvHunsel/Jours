package server.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="`company`")
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany
            @JoinTable(
                    name = "company_user",
                    joinColumns = @JoinColumn(name = "company_id"),
                    inverseJoinColumns = @JoinColumn(name = "user_id")
            )
    List<DAOUser> users;



    public Company(String name, List<DAOUser> users){
        this.name = name;
        this.users = users;
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

    public List<DAOUser> getUsers() {
        return users;
    }

    public void setUsers(List<DAOUser> users) {
        this.users = users;
    }
}
