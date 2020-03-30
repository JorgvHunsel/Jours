package server.dto;

import server.entity.Company;
import server.entity.DAOUser;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private String username;
    private String password;

    private List<CompanyDTO> companies = new ArrayList<>();

    public UserDTO() {
    }

    public UserDTO(DAOUser user){
        this.username = user.getUsername();
        this.password = user.getPassword();

        for(Company comp: user.getCompanies()){
            companies.add(new CompanyDTO(comp));
        }
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

    public List<CompanyDTO> getCompanies() {
        return companies;
    }

    public void setCompanies(List<CompanyDTO> companies) {
        this.companies = companies;
    }


}

