package server.logic;

import server.entity.Company;
import server.entity.DAOUser;
import server.service.CodeGenerator;

import java.util.ArrayList;
import java.util.List;

public class CompanyLogic {
    public Company createCompany(int userId, String companyName){
        List<DAOUser> usersInCompany = new ArrayList<>();
        usersInCompany.add(new DAOUser(userId));

        String code = CodeGenerator.getRandomNumberString();

        return new Company(companyName, usersInCompany, code);
    }
}
