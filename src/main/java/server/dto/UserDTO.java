package server.dto;

import org.springframework.security.core.userdetails.User;
import server.entity.Company;
import server.entity.DAOUser;
import server.entity.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDTO {
    private int id;
    private String username;
    private String password;
    private String role;

    private List<CompanyDTO> companies;

    private List<TaskDTO> tasks;

    public UserDTO(Collection<Task> newTasks) {
        tasks = new ArrayList<>();
        for (Task task : newTasks) {
            tasks.add(new TaskDTO(task));
        }
    }


    public UserDTO(int id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public UserDTO(DAOUser user) {
        this.username = user.getUsername();
        this.password = user.getPassword();

        tasks = new ArrayList<>();
        for(Task task: user.getTasks()){
            tasks.add(new TaskDTO(task));
        }

        companies = new ArrayList<>();
        for (Company comp : user.getCompanies()) {
            String role = comp.getRoleFromUser(user.getId());
            companies.add(new CompanyDTO(comp, role));
        }
    }

    public UserDTO(int userId) {
        this.id = userId;
    }

    public UserDTO() {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<CompanyDTO> getCompanies() {
        return companies;
    }

    public void setCompanies(List<CompanyDTO> companies) {
        this.companies = companies;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }


}

