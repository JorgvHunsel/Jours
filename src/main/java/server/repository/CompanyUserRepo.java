package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import server.entity.CompanyUser;

public interface CompanyUserRepo extends JpaRepository<CompanyUser, Integer> {
    @Query(value = "UPDATE company_user SET role = ?1 WHERE company_id = ?2 AND user_id = ?3 RETURNING true", nativeQuery = true)
    boolean setRole(String role, int companyId, int userId);
}
