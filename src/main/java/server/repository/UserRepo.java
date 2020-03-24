package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.entity.DAOUser;

@Repository
public interface UserRepo extends JpaRepository<DAOUser, Integer> {
    DAOUser findByUsername(String username);
    DAOUser findById(int id);

}
