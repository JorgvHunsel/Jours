package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import server.dto.CompanyDTO;
import server.entity.Company;
import server.entity.DAOUser;

import java.util.List;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Integer>{
    @Query("SELECT new server.dto.CompanyDTO(c.id, c.name) FROM Company c WHERE c.id = ?1")
    CompanyDTO findCompanyById(int id);

    @Query("select new server.dto.CompanyDTO(c) from Company c where c.id = ?1")
    CompanyDTO findUsersFromCompany(int companyId);

    @Transactional
    @Modifying
    @Query("UPDATE Company c SET c.name = :name WHERE c.id = :id")
    int updateCompany(@Param("name") String name, @Param("id") int companyId);

    @Transactional
    @Modifying
    @Query("UPDATE Company c SET c.code = :code WHERE c.id = :id")
    int setCompanyCode(@Param("id") int companyId, @Param("code") int code);


}
