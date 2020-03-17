package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.entity.Company;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Integer>{

}
