package server.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import server.dto.CompanyDTO;
import server.entity.Company;
import server.entity.DAOUser;
import server.service.CodeGenerator;
import server.service.CompanyService;
import server.service.CompanyUserService;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyLogic {

        private final CompanyService companyService;
        private final CompanyUserService companyUserService;

        @Autowired
        public CompanyLogic(CompanyService companyService, CompanyUserService companyUserService){
            this.companyService = companyService;
            this.companyUserService = companyUserService;
        }

        public Company createCompany(int userId, String companyName) {
            Company newCompany = getCompanyInstance(userId, companyName);

            int companyId = companyService.createOrUpdate(newCompany).getId();
            companyUserService.setRole("admin", companyId, userId);

            return newCompany;
        }

        private Company getCompanyInstance(int userId, String companyName){
            List<DAOUser> usersInCompany = new ArrayList<>();
            usersInCompany.add(new DAOUser(userId));

            String code = CodeGenerator.getRandomNumberString();

            return new Company(companyName, usersInCompany, code);
        }

    public void updateCompany(String companyName, int companyId) {
        companyService.updateCompany(companyName, companyId);
    }

    public CompanyDTO getCompany(int userId, int companyId) {
        CompanyDTO company = companyService.findUsersFromCompany(companyId);
        company.setCurrentUserRole(userId);
        return company;
    }

    public CompanyDTO findCompanyById(int companyId){
            return companyService.findCompanyById(companyId);
    }

    public String getNewCode(int companyId) {
        String newCode = CodeGenerator.getRandomNumberString();
        companyService.setCompanyCode(companyId, newCode);
        return newCode;
    }

    public CompanyDTO joinCompany(int userId, String code) {
        CompanyDTO currentCompany = companyService.findCompanyByCode(code);
        companyUserService.addUserToCompany(currentCompany.getId(), userId, "employee");
        return currentCompany;
    }
}
