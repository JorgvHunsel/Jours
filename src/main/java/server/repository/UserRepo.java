package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import server.dto.CompanyDTO;
import server.dto.UserDTO;
import server.entity.Company;
import server.entity.DAOUser;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<DAOUser, Integer> {
    DAOUser findByUsername(String username);

    @Query("SELECT new server.dto.UserDTO(u.id, u.username, u.password) FROM DAOUser u WHERE u.id = ?1")
    UserDTO findById(int id);

    @Query("SELECT new server.dto.UserDTO(u) FROM DAOUser u WHERE u.id = ?1")
    UserDTO findCompaniesByUser(int id);

}
