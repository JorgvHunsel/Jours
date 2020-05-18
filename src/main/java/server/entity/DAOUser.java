package server.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="`user`")
@Data
public class DAOUser {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "username")
    private String username;

        @Column(name = "password")
    private String password;

    @ManyToMany(mappedBy = "users")
    List<Company> companies;

    @ManyToMany(mappedBy = "userTasks")
    List<Task> tasks;

    @OneToMany(mappedBy = "user")
    List<Work> workList;

    @OneToMany(mappedBy = "user")
    List<CompanyUser> roles;

    public DAOUser(int id, String username, String password, List<Company> companies, List<Task> tasks) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.companies = companies;
        this.tasks = tasks;
    }


    public DAOUser(int userId){
        this.id = userId;
    }
    public DAOUser(){}

    public DAOUser(int id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<CompanyUser> getRoles() {
        return roles;
    }

    public void setRoles(List<CompanyUser> roles) {
        this.roles = roles;
    }

    public List<Work> getWorkList() {
        return workList;
    }

    public void setWorkList(List<Work> workList) {
        this.workList = workList;
    }
}