package server.dto;

import server.entity.Company;

import javax.persistence.Entity;


public class CompanyDTO {
    private int id;

    private String name;

    public CompanyDTO(Company company){
        this.id = company.getId();
        this.name = company.getName();

    }

    public CompanyDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CompanyDTO(){}

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
}
