package server.entity;

import lombok.Data;
import server.dto.CompanyDTO;

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

    public Company(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Company(CompanyDTO companyDTO){
        this.id = companyDTO.getId();
        this.name = companyDTO.getName();
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
