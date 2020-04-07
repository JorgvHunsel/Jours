package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import server.dto.CompanyDTO;
import server.entity.Company;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Integer>{
    @Query("SELECT new server.dto.CompanyDTO(c.id, c.name) FROM Company c WHERE c.id = ?1")
    CompanyDTO findCompanyById(int id);

    @Query("select new server.dto.CompanyDTO(c) from Company c where c.id = ?1")
    CompanyDTO findUsersFromCompany(int companyId);
}
