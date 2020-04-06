package server.entity;

import javax.persistence.*;

@Entity
public class CompanyUser {

    @EmbeddedId
    CompanyUserKey id;

    @ManyToOne
    @MapsId("company_id")
    @JoinColumn(name = "company_id")
    Company company;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    DAOUser user;

    String role;

    public CompanyUser(CompanyUserKey id, Company company, DAOUser user, String role) {
        this.id = id;
        this.company = company;
        this.user = user;
        this.role = role;
    }

    public CompanyUser() {
    }


    public CompanyUserKey getId() {
        return id;
    }

    public void setId(CompanyUserKey id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public DAOUser getUser() {
        return user;
    }

    public void setUser(DAOUser user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
