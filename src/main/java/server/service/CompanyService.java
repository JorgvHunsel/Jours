package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.dto.CompanyDTO;
import server.entity.Company;
import server.repository.CompanyRepo;

@Service
public class CompanyService {

    private final CompanyRepo companyRepo;

    @Autowired
    public CompanyService(CompanyRepo companyRepo){this.companyRepo = companyRepo;}

    public Company createOrUpdate(Company company){
        return this.companyRepo.save(company);
    }

    public CompanyDTO findUsersFromCompany(int companyId){return this.companyRepo.findUsersFromCompany(companyId);}
    public CompanyDTO findCompanyByCode(String code){return this.companyRepo.findCompanyByCode(code);}
    public CompanyDTO findCompanyById(int id){return this.companyRepo.findCompanyById(id);}
    public int setCompanyCode(int companyId, String code){return this.companyRepo.setCompanyCode(companyId, code);}
    public int updateCompany(String name, int companyId){return this.companyRepo.updateCompany(name, companyId);}
}
