package server.dto;

import server.entity.Company;
import server.entity.DAOUser;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private int id;
    private String username;
    private String password;
    private String role;

    private List<CompanyDTO> companies;

    public UserDTO(int id, String username, String role){
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public UserDTO(DAOUser user){
        this.username = user.getUsername();
        this.password = user.getPassword();

        companies = new ArrayList<>();
        for(Company comp: user.getCompanies()){
            String role = comp.getRoleFromUser(user.getId());
            companies.add(new CompanyDTO(comp, role));
        }
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


}

