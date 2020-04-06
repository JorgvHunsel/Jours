package server.dto;

import server.entity.Company;
import server.entity.DAOUser;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;


public class CompanyDTO {
    private int id;

    private String name;

    private List<UserDTO> usersInCompany = new ArrayList<>();

    public CompanyDTO(){}

    public CompanyDTO(Company company){
        this.id = company.getId();
        this.name = company.getName();

        if(company.getUsers() != null) {
            for (DAOUser user : company.getUsers()) {
                usersInCompany.add(new UserDTO(user.getId(), user.getUsername()));
            }
        }
    }


    public CompanyDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }



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

    public List<UserDTO> getUsersInCompany() {
        return usersInCompany;
    }

    public void setUsersInCompany(List<UserDTO> usersInCompany) {
        this.usersInCompany = usersInCompany;
    }
}
