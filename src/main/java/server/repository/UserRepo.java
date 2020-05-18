package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import server.dto.UserDTO;
import server.entity.DAOUser;

@Repository
public interface UserRepo extends JpaRepository<DAOUser, Integer> {
    DAOUser findByUsername(String username);

    @Query("SELECT new server.dto.UserDTO(u) FROM DAOUser u WHERE u.id = ?1")
    UserDTO getUser(int id);

}
