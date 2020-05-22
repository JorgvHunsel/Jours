package server.service;

import org.springframework.stereotype.Service;
import server.repository.CompanyUserRepo;

@Service
public class CompanyUserService {
    private final CompanyUserRepo companyUserRepo;

    public CompanyUserService(CompanyUserRepo companyUserRepo) {
        this.companyUserRepo = companyUserRepo;
    }


    public boolean addUserToCompany(int companyId, int userId, String role){return this.companyUserRepo.addUserToCompany(companyId, userId, role);}
    public boolean setRole(String role, int companyId, int userId){return this.companyUserRepo.setRole(role, companyId, userId);}

}
