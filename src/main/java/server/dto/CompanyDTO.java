package server.dto;

import server.entity.Company;
import server.entity.CompanyUser;
import server.entity.DAOUser;

import java.util.ArrayList;
import java.util.List;


public class CompanyDTO {
    private int id;

    private String name;
    private String code;

    private List<UserDTO> usersInCompany;

    private List<CompanyUserDTO> roles;

    private String currentUserRole;

    public CompanyDTO(){}

    public CompanyDTO(Company company){
        this.id = company.getId();
        this.name = company.getName();
        this.code = company.getCode();

        setRoles(company);
        setUsers(company);
    }

    private void setUsers(Company company) {
        usersInCompany = new ArrayList<>();
        for (DAOUser user : company.getUsers()) {
            usersInCompany.add(new UserDTO(user.getId(), user.getUsername(), company.getRoleFromUser(user.getId())));
        }
    }

    private void setRoles(Company company) {
        roles = new ArrayList<>();
        for(CompanyUser companyUser: company.getRoles()){
            if(companyUser.getCompany().getId() == company.getId()){
                roles.add(new CompanyUserDTO(companyUser.getUser().getId(), companyUser.getRole()));
            }
        }
    }

    public CompanyDTO(Company company, String userRole){
        this.id = company.getId();
        this.name = company.getName();
        this.currentUserRole = userRole;
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

    public String getCurrentUserRole() {
        return currentUserRole;
    }

    public void setCurrentUserRole(int userId) {
        for(CompanyUserDTO role: roles){
            if(role.getUserId() == userId){
                this.currentUserRole = role.getRole();
            }
        }
    }
}
